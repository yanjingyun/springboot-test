
-- tb_user 表
CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL auto_increment,
  `username` varchar(255) DEFAULT NULL,
  `age` int(2) DEFAULT NULL,
  `version_number` int(2) DEFAULT 0,
  `create_time` datetime(0),
  `create_user` varchar(50),
  `last_update_time` datetime(0),
  `last_update_user` varchar(50),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


