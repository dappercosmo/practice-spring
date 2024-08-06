CREATE TABLE address(
address_id BIGINT AUTO_INCREMENT PRIMARY KEY,
house_name VARCHAR(20),
street VARCHAR(100),
city VARCHAR(30),
country VARCHAR(10),
created_on DATE,
updated_on DATE,
is_deleted BIT,
customer_id BIGINT,
CONSTRAINT fk_customer_id FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);