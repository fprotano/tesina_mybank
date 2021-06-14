/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `tesina_mybank` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `tesina_mybank`;

CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `iban` varchar(30) DEFAULT NULL,
  `balance` decimal(11,2) DEFAULT 0.00,
  `email` varchar(70) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `surname` varchar(40) DEFAULT NULL,
  `next_otp_code_after_date` datetime DEFAULT NULL,
  `otp_code` varchar(10) DEFAULT NULL,
  `otp_code_expires_at` datetime DEFAULT NULL,
  `credit_card_no` varchar(40) DEFAULT NULL,
  `credit_card_cin` varchar(5) DEFAULT NULL,
  `credit_card_expires_at` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `otp_code` (`otp_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT IGNORE INTO `account` (`id`, `created_at`, `updated_at`, `iban`, `balance`, `email`, `password`, `name`, `surname`, `next_otp_code_after_date`, `otp_code`, `otp_code_expires_at`, `credit_card_no`, `credit_card_cin`, `credit_card_expires_at`) VALUES
	(1, '2021-06-03 10:16:57', '2021-06-03 10:16:59', 'IT32X0909089898000000000099', 120.00, 'guest@guest.it', 'guest', 'guestnome', 'guestcognome', '2022-06-03 10:17:35', 'guest', '2022-06-03 10:17:42', '999999999999999', '998', '01/25'),
	(2, '2021-06-03 10:21:12', '2021-06-03 10:21:13', 'IT32X0000000000000099228888', 4848.00, 'test1@test.it', 'test', 'test1', 'test1', '2022-06-03 10:21:44', 'test1', '2022-06-03 10:21:51', '10999999999998', '101', '12/24'),
	(3, '2021-06-03 10:47:24', '2021-06-03 10:47:25', 'IT44Z9999999999999999999887', 140.00, 'test2@test.it', 'test', 'test2', 'test2', '2021-06-03 10:47:52', 'test2', '2021-06-03 10:47:59', '44444444444444', '401', '12/23'),
	(4, '2021-06-14 11:18:57', '2021-06-14 11:19:11', 'IT53X1904300000336904480547', 1000.00, 'demo@demo.it', 'demodemo99', 'ppppppp', 'pppppppp', NULL, 'VwaUn5LO5a', '2021-06-14 11:22:11', '5677969594069188', '605', '5/2026');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `external_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `custom_code` varchar(255) NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `amount` decimal(11,2) NOT NULL DEFAULT 0.00,
  `to_account_id` int(11) NOT NULL,
  `transaction_status_id` int(11) NOT NULL,
  `transaction_error_reason` varchar(255) DEFAULT NULL,
  `verify_assigned_to` int(11) NOT NULL,
  `customer_name` varchar(30) NOT NULL,
  `customer_surname` varchar(40) NOT NULL,
  `customer_credit_card_no` varchar(40) NOT NULL,
  `customer_credit_card_cin` varchar(5) NOT NULL,
  `customer_credit_card_expires_at` varchar(7) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `transaction_id` (`transaction_id`),
  UNIQUE KEY `to_account_id` (`to_account_id`,`custom_code`),
  KEY `transaction_status_id` (`transaction_status_id`),
  KEY `verify_assigned_to` (`verify_assigned_to`),
  CONSTRAINT `external_transaction_ibfk_1` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `external_transaction_ibfk_2` FOREIGN KEY (`transaction_status_id`) REFERENCES `transaction_status` (`id`),
  CONSTRAINT `external_transaction_ibfk_3` FOREIGN KEY (`verify_assigned_to`) REFERENCES `staff` (`id`),
  CONSTRAINT `external_transaction_ibfk_4` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_unique_id` (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `external_transaction` DISABLE KEYS */;
INSERT IGNORE INTO `external_transaction` (`id`, `created_at`, `custom_code`, `transaction_id`, `amount`, `to_account_id`, `transaction_status_id`, `transaction_error_reason`, `verify_assigned_to`, `customer_name`, `customer_surname`, `customer_credit_card_no`, `customer_credit_card_cin`, `customer_credit_card_expires_at`) VALUES
	(1, '2021-06-03 10:20:17', 'X2', '645946516354654', 125.00, 1, 2, 'dedededododo', 1, 'Andrea', 'Agassi', '99999999999FF2', '378', '2025/12'),
	(2, '2021-06-10 09:52:18', 'XL', 'RRRREEEEEEEEEEEEEEEEEEEEEEEEEEEEEE', 5151.00, 2, 3, NULL, 1, 'Mauro', 'Poggiq', '888989898989FF', '962', '2025/11'),
	(3, '2021-06-11 10:57:44', 'PPUJ9', 'ABABABABABCDCDCDCDCDABABABABAB141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141455555', 412.00, 2, 2, NULL, 1, 'Piero', 'Lolli', '999iuj', '854', '2024/12');
/*!40000 ALTER TABLE `external_transaction` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `faq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) NOT NULL,
  `answer` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT IGNORE INTO `faq` (`id`, `question`, `answer`) VALUES
	(1, 'Is this real?', 'No, Neo'),
	(2, 'HUUHEDH3IOJIDIJD3IJOJ3D', 'HUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3DHUUHEDH3IOJIDIJD3IJOJ3D');
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `help_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `closed_at` datetime DEFAULT NULL,
  `from_account_id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `assigned_to_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `from_account_id` (`from_account_id`),
  KEY `assigned_to_id` (`assigned_to_id`),
  CONSTRAINT `help_center_ibfk_1` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `help_center_ibfk_2` FOREIGN KEY (`assigned_to_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `help_center` DISABLE KEYS */;
