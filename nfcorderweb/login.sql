-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- 主機: localhost
-- 建立日期: Mar 04, 2015, 03:23 AM
-- 伺服器版本: 5.0.51
-- PHP 版本: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 資料庫: `login`
-- 

-- --------------------------------------------------------

-- 
-- 資料表格式： `tbl_user`
-- 

CREATE TABLE `tbl_user` (
  `id` bigint(100) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `pic` varchar(100) NOT NULL,
  `myuser` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE `nfcorder` (
  `id` bigint(100) NOT NULL auto_increment,
  `uid` varchar(100) NOT NULL,
  `mclass` varchar(100) NOT NULL,
  `rdate` varchar(100) NOT NULL,
   PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE `myclass` (
  `id` bigint(100) NOT NULL auto_increment,
  `mclass` varchar(100) NOT NULL,
  `noworder` varchar(100) NOT NULL,
   PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

