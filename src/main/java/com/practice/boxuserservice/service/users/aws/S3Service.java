package com.practice.boxuserservice.service.users.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.practice.boxuserservice.global.env.EnvUtil;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import com.practice.boxuserservice.service.users.aws.dto.S3Dto;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudfront.CloudFrontClient;
import software.amazon.awssdk.services.cloudfront.model.CreateInvalidationRequest;
import software.amazon.awssdk.services.cloudfront.model.InvalidationBatch;
import software.amazon.awssdk.services.cloudfront.model.Paths;

/**
 * S3Service.
 *
 * @author : middlefitting
 * @since : 2023/08/27
 */


@Service
@AllArgsConstructor
public class S3Service {

  private static final String userProfileImageFolder = "user_profile_image/";

  private final AmazonS3 s3client;
  private final EnvUtil envUtil;

  public S3Dto uploadDefaultImage(int width, int height) {
    try {
      String fileName = UUID.randomUUID() + ".png";
      String bucketName = envUtil.getStringEnv("bucket.name");
      String s3FilePath = userProfileImageFolder + fileName;
      InputStream is = getClass().getResourceAsStream("/default_user_profile_image.png");
      assert is != null;
      byte[] contentBytes = IOUtils.toByteArray(is);

      BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(contentBytes));
      BufferedImage resizedImage = Thumbnails.of(originalImage).size(width, height)
          .asBufferedImage();
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      ImageIO.write(resizedImage, "png", os);
      byte[] imageBytes = os.toByteArray();
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
      long contentLength = imageBytes.length;

      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType("image/png");
      metadata.setContentLength(contentLength);
      s3client.putObject(
          new PutObjectRequest(bucketName, s3FilePath, byteArrayInputStream, metadata));
      return new S3Dto(s3FilePath, s3client.getUrl(bucketName, s3FilePath).toString());
    } catch (Exception e) {
      throw new DefaultServiceException("global.error.unexpected", envUtil);
    }
  }

  public void updateUserProfileImage(String filePath, BufferedImage image, long fileSize) {
    try {
      String bucketName = envUtil.getStringEnv("bucket.name");
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      ImageIO.write(image, "png", os);
      InputStream is = new ByteArrayInputStream(os.toByteArray());
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType("image/png");
      metadata.setContentLength(fileSize);
      s3client.putObject(
          new PutObjectRequest(bucketName, filePath, is, metadata));
    } catch (Exception e) {
      throw new DefaultServiceException("users.error.profile-update-failed", envUtil);
    }
    invalidateCache(envUtil.getStringEnv("cloudfront.distribution.id"), filePath);
  }

  public void invalidateCache(String distributionId, String filePath) {
    try (CloudFrontClient cloudFrontClient = CloudFrontClient.builder()
        .region(Region.AWS_GLOBAL)
        .build()) {

      InvalidationBatch batch = InvalidationBatch.builder()
          .paths(Paths.builder()
              .quantity(1)
              .items(filePath)
              .build())
          .callerReference(String.valueOf(System.currentTimeMillis()))
          .build();

      CreateInvalidationRequest createInvalidationRequest = CreateInvalidationRequest.builder()
          .distributionId(distributionId)
          .invalidationBatch(batch)
          .build();
      deleteUserProfileFileFromS3(filePath);
    }
  }

  public void deleteUserProfileFileFromS3(String filePath) {
    try {
      String bucketName = envUtil.getStringEnv("bucket.name");
      s3client.deleteObject(new DeleteObjectRequest(bucketName, "/" + filePath));
    } catch (Exception e) {
      throw new DefaultServiceException("global.error.unexpected", envUtil);
    }
  }
}
