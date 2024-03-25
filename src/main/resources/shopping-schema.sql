DROP TABLE IF EXISTS `item`; 
DROP TABLE IF EXISTS `cart`; 
DROP TABLE IF EXISTS `checkout`;

CREATE TABLE `cart` (
    `id` INT PRIMARY KEY AUTO_INCREMENT, 
    `customer` VARCHAR(255)
);

CREATE TABLE `item` (
    `id` INT PRIMARY KEY AUTO_INCREMENT, 
    `name` VARCHAR(255), 
    `price` DOUBLE, 
    `quantity` INT,
    `cart_id` INT,
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
);

CREATE TABLE `checkout` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `cardholder_name` VARCHAR(255),
    `PAN` VARCHAR(255),
    `expiry_date` VARCHAR(10),
    `CVC` VARCHAR(3),
    `post_code` VARCHAR(10),
    `cart_id` INT,
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
);
