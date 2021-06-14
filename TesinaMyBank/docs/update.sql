-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              8.0.23 - MySQL Community Server - GPL
-- S.O. server:                  Win64
-- HeidiSQL Versione:            11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dump della struttura del database tesina_mybank
CREATE DATABASE IF NOT EXISTS `tesina_mybank` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tesina_mybank`;

-- Dump della struttura di tabella tesina_mybank.account
CREATE TABLE IF NOT EXISTS `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `iban` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `balance` decimal(11,2) DEFAULT '0.00',
  `email` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `surname` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `next_otp_code_after_date` datetime DEFAULT NULL,
  `otp_code` varchar(10) DEFAULT NULL,
  `otp_code_expires_at` datetime DEFAULT NULL,
  `credit_card_no` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `credit_card_cin` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `credit_card_expires_at` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `otp_code` (`otp_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.account: ~2 rows (circa)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `created_at`, `updated_at`, `iban`, `balance`, `email`, `password`, `name`, `surname`, `next_otp_code_after_date`, `otp_code`, `otp_code_expires_at`, `credit_card_no`, `credit_card_cin`, `credit_card_expires_at`) VALUES
	(1, '2021-06-03 10:38:10', '2021-06-03 10:38:10', 'IT59X1904300000981526166172', 1000.00, 'aaaaa', 'aaaaa', 'aaaa', 'aaaa', NULL, 'kEoG9BmAH5', '2021-06-03 10:53:09', NULL, NULL, NULL),
	(2, '2021-06-03 12:10:50', '2021-06-14 12:00:44', 'IT90X1904300000767913079700', 1000.00, 'luca.btt97@gmail.com', 'ciaociao99', 'aaaaaaa', 'aaaaaaaaaa', NULL, 'MBzSDqKpCE', '2021-06-14 12:03:44', NULL, NULL, NULL),
	(3, '2021-06-03 15:13:54', '2021-06-03 15:13:54', 'IT26X1904300000635557964945', 1000.00, 'q', 'qqqq', 'qqq', 'qq', NULL, 'fHwiUzcmNs', '2021-06-03 15:16:54', '7340150867123304', '965', '6/2026');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.external_transaction
CREATE TABLE IF NOT EXISTS `external_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `custom_code` varchar(255) NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `amount` decimal(11,2) NOT NULL DEFAULT '0.00',
  `to_account_id` int NOT NULL,
  `transaction_status_id` int NOT NULL,
  `transaction_error_reason` varchar(255) DEFAULT NULL,
  `verify_assigned_to` int NOT NULL,
  `customer_name` varchar(30) NOT NULL,
  `customer_surname` varchar(40) NOT NULL,
  `customer_credit_card_no` varchar(40) NOT NULL,
  `customer_credit_card_cin` varchar(5) NOT NULL,
  `customer_credit_card_expires_at` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `transaction_id` (`transaction_id`),
  UNIQUE KEY `to_account_id` (`to_account_id`,`custom_code`),
  KEY `transaction_status_id` (`transaction_status_id`),
  KEY `verify_assigned_to` (`verify_assigned_to`),
  CONSTRAINT `external_transaction_ibfk_1` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `external_transaction_ibfk_2` FOREIGN KEY (`transaction_status_id`) REFERENCES `transaction_status` (`id`),
  CONSTRAINT `external_transaction_ibfk_3` FOREIGN KEY (`verify_assigned_to`) REFERENCES `staff` (`id`),
  CONSTRAINT `external_transaction_ibfk_4` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_unique_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.external_transaction: ~0 rows (circa)
/*!40000 ALTER TABLE `external_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `external_transaction` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.faq
CREATE TABLE IF NOT EXISTS `faq` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) NOT NULL,
  `answer` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.faq: ~0 rows (circa)
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.help_center
CREATE TABLE IF NOT EXISTS `help_center` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `closed_at` datetime DEFAULT NULL,
  `from_account_id` int NOT NULL,
  `question` varchar(255) NOT NULL,
  `assigned_to_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `from_account_id` (`from_account_id`),
  KEY `assigned_to_id` (`assigned_to_id`),
  CONSTRAINT `help_center_ibfk_1` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `help_center_ibfk_2` FOREIGN KEY (`assigned_to_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.help_center: ~0 rows (circa)
/*!40000 ALTER TABLE `help_center` DISABLE KEYS */;
INSERT INTO `help_center` (`id`, `created_at`, `updated_at`, `closed_at`, `from_account_id`, `question`, `assigned_to_id`) VALUES
	(1, '2021-06-14 12:01:04', '2021-06-14 12:01:04', NULL, 2, 'MBzSDqKpCEMBzSDqKpCE', 3);
/*!40000 ALTER TABLE `help_center` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.help_center_thread
CREATE TABLE IF NOT EXISTS `help_center_thread` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `help_center_id` int NOT NULL,
  `question` varchar(255) NOT NULL,
  `answer` text,
  PRIMARY KEY (`id`),
  KEY `help_center_id` (`help_center_id`),
  CONSTRAINT `help_center_thread_ibfk_1` FOREIGN KEY (`help_center_id`) REFERENCES `help_center` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.help_center_thread: ~0 rows (circa)
/*!40000 ALTER TABLE `help_center_thread` DISABLE KEYS */;
/*!40000 ALTER TABLE `help_center_thread` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.internal_transaction
CREATE TABLE IF NOT EXISTS `internal_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `custom_code` varchar(255) NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `amount` decimal(11,2) NOT NULL DEFAULT '0.00',
  `from_account_id` int NOT NULL,
  `to_account_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `transaction_id` (`transaction_id`),
  UNIQUE KEY `to_account_id` (`to_account_id`,`custom_code`),
  KEY `from_account_id` (`from_account_id`),
  CONSTRAINT `internal_transaction_ibfk_1` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `internal_transaction_ibfk_2` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `internal_transaction_ibfk_3` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_unique_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.internal_transaction: ~0 rows (circa)
/*!40000 ALTER TABLE `internal_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `internal_transaction` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `id` int NOT NULL,
  `amount` double NOT NULL DEFAULT '0',
  `email` varchar(255) NOT NULL DEFAULT '0',
  `custom_code` varchar(255) NOT NULL DEFAULT '0',
  `transaction_id` varchar(255) NOT NULL DEFAULT '0',
  `url` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.payment: ~0 rows (circa)
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL,
  `title` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.role: ~2 rows (circa)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `title`) VALUES
	(1, 'Amministratore'),
	(2, 'Verificatore'),
	(3, 'Help Desk');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.staff
CREATE TABLE IF NOT EXISTS `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `email` varchar(70) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `role_id` int DEFAULT NULL,
  `next_otp_code_after_date` datetime DEFAULT NULL,
  `otp_code` varchar(10) DEFAULT NULL,
  `otp_code_expires_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `otp_code` (`otp_code`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.staff: ~1 rows (circa)
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`id`, `created_at`, `updated_at`, `email`, `password`, `name`, `surname`, `role_id`, `next_otp_code_after_date`, `otp_code`, `otp_code_expires_at`) VALUES
	(1, '2021-06-01 12:57:51', '2021-06-03 10:32:47', 'luca.btt97@gmail.com', 'll', 'luca', 'battaglia', 1, '2021-06-01 12:58:05', 'AAYOLR13UH', '2021-06-03 10:47:46'),
	(2, '2021-06-03 16:02:02', '2021-06-03 16:02:02', 's', 's', 's', 's', 2, NULL, 'rFKBC0gw4E', '2021-06-03 16:17:01'),
	(3, '2021-06-14 11:58:06', '2021-06-14 11:58:06', 'sdsdvsdccdscsdc', 'dcsdcd', 'cwcdscs', 'cdscsc', 3, '2021-06-14 11:58:20', 'scdvasdvsd', NULL);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.transaction_status
CREATE TABLE IF NOT EXISTS `transaction_status` (
  `id` int NOT NULL,
  `title` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.transaction_status: ~2 rows (circa)
/*!40000 ALTER TABLE `transaction_status` DISABLE KEYS */;
INSERT INTO `transaction_status` (`id`, `title`) VALUES
	(1, 'in verifica'),
	(2, 'verifica terminata con errore'),
	(3, 'verifica terminata con successo');
/*!40000 ALTER TABLE `transaction_status` ENABLE KEYS */;

-- Dump della struttura di tabella tesina_mybank.transaction_unique_id
CREATE TABLE IF NOT EXISTS `transaction_unique_id` (
  `transaction_id` varchar(255) NOT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella tesina_mybank.transaction_unique_id: ~0 rows (circa)
/*!40000 ALTER TABLE `transaction_unique_id` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_unique_id` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
