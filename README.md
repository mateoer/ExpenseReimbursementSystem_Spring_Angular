# Expense Reimbursement System Spring-Boot/Angular
<br />


## Project Description
Expense Reimbursement System (ERS) - Java

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

This is a redo of Revature_Project_1_ExpenseReimbursementSystem with Spring-Boot and Angular technologies.
As an added feature this application was hosted into an EC2 instance and it is continually integrated from **codepipeline** branch on this repository
Additionally, I also included new features like clickable rows, email notifications, password resets, and profile pictures; with the intention of experimenting
with Angular material tables, Spring Mail, and AWS S3 buckets and the subsequent configuration on Spring Boot for image hosting.
## Technologies Used



* Angular
* Spring Boot
* Node.js
* AWS RDS, EC2, S3, CodeBuild, CodeDeploy, CodePipeline

* DBeaver
* Spring STS 4
* VS Code
* TypeScript
* Java
* PostgreSQL
* Trello board https://trello.com/b/HF5k6pi8/ers-spring-angular


## Features

* User validation at login
* Dynamic home page based on login credentials
* Onload functionalities to retrieve reimbursement tables
* Filtering requests
* Employee account has ability to create new requests
* Ability to cancel/edit reimbursements when the status of the request is still **pending**
* Manager account has ability to approve/deny requests
* Clickable table rows 
* Spring Mail to receive alerts about reimbursement's status 
* Ability to upload profile pictures 
* Ability to reset passwords from user home or through email
* Password hasshing functionality 
* Register new users 
 
## Showcase
> Landing page
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/login.PNG" width=50% height=50%> 

> Registration
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/registration.PNG" width=30% height=50%>

> Reset password from login
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/reset_login.PNG" width=50% height=50%>

> Reset password email
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/reset_email.PNG" width=50% height=50%>
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/reset_email_inside.PNG" width=50% height=30%>

> Reset password page
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/reset_token.PNG" width=50% height=50%>
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/reset_success.PNG" width=50% height=50%>

> Reset password from user home
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/reset_home.PNG" width=50% height=50%>
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/reset_home_success.PNG" width=50% height=50%>

> Reimbursement Approved and Rejected notifications
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/app_del_notification.PNG" width=50% height=50%>

> Initial user home page
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/blank_pfp.PNG" width=50% height=50%>

>Upload picture menu
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/upload_menu.PNG" width=50% height=50%>

> After image was uploaded
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/pfp_success.PNG" width=50% height=50%>

> Edit/Delete dialog box. This is only available to employee while reimbursement status is 'Pending'
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/edit_cancel_dialog.PNG" width=50% height=50%>

> After status of reimbursement changes, edit and delete are not available
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/approved_emp_dialog.PNG" width=50% height=50%>

> Notification inside dialog box
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/deleted_rei.PNG" width=50% height=50%>

> New reimbursement request
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/new_req.PNG" width=50% height=50%>
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/new_req_success.PNG" width=50% height=50%>

> Dialog box for manager
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/approved_dialog.PNG" width=50% height=50%>

> Manager can also approve/reject reimbursements from the home page
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/app_den_mang_dash.PNG" width=50% height=50%>

> Both types of user can filter reimbursement by status or type
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/filter_type.PNG" width=50% height=50%>
<img src="https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular/blob/main/screenshots/webapp/filter_status.PNG" width=50% height=50%>


## Getting Started

> git clone https://github.com/mateoer/ExpenseReimbursementSystem_Spring_Angular.git

You will need an RDS, an S3 bucket, and a Gmail account

> Open project from your IDE, in this example I use Spring STS 4

> Click on *File* and then, *Open projects from file system*

> Update maven project

## Setting up environment variables

> Follow the list of environment variables to configure into your system in order to run the application
> 
> **HOW_TO_SETUP_ENVIRONMENT_VARIABLES.txt** found on the root directory of this project
> 
> After the variables are set, close and open your IDE back up

## Running the application

> Open the terminal
 
> From the project directory run:
 
> **mvn package**
 
> **cd target/**

> **java -jar ERS-0.0.1-SNAPSHOT.jar**
 
> Then open your **localhost:9050/**  

## License

No License
