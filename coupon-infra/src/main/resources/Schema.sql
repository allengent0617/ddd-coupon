
CREATE TABLE merchant_stock (
   id INT PRIMARY KEY,
   merchant_id INT ,
   goods_id INT ,
   quantity INT
)   ;


CREATE TABLE user_account (
     id INT PRIMARY KEY,
     user_id INT ,
     available DECIMAL(18,4)
)  ;
