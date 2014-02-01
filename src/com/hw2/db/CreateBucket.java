package com.hw2.db;


	import java.io.IOException;

	import com.amazonaws.auth.AWSCredentials;
	import com.amazonaws.auth.PropertiesCredentials;
	import com.amazonaws.services.s3.AmazonS3Client;


	public class CreateBucket {

		/**
		 * @param args
		 * @throws IOException 
		 * Create a bucket for the videos if it does exist already
		 * Constructs a new Amazon S3 client using the specified AWS credentials to access Amazon S3.
		 */
		public static void main(String[] args) throws IOException {
			
		   	 AWSCredentials credentials = new PropertiesCredentials(
					 CreateBucket.class.getResourceAsStream("AwsCredentials.properties"));
		   	 
		   	AmazonS3Client s3 = new AmazonS3Client(credentials);
			
	        String bucketName = "js4153";
			s3.createBucket(bucketName);

		}

	}


