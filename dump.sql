-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: 189.125.69.42    Database: 11barra11
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.26-MariaDB

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
-- Dumping data for table `tb_atividade`
--

LOCK TABLES `tb_atividade` WRITE;
/*!40000 ALTER TABLE `tb_atividade` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_atividade` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_atividade_artefatos`
--

LOCK TABLES `tb_atividade_artefatos` WRITE;
/*!40000 ALTER TABLE `tb_atividade_artefatos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_atividade_artefatos` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_atuacao`
--

LOCK TABLES `tb_atuacao` WRITE;
/*!40000 ALTER TABLE `tb_atuacao` DISABLE KEYS */;
INSERT INTO `tb_atuacao` VALUES ('03633fac-1cfb-4a94-a129-1239cafca7b5','Banco de Dados'),('2e3cb478-7603-47ee-8c74-fcfce63f92e2','Qualidade'),('4416d697-9367-4709-b8f1-4bbf787fa5cd','bdmg'),('52631ca2-aba7-4132-af48-2f7209d50441','Gerencial'),('ca835e74-ee5d-4046-8820-343e290de99f','Análise'),('e41aef05-c222-47f9-9a6a-5006978fe3af','Desenvolvimento');
/*!40000 ALTER TABLE `tb_atuacao` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_atuando`
--

LOCK TABLES `tb_atuando` WRITE;
/*!40000 ALTER TABLE `tb_atuando` DISABLE KEYS */;
INSERT INTO `tb_atuando` VALUES ('03633fac-1cfb-4a94-a129-1239cafca7b5','ee57a9d9-dce6-42d9-b581-3055eeb5e365'),('2e3cb478-7603-47ee-8c74-fcfce63f92e2','906c5f08-7c55-4cbd-8133-adc57aeaf4fe'),('4416d697-9367-4709-b8f1-4bbf787fa5cd','fdcefbdd-4e31-45ff-b36b-dc844b838f82'),('52631ca2-aba7-4132-af48-2f7209d50441','a2f13b19-6b63-4c7a-bac9-dde5db125d9d'),('ca835e74-ee5d-4046-8820-343e290de99f','4f915333-f8ec-4751-b079-9d0c6d044eb7'),('e41aef05-c222-47f9-9a6a-5006978fe3af','42989f44-85a6-4ef8-a42d-f0746d3093a2');
/*!40000 ALTER TABLE `tb_atuando` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_modificacao_atividade`
--

LOCK TABLES `tb_modificacao_atividade` WRITE;
/*!40000 ALTER TABLE `tb_modificacao_atividade` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_modificacao_atividade` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_modulo`
--

LOCK TABLES `tb_modulo` WRITE;
/*!40000 ALTER TABLE `tb_modulo` DISABLE KEYS */;
INSERT INTO `tb_modulo` VALUES ('250d25f9-e03e-422a-bdcb-e2f2a416cfe3','Monitoramento','94cbfe66-5c33-4bcc-8540-3d8bbc8b4660'),('33678df2-13d0-415a-8f7a-a645b43ac359','Workflow','94cbfe66-5c33-4bcc-8540-3d8bbc8b4660'),('490818cb-d528-4f9a-b404-49d528dffed4','Migração Sistema','94cbfe66-5c33-4bcc-8540-3d8bbc8b4660'),('740f79ac-2b0d-4c33-a0b2-07e73fb31f8a','Dashboard','94cbfe66-5c33-4bcc-8540-3d8bbc8b4660'),('9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e','Avaliação de Garantias','94cbfe66-5c33-4bcc-8540-3d8bbc8b4660'),('9bddcfc7-24bc-409a-a010-9f88e4545fcd','Venda de Bens','94cbfe66-5c33-4bcc-8540-3d8bbc8b4660'),('bd0af9cb-3c6d-4cfe-a9b4-f4f7e0d69006','Arquitetura','94cbfe66-5c33-4bcc-8540-3d8bbc8b4660'),('c883b8f0-8353-4559-a8a0-9c1a50659d35','Fundos de Aval','94cbfe66-5c33-4bcc-8540-3d8bbc8b4660'),('fb1f3d5a-c984-440d-8ea1-0e4bc2c9d037','Gestão da Carteira','94cbfe66-5c33-4bcc-8540-3d8bbc8b4660');
/*!40000 ALTER TABLE `tb_modulo` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_ordem_servico`
--

LOCK TABLES `tb_ordem_servico` WRITE;
/*!40000 ALTER TABLE `tb_ordem_servico` DISABLE KEYS */;
INSERT INTO `tb_ordem_servico` VALUES ('7aa75273-cd14-4dce-ab96-0f4562c76fdc','Ordem 1');
/*!40000 ALTER TABLE `tb_ordem_servico` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_pacote`
--

LOCK TABLES `tb_pacote` WRITE;
/*!40000 ALTER TABLE `tb_pacote` DISABLE KEYS */;
INSERT INTO `tb_pacote` VALUES ('013b9a99-3e89-4e5d-bd4a-433bc8905c8c','Controle de recuperação de crédito FAMPE','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('09231e91-7130-4fb9-8208-c3a449b6266e','Arquitetura dos Sistemas','bd0af9cb-3c6d-4cfe-a9b4-f4f7e0d69006'),('0c81989d-b675-49d6-b90e-1cf72b71287d','Controle de recuperação de crédito FGI','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('1e610e35-f174-494a-9766-226a55039bec','Fluxo de Revisão de Laudo de Bens','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('237d2949-867d-41fa-8535-5ccd27c01b4e','Caixa de Entrada','740f79ac-2b0d-4c33-a0b2-07e73fb31f8a'),('2563194b-17de-440b-9db8-d2d5f41971e2','Monitorar Mercadorias','250d25f9-e03e-422a-bdcb-e2f2a416cfe3'),('3662cb7d-80cb-4236-ae61-7277181b811a','Garantia Contrato','490818cb-d528-4f9a-b404-49d528dffed4'),('367585c0-c371-4c07-9176-576eb5e2e47e','Fluxo de Validação da Garantia a Constituir','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('36cbb4e1-c813-4670-bb03-7deff59a6a41','Monitorar Fiança bancária, carta de crédito, seguro garantia financeiro e títulos','250d25f9-e03e-422a-bdcb-e2f2a416cfe3'),('39c31260-afc9-4428-9a37-d795d9a225ca','Orçamento da Avaliação de Bens','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('3f0f9fdc-bfc2-47bd-ae67-cca1e3698b80','Sistema Gestão Workflow','33678df2-13d0-415a-8f7a-a645b43ac359'),('40d7485a-532d-4917-890f-61937c9930fd','Sistemas de Comunicações','bd0af9cb-3c6d-4cfe-a9b4-f4f7e0d69006'),('442c3d7b-6cc2-4fca-b3f4-7d76c374fee2','Relatórios Gerenciais - Fundos de Aval','fb1f3d5a-c984-440d-8ea1-0e4bc2c9d037'),('4650c1f2-3d0b-4d87-98e3-07463ba40733','Tabelas e Cadastros','490818cb-d528-4f9a-b404-49d528dffed4'),('47210049-2713-49be-9555-f565cd60bd53','Honra e aplicação FGI','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('4c89a928-04d5-401d-a6d0-8cc1954ec040','Constituição de Garantias','490818cb-d528-4f9a-b404-49d528dffed4'),('4d13ac65-f781-46a9-ae19-3513520e7c1a','Monitorar Seguro garantia não financeiro','250d25f9-e03e-422a-bdcb-e2f2a416cfe3'),('4e576094-4cb5-4f76-8b7d-6d9b1b4afa4e','Monitorar Recebíveis','250d25f9-e03e-422a-bdcb-e2f2a416cfe3'),('524c25ba-a1b5-431e-af07-8c04b40a0c06','Mensageria','bd0af9cb-3c6d-4cfe-a9b4-f4f7e0d69006'),('5471c66e-c626-4450-9cdb-5aa684bad710','Conciliação Automática FGI','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('57c0abf6-b4e3-4f9c-a04e-6c28a8900cc0','Pagamentos Concessão FAMPE e FGI','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('5a4a15d7-5151-4b61-bfa1-6a44fb214a54','Configurar Fundo','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('5c006a04-1fab-45a4-bf4e-90ae2bd81d96','Fluxo de Avaliação de Mercadorias e Direitos','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('5cc41733-7c56-4360-a758-9ceabc71b7a1','Controle de Acessos','bd0af9cb-3c6d-4cfe-a9b4-f4f7e0d69006'),('5d69aa16-221a-49d2-8a80-6afd25f949e4','Painel de Sistemas','740f79ac-2b0d-4c33-a0b2-07e73fb31f8a'),('6c0d3ce9-086b-42f0-aa52-6919fb4fa001','Transferência/Integrações de Arquivos FAMPE','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('7a5981a6-5400-41e6-a345-b53da5e5ee46','Cadastros de Avaliação','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('7d7d3933-812d-42ce-ac42-808b545af152','Gravame','490818cb-d528-4f9a-b404-49d528dffed4'),('869e0512-553a-4867-a679-cc692d61eb82','Honra e aplicação FAMPE','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('8808362a-79c7-4826-b192-39ad60cd923d','Fluxo de Avaliação de Bens','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('8e9c957d-3077-499c-8d67-ac108a54e92b','Conclusão da Avaliação de Bens','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('9be5adc5-c625-451e-9dd3-1fc5bb2693de','Distribuição do Pedido de Avaliação de Bens','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('a2d1edb6-895d-4521-a318-8e6ac6b3fe41','Liberação e bloqueio de garantia','490818cb-d528-4f9a-b404-49d528dffed4'),('ae95cae5-2aab-49f4-8125-4e5aece3aea3','Fluxo de Revisão de Laudo de Bens','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('b93e1169-bb20-4f8e-8997-e4ec2a89446c','Monitorar seguros','250d25f9-e03e-422a-bdcb-e2f2a416cfe3'),('b9f53680-3cc4-4658-87ca-aaca1f2a3113','Fluxo de Validação de Laudos Previamente Elaborados','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('bde25ba6-d2b0-466e-8da7-29f564fb4563','Vincular Garantia Fundo','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('c514a16d-cbfa-4ced-b5b2-9f3ea4a92d43','Cadastros das réguas de monitoramento','250d25f9-e03e-422a-bdcb-e2f2a416cfe3'),('c5676c5b-55e9-4288-ad78-00a3dcf2d505','Manutenção de Bens','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('c8c3ccde-5a34-4367-8336-c4a3188f9bde','Fluxo de Cotação Simplificada','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('cdb51204-75e9-4ee4-9192-1db23486b9f2','Engine','33678df2-13d0-415a-8f7a-a645b43ac359'),('d2208761-5cb6-4b4e-9127-ba7b604716bc','Relatórios Gerenciais - Gestão da carteira de garantias','fb1f3d5a-c984-440d-8ea1-0e4bc2c9d037'),('d56d2cd1-082e-43cd-bec9-f77f7c6b54e2','Conciliação Automática FAMPE','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('d6fd33eb-9e46-4a5a-a4f3-200b81b0a096','Monitoramento de fundos de Aval','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('e39a3674-e3ef-4f4a-adc7-5cca13f3105d','Registro da entrada do bem e controle do ciclo de vida','9bddcfc7-24bc-409a-a010-9f88e4545fcd'),('e82c4710-127e-4988-bb71-00cae1f8ed41','Pagamentos Renegociação FAMPE e FGI','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('e8e4c0c2-fba8-4223-84b9-a742f368b7db','Monitorar averbação de bens','250d25f9-e03e-422a-bdcb-e2f2a416cfe3'),('ee0316d8-3e53-4ecb-a9a6-8773bfc60b21','Revisão de Laudo de Bens','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('f0c74acc-504a-41cf-b727-f43437ef488b','Pagamentos Renegociação FAMPE e FGI','c883b8f0-8353-4559-a8a0-9c1a50659d35'),('f47162d9-c160-4da3-8e8c-c555a177d200','Pedido de Avaliação de Bens','9b4b6486-ebc7-47dd-9cbd-e5c7017ce79e'),('fcb6a0c5-a37f-4e3d-a2f2-d1f31d2b5ab5','Segurança','bd0af9cb-3c6d-4cfe-a9b4-f4f7e0d69006');
/*!40000 ALTER TABLE `tb_pacote` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_parametro`
--

LOCK TABLES `tb_parametro` WRITE;
/*!40000 ALTER TABLE `tb_parametro` DISABLE KEYS */;
INSERT INTO `tb_parametro` VALUES ('12e5400f-d427-44bc-adbb-ba071efccecc','2018-03-03 18:23:45',1,439.46),('c5abc622-f912-439f-add2-bad4059e4212','2018-03-03 18:22:51',0,641.55);
/*!40000 ALTER TABLE `tb_parametro` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_perfil`
--

LOCK TABLES `tb_perfil` WRITE;
/*!40000 ALTER TABLE `tb_perfil` DISABLE KEYS */;
INSERT INTO `tb_perfil` VALUES ('2be37ab1-947e-431c-b0d1-2c7ff33466b8','BDMG'),('5e7970f1-7b9a-4361-8332-683695b1cf70','ANALISTA'),('8053247f-cefe-4f8b-9084-e906364e78e1','QUALIDADE'),('aae42061-4e52-4fcf-a9e0-1fa5f1a70453','DESENVOLVEDOR'),('fe529a1c-b35a-48b9-883e-c90335219893','GERENTE');
/*!40000 ALTER TABLE `tb_perfil` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_pessoa`
--

LOCK TABLES `tb_pessoa` WRITE;
/*!40000 ALTER TABLE `tb_pessoa` DISABLE KEYS */;
INSERT INTO `tb_pessoa` VALUES ('2acfa440-8231-4b5f-931a-848988b3a0ca','222.222.222-22','analista@stefanini.com','Analista - Stefanini'),('63674ee6-38fa-4ad5-9903-f6d8a6c40dfc','111.111.111-11','bdmg@stefanini.com','Bdmg'),('7102e44e-15a9-470e-9f21-9acdc4b5adf0','333.333.333-33','desenvolvedor@stefanini.com','Desenvolvedor - Stefanini'),('b4eefb4e-92eb-4b7f-a8c6-86541afc1450','555.555.555-55','bd@stefanini.com','Bando de dados - Stefanini'),('c8503eb6-e98a-449a-bf20-44f52bdcdc81','000.000.000-00','gerente@stefanini.com','Gerente - Stefanini'),('ed2867e2-ba6c-41e8-a984-9474d28f0d35','444.444.444-44','qualidade@stefanin.com','Qualidade - Stefanini');
/*!40000 ALTER TABLE `tb_pessoa` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_progresso_atividade`
--

LOCK TABLES `tb_progresso_atividade` WRITE;
/*!40000 ALTER TABLE `tb_progresso_atividade` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_progresso_atividade` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tb_projeto`
--

LOCK TABLES `tb_projeto` WRITE;
/*!40000 ALTER TABLE `tb_projeto` DISABLE KEYS */;
INSERT INTO `tb_projeto` VALUES ('0637ce32-9d26-43da-bba5-e75ed35eb4b6','BDMG-DIGITAL'),('94cbfe66-5c33-4bcc-8540-3d8bbc8b4660','BDMG-GARANTIAS');
/*!40000 ALTER TABLE `tb_projeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_usuario` (
  `ID_USUARIO` varchar(255) NOT NULL,
  `ST_ATIVO` bit(1) DEFAULT NULL,
  `TP_PERFIL` varchar(255) DEFAULT NULL,
  `TX_SENHA` varchar(255) DEFAULT NULL,
  `ID_PESSOA` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  UNIQUE KEY `UK_duq9n0iyjgvl1h1id6ikminbl` (`ID_PESSOA`),
  CONSTRAINT `FK_duq9n0iyjgvl1h1id6ikminbl` FOREIGN KEY (`ID_PESSOA`) REFERENCES `tb_pessoa` (`ID_PESSOA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES ('42989f44-85a6-4ef8-a42d-f0746d3093a2','','DESENVOLVEDOR','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','7102e44e-15a9-470e-9f21-9acdc4b5adf0'),('4f915333-f8ec-4751-b079-9d0c6d044eb7','','ANALISTA','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','2acfa440-8231-4b5f-931a-848988b3a0ca'),('906c5f08-7c55-4cbd-8133-adc57aeaf4fe','','QUALIDADE','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','ed2867e2-ba6c-41e8-a984-9474d28f0d35'),('a2f13b19-6b63-4c7a-bac9-dde5db125d9d','','GERENTE','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','c8503eb6-e98a-449a-bf20-44f52bdcdc81'),('ee57a9d9-dce6-42d9-b581-3055eeb5e365','','BANCO_DADOS','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','b4eefb4e-92eb-4b7f-a8c6-86541afc1450'),('fdcefbdd-4e31-45ff-b36b-dc844b838f82','','BDMG','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','63674ee6-38fa-4ad5-9903-f6d8a6c40dfc');
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-04 13:36:11
