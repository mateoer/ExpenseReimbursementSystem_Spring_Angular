INSERT INTO user_table (email,username, first_name, last_name, user_password, user_role)
VALUES ('mateoer@kean.edu','mateoer','Eric','Mateo','abc123', 'MANAGER'),
 		('sueortiz@kean.edu','suechan2','Sue','Chan','abc123', 'EMPLOYEE');


INSERT INTO reimbursements_table
(rei_status, rei_type, rei_amount, rei_description, rei_resolver,rei_author) VALUES 
('PENDING','FOOD', 20.00, 'bread', 1,1),
('PENDING','FOOD', 20.00, 'ice cream', 1,1),
('PENDING','FOOD', 20.00, 'grocery', 1,1),
('PENDING','FOOD', 20.00, 'celery', 2,2),
('PENDING','FOOD', 20.00, 'ice cream', 1,1),
('PENDING','FOOD', 20.00, 'ice', 2,2),
('PENDING','FOOD', 20.00, 'ice cream', 2,2),
('PENDING','FOOD', 20.00, 'butter', 1,1),
('PENDING','FOOD', 20.00, 'gas', 1,1),
('PENDING','FOOD', 20.00, 'jewelry', 2,2);