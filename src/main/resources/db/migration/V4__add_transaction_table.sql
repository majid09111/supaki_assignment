CREATE TABLE IF NOT EXISTS `transaction_details` (
  `id` VARCHAR (100) NOT NULL,
  `buyer_id` int(11) DEFAULT 0,
  `seller_id` int(11) DEFAULT 0,
  `total_amount` DECIMAL(6,1) DEFAULT 0.0,
  `seller_amount` DECIMAL(6,1) DEFAULT 0.0,
  `commission_amount` DECIMAL(6,1) DEFAULT 0.0,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;