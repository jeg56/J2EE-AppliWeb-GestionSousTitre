-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 27 août 2019 à 14:00
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `soustitre`
--

-- --------------------------------------------------------

--
-- Structure de la table `fichier`
--

CREATE TABLE `fichier` (
  `id` int(11) NOT NULL COMMENT 'Identifiant du fichier',
  `nom_fichier` varchar(255) NOT NULL COMMENT 'Nom du fichier'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `traduction`
--

CREATE TABLE `traduction` (
  `id` int(11) NOT NULL COMMENT 'Id',
  `id_fichier` int(11) NOT NULL COMMENT 'Identifiant du fichier',
  `num_sequence` int(11) NOT NULL COMMENT 'Numéros de la séquence',
  `time_deb` text NOT NULL COMMENT 'Temps du début de la séquence',
  `time_fin` text NOT NULL COMMENT 'Temps de fin de la séquence',
  `message_source` text NOT NULL COMMENT 'Message source',
  `message_cible` text COMMENT 'Message traduit'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Traduction de sous titre';

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `fichier`
--
ALTER TABLE `fichier`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `traduction`
--
ALTER TABLE `traduction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_fichier` (`id_fichier`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `fichier`
--
ALTER TABLE `fichier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identifiant du fichier', AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `traduction`
--
ALTER TABLE `traduction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id', AUTO_INCREMENT=5239;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `traduction`
--
ALTER TABLE `traduction`
  ADD CONSTRAINT `fk_id_fichier` FOREIGN KEY (`id_fichier`) REFERENCES `fichier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
