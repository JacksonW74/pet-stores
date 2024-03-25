CREATE TABLE PetStore (
    pet_store_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    pet_store_name VARCHAR(255),
    pet_store_address VARCHAR(255),
    pet_store_city VARCHAR(255),
    pet_store_state VARCHAR(255),
    pet_store_zip VARCHAR(20),
    pet_store_phone VARCHAR(20)
);

CREATE TABLE Customer (
    customer_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_first_name VARCHAR(255),
    customer_last_name VARCHAR(255),
    customer_email VARCHAR(255)
);

CREATE TABLE Employee (
    employee_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_first_name VARCHAR(255),
    employee_last_name VARCHAR(255),
    employee_email VARCHAR(255),
    employee_job_title VARCHAR(255),
    pet_store_id BIGINT,
    FOREIGN KEY (pet_store_id) REFERENCES PetStore(pet_store_id)
);

CREATE TABLE PetStore_Customer (
    pet_store_id BIGINT,
    customer_id BIGINT,
    PRIMARY KEY (pet_store_id, customer_id),
    FOREIGN KEY (pet_store_id) REFERENCES PetStore(pet_store_id),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);
