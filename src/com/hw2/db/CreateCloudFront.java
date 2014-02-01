package com.hw2.db;

import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.cloudfront.AmazonCloudFrontClient;
import com.amazonaws.services.cloudfront.model.CreateCloudFrontOriginAccessIdentityRequest;
import com.amazonaws.services.cloudfront.model.CreateDistributionRequest;
import com.amazonaws.services.cloudfront.model.CreateStreamingDistributionRequest;
import com.amazonaws.services.cloudfront.model.DistributionConfig;
import com.amazonaws.services.cloudfront.model.Origin;
import com.amazonaws.services.cloudfront.model.Origins;
import com.amazonaws.services.cloudfront.model.S3OriginConfig;
import com.amazonaws.services.cloudfront_2012_03_15.model.StreamingDistributionConfig;

/**
 * Create a CreateCloudFront for the S3 bucket.
 */

public class CreateCloudFront{
//	String bucketName = "js4153.bucket";
//	 AWSCredentials credentials = new PropertiesCredentials(
//			 CreateBucket.class.getResourceAsStream("AwsCredentials.properties"));
//	 
//	 AmazonCloudFrontClient cloudfront = new AmazonCloudFrontClient(credentials);
//	 
//     CreateCloudFrontOriginAccessIdentityRequest originRequest = new CreateCloudFrontOriginAccessIdentityRequest();
//     originRequest.setRequestCredentials(credentials);
//
//     Origin origin = new Origin()
//     .withDomainName(bucketName+"s3.amazonaws.com")
//     .withId(bucketName)
//     .withS3OriginConfig(new S3OriginConfig().withOriginAccessIdentity(""));
//
//     Origins origins = new Origins().withItems(origin);
//
//     
//     StreamingDistributionConfig streamingDistributionConfig = new StreamingDistributionConfig()
//     .withCallerReference("unique-id-for-idempotency")
//     .withComment("Streaming CloudFront distribution")
//     .withDefaultRootObject("index.html")
//     .withEnabled(true)
//     .withOrigins(origins);  
//     
//     CreateStreamingDistributionRequest streamingDistribution = new CreateStreamingDistributionRequest()
//           .withStreamingDistributionConfig(streamingDistributionConfig);          
//           cloudfront.createStreamingDistribution(streamingDistribution);
//     
//
//     DistributionConfig downloadingDistributionConfig = new DistributionConfig();
//     CreateDistributionRequest downloadingDistribution = new CreateDistributionRequest()
//             .withDistributionConfig(downloadingDistributionConfig); 
//             cloudfront.createDistribution(downloadingDistribution);


	   
	  
}
