-- Create the 'user_tb' table
CREATE TABLE user_tb (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password TEXT NOT NULL
);

-- Create the 'role_tb' table
CREATE TABLE role_tb (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Create the 'users_roles_tb' table for many-to-many relationship between users and roles
CREATE TABLE users_roles_tb (
    user_id_fk BIGINT NOT NULL,
    role_id_fk BIGINT NOT NULL,
    PRIMARY KEY (user_id_fk, role_id_fk),
    FOREIGN KEY (user_id_fk) REFERENCES user_tb(user_id),
    FOREIGN KEY (role_id_fk) REFERENCES role_tb(role_id)
);


-- Create the 'order_tb' table
CREATE TABLE order_tb (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP,
    order_status VARCHAR(255) NOT NULL DEFAULT 'PENDING' , -- Add status column
    user_id_fk BIGINT NOT NULL,
    FOREIGN KEY (user_id_fk) REFERENCES user_tb(user_id)
);

-- Create the 'grocery_item_tb' table
CREATE TABLE grocery_item_tb (
    groc_itm_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    category VARCHAR(255),
    inventory_level BIGINT,
    description TEXT
);


-- Create the join 'order_grocery_items_tb' table for many-to-many relationship between Order and grocery item table
CREATE TABLE orders_grocery_items_tb (
    order_id_fk BIGINT,
    grocery_item_id_fk BIGINT,
    PRIMARY KEY (order_id_fk, grocery_item_id_fk),
    FOREIGN KEY (order_id_fk) REFERENCES order_tb(order_id),
    FOREIGN KEY (grocery_item_id_fk) REFERENCES grocery_item_tb(groc_itm_id)
);

-- Create the 'payment_tb' table
CREATE TABLE payment_tb (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_status VARCHAR(50) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
     total_amount DECIMAL(10,2),
    payment_date TIMESTAMP,
    order_id BIGINT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES order_tb(order_id)
);
