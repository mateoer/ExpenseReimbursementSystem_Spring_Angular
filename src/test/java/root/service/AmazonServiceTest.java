package root.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.amazonaws.services.s3.AmazonS3;

import root.service.amazon.AWSConfig;
import root.service.amazon.StorageService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AmazonServiceTest {
	
	
	@Mock
	private AmazonS3 amazonS3;	
	
	@Mock
	private StorageService s3service;
	
	
	
	
	private MockMultipartFile myPNGfile = new MockMultipartFile(
			"file", "25231.png", "src/test/resources/multipart_file", "some png".getBytes());
	
	private MockMultipartFile myTXTfile = new MockMultipartFile(
			"file", "hello.txt", "src/test/resources/multipart_file", "some txt".getBytes());
	
	
	@BeforeEach
	void setUp() throws Exception {
		s3service = new StorageService(amazonS3);		
	}	

	@Test
	void uploadAWSFile() throws IOException {		
		
		System.out.println("\nUploadAWSFile Test:");
		String result = s3service.uploadAWSFile(myPNGfile, "");	
		System.out.println(""+myPNGfile.getOriginalFilename()+"\n");
		
		assertAll(				
				()->	assertNotNull(result),				
				()->	assertThat(result.contains(myPNGfile.getOriginalFilename()))				
				 );			
	}
	
	@Disabled
	@Test
	void presignedUrl() throws IOException {
		
		System.out.println("\nPresignedUrl Test:");
		String result = s3service.presignedUrl(myPNGfile.getOriginalFilename(),"");
		System.out.println(""+result+"\n");
		
		assertAll(				
				()->	assertNotNull(result),				
				()->	assertThat(result.contains(myPNGfile.getOriginalFilename())),				
				()->	assertThat(result.contains(AWSConfig.getBucketName())),				
				()->	assertThat(result.contains(amazonS3.getRegionName()))				
				 );
		
		System.out.println("Filename: "+ myPNGfile.getOriginalFilename());
		System.out.println("Bucket: "+ AWSConfig.getBucketName());
		System.out.println("Region Name: "+ amazonS3.getRegionName()+"\n");
	}
	
	@Test
	void deleteAWSFile() {
		System.out.println("\nDeleteAWSFile Test:");
		ResponseEntity<String> result = s3service.deleteAWSFile(myTXTfile.getOriginalFilename(),"");		
		System.out.println("Response after deleting file: "+result+"\n");
		assertAll(				
				()->	assertNotNull(result)				
				 );
	}
	
	@Test
	void convertMultipartFileToFile() throws IOException{
		
		System.out.println("\nConvertMultipartFileToFile Test:");
		File myConvertedFile = s3service.convertMultipartFileToFile(myTXTfile);
		System.out.println("Converted multipart file to file: "+ myConvertedFile.toString());
		
		assertAll(				
				()->	assertNotNull(myConvertedFile)
				 );
		
	}

}
