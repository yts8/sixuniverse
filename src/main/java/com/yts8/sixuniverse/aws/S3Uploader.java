package com.yts8.sixuniverse.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.UUID;

@Component
public class S3Uploader {
  private AmazonS3 s3Client;

  @Value("${cloud.aws.credentials.accessKey}")
  private String ACCESS_KEY;

  @Value("${cloud.aws.credentials.secretKey}")
  private String SECRET_KEY;

  @Value("${cloud.aws.s3.bucket}")
  private String BUCKET;

  @Value("${cloud.aws.region.static}")
  private String REGION;

  @PostConstruct
  public void setS3Client() {
    AWSCredentials credentials = new BasicAWSCredentials(this.ACCESS_KEY, this.SECRET_KEY);

    s3Client = AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(this.REGION)
        .build();
  }

  public String upload(MultipartFile file, String dirname) throws IOException {
    String fileName = dirname + "/" + UUID.randomUUID().toString() + file.getOriginalFilename();

    s3Client.putObject(new PutObjectRequest(this.BUCKET, fileName, file.getInputStream(), null)
        .withCannedAcl(CannedAccessControlList.PublicRead));
    return s3Client.getUrl(this.BUCKET, fileName).toString();
  }
}
