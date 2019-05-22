CREATE TABLE ad.route_site
(
    id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    route_id int(11) NOT NULL COMMENT '线路id',
    site_id int(11) NOT NULL COMMENT '站点id',
    order_position int(11) NOT NULL COMMENT '站点顺序',
    CONSTRAINT route_site_ibfk_1 FOREIGN KEY (route_id) REFERENCES ad.route (id) ON DELETE CASCADE,
    CONSTRAINT route_site_ibfk_2 FOREIGN KEY (site_id) REFERENCES ad.site (id) ON DELETE CASCADE
);
CREATE INDEX route_id ON ad.route_site (route_id);
CREATE INDEX site_id ON ad.route_site (site_id);
INSERT INTO ad.route_site (id, route_id, site_id, order_position) VALUES (35, 12, 12, 1);
INSERT INTO ad.route_site (id, route_id, site_id, order_position) VALUES (36, 12, 13, 2);
INSERT INTO ad.route_site (id, route_id, site_id, order_position) VALUES (37, 13, 13, 1);
INSERT INTO ad.route_site (id, route_id, site_id, order_position) VALUES (38, 13, 14, 2);