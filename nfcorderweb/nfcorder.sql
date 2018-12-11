-- phpMyAdmin SQL Dump
-- version 4.0.4.2
-- http://www.phpmyadmin.net
--
-- 主機: localhost
-- 產生日期: 2018 年 09 月 19 日 08:23
-- 伺服器版本: 5.6.13
-- PHP 版本: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 資料庫: `nfcorder`
--
CREATE DATABASE IF NOT EXISTS `nfcorder` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `nfcorder`;

-- --------------------------------------------------------

--
-- 表的結構 `myclass`
--

CREATE TABLE IF NOT EXISTS `myclass` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `mclass` varchar(100) NOT NULL,
  `noworder` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 轉存資料表中的資料 `myclass`
--

INSERT INTO `myclass` (`id`, `mclass`, `noworder`) VALUES
(1, 'test', '1');

-- --------------------------------------------------------

--
-- 表的結構 `nfcorder`
--

CREATE TABLE IF NOT EXISTS `nfcorder` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `mclass` varchar(100) NOT NULL,
  `rdate` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 轉存資料表中的資料 `nfcorder`
--

INSERT INTO `nfcorder` (`id`, `uid`, `mclass`, `rdate`) VALUES
(2, 'N000001', 'test', '2018-09-19 16:17:19');

-- --------------------------------------------------------

--
-- 表的結構 `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `pic` varchar(100) NOT NULL,
  `myuser` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 轉存資料表中的資料 `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `username`, `name`, `pic`, `myuser`) VALUES
(1, 'N000001', 'test', 'test.png', 'myphone');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
