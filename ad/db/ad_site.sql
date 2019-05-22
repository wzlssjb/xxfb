CREATE TABLE ad.site
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL COMMENT '站点名称',
    site_code varchar(50) NOT NULL COMMENT '站点编码'
);
INSERT INTO ad.site (id, name, site_code) VALUES (4, '深圳北', '1909');
INSERT INTO ad.site (id, name, site_code) VALUES (5, '宝安中心', '1907');
INSERT INTO ad.site (id, name, site_code) VALUES (6, '翻身', '1502');
INSERT INTO ad.site (id, name, site_code) VALUES (7, '翻身2', '1506');
INSERT INTO ad.site (id, name, site_code) VALUES (8, '翻身3', '1509');
INSERT INTO ad.site (id, name, site_code) VALUES (9, '123', '132');
INSERT INTO ad.site (id, name, site_code) VALUES (10, '132', '312');
INSERT INTO ad.site (id, name, site_code) VALUES (11, '423', '423');
INSERT INTO ad.site (id, name, site_code) VALUES (12, '深圳北', '1102');
INSERT INTO ad.site (id, name, site_code) VALUES (13, '塘朗', '1101');
INSERT INTO ad.site (id, name, site_code) VALUES (14, '坪洲', '1107');