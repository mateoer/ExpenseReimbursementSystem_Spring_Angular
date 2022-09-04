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
 



## Getting Started

> git clone https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular.git

#You will need:
* An RDS, an S3 bucket, a Gmail account

From RDS save **endpoint**, **port number**, **username**, and **password**
From S3 bucket make sure you have **AWSAccessKeyId** and **AWSSecretKey**
From Gmail account, **username** and **password**

<br><br><br><br>
> Have an IDE capable of running Spring Boot applications, such as Spring STS.

> Once the project is cloned you need to update the maven project. On Eclipse, **Alt+F5** and select the project to update

> To enable **email notifications** as well as password resets, you need to configure a Gamil account <br/>
>You can follow the step-by-step guide here to configure a Gmail no-reply account to send and receive emails:

https://stackoverflow.com/questions/26594097/javamail-exception-javax-mail-authenticationfailedexception-534-5-7-9-applicatio/72592946#72592946

>Copy the username and the password generated. 
>Paste them in the properties.yml file located in **ERS/src/main/resources** -> 

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


## Usage

On DBeaver <br/><br/>
Make sure to save the **endpoint**, **port number**, **username**, and **password**.<br/>
Then create environment variables on the local system and name them as this:
>    TRAINING_DB_ENDPOINT -> for endpoint and port
>    
>    ERS_DB_DBNAME -> for DB name
>
>    TRAINING_DB_USERNAME -> for username
>    
>    TRAINING_DB_PASSWORD -> for password

Open the DB assigned to **ERS_DB_DBNAME** from your DB application (like DBeaver) but don't run the script **ERS_SA_Script.sql** (found in the root folder) just yet.
<br/>
   **Note that the script is in PostgreSQL**  
<br/><br/>




On VS Code <br/>
- Open the folder ../ERS_Spring_Angular/src/main/resources/frontend/**ERS_Angular**<br/>
- Open the terminal and run **npm install** to retrieve node modules<br/>
- Once it's done run **ng build**<br/>
 By running **ng serve** you can check if the application works. Clicking on the link produced confirms that it is able to load up. However, it is not ready yet. 
- You can close VS Code now<br/>
<br />

On Java IDE (in this case Spring STS v3).<br/>

- Go to **File** **->** **Open Project from File System** and select this project
  **Expense Reimbursement System** wherever it was downloaded in your system
  
- Right click on the project name -> Maven -> Update Maven Project

- On **application.yml** found at **src/main/resources**<br/>
  Comment out the line **ddl-auto: none** and uncomment **ddl-auto: create** before running the application the first time.<br/>
  This will create the tables in the database.<br/> 
  
  After the tables have been created, comment out **ddl-auto: create** and uncomment **ddl-auto: none**.<br/>
  This way you avoid having to re-populate the tables every time you restart the application
<br />

Run the application. <br/>
- Spring STS detects Spring Boot applications after updating the Maven project<br/>
  Simply select **ERS[9050]** and click the **start** button on the Boot Dashboard panel. It will start the application.<br/>
  **NOTE: the application runs on port 9050. So, make sure no other application is running on that port at that moment.**<br/><br/>
- **Now, with the application running run the script **ERS_SA_Script.sql** on your DB application to populate the tables. Remember, it is in PostgreSQL**
- Before running the application a second time, make sure to uncomment **ddl-auto: none** on **application.yml**
  
  <img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/boot.PNG" width=40% height=40%>
  <br /><br/>


Now the project is ready to go. Go to localhost:9050//<br/><br/>
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/login.PNG" width=50% height=50%>
<br />
- To log in with authorized credentials use:
>                         username     password
>                         
>     Employee             suechan         abc123
>  
>     Finance Manager       admin          abc123
<br/>

Employee<br/>
- All reimbursements associated with that user are retrieved on page load<br/>
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/employee.PNG" width=50% height=50%>
<br />

- You can filter reimbursements by status<br/>
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/filter.PNG" width=50% height=50%>
<br />

Finance Manager<br/>

- User name is retrieved automatically from the database as well <br/>
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/manager.PNG" width=50% height=50%>
<br />

## License

No License
