CREATE TABLE T_LANGUAGE (
  id INT NOT NULL AUTO_INCREMENT,
  name varchar(256) ,
  description varchar(256) DEFAULT '',
  create_time datetime NOT NULL as CURRENT_TIMESTAMP ,
  update_time datetime NOT NULL as CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);