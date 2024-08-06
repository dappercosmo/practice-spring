CREATE TABLE customer(
customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(20),
last_name VARCHAR(20),
email VARCHAR(30),
phone VARCHAR(10),
is_email_verified BIT,
created_on DATE,
updated_on DATE,
is_deleted BIT,
password VARCHAR(30)
);