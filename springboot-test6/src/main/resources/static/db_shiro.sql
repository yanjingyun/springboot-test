--角色数据
INSERT INTO `sec_role` VALUES ('297ea8576c2cc0cd016c2cc0dfab0003','role1',NULL);
INSERT INTO `sec_role` VALUES ('297ea8576c2cc0cd016c2cc0dfaf0004','role2',NULL);

--权限数据
INSERT INTO `sec_permission` VALUES ('297ea8576c2cc238016c2cc249130000','permission1','permission1权限');
INSERT INTO `sec_permission` VALUES ('297ea8576c2cc238016c2cc249670001','permission2','permission2权限');

--角色与权限关系数据
INSERT INTO `sec_role_permission` VALUES ('297ea8576c2cc0cd016c2cc0dfab0003','297ea8576c2cc238016c2cc249130000');
INSERT INTO `sec_role_permission` VALUES ('297ea8576c2cc0cd016c2cc0dfaf0004','297ea8576c2cc238016c2cc249670001');

--用户数据
INSERT INTO `sec_user` VALUES ('297ea8576c2cc0cd016c2cc0df500000','admin',NULL,'admin','admin');
INSERT INTO `sec_user` VALUES ('297ea8576c2cc0cd016c2cc0df980001','user1',NULL,'user1','user1');
INSERT INTO `sec_user` VALUES ('297ea8576c2cc0cd016c2cc0df9e0002','user2',NULL,'user2','user2');

--用户与角色关系数据
INSERT INTO `sec_user_role` VALUES ('297ea8576c2cc0cd016c2cc0df500000','297ea8576c2cc0cd016c2cc0dfab0003');
INSERT INTO `sec_user_role` VALUES ('297ea8576c2cc0cd016c2cc0df500000','297ea8576c2cc0cd016c2cc0dfaf0004');
INSERT INTO `sec_user_role` VALUES ('297ea8576c2cc0cd016c2cc0df980001','297ea8576c2cc0cd016c2cc0dfab0003');
INSERT INTO `sec_user_role` VALUES ('297ea8576c2cc0cd016c2cc0df9e0002','297ea8576c2cc0cd016c2cc0dfaf0004');