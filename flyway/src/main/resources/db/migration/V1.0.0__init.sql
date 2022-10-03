

CREATE TABLE `merchant_stock`  (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `merchant_id` bigint NOT NULL COMMENT '商户id',
    `goods_id` bigint NOT NULL COMMENT '商品id',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '库存数量',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `merchant_goods`(`merchant_id` ASC, `goods_id` ASC) USING BTREE
)  COMMENT = '商户库存表' ;


CREATE TABLE `user_account`  (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `user_id` bigint NOT NULL COMMENT 'user id',
   `available` decimal(18,4) NOT NULL default 0 COMMENT '用户预存金额',
   PRIMARY KEY (`id`) USING BTREE,
   UNIQUE INDEX `user_id_index`(`user_id` ) USING BTREE
)  COMMENT = '用户预存资金表' ;