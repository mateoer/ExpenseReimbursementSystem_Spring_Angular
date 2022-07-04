/* CREATE TABLE user_role (
		user_role_id INTEGER --PK
		,user_role TEXT
		,PRIMARY KEY (user_role_id)
 );  */ 

CREATE TABLE user_table (
user_id SERIAL 	   	    			
,username TEXT UNIQUE NOT NULL	
,user_password TEXT NOT NULL	
,first_name TEXT    NOT NULL 
,last_name TEXT     NOT NULL 
,email TEXT 		   UNIQUE NOT NULL 		
,user_role INTEGER         NOT NULL 			
,PRIMARY KEY (user_id)
,FOREIGN KEY (user_role) REFERENCES user_role (user_role_id)	
);