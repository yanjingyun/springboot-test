
-- tb_user 表
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tb_user`(username, password) VALUES ('test01', 'test01');
INSERT INTO `tb_user`(username, password) VALUES ('test02', 'test02');
INSERT INTO `tb_user`(username, password) VALUES ('test03', 'test03');


-- tb_order 表
CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_order`(name, user_id) VALUES ('aaa', 1);
INSERT INTO `tb_order`(name, user_id) VALUES ('bbb', 2);

