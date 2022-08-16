
INSERT INTO user_table (email,first_name,last_name,user_password,user_role,username) VALUES
	 ('admin@company.com','Eric','Smith','abc123','MANAGER','admin'),
	 ('employee@company.com','Sue','Chan','abc123','EMPLOYEE','suechan');




INSERT INTO reimbursements_table (rei_author,rei_status,rei_type,rei_amount,rei_description,rei_resolved_date,rei_resolver,rei_submitted_date,reimbursement_author_user_id) VALUES
	 (1,'DENIED','FOOD',20.0,'ice cream','2022-08-09 00:17:41.714',NULL,'2022-07-20 00:00:00.000',1),
	 (2,'APPROVED','OTHER',12.5,'celery','2022-08-12 00:10:05.889',NULL,'2022-08-08 00:00:00.000',2),
	 (2,'APPROVED','GAS',120.0,'van refuel','2022-08-14 02:45:05.656',NULL,'2022-08-13 23:26:09.332',NULL),
	 (2,'DENIED','LODGING',450.0,'tomorrowland','2022-08-14 02:45:25.070',NULL,'2022-08-14 02:38:54.235',NULL),
	 (2,'APPROVED','FOOD',15.0,'tacos locos','2022-08-14 03:08:23.942',NULL,'2022-08-14 02:46:51.275',NULL),
	 (2,'DENIED','LODGING',235.0,'hotel','2022-08-14 03:08:24.844',NULL,'2022-08-13 23:57:21.207',NULL),
	 (2,'PENDING','FOOD',12.32,'burger',NULL,NULL,'2022-08-14 03:11:08.715',NULL),
	 (2,'APPROVED','FOOD',20.0,'celery','2022-08-14 03:36:38.343',NULL,'2022-07-20 00:00:00.000',2),
	 (2,'DENIED','FOOD',20.0,'ice','2022-08-14 03:36:43.133',NULL,'2022-07-20 00:00:00.000',2),
	 (2,'PENDING','OTHER',10.0,'stuff',NULL,NULL,'2022-08-14 03:37:09.985',NULL);
INSERT INTO reimbursements_table (rei_author,rei_status,rei_type,rei_amount,rei_description,rei_resolved_date,rei_resolver,rei_submitted_date,reimbursement_author_user_id) VALUES
	 (2,'DENIED','FOOD',20.0,'ice cream','2022-08-08 20:39:00.256',NULL,'2022-07-20 00:00:00.000',2),
	 (2,'PENDING','OTHER',15.0,'ice',NULL,NULL,'2022-08-08 00:00:00.000',2),
	 (2,'PENDING','FOOD',86.78,'ice cream',NULL,NULL,'2022-08-08 00:00:00.000',2),
	 (1,'PENDING','GAS',150.75,'gas',NULL,NULL,'2022-08-08 00:00:00.000',1),
	 (2,'PENDING','OTHER',452.0,'jewelry',NULL,NULL,'2022-08-08 00:00:00.000',2),
	 (1,'APPROVED','FOOD',20.0,'bread','2022-08-08 20:42:41.164',NULL,'2022-07-20 00:00:00.000',1),
	 (1,'APPROVED','FOOD',40.0,'ice cream','2022-08-08 20:43:57.557',NULL,'2022-08-08 00:00:00.000',1),
	 (1,'DENIED','FOOD',25.35,'bread','2022-08-08 20:45:40.466',NULL,'2022-08-08 00:00:00.000',1),
	 (1,'DENIED','FOOD',40.36,'ice cream','2022-08-08 20:45:59.252',NULL,'2022-08-08 00:00:00.000',1),
	 (1,'DENIED','FOOD',5.46,'butter','2022-08-08 20:46:03.810',NULL,'2022-08-08 00:00:00.000',1);
INSERT INTO reimbursements_table (rei_author,rei_status,rei_type,rei_amount,rei_description,rei_resolved_date,rei_resolver,rei_submitted_date,reimbursement_author_user_id) VALUES
	 (1,'DENIED','FOOD',20.0,'ice cream','2022-08-08 20:46:25.128',NULL,'2022-07-20 00:00:00.000',1),
	 (1,'PENDING','LODGING',236.74,'convention',NULL,NULL,'2022-08-08 20:47:15.234',NULL),
	 (1,'DENIED','FOOD',123.04,'grocery','2022-08-08 20:56:02.306',NULL,'2022-08-08 00:00:00.000',1);
