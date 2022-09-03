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

	
	public String uploadAWSFile(MultipartFile file) throws IOException {
		System.out.println("In the service upload!");
		File fileObj = convertMultipartFileToFile(file);
		String tempFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		amazonS3.putObject(new PutObjectRequest(AWSConfig.getBucketName(), tempFileName, fileObj));

		fileObj.delete();
		System.out.println("File Uploaded\n" + tempFileName);
		return tempFileName;
	}
	
		
	public String presignedUrl(String objectKey) throws IOException {
		
		// Set the presigned URL to expire after one hour.
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = Instant.now().toEpochMilli();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);
		
		
		GeneratePresignedUrlRequest generatePresignedUrlRequest = 
			new GeneratePresignedUrlRequest(AWSConfig.getBucketName(), objectKey)
				.withMethod(HttpMethod.GET)
				.withExpiration(expiration);
		
		URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}	
	
	public ResponseEntity<String> deleteAWSFile(String fileName) {
		System.out.println("Deleting file: " + fileName);
		amazonS3.deleteObject(AWSConfig.getBucketName(), fileName);
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
