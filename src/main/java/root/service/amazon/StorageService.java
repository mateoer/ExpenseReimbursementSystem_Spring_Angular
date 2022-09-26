package root.service.amazon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class StorageService {

	private AmazonS3 amazonS3;	
	

	@Autowired
	public StorageService(AmazonS3 amazonS3) {
//		super();
		this.amazonS3 = amazonS3;
	}

	public String uploadAWSFile(MultipartFile file, String username) throws IOException {
		System.out.println("Profile picture service upload!");
		File fileObj = convertMultipartFileToFile(file);
		String tempFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		String profilePicLocation = AWSConfig.getBucketName() + "/" + username + "/profile_picture";
		amazonS3.putObject(new PutObjectRequest(profilePicLocation, tempFileName, fileObj));

		fileObj.delete();
		System.out.println("File Uploaded\n" + tempFileName);
		return tempFileName;
	}

	public String uploadReceiptFile(MultipartFile file, String username) throws IOException {
		System.out.println("Receipt service upload!");
		File fileObj = convertMultipartFileToFile(file);
		String tempFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		String receiptPicLocation = AWSConfig.getBucketName() + "/" + username + "/receipt";
		amazonS3.putObject(new PutObjectRequest(receiptPicLocation, tempFileName, fileObj));

		fileObj.delete();
		System.out.println("File Uploaded\n" + tempFileName);
		return tempFileName;
	}

	public String presignedReceiptUrl(String objectKey, String username) throws IOException {

		// Set the presigned URL to expire after one hour.
		java.util.Date expiration = new java.util.Date();
		long expTimeMillis = Instant.now().toEpochMilli();
		expTimeMillis += 1000 * 60 * 60;
		expiration.setTime(expTimeMillis);
		String receiptPicLocation = AWSConfig.getBucketName() + "/" + username + "/receipt";

		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(receiptPicLocation,
				objectKey).withMethod(HttpMethod.GET).withExpiration(expiration);

		URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}

	public String presignedUrl(String objectKey, String username) throws IOException {

		// Set the pre-signed URL to expire after one hour.
		java.util.Date expiration = new java.util.Date();
		long expTimeMillis = Instant.now().toEpochMilli();
		expTimeMillis += 1000 * 60 * 60;
		expiration.setTime(expTimeMillis);
		String profilePicLocation = AWSConfig.getBucketName() + "/" + username + "/profile_picture";

		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(profilePicLocation,
				objectKey).withMethod(HttpMethod.GET).withExpiration(expiration);

		URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}

	public ResponseEntity<String> deleteAWSFile(String fileName, String username) {
		System.out.println("Deleting file: " + fileName);
		String profilePicLocation = AWSConfig.getBucketName() + "/" + username + "/profile_picture";
		amazonS3.deleteObject(profilePicLocation, fileName);
		return ResponseEntity.ok().build();
	}
	public ResponseEntity<String> deleteReceiptFile(String fileName, String username) {
		System.out.println("Deleting file: " + fileName);
		String receiptPicLocation = AWSConfig.getBucketName() + "/" + username + "/receipt";
		amazonS3.deleteObject(receiptPicLocation, fileName);
		return ResponseEntity.ok().build();
	}

	public File convertMultipartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			System.out.println("Error converting multipart file to file" + e);
		}
		return convertedFile;
	}

}
