-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: nrf
-- ------------------------------------------------------
-- Server version	5.5.35-0ubuntu0.12.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AtcCode`
--

DROP TABLE IF EXISTS `AtcCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AtcCode` (
  `code` varchar(12) NOT NULL,
  `ddd` varchar(200) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AtcCode`
--

LOCK TABLES `AtcCode` WRITE;
/*!40000 ALTER TABLE `AtcCode` DISABLE KEYS */;
/*!40000 ALTER TABLE `AtcCode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AttributeCategory`
--

DROP TABLE IF EXISTS `AttributeCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AttributeCategory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(200) DEFAULT NULL,
  `parenttype` varchar(16) DEFAULT NULL,
  `sequencenumber` int(11) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AttributeCategory`
--

LOCK TABLES `AttributeCategory` WRITE;
/*!40000 ALTER TABLE `AttributeCategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `AttributeCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AttributeCategory_AUD`
--

DROP TABLE IF EXISTS `AttributeCategory_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AttributeCategory_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `label` varchar(200) DEFAULT NULL,
  `parenttype` varchar(16) DEFAULT NULL,
  `sequencenumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKF889806BE9152785` (`REV`),
  CONSTRAINT `FKF889806BE9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AttributeCategory_AUD`
--

LOCK TABLES `AttributeCategory_AUD` WRITE;
/*!40000 ALTER TABLE `AttributeCategory_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `AttributeCategory_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AttributeType`
--

DROP TABLE IF EXISTS `AttributeType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AttributeType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attributeusage` varchar(32) DEFAULT NULL,
  `datatype` varchar(32) NOT NULL,
  `defaultvalue` varchar(100) DEFAULT NULL,
  `enumitems` varchar(800) DEFAULT NULL,
  `groupname` varchar(80) NOT NULL,
  `label` varchar(128) NOT NULL,
  `maximumlength` int(11) DEFAULT NULL,
  `maximumvalue` double DEFAULT NULL,
  `minimumlength` int(11) DEFAULT NULL,
  `minimumvalue` double DEFAULT NULL,
  `required` tinyint(1) NOT NULL,
  `sequencenumber` int(11) NOT NULL,
  `shortcut` varchar(128) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD9402FD676B5471E` (`category_id`),
  CONSTRAINT `FKD9402FD676B5471E` FOREIGN KEY (`category_id`) REFERENCES `AttributeCategory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AttributeType`
--

LOCK TABLES `AttributeType` WRITE;
/*!40000 ALTER TABLE `AttributeType` DISABLE KEYS */;
/*!40000 ALTER TABLE `AttributeType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AttributeType_AUD`
--

DROP TABLE IF EXISTS `AttributeType_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AttributeType_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `attributeusage` varchar(32) DEFAULT NULL,
  `datatype` varchar(32) DEFAULT NULL,
  `defaultvalue` varchar(100) DEFAULT NULL,
  `enumitems` varchar(800) DEFAULT NULL,
  `groupname` varchar(80) DEFAULT NULL,
  `label` varchar(128) DEFAULT NULL,
  `maximumlength` int(11) DEFAULT NULL,
  `maximumvalue` double DEFAULT NULL,
  `minimumlength` int(11) DEFAULT NULL,
  `minimumvalue` double DEFAULT NULL,
  `required` tinyint(1) DEFAULT NULL,
  `sequencenumber` int(11) DEFAULT NULL,
  `shortcut` varchar(128) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKDB8482A7E9152785` (`REV`),
  CONSTRAINT `FKDB8482A7E9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AttributeType_AUD`
--

LOCK TABLES `AttributeType_AUD` WRITE;
/*!40000 ALTER TABLE `AttributeType_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `AttributeType_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Diagnosis`
--

DROP TABLE IF EXISTS `Diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Diagnosis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(80) DEFAULT NULL,
  `codingsystem` varchar(30) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `exclusion` tinyint(1) NOT NULL,
  `label` varchar(200) NOT NULL,
  `validfrom_date` date DEFAULT NULL,
  `validfrom_precision` int(11) DEFAULT NULL,
  `validuntil_date` date DEFAULT NULL,
  `validuntil_precision` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBECACAD1B11E28B2` (`subject_id`),
  CONSTRAINT `FKBECACAD1B11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Diagnosis`
--

LOCK TABLES `Diagnosis` WRITE;
/*!40000 ALTER TABLE `Diagnosis` DISABLE KEYS */;
/*!40000 ALTER TABLE `Diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DiagnosisCode`
--

DROP TABLE IF EXISTS `DiagnosisCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DiagnosisCode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `codingsystem` varchar(30) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `label` varchar(200) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DiagnosisCode`
--

LOCK TABLES `DiagnosisCode` WRITE;
/*!40000 ALTER TABLE `DiagnosisCode` DISABLE KEYS */;
/*!40000 ALTER TABLE `DiagnosisCode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Diagnosis_AUD`
--

DROP TABLE IF EXISTS `Diagnosis_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Diagnosis_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `code` varchar(80) DEFAULT NULL,
  `codingsystem` varchar(30) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `exclusion` tinyint(1) DEFAULT NULL,
  `label` varchar(200) DEFAULT NULL,
  `validfrom_date` date DEFAULT NULL,
  `validfrom_precision` int(11) DEFAULT NULL,
  `validuntil_date` date DEFAULT NULL,
  `validuntil_precision` int(11) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK74032822E9152785` (`REV`),
  CONSTRAINT `FK74032822E9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Diagnosis_AUD`
--

LOCK TABLES `Diagnosis_AUD` WRITE;
/*!40000 ALTER TABLE `Diagnosis_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Diagnosis_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Encounter`
--

DROP TABLE IF EXISTS `Encounter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Encounter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `encounterdate` date NOT NULL,
  `label` varchar(400) DEFAULT NULL,
  `notes` varchar(2000) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD24A8913B11E28B2` (`subject_id`),
  CONSTRAINT `FKD24A8913B11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Encounter`
--

LOCK TABLES `Encounter` WRITE;
/*!40000 ALTER TABLE `Encounter` DISABLE KEYS */;
/*!40000 ALTER TABLE `Encounter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EncounterAttribute`
--

DROP TABLE IF EXISTS `EncounterAttribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EncounterAttribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attributevalue` longtext,
  `version` int(11) DEFAULT NULL,
  `attributetype_id` bigint(20) NOT NULL,
  `encounter_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK82CC6C987906D82` (`attributetype_id`),
  KEY `FK82CC6C99968C219` (`encounter_id`),
  CONSTRAINT `FK82CC6C99968C219` FOREIGN KEY (`encounter_id`) REFERENCES `Encounter` (`id`),
  CONSTRAINT `FK82CC6C987906D82` FOREIGN KEY (`attributetype_id`) REFERENCES `AttributeType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EncounterAttribute`
--

LOCK TABLES `EncounterAttribute` WRITE;
/*!40000 ALTER TABLE `EncounterAttribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `EncounterAttribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EncounterAttribute_AUD`
--

DROP TABLE IF EXISTS `EncounterAttribute_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EncounterAttribute_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `encounter_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK396681AE9152785` (`REV`),
  CONSTRAINT `FK396681AE9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EncounterAttribute_AUD`
--

LOCK TABLES `EncounterAttribute_AUD` WRITE;
/*!40000 ALTER TABLE `EncounterAttribute_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `EncounterAttribute_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EncounterCategory`
--

DROP TABLE IF EXISTS `EncounterCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EncounterCategory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `category_id` bigint(20) NOT NULL,
  `encounter_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8FA2E73176B5471E` (`category_id`),
  KEY `FK8FA2E7319968C219` (`encounter_id`),
  CONSTRAINT `FK8FA2E7319968C219` FOREIGN KEY (`encounter_id`) REFERENCES `Encounter` (`id`),
  CONSTRAINT `FK8FA2E73176B5471E` FOREIGN KEY (`category_id`) REFERENCES `AttributeCategory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EncounterCategory`
--

LOCK TABLES `EncounterCategory` WRITE;
/*!40000 ALTER TABLE `EncounterCategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `EncounterCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EncounterCategory_AUD`
--

DROP TABLE IF EXISTS `EncounterCategory_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EncounterCategory_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `encounter_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKA8B61482E9152785` (`REV`),
  CONSTRAINT `FKA8B61482E9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EncounterCategory_AUD`
--

LOCK TABLES `EncounterCategory_AUD` WRITE;
/*!40000 ALTER TABLE `EncounterCategory_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `EncounterCategory_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Encounter_AUD`
--

DROP TABLE IF EXISTS `Encounter_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Encounter_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `encounterdate` date DEFAULT NULL,
  `label` varchar(400) DEFAULT NULL,
  `notes` varchar(2000) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK2915F564E9152785` (`REV`),
  CONSTRAINT `FK2915F564E9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Encounter_AUD`
--

LOCK TABLES `Encounter_AUD` WRITE;
/*!40000 ALTER TABLE `Encounter_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Encounter_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Event`
--

DROP TABLE IF EXISTS `Event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `eventenddate_date` date DEFAULT NULL,
  `eventenddate_precision` int(11) DEFAULT NULL,
  `eventstartdate_date` date DEFAULT NULL,
  `eventstartdate_precision` int(11) DEFAULT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `subject_id` bigint(20) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK403827AB11E28B2` (`subject_id`),
  KEY `FK403827A54CC2C1A` (`type_id`),
  CONSTRAINT `FK403827A54CC2C1A` FOREIGN KEY (`type_id`) REFERENCES `EventType` (`id`),
  CONSTRAINT `FK403827AB11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Event`
--

LOCK TABLES `Event` WRITE;
/*!40000 ALTER TABLE `Event` DISABLE KEYS */;
/*!40000 ALTER TABLE `Event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EventType`
--

DROP TABLE IF EXISTS `EventType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EventType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `label` varchar(200) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EventType`
--

LOCK TABLES `EventType` WRITE;
/*!40000 ALTER TABLE `EventType` DISABLE KEYS */;
/*!40000 ALTER TABLE `EventType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EventType_AUD`
--

DROP TABLE IF EXISTS `EventType_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EventType_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `label` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK958D5CA5E9152785` (`REV`),
  CONSTRAINT `FK958D5CA5E9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EventType_AUD`
--

LOCK TABLES `EventType_AUD` WRITE;
/*!40000 ALTER TABLE `EventType_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `EventType_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Event_AUD`
--

DROP TABLE IF EXISTS `Event_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Event_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `eventenddate_date` date DEFAULT NULL,
  `eventenddate_precision` int(11) DEFAULT NULL,
  `eventstartdate_date` date DEFAULT NULL,
  `eventstartdate_precision` int(11) DEFAULT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK7955E34BE9152785` (`REV`),
  CONSTRAINT `FK7955E34BE9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Event_AUD`
--

LOCK TABLES `Event_AUD` WRITE;
/*!40000 ALTER TABLE `Event_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Event_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Icd10Code`
--

DROP TABLE IF EXISTS `Icd10Code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Icd10Code` (
  `code` varchar(12) NOT NULL,
  `name` varchar(200) NOT NULL,
  `trimmedcode` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Icd10Code`
--

LOCK TABLES `Icd10Code` WRITE;
/*!40000 ALTER TABLE `Icd10Code` DISABLE KEYS */;
/*!40000 ALTER TABLE `Icd10Code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Medicament`
--

DROP TABLE IF EXISTS `Medicament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Medicament` (
  `mid` int(11) NOT NULL,
  `atccode` varchar(12) DEFAULT NULL,
  `offerer` varchar(200) DEFAULT NULL,
  `pharmaceuticalform` varchar(20) DEFAULT NULL,
  `tradename` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Medicament`
--

LOCK TABLES `Medicament` WRITE;
/*!40000 ALTER TABLE `Medicament` DISABLE KEYS */;
/*!40000 ALTER TABLE `Medicament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Medication`
--

DROP TABLE IF EXISTS `Medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Medication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `atccode` varchar(12) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `label` varchar(200) NOT NULL,
  `mid` int(11) NOT NULL,
  `pharmaceuticalform` varchar(20) DEFAULT NULL,
  `validfrom_date` date DEFAULT NULL,
  `validfrom_precision` int(11) DEFAULT NULL,
  `validuntil_date` date DEFAULT NULL,
  `validuntil_precision` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEDF7A6EFB11E28B2` (`subject_id`),
  CONSTRAINT `FKEDF7A6EFB11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Medication`
--

LOCK TABLES `Medication` WRITE;
/*!40000 ALTER TABLE `Medication` DISABLE KEYS */;
/*!40000 ALTER TABLE `Medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Medication_AUD`
--

DROP TABLE IF EXISTS `Medication_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Medication_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `atccode` varchar(12) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `label` varchar(200) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  `pharmaceuticalform` varchar(20) DEFAULT NULL,
  `validfrom_date` date DEFAULT NULL,
  `validfrom_precision` int(11) DEFAULT NULL,
  `validuntil_date` date DEFAULT NULL,
  `validuntil_precision` int(11) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK4B08C540E9152785` (`REV`),
  CONSTRAINT `FK4B08C540E9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Medication_AUD`
--

LOCK TABLES `Medication_AUD` WRITE;
/*!40000 ALTER TABLE `Medication_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Medication_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PharmaceuticalForm`
--

DROP TABLE IF EXISTS `PharmaceuticalForm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PharmaceuticalForm` (
  `shortcut` varchar(40) NOT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`shortcut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PharmaceuticalForm`
--

LOCK TABLES `PharmaceuticalForm` WRITE;
/*!40000 ALTER TABLE `PharmaceuticalForm` DISABLE KEYS */;
/*!40000 ALTER TABLE `PharmaceuticalForm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Relation`
--

DROP TABLE IF EXISTS `Relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `father_id` bigint(20) DEFAULT NULL,
  `mother_id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `subject_id` (`subject_id`),
  KEY `FKE2CE5E1CB11E28B2` (`subject_id`),
  KEY `FKE2CE5E1CAEA447BB` (`mother_id`),
  KEY `FKE2CE5E1CF274CA82` (`father_id`),
  CONSTRAINT `FKE2CE5E1CF274CA82` FOREIGN KEY (`father_id`) REFERENCES `Subject` (`id`),
  CONSTRAINT `FKE2CE5E1CAEA447BB` FOREIGN KEY (`mother_id`) REFERENCES `Subject` (`id`),
  CONSTRAINT `FKE2CE5E1CB11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Relation`
--

LOCK TABLES `Relation` WRITE;
/*!40000 ALTER TABLE `Relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Relation_AUD`
--

DROP TABLE IF EXISTS `Relation_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Relation_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `father_id` bigint(20) DEFAULT NULL,
  `mother_id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKFA261DEDE9152785` (`REV`),
  CONSTRAINT `FKFA261DEDE9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Relation_AUD`
--

LOCK TABLES `Relation_AUD` WRITE;
/*!40000 ALTER TABLE `Relation_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Relation_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Result`
--

DROP TABLE IF EXISTS `Result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `classification` varchar(32) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `resultdatetime` datetime DEFAULT NULL,
  `title` varchar(400) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK91B2B43DB11E28B2` (`subject_id`),
  CONSTRAINT `FK91B2B43DB11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Result`
--

LOCK TABLES `Result` WRITE;
/*!40000 ALTER TABLE `Result` DISABLE KEYS */;
/*!40000 ALTER TABLE `Result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ResultItem`
--

DROP TABLE IF EXISTS `ResultItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ResultItem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itemdatetime` datetime DEFAULT NULL,
  `itemtype` varchar(255) DEFAULT NULL,
  `label` varchar(200) NOT NULL,
  `numericvalue` double DEFAULT NULL,
  `rawtype` varchar(200) DEFAULT NULL,
  `stringvalue` varchar(800) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `result_id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK54B73CF0B11E28B2` (`subject_id`),
  KEY `FK54B73CF04673D385` (`result_id`),
  CONSTRAINT `FK54B73CF04673D385` FOREIGN KEY (`result_id`) REFERENCES `Result` (`id`),
  CONSTRAINT `FK54B73CF0B11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ResultItem`
--

LOCK TABLES `ResultItem` WRITE;
/*!40000 ALTER TABLE `ResultItem` DISABLE KEYS */;
/*!40000 ALTER TABLE `ResultItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ResultItem_AUD`
--

DROP TABLE IF EXISTS `ResultItem_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ResultItem_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `itemdatetime` datetime DEFAULT NULL,
  `itemtype` varchar(255) DEFAULT NULL,
  `label` varchar(200) DEFAULT NULL,
  `numericvalue` double DEFAULT NULL,
  `rawtype` varchar(200) DEFAULT NULL,
  `stringvalue` varchar(800) DEFAULT NULL,
  `result_id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK7C1B72C1E9152785` (`REV`),
  CONSTRAINT `FK7C1B72C1E9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ResultItem_AUD`
--

LOCK TABLES `ResultItem_AUD` WRITE;
/*!40000 ALTER TABLE `ResultItem_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `ResultItem_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Result_AUD`
--

DROP TABLE IF EXISTS `Result_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Result_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `classification` varchar(32) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `resultdatetime` datetime DEFAULT NULL,
  `title` varchar(400) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK54C07B8EE9152785` (`REV`),
  CONSTRAINT `FK54C07B8EE9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Result_AUD`
--

LOCK TABLES `Result_AUD` WRITE;
/*!40000 ALTER TABLE `Result_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Result_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RevisionInfo`
--

DROP TABLE IF EXISTS `RevisionInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RevisionInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` bigint(20) NOT NULL,
  `username` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RevisionInfo`
--

LOCK TABLES `RevisionInfo` WRITE;
/*!40000 ALTER TABLE `RevisionInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `RevisionInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sample`
--

DROP TABLE IF EXISTS `Sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sample` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `concentration` double DEFAULT NULL,
  `opticaldensity` double DEFAULT NULL,
  `sampledate_date` date DEFAULT NULL,
  `sampledate_precision` int(11) DEFAULT NULL,
  `sampleid` varchar(40) NOT NULL,
  `sampletype` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK932C61CAB11E28B2` (`subject_id`),
  CONSTRAINT `FK932C61CAB11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sample`
--

LOCK TABLES `Sample` WRITE;
/*!40000 ALTER TABLE `Sample` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sample_AUD`
--

DROP TABLE IF EXISTS `Sample_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sample_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `concentration` double DEFAULT NULL,
  `opticaldensity` double DEFAULT NULL,
  `sampledate_date` date DEFAULT NULL,
  `sampledate_precision` int(11) DEFAULT NULL,
  `sampleid` varchar(40) DEFAULT NULL,
  `sampletype` varchar(255) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK7F5E9A9BE9152785` (`REV`),
  CONSTRAINT `FK7F5E9A9BE9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sample_AUD`
--

LOCK TABLES `Sample_AUD` WRITE;
/*!40000 ALTER TABLE `Sample_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sample_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Subject`
--

DROP TABLE IF EXISTS `Subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthname` varchar(64) DEFAULT NULL,
  `birthdate` datetime NOT NULL,
  `dateofdeath_date` date DEFAULT NULL,
  `dateofdeath_precision` int(11) DEFAULT NULL,
  `death` tinyint(1) NOT NULL,
  `firstname` varchar(128) NOT NULL,
  `gender` varchar(16) NOT NULL,
  `lastname` varchar(128) NOT NULL,
  `patientid` varchar(32) NOT NULL,
  `pseudonym` varchar(8) NOT NULL,
  `title` varchar(64) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Subject`
--

LOCK TABLES `Subject` WRITE;
/*!40000 ALTER TABLE `Subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `Subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubjectAttribute`
--

DROP TABLE IF EXISTS `SubjectAttribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubjectAttribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attributevalue` longtext,
  `version` int(11) DEFAULT NULL,
  `attributetype_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33C2CAF087906D82` (`attributetype_id`),
  KEY `FK33C2CAF0B11E28B2` (`subject_id`),
  CONSTRAINT `FK33C2CAF0B11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`),
  CONSTRAINT `FK33C2CAF087906D82` FOREIGN KEY (`attributetype_id`) REFERENCES `AttributeType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubjectAttribute`
--

LOCK TABLES `SubjectAttribute` WRITE;
/*!40000 ALTER TABLE `SubjectAttribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `SubjectAttribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubjectAttribute_AUD`
--

DROP TABLE IF EXISTS `SubjectAttribute_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubjectAttribute_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKAEB000C1E9152785` (`REV`),
  CONSTRAINT `FKAEB000C1E9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubjectAttribute_AUD`
--

LOCK TABLES `SubjectAttribute_AUD` WRITE;
/*!40000 ALTER TABLE `SubjectAttribute_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `SubjectAttribute_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubjectCategory`
--

DROP TABLE IF EXISTS `SubjectCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubjectCategory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `category_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK46B8422AB11E28B2` (`subject_id`),
  KEY `FK46B8422A76B5471E` (`category_id`),
  CONSTRAINT `FK46B8422A76B5471E` FOREIGN KEY (`category_id`) REFERENCES `AttributeCategory` (`id`),
  CONSTRAINT `FK46B8422AB11E28B2` FOREIGN KEY (`subject_id`) REFERENCES `Subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubjectCategory`
--

LOCK TABLES `SubjectCategory` WRITE;
/*!40000 ALTER TABLE `SubjectCategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `SubjectCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubjectCategory_AUD`
--

DROP TABLE IF EXISTS `SubjectCategory_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubjectCategory_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKCF434AFBE9152785` (`REV`),
  CONSTRAINT `FKCF434AFBE9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubjectCategory_AUD`
--

LOCK TABLES `SubjectCategory_AUD` WRITE;
/*!40000 ALTER TABLE `SubjectCategory_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `SubjectCategory_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Subject_AUD`
--

DROP TABLE IF EXISTS `Subject_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Subject_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `birthname` varchar(64) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `dateofdeath_date` date DEFAULT NULL,
  `dateofdeath_precision` int(11) DEFAULT NULL,
  `death` tinyint(1) DEFAULT NULL,
  `firstname` varchar(128) DEFAULT NULL,
  `gender` varchar(16) DEFAULT NULL,
  `lastname` varchar(128) DEFAULT NULL,
  `patientid` varchar(32) DEFAULT NULL,
  `pseudonym` varchar(8) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK3F79B4DDE9152785` (`REV`),
  CONSTRAINT `FK3F79B4DDE9152785` FOREIGN KEY (`REV`) REFERENCES `RevisionInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Subject_AUD`
--

LOCK TABLES `Subject_AUD` WRITE;
/*!40000 ALTER TABLE `Subject_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Subject_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserInfo`
--

DROP TABLE IF EXISTS `UserInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserInfo` (
  `username` varchar(32) NOT NULL,
  `email` varchar(80) NOT NULL,
  `firstname` varchar(80) NOT NULL,
  `lastname` varchar(80) NOT NULL,
  `password` varchar(32) NOT NULL,
  `title` varchar(40) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserInfo`
--

LOCK TABLES `UserInfo` WRITE;
/*!40000 ALTER TABLE `UserInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserRole`
--

DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRole` (
  `username` varchar(32) NOT NULL,
  `role` varchar(40) NOT NULL,
  KEY `FKF3F76701BF3A42AC` (`username`),
  CONSTRAINT `FKF3F76701BF3A42AC` FOREIGN KEY (`username`) REFERENCES `UserInfo` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserRole`
--

LOCK TABLES `UserRole` WRITE;
/*!40000 ALTER TABLE `UserRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserRole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-05 15:13:13
