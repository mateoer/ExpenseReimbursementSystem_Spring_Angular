# Expense Reimbursement System Spring-Boot/Angular
<br />

## Project Description
Expense Reimbursement System (ERS) - Java

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

This is a redo of Revature_Project_1_ExpenseReimbursementSystem with Spring-Boot and Angular technologies.
As an added feature this application was hosted into an EC2 instance and it is continually integrated from codepipeline branch on this repository
## Technologies Used

* Angular
* Spring Boot
* Node.js
* AWS RDS, EC2, S3, CodeBuild, CodeDeploy, CodePipeline

* DBeaver
* Spring STS 3
* VS Code
* TypeScript
* Java
* PostgreSQL


## Features

Features:
* User validation at login
* Dynamic home page based on login credentials
* Onload functionalities to retrieve reimbursement tables
* Filtering requests
* Employee account has ability to create new requests
* Ability to cancel/edit reimbursements when the status of the request is still **pending**
* Manager account has ability to approve/deny requests
* Clickable table rows 
* Spring Security 
* Spring Mail to receive alerts about reimbursement's status 
* Ability to upload profile pictures 
* Ability to reset passwords from user home or through email
* Password hasshing functionality 
* Register new users 
 
## Showcase

[LOGIN GOES HERE]***
[REGISTRATION GOES HERE]***
[EMPLOYEE PAGE CREATE REQUEST]***
[EMPLOYE DIALOG PENDING]***
[EMPLOYE DIALOG APP/DENIED]***
[MANAGER PAGE]***
[MANAGER DIALOG]***
[APP/DENY FROM DASHBOARD]***
[UPLOAD PFP]***
[RESET PASSWORD FROM HOME]***
[RESET PASSWORD FROM LOGIN]***
[EMAIL APPROVED/DENIED REI]***
[EMAIL RESET TOKEN]***
[RESET PASSWORD PAGE]****




## Getting Started

> git clone https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular.git

#You will need:
* An RDS, an S3 bucket, a Gmail account

From RDS save **endpoint**, **port number**, **username**, and **password** <br>
From S3 bucket make sure you have **AWSAccessKeyId** and **AWSSecretKey** <br>
From Gmail account, **username** and **password** <br>

<br><br><br>
> Have an IDE capable of running Spring Boot applications, such as Spring STS.

> Once the project is cloned you need to update the maven project. On Eclipse, **Alt+F5** and select the project to update

- On **application.yml** found at **src/main/resources**<br/>
  Comment out the line **ddl-auto: none** and uncomment **ddl-auto: create** before running the application the first time.<br/>
  This will create the tables in the database.<br/> 
  
  After the tables have been created, comment out **ddl-auto: create** and uncomment **ddl-auto: none**.<br/>
  This way you avoid having to re-populate the tables every time you restart the application

> To enable **email notifications** as well as password resets, you need to configure a Gamil account <br/>
>You can follow the step-by-step guide here to configure a Gmail no-reply account to send and receive emails:

https://stackoverflow.com/questions/26594097/javamail-exception-javax-mail-authenticationfailedexception-534-5-7-9-applicatio/72592946#72592946

>Copy the username and the password generated. 
>Paste them in the **properties.yml** file located in **ERS/src/main/resources** 
>The, on **src/main/java/ service/mmail/EmailService.java** change the field marked <br>
>**private static final String NOREPLY_ADDRESS = "your_email_goes_here";**

<SCREENSHOT OF THE EMAIL USERNAME AND PASSWORD GOES HERE>

> To enable **profile pictures** you need to set up an S3 bucket from AWS
The process is a little tedious if you don't know how to set up an S3 bucket. But I left a few good guides on the **S3 AWS** card under **General Information**
list of my Trello board

https://trello.com/b/HF5k6pi8/ers-spring-angular

If you have one already set up, then just go into the **application.yml** file and update the region to the one where your bucket is located
Also, replace **AWSAccessKeyId** and **AWSSecretKey** for your AWS Acess Key and Secret Key respectively
Then, on **src/main/java** **service/amazon** **/AWSConfig.java** replace bucket name with your bucket name
Update the project with **Alt+F5**
  
> **Ctrl+Alt+T** will open the project terminal. 
> Run the following command: **mvn package**
> That will create an executable file you can locate at **ERS/target/** with the name **ERS-0.0.1-SNAPSHOT.jar**
> Run **cd target/**
> Run **java -jar ERS-0.0.1-SNAPSHOT.jar** 
  
  This will start executing the project. Once the boot is complete, open your **localhost:9050/**
  
  You can create an account for manager/employee and start creating/approving/denying reimbursements
  When reimbursements have a **pending** status employees will be able to cancel or edit some portions of their requests. This feature is disabled when
  the status changes to **approved/denied**.
  Additionally, if the email and S3 bucket have been setup properly the employee users will receive notifications when their requests is approved/denied by the manager
  All users will be able to reset their passwords from the login page by inserting their username and email accounts to receive a reset token
  And also all users will be able to upload profile pictures

<br /> 

## License

No License
