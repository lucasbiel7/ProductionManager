-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: production_manager
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Current Database: `production_manager`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `production_manager` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `production_manager`;

--
-- Table structure for table `tb_atividade`
--

DROP TABLE IF EXISTS `tb_atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_atividade` (
  `ID_ATIVIDADE` varchar(255) NOT NULL,
  `VL_DETALHADA` double DEFAULT NULL,
  `VL_ESTIMADA` double DEFAULT NULL,
  `TX_DESCRICAO` varchar(255) DEFAULT NULL,
  `FL_FATURAMENTO` varchar(2) DEFAULT NULL,
  `FL_MES` varchar(255) DEFAULT NULL,
  `DT_PREVISAO_INICIO` date DEFAULT NULL,
  `FL_SITUACAO_ATIVIDADE` varchar(255) DEFAULT NULL,
  `ID_ORDEM_SERVICO` varchar(255) NOT NULL,
  `ID_PACOTE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_ATIVIDADE`),
  KEY `FK_eca1eu91tcj8cawm0f9elh5xr` (`ID_ORDEM_SERVICO`),
  KEY `FK_tr44tl7q9xlrmmwavlgrly0h3` (`ID_PACOTE`),
  CONSTRAINT `FK_eca1eu91tcj8cawm0f9elh5xr` FOREIGN KEY (`ID_ORDEM_SERVICO`) REFERENCES `tb_ordem_servico` (`ID_ORDEM_SERVICO`),
  CONSTRAINT `FK_tr44tl7q9xlrmmwavlgrly0h3` FOREIGN KEY (`ID_PACOTE`) REFERENCES `tb_pacote` (`ID_PACOTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_atividade_artefatos`
--

DROP TABLE IF EXISTS `tb_atividade_artefatos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_atividade_artefatos` (
  `TP_ARTEFATO` varchar(255) NOT NULL,
  `ID_ATIVIDADE` varchar(255) NOT NULL,
  PRIMARY KEY (`TP_ARTEFATO`,`ID_ATIVIDADE`),
  KEY `FK_s620nb0j1p7rdf8kndtg8339h` (`ID_ATIVIDADE`),
  CONSTRAINT `FK_s620nb0j1p7rdf8kndtg8339h` FOREIGN KEY (`ID_ATIVIDADE`) REFERENCES `tb_atividade` (`ID_ATIVIDADE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_atuacao`
--

DROP TABLE IF EXISTS `tb_atuacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_atuacao` (
  `ID_ATUACAO` varchar(255) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ATUACAO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_atuando`
--

DROP TABLE IF EXISTS `tb_atuando`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_atuando` (
  `ID_ATUACAO` varchar(255) NOT NULL,
  `ID_USUARIO` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_ATUACAO`,`ID_USUARIO`),
  KEY `FK_8alickkuvxmnt0j5w9yvf8f90` (`ID_USUARIO`),
  CONSTRAINT `FK_50572c6a1bhvs02m181qbfv4g` FOREIGN KEY (`ID_ATUACAO`) REFERENCES `tb_atuacao` (`ID_ATUACAO`),
  CONSTRAINT `FK_8alickkuvxmnt0j5w9yvf8f90` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_usuario` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_modificacao_atividade`
--

DROP TABLE IF EXISTS `tb_modificacao_atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_modificacao_atividade` (
  `DT_MODIFICACAO` datetime DEFAULT NULL,
  `TX_DESCRICAO_MODIFICACAO` longtext,
  `TP_ATIVIDADE` varchar(255) NOT NULL,
  `ID_ATIVIDADE` varchar(255) NOT NULL,
  `ID_ATIVIDADE_PRINCIPAL` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_ATIVIDADE`),
  KEY `FK_4tv795rgsbqlvir70yyd324bh` (`ID_ATIVIDADE_PRINCIPAL`),
  CONSTRAINT `FK_4tv795rgsbqlvir70yyd324bh` FOREIGN KEY (`ID_ATIVIDADE_PRINCIPAL`) REFERENCES `tb_atividade` (`ID_ATIVIDADE`),
  CONSTRAINT `FK_8p6lb2lya7q2nwmoc8ei3fnt0` FOREIGN KEY (`ID_ATIVIDADE`) REFERENCES `tb_atividade` (`ID_ATIVIDADE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_modulo`
--

DROP TABLE IF EXISTS `tb_modulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_modulo` (
  `ID_MODULO` varchar(255) NOT NULL,
  `TX_DESCRICAO` varchar(255) DEFAULT NULL,
  `ID_PROJETO` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_MODULO`),
  KEY `FK_sdoytqxrofo7hsdp0aa51kfgl` (`ID_PROJETO`),
  CONSTRAINT `FK_sdoytqxrofo7hsdp0aa51kfgl` FOREIGN KEY (`ID_PROJETO`) REFERENCES `tb_projeto` (`ID_PROJETO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_ordem_servico`
--

DROP TABLE IF EXISTS `tb_ordem_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ordem_servico` (
  `ID_ORDEM_SERVICO` varchar(255) NOT NULL,
  `TX_DESCRICAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ORDEM_SERVICO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_pacote`
--

DROP TABLE IF EXISTS `tb_pacote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pacote` (
  `ID_PACOTE` varchar(255) NOT NULL,
  `TX_DESCRICAO` varchar(255) DEFAULT NULL,
  `ID_MODULO` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_PACOTE`),
  KEY `FK_2r05hajdk93i17c8p9nty8m4v` (`ID_MODULO`),
  CONSTRAINT `FK_2r05hajdk93i17c8p9nty8m4v` FOREIGN KEY (`ID_MODULO`) REFERENCES `tb_modulo` (`ID_MODULO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_parametro`
--

DROP TABLE IF EXISTS `tb_parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_parametro` (
  `ID_PARAMETRO` varchar(255) NOT NULL,
  `DT_INCLUSAO` datetime DEFAULT NULL,
  `TP_PARAMETRO` int(11) DEFAULT NULL,
  `VL_PARAMETRO` double DEFAULT NULL,
  PRIMARY KEY (`ID_PARAMETRO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_perfil`
--

DROP TABLE IF EXISTS `tb_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_perfil` (
  `ID_PERFIL` varchar(255) NOT NULL,
  `TX_DESCRICAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PERFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_pessoa`
--

DROP TABLE IF EXISTS `tb_pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pessoa` (
  `ID_PESSOA` varchar(255) NOT NULL,
  `TX_CPF` varchar(255) DEFAULT NULL,
  `TX_EMAIL` varchar(255) DEFAULT NULL,
  `TX_NOME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PESSOA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_progresso_atividade`
--

DROP TABLE IF EXISTS `tb_progresso_atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_progresso_atividade` (
  `ID_PROGRESSO_ATIVIDADE` varchar(255) NOT NULL,
  `TM_PROGRESSO` datetime DEFAULT NULL,
  `TM_FIM` datetime DEFAULT NULL,
  `TM_INICIO` datetime DEFAULT NULL,
  `TP_FATURAMENTO` varchar(255) DEFAULT NULL,
  `VL_PROGRESSO` double DEFAULT NULL,
  `TP_ATIVIDADE` varchar(255) DEFAULT NULL,
  `ID_ATIVIDADE` varchar(255) DEFAULT NULL,
  `ID_PARAMETRO_CONTRATO` varchar(255) DEFAULT NULL,
  `ID_PARAMETRO_REPASSE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PROGRESSO_ATIVIDADE`),
  KEY `FK_ec3vn07vh2x6gpvur5utb53cc` (`ID_ATIVIDADE`),
  KEY `FK_4r6gi0aw02yrfglmh5ywuuuri` (`ID_PARAMETRO_CONTRATO`),
  KEY `FK_tivlh7em627oacok8rlyqcme6` (`ID_PARAMETRO_REPASSE`),
  CONSTRAINT `FK_4r6gi0aw02yrfglmh5ywuuuri` FOREIGN KEY (`ID_PARAMETRO_CONTRATO`) REFERENCES `tb_parametro` (`ID_PARAMETRO`),
  CONSTRAINT `FK_ec3vn07vh2x6gpvur5utb53cc` FOREIGN KEY (`ID_ATIVIDADE`) REFERENCES `tb_atividade` (`ID_ATIVIDADE`),
  CONSTRAINT `FK_tivlh7em627oacok8rlyqcme6` FOREIGN KEY (`ID_PARAMETRO_REPASSE`) REFERENCES `tb_parametro` (`ID_PARAMETRO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_projeto`
--

DROP TABLE IF EXISTS `tb_projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_projeto` (
  `ID_PROJETO` varchar(255) NOT NULL,
  `TX_DESCRICAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PROJETO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_usuario` (
  `ID_USUARIO` varchar(255) NOT NULL,
  `ST_ATIVO` bit(1) DEFAULT NULL,
  `TX_SENHA` varchar(255) DEFAULT NULL,
  `ID_PERFIL` varchar(255) NOT NULL,
  `ID_PESSOA` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  UNIQUE KEY `UK_duq9n0iyjgvl1h1id6ikminbl` (`ID_PESSOA`),
  KEY `FK_ilnnbpki9ti8t0dac618iwdj9` (`ID_PERFIL`),
  CONSTRAINT `FK_duq9n0iyjgvl1h1id6ikminbl` FOREIGN KEY (`ID_PESSOA`) REFERENCES `tb_pessoa` (`ID_PESSOA`),
  CONSTRAINT `FK_ilnnbpki9ti8t0dac618iwdj9` FOREIGN KEY (`ID_PERFIL`) REFERENCES `tb_perfil` (`ID_PERFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-22 16:17:19
