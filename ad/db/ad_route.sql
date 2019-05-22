CREATE TABLE ad.route
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    line_name varchar(30) NOT NULL COMMENT '线路名称',
    name varchar(30) NOT NULL COMMENT '名称',
    start_date varchar(30) NOT NULL COMMENT '起始时间',
    end_date varchar(30) NOT NULL COMMENT '结束时间'
);
INSERT INTO ad.route (id, line_name, name, start_date, end_date) VALUES (12, '320路', '深圳北->宝安中心', '12:20', '23:30');
INSERT INTO ad.route (id, line_name, name, start_date, end_date) VALUES (13, '330', '塘朗->坪洲', '06:10', '23:10');