CREATE TABLE ad.stb
(
    id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    stb_group_id bigint(20) NOT NULL COMMENT '分组id',
    site_id int(11) NOT NULL COMMENT '站点id',
    name varchar(30) NOT NULL COMMENT '名称',
    mac varchar(30) NOT NULL COMMENT '设备号',
    program_id bigint(20) COMMENT '节目id',
    ipAddress varchar(20) COMMENT 'ip地址',
    screen_pic varchar(100) COMMENT '屏幕截屏图片',
    voice_set tinyint(4) COMMENT '声音',
    switch_status tinyint(4) DEFAULT '1' NOT NULL COMMENT '1开 0关',
    online_status tinyint(4) DEFAULT '0' COMMENT '1在线 0下线',
    resolution varchar(20) COMMENT '分辨率',
    version varchar(10) COMMENT '版本号',
    ram varchar(30) COMMENT '内存情况'
);
CREATE UNIQUE INDEX mac ON ad.stb (mac);
INSERT INTO ad.stb (id, stb_group_id, site_id, name, mac, program_id, ipAddress, screen_pic, voice_set, switch_status, online_status, resolution, version, ram) VALUES (1, 3, 0, '23', '132131', null, null, null, null, 1, 0, null, null, null);
INSERT INTO ad.stb (id, stb_group_id, site_id, name, mac, program_id, ipAddress, screen_pic, voice_set, switch_status, online_status, resolution, version, ram) VALUES (2, 6, 0, '123', '123213', null, null, null, null, 1, 0, null, null, null);
INSERT INTO ad.stb (id, stb_group_id, site_id, name, mac, program_id, ipAddress, screen_pic, voice_set, switch_status, online_status, resolution, version, ram) VALUES (3, 8, 12, '北站终端', '00:15:18:16:19:20', null, null, null, null, 1, 0, null, null, null);
INSERT INTO ad.stb (id, stb_group_id, site_id, name, mac, program_id, ipAddress, screen_pic, voice_set, switch_status, online_status, resolution, version, ram) VALUES (4, 8, 13, '塘朗站', '00:17:13:24', null, null, null, null, 1, 0, null, null, null);