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
* AWS RDS, EC2, CodeBuild, CodeDeploy, CodePipeline

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
* Request, Approve and Deny functionalities
* Ability to create new requests

To-do list:
* Spring Security
* Spring Mail to receive alerts about reimbursement's status
* Ability to reset passwords
* Ability to upload profile pictures/ recepit pictures together with the reimbursement request
* Password hasshing functionality


## Getting Started

> git clone https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular.git

> Have an RDS and connect to your DB

> Have an IDE capable of running Spring Boot applications, such as Spring STS.

> Make sure your IDE has project Lombok installed. On Eclipse-based IDEs: Help -> About.. -> Look for Lombok and version

<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/Capture.PNG" width=30% height=30%>
<br />


## Usage

On your DB make sure to save the **endpoint**, **port number**, **username**, and **password**.<br/>
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
<br/>

On VS Code <br/>
- Open the folder ../ERS_Spring_Angular/src/main/resources/frontend/**ERS_Angular**<br/>
- Open the terminal and run **npm install** to retrieve node modules<br/>
- Once it's done run **ng build**<br/>
 By running **ng serve** you can check if the application works. Clicking on the link produced confirms that it is able to load up. However, it is not ready yet.
<br/>  
  **You can close VS Code now**<br/>
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
- On Spring STS it gets included when imported and STS detects it's a Spring Boot application<br/>
  Select **ERS[9050]** and click the **start** button. It will start the application.<br/>
  **NOTE: the application runs on port 9050. So, make sure no other application is running on that port at that moment.**<br/><br/>
  **Now, with the application running run the script **ERS_SA_Script.sql** on your DB application to populate the tables. Remember, it is in PostgreSQL**<br/><br/>
  **Before running the application a second time, make sure to uncomment **ddl-auto: none** on **application.yml** **<br/><br/>
  
  <img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/boot.PNG" width=40% height=40%>
  <br /><br/>


Now the project is ready to go<br/><br/>
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