INSERT IGNORE INTO `help_center` (`id`, `created_at`, `updated_at`, `closed_at`, `from_account_id`, `question`, `assigned_to_id`) VALUES
	(1, '2021-06-03 10:18:38', '2021-06-03 10:18:40', '2021-06-03 10:18:40', 3, 'Is this matrix?', 1);
/*!40000 ALTER TABLE `help_center` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `help_center_thread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `help_center_id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `answer` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `help_center_id` (`help_center_id`),
  CONSTRAINT `help_center_thread_ibfk_1` FOREIGN KEY (`help_center_id`) REFERENCES `help_center` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `help_center_thread` DISABLE KEYS */;
INSERT IGNORE INTO `help_center_thread` (`id`, `created_at`, `help_center_id`, `question`, `answer`) VALUES
	(1, '2021-06-03 10:19:03', 1, 'Are you Morpheus', 'Yes');
/*!40000 ALTER TABLE `help_center_thread` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `internal_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `custom_code` varchar(255) NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `amount` decimal(11,2) NOT NULL DEFAULT 0.00,
  `from_account_id` int(11) NOT NULL,
  `to_account_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `transaction_id` (`transaction_id`),
  UNIQUE KEY `to_account_id` (`to_account_id`,`custom_code`),
  KEY `from_account_id` (`from_account_id`),
  CONSTRAINT `internal_transaction_ibfk_1` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `internal_transaction_ibfk_2` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `internal_transaction_ibfk_3` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_unique_id` (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `internal_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `internal_transaction` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `custom_code` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `url_undo` varchar(255) DEFAULT NULL,
  `url_success` varchar(255) DEFAULT NULL,
  `url_notify` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL,
  `title` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT IGNORE INTO `role` (`id`, `title`) VALUES
	(1, 'Amministratore'),
	(2, 'Verificatore'),
	(3, 'Help Desk');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `email` varchar(70) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `surname` varchar(40) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `next_otp_code_after_date` datetime DEFAULT NULL,
  `otp_code` varchar(10) DEFAULT NULL,
  `otp_code_expires_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `otp_code` (`otp_code`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT IGNORE INTO `staff` (`id`, `created_at`, `updated_at`, `email`, `password`, `name`, `surname`, `role_id`, `next_otp_code_after_date`, `otp_code`, `otp_code_expires_at`) VALUES
	(1, '2021-06-03 10:17:58', '2021-06-03 11:41:31', 'demo@demo.it', 'demo', 'demo', 'demo', 1, '2022-06-03 10:18:10', 'nOxm27uuGt', '2021-06-03 11:56:30'),
	(2, '2021-06-03 10:48:18', '2021-06-03 10:48:19', 'helpcenter@staff.it', 'demo', 'hc', 'staff', 3, '2022-06-03 10:48:45', 'nOxm27uuGf', '2022-06-03 10:49:21'),
	(3, '2021-06-03 10:49:46', '2021-06-03 10:49:48', 'validate@staff.it', 'demo', 'validate', 'staff', 2, '2022-06-03 10:50:02', 'nOxm27uuGr', '2022-06-03 10:50:08'),
	(4, NULL, NULL, 'poi@poi.it', 'domani', 'poi', 'domani', 2, '2021-06-14 12:36:58', NULL, NULL),
	(5, '2021-06-08 15:03:27', '2021-06-08 15:03:27', 'koi@it.it', 'koi', 'carpa', 'koi', 2, NULL, 'TGPp9PHC91', '2021-06-08 15:18:26'),
	(6, NULL, NULL, 'diem@diem.it', 'diem', 'carpe', 'diem', 3, '2021-06-14 10:21:25', NULL, NULL),
	(7, '2021-06-08 15:07:58', '2021-06-08 15:07:58', 'generics@demo.it', 'demo', 'generics', 'generics', 3, NULL, 'FpANat6WfP', '2021-06-08 15:22:57'),
	(8, '2021-06-08 15:12:01', '2021-06-08 15:12:01', 'io', 'io', 'io', 'io', 1, NULL, 'UHXq4rHsUB', '2021-06-08 15:27:00'),
	(9, '2021-06-08 15:13:39', '2021-06-08 15:13:39', 'iu', 'iu', 'iu', 'iu', 1, NULL, 'viQ8YbSpCi', '2021-06-08 15:28:38'),
	(10, '2021-06-08 15:14:11', '2021-06-08 15:14:11', 'ij', 'ij', 'ij', 'ij', 1, NULL, '1bFKEPZeNL', '2021-06-08 15:29:10');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `transaction_status` (
  `id` int(11) NOT NULL,
  `title` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `transaction_status` DISABLE KEYS */;
INSERT IGNORE INTO `transaction_status` (`id`, `title`) VALUES
	(1, 'in verifica'),
	(2, 'verifica terminata con errore'),
	(3, 'verifica terminata con successo');
/*!40000 ALTER TABLE `transaction_status` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `transaction_unique_id` (
  `transaction_id` varchar(255) NOT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `transaction_unique_id` DISABLE KEYS */;
INSERT IGNORE INTO `transaction_unique_id` (`transaction_id`) VALUES
	('212121212121221'),
	('645946516354654'),
	('ABABABABABCDCDCDCDCDABABABABAB141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141455555'),
	('RRRREEEEEEEEEEEEEEEEEEEEEEEEEEEEEE');
/*!40000 ALTER TABLE `transaction_unique_id` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
