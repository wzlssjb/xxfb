CREATE TABLE ad.skin
(
    id int(10) unsigned NOT NULL AUTO_INCREMENT,
    title varchar(30) NOT NULL COMMENT '名称',
    description varchar(200) COMMENT '描述',
    type tinyint(1) NOT NULL COMMENT '1.系统默认 2.自定义',
    canvas_height int(10) NOT NULL COMMENT '屏幕高度',
    canvas_width int(10) NOT NULL COMMENT '屏幕宽度',
    line_count int(2) COMMENT '公交线路',
    ad_height int(10) COMMENT '广告位高度',
    ad_width int(10) COMMENT '广告位宽度',
    CONSTRAINT `PRIMARY` PRIMARY KEY (id, title)
);
INSERT INTO ad.skin (id, title, description, type, canvas_height, canvas_width, line_count, ad_height, ad_width) VALUES (5, '标题1540432434530', '描述', 1, 1920, 1080, 1, null, null);
INSERT INTO ad.skin (id, title, description, type, canvas_height, canvas_width, line_count, ad_height, ad_width) VALUES (7, '标题1540608623667', '描述', 2, 1920, 1080, 7, 434, 1079);