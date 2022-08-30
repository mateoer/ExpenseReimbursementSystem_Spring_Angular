package root.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import root.dao.UserRepository;
import root.model.User;
import root.model.UserResponse;
import root.service.UserService;
import root.service.amazon.StorageService;

@RestController
@CrossOrigin()
public class S3Controller {

	@Autowired
	private UserRepository userRepo;

	private StorageService storageServ;
	private UserService myUserService;

	@Autowired
	public S3Controller(UserService myUserService, StorageService storageServ) {
		this.myUserService = myUserService;
		this.storageServ = storageServ;
	}

	@PostMapping("/uploadPfp")
	public String updateProfilePicture(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "userId") int userId) throws IOException {

		User myUser = myUserService.getUserByID(userId);
		
		//Checks file extension type
		String fileType = file.getOriginalFilename();
		String[] splitStringName = fileType.split("\\.");
		String extenstion = splitStringName[splitStringName.length-1].toLowerCase();
		if (!(extenstion.equals("jpg") || extenstion.equals("png") || extenstion.equals("jpeg"))) {
			return "Invalid file type. Only jpg, jpeg, or png files allowed";
		}
		
		//Uploads file
		String newFileName = storageServ.uploadAWSFile(file);
		
		//Deletes previous picture
		if (myUser.getProfilePicName() != null) {
			storageServ.deleteAWSFile(myUser.getProfilePicName());
		}
		
		//Updates new picture in user table
		myUser.setProfilePicName(newFileName);
		userRepo.save(myUser);

		return myUser.getProfilePicName();
	}

	@PostMapping("/getPfp")
	public String getPictureURL(@RequestBody UserResponse reqUser) throws IOException {
		
		User myUser = reqUser.getUser();
		if (myUser.getProfilePicName() == null || myUser.getProfilePicName() == "") {
			return "null";
		}
		String URL = storageServ.presignedUrl(myUser.getProfilePicName());
		System.out.println("\n\n" + URL + "\n\n");
		return URL;
	}

}