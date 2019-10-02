CREATE TABLE place (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	slug VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	state VARCHAR(50) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO place (name, slug, city, state, created_at) 
values ('name 1', 'slug 1', 'city 1', 'state 1', CURRENT_TIMESTAMP);

INSERT INTO place (name, slug, city, state, created_at) 
values ('name 2', 'slug 2', 'city 2', 'state 2', CURRENT_TIMESTAMP);

INSERT INTO place (name, slug, city, state, created_at) 
values ('name 3', 'slug 3', 'city 3', 'state 3', CURRENT_TIMESTAMP);

INSERT INTO place (name, slug, city, state, created_at) 
values ('name 4', 'slug 4', 'city 4', 'state 4', CURRENT_TIMESTAMP);