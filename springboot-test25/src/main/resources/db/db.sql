
-- tb_user 表
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) DEFAULT NULL,
  `age` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tb_user`(username, age) VALUES ('test01', 20);
INSERT INTO `tb_user`(username, age) VALUES ('test02', 23);
INSERT INTO `tb_user`(username, age) VALUES ('test03', 25);


-- tb_order 表
CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_order`(name, user_id) VALUES ('aaa', 1);
INSERT INTO `tb_order`(name, user_id) VALUES ('bbb', 2);

