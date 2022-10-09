package root.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import root.dao.ReimbursementRepository;
import root.dao.UserRepository;
import root.model.Reimbursement;
import root.model.User;
import root.model.UserResponse;
import root.service.UserService;
import root.service.amazon.StorageService;

@RestController
//@CrossOrigin("*")
@CrossOrigin("http://localhost:4200")
public class S3Controller {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReimbursementRepository reiRepo;

	@Autowired
	private StorageService storageServ;
	
	@Autowired
	private UserService myUserService;

	@Autowired
	public S3Controller(UserService myUserService, StorageService storageServ, UserRepository userRepo, ReimbursementRepository reiRepo) {
		this.myUserService = myUserService;
		this.storageServ = storageServ;
		this.userRepo = userRepo;
		this.reiRepo = reiRepo;
	}

	@PostMapping("/uploadPfp")
	public String updateProfilePicture(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "userId") int userId) throws IOException {

		System.out.println("/nUploading profile picture. Please, wait...");
		
		User myUser = myUserService.getUserByID(userId);
		
		//Checks file extension type
		String fileType = file.getOriginalFilename();
		String[] splitStringName = fileType.split("\\.");
		String extenstion = splitStringName[splitStringName.length-1].toLowerCase();
		if (!(extenstion.equals("jpg") || extenstion.equals("png") || extenstion.equals("jpeg"))) {
			return "Invalid file type. Only jpg, jpeg, or png files allowed";
		}
		
		//Uploads file
		String newFileName = storageServ.uploadAWSFile(file,myUser.getUsername());
		
		//Deletes previous picture
		if (myUser.getProfilePicName() != null) {
			storageServ.deleteAWSFile(myUser.getProfilePicName(), myUser.getUsername());
		}
		
		//Updates new picture in user table
		myUser.setProfilePicName(newFileName);
		userRepo.save(myUser);

		return myUser.getProfilePicName();
	}
	
	@PostMapping("/uploadReceipt")
	public String uploadReiReceipt(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "userId") int userId, @RequestParam(value = "reiId") int reiId) throws IOException {

		System.out.println("/nUploading receipt picture. Please, wait...");
		
		User myUser = myUserService.getUserByID(userId);
		Reimbursement myReimb = reiRepo.findByReiId(reiId);
		
		//Checks file extension type
		String fileType = file.getOriginalFilename();
		String[] splitStringName = fileType.split("\\.");
		String extenstion = splitStringName[splitStringName.length-1].toLowerCase();
		if (!(extenstion.equals("jpg") || extenstion.equals("png") || extenstion.equals("jpeg"))) {
			return "Invalid file type. Only jpg, jpeg, or png files allowed";
		}
		if (myUser.getUserId() != myReimb.getReiAuthor()) {
			return "File not found";
		}
		
		//Uploads file
		String newFileName = storageServ.uploadReceiptFile(file,myUser.getUsername());
		
		//Deletes previous file. That way upload works as update as well
		if (myReimb.getReceiptPicName() != null) {
			storageServ.deleteReceiptFile(myReimb.getReceiptPicName(), myUser.getUsername());
		}
		
		//Updates new picture in reibursement table
		myReimb.setReceiptPicName(newFileName);
		reiRepo.save(myReimb);

		return myReimb.getReceiptPicName();
	}
	
	@PostMapping("/deleteReceipt")
	public String deleteReiReceipt(@RequestParam(value = "username") String username, @RequestParam(value = "receiptPicName") String receiptPicName) throws IOException{
		if(username == null || username == "" ||  receiptPicName == null || receiptPicName == "") {
			return "File not found";
		}		
		User myUser = userRepo.findByUsername(username);
		Reimbursement myReimb = reiRepo.findByReiAuthorAndReceiptPicName(myUser.getUserId(), receiptPicName);
		storageServ.deleteReceiptFile(receiptPicName, username);
		myReimb.setReceiptPicName(null);
		reiRepo.save(myReimb);
		
		
		return "File deleted";
	}
	
	@PostMapping("/getReceipt")
	public String getReceiptURL(@RequestParam String username,@RequestParam String receiptPicName) throws IOException {
		
		System.out.println("\nRetrieving receipt picture url");
		
		if (username == null || username == "" || receiptPicName == null || receiptPicName == "") {
			return "null";
		}
		String URL = storageServ.presignedReceiptUrl(receiptPicName, username);
		System.out.println("\n\n" + URL + "\n\n");
		return URL;
	}

	@PostMapping("/getPfp")
	public String getPictureURL(@RequestBody UserResponse reqUser) throws IOException {
		
		System.out.println("\nRetrieving profile picture url");
		User myUser = reqUser.getUser();
		if (myUser.getProfilePicName() == null || myUser.getProfilePicName() == "") {
			return "null";
		}
		String URL = storageServ.presignedUrl(myUser.getProfilePicName(), myUser.getUsername());
		System.out.println("\n\n" + URL + "\n\n");
		return URL;
	}

}