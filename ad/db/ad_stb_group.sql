CREATE TABLE ad.stb_group
(
    id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL COMMENT '分组名称',
    parent_id bigint(20) NOT NULL COMMENT '父ID',
    route_id int(20) NOT NULL COMMENT '公交线路ID',
    defalt_group tinyint(1) DEFAULT '0' NOT NULL COMMENT '1 是 0否'
);
INSERT INTO ad.stb_group (id, name, parent_id, route_id, defalt_group) VALUES (8, '深圳北->宝安中心', 0, 12, 0);
INSERT INTO ad.stb_group (id, name, parent_id, route_id, defalt_group) VALUES (9, '塘朗->坪洲', 0, 13, 0);