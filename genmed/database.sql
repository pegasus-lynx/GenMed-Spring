CREATE TABLE address
(
  plot_no VARCHAR(32),
  street VARCHAR(32),
  latitude NUMERIC(24,20) NOT NULL,
  longitude NUMERIC(24,20) NOT NULL,
  address_id INT NOT NULL AUTO_INCREMENT,
  city VARCHAR(32) NOT NULL,
  district VARCHAR(32) NOT NULL,
  state VARCHAR(16) NOT NULL,
  PRIMARY KEY (address_id)
);

CREATE TABLE user
(
  first_name VARCHAR(16) NOT NULL,
  last_name VARCHAR(32),
  user_id INT NOT NULL  AUTO_INCREMENT,
  password VARCHAR(48) NOT NULL,
  email_id VARCHAR(32) NOT NULL,
  role VARCHAR(10) NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE genericDrugs
(
  gen_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(64) NOT NULL,
  PRIMARY KEY (gen_id)
);

CREATE TABLE drugs
(
  drug_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(48) NOT NULL,
  mf_name VARCHAR(48) NOT NULL,
  is_generic INT NOT NULL,
  gen_id INT NOT NULL,
  PRIMARY KEY (drug_id),
  FOREIGN KEY (gen_id) REFERENCES genericDrugs(gen_id)
);

CREATE TABLE userPhone
(
  phone_no VARCHAR(13) NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (phone_no, user_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE addressOfUser
(
  address_type VARCHAR(122) NOT NULL,
  user_id INT NOT NULL,
  address_id INT NOT NULL,
  PRIMARY KEY (user_id, address_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (address_id) REFERENCES address(address_id)
);

CREATE TABLE drugComponents
(
  comp_id INT NOT NULL AUTO_INCREMENT,
  comp_name VARCHAR(36) NOT NULL,
  PRIMARY KEY (comp_id)
);

CREATE TABLE drugBatch
(
  batch_no VARCHAR(12) NOT NULL,
  mfg_date DATE NOT NULL,
  exp_date DATE NOT NULL,
  batch_id INT NOT NULL AUTO_INCREMENT,
  drug_id INT NOT NULL,
  PRIMARY KEY (batch_id),
  FOREIGN KEY (drug_id) REFERENCES drugs(drug_id)
);

CREATE TABLE shop
(
  shop_id INT NOT NULL AUTO_INCREMENT,
  shop_name VARCHAR(64) NOT NULL,
  license VARCHAR(10) NOT NULL,
  address_id INT NOT NULL,
  email_id VARCHAR(48) NOT NULL,
  PRIMARY KEY (shop_id),
  FOREIGN KEY (address_id) REFERENCES address(address_id)
);

CREATE TABLE shopInventory
(
  item_id INT NOT NULL AUTO_INCREMENT,
  quantity INT NOT NULL,
  shop_id INT NOT NULL,
  batch_id INT NOT NULL,
  price NUMERIC(8,2) NOT NULL,
  PRIMARY KEY (item_id),
  FOREIGN KEY (shop_id) REFERENCES shop(shop_id),
  FOREIGN KEY (batch_id) REFERENCES drugBatch(batch_id)
);

CREATE TABLE orders
(
  order_id INT NOT NULL AUTO_INCREMENT,
  order_status VARCHAR(12) NULL,
  order_date DATE NOT NULL,
  order_time TIME NOT NULL,
  bill_amount NUMERIC(8,2) NULL,
  shop_id INT NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (order_id),
  FOREIGN KEY (shop_id) REFERENCES shop(shop_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE shopPhone
(
  phone_no VARCHAR(13) NOT NULL,
  shop_id INT NOT NULL,
  PRIMARY KEY (phone_no, shop_id),
  FOREIGN KEY (shop_id) REFERENCES shop(shop_id) ON DELETE CASCADE
);

CREATE TABLE genericDrugComposition
(
  percent NUMERIC(5,2) NOT NULL,
  gen_id INT NOT NULL,
  comp_id INT NOT NULL,
  PRIMARY KEY (gen_id, comp_id),
  FOREIGN KEY (gen_id) REFERENCES genericDrugs(gen_id),
  FOREIGN KEY (comp_id) REFERENCES drugComponents(comp_id)
);

CREATE TABLE itemsOrdered
(
  quantity INT NOT NULL,
  price NUMERIC(7,2) NOT NULL,
  order_id INT NOT NULL,
  item_id INT NOT NULL,
  PRIMARY KEY (order_id, item_id),
  FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
  FOREIGN KEY (item_id) REFERENCES shopInventory(item_id)
);

CREATE TABLE owner
(
  user_id INT NOT NULL,
  shop_id INT NOT NULL,
  PRIMARY KEY (user_id, shop_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
  FOREIGN KEY (shop_id) REFERENCES shop(shop_id) ON DELETE CASCADE
);

CREATE TABLE reviews
(
  comment VARCHAR(1024) NOT NULL,
  rating NUMERIC(3,2) NOT NULL,
  review_id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  PRIMARY KEY (review_id),
  FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);