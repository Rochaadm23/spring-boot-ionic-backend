package com.rsecinformation.cursomc.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.amazonaws.services.s3control.model.JobStatus.New;

@Configuration
public class S3Config {

    @Value("${aws.access_key_id}")
    private String awsId;
    @Value("${aws.secret_access_key}")
    private String awsKey;
    @Value("${s3.region}")
    private String region;
    @Bean
    public AmazonS3 s3client(){
        BasicAWSCredentials awsCred =  new BasicAWSCredentials(awsId, awsKey);
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(String.valueOf(Region.fromValue(region)))
                .withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();
        return s3client;

   }
}
