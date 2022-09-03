package root.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import root.dao.UserRepository;
import root.model.User;
import root.model.UserResponse;
import root.model.UserRole;
import root.service.UserService;
import root.service.amazon.StorageService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class S3ControllerTest {
	
	@Mock
	private UserRepository userRepo;	
	@Mock
	private StorageService storageServ;	
	@Mock
	private UserService userService;
	
	
	@Mock
	private S3Controller s3Controller;
	
	
	
	private MockMultipartFile myPNGfile = new MockMultipartFile(
			"file", "25231.png", "src/test/resources/multipart_file", "some png".getBytes());
	
	private MockMultipartFile myTXTfile = new MockMultipartFile(
			"file", "hello.txt", "src/test/resources/multipart_file", "some txt".getBytes());

	private User myUser = new User(3, "eric", "abc123", "Eric", "Smith", "mateoer@kean.edu", UserRole.MANAGER, null, null, null);
	
	private User emp2 = new User(2, "anri", "abc123", "Anri", "Ban", "sdfsagm.com", UserRole.EMPLOYEE, null, null, null);		
	
	
	@BeforeEach
	void setUp() throws Exception {
		s3Controller = new S3Controller(userService, storageServ, userRepo); 
		userRepo.save(myUser);
		emp2.setProfilePicName("randomProfPicName");
		userRepo.save(emp2);
		
	}

	
	@Test
	void updateProfilePicture() throws IOException {
		
		when(userService.getUserByID(myUser.getUserId())).thenReturn(myUser);
		String invalidFormat = "Invalid file type. Only jpg, jpeg, or png files allowed";
		
		assertThat(s3Controller.updateProfilePicture(myPNGfile, myUser.getUserId()), is(myUser.getProfilePicName()));
		assertThat(s3Controller.updateProfilePicture(myTXTfile, myUser.getUserId()), is(invalidFormat));
	}
	
	@Test
	void getPictureUrl() throws IOException {		
		
		UserResponse newUserResponse = new UserResponse();
		newUserResponse.setUser(myUser);
		newUserResponse.setFound(true);		
		
		UserResponse emp2Response = new UserResponse();
		emp2Response.setUser(emp2);
		emp2Response.setFound(true);
		
		String random = "dsakhdakjhdjka"+emp2.getProfilePicName()+"kdhakjgdha";		
		when(storageServ.presignedUrl(emp2.getProfilePicName())).thenReturn(random);
			
					
		String emp2URL = s3Controller.getPictureURL(emp2Response);	
		
		System.out.println("\n\n"+emp2URL+"\n\n");
		
		assertThat(s3Controller.getPictureURL(newUserResponse), is("null"));
		assertNotNull(emp2URL);
		assertTrue(emp2URL.contains(emp2.getProfilePicName()));
		
	}
		

}
