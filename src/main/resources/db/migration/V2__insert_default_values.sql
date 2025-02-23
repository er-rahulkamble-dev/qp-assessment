-- Insert default roles into 'role_tb'
INSERT INTO role_tb (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');

-- Insert default users into 'user_tb'
INSERT INTO user_tb (name , username, email, password) VALUES
    -- password: admin@123
    ('Admin User', 'admin', 'admin@example.com', '$2a$12$VM0DpgIn5u40k9jmci9XHOGPe1w/d6JwTMarDDexE2OepUwmgWD2i'),
    -- password: user@123
    ('Regular User', 'user', 'user@example.com', '$2a$12$rIcuhnjtzXv1DZjD7UXIROG3z1JwJo..IRWuqJuoqvSgpvjbzIsDK');

-- Map roles to users via join table users_roles_tb
-- Insert default user-role relationships into 'users_roles_tb'
INSERT INTO users_roles_tb (user_id_fk, role_id_fk) VALUES
    (1, 1),  -- Admin User has ROLE_ADMIN
    (2, 2);  -- Regular User has ROLE_USER

-- Insert default grocery items into 'grocery_item_tb'
INSERT INTO grocery_item_tb (name, price, category, inventory_level, description) VALUES
    ('Apple', 1.99, 'Fruits', 100, 'Fresh red apples'),
    ('Banana', 0.99, 'Fruits', 200, 'Ripe yellow bananas'),
    ('Milk', 2.49, 'Dairy', 50, 'Whole milk, 1 liter'),
    ('Bread', 1.49, 'Bakery', 75, 'Whole grain bread');

-- Insert default orders  into 'order_tb'
INSERT INTO order_tb (order_date, order_status, user_id_fk) VALUES
    (NOW(), 'COMPLETED', 1),
    (NOW(), 'PENDING', 2);

-- Map the order with groecery items via join table
-- Insert default order-grocery item relationships into 'order_grocery_items_tb'
INSERT INTO orders_grocery_items_tb (order_id_fk, grocery_item_id_fk) VALUES
    (1, 1),  -- Order 1 contains Apple
    (1, 3),  -- Order 1 contains Milk
    (2, 2),  -- Order 2 contains Banana
    (2, 4);  -- Order 2 contains Bread

-- Insert default payments into 'payment_tb'
INSERT INTO payment_tb (payment_status, payment_method, total_amount, payment_date, order_id) VALUES
    ('COMPLETED', 'CREDIT_CARD', 4.48, NOW(), 1),
    ('PENDING', 'PAYPAL', 2.48, NOW(), 2);


