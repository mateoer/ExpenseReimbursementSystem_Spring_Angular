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
* Spring STS 4
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

You will need an RDS, an S3 bucket, and a Gmail account

> Open project from your IDE, in this example I use Spring STS 4

> Update maven project

## Setting up environment variables

> Follow the list of environment variables to configure into your system in order to run the application
> 
> **HOW_TO_SETUP_ENVIRONMENT_VARIABLES.txt**
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
