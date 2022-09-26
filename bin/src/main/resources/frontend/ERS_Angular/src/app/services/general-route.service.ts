import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GeneralRouteService {

  constructor() { }

  API_URL = `http://localhost:9050`;

  //login
  USER_CREDENTIALS = `${this.API_URL}/getcredentials`;
  GREETINGS = `${this.API_URL}/greetings`;

  //employee
  GET_REI = `${this.API_URL}/getreimbursements` 
  GET_USER = `${this.API_URL}/getusername`;
  NEW_REI = `${this.API_URL}/addreimbursement`;
  DEL_REI = `${this.API_URL}/deleteReimbursement`;
  EDIT_REI = `${this.API_URL}/updateReimbursement`;
  UPLOAD_RECEIPT = `${this.API_URL}/uploadReceipt`;
  DELETE_RECEIPT = `${this.API_URL}/deleteReceipt`;
  REVIEW_RECEIPT = `${this.API_URL}/getReceipt`;

  //manager
  VIEW_USER = `${this.API_URL}/getmngusername`;
  APP_REI = `${this.API_URL}/approvereimbursement`;
  DEN_REI = `${this.API_URL}/denyreimbursement`;
  LIST_REI = `${this.API_URL}/users_rei_list`;
  VIEW_RECEIPT = `${this.API_URL}/getReceipt`;
  // LIST_REI = `${this.API_URL}/getlistofreimbursements`;
  // MAP_REI = `${this.API_URL}/users_rei_list`;

  //password
  FROM_LOGIN = `${this.API_URL}/validateUserEmail`;
  FROM_HOME = `${this.API_URL}/validateUserPassword`;
  RESET_WITH_TOKEN = `${this.API_URL}/validateResetToken`;

  //profile pictures
  UPLOAD_PFP = `${this.API_URL}/uploadPfp`;
  GET_PFP = `${this.API_URL}/getPfp`;

  //registration service
  CHECK_USERNAME = `${this.API_URL}/checkUsername/`;
  CHECK_EMAIL = `${this.API_URL}/checkEmail/`;
  NEW_USER = `${this.API_URL}/registerNewUser`;

}
