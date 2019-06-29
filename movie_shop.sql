CREATE TABLE Customers(
	customer_id INTEGER PRIMARY KEY,
	last_name VARCHAR(50) NOT NULL,
	first_name VARCHAR(50)NOT NULL,
	address VARCHAR(200),
	city VARCHAR(50),
	state VARCHAR(3),
	postcode VARCHAR(8),
	CHECK (state = ANY ('{"NSW", "VIC", "QLD", "ACT", "TAS", "NT", "SA", "WA"}'::text[]))
);	

CREATE TABLE Movie(
	movie_id INTEGER PRIMARY KEY,
	movie_title VARCHAR(100) NOT NULL,
	director_last_name VARCHAR(50) NOT NULL,
	director_first_name VARCHAR(50) NOT NULL,
	genre VARCHAR(20),
	media_type VARCHAR(20),
	release_date DATE,
	studio_name VARCHAR(50),
	retail_price REAL NOT NULL,
	current_stock INTEGER NOT NULL,
	CHECK (genre = ANY ('{"Action", "Adventure", "Comedy", "Romance", "Science Fiction", "Documentary", "Drama", "Horror"}'::text[])),
	CHECK (media_type = ANY ('{"DVD", "Blu-Ray"}'::text[])),
        CHECK (retail_price > 0),
	CHECK (current_stock >= 0)
);

CREATE TABLE Shipments(
	shipment_id INTEGER PRIMARY KEY,
	customer_id INTEGER NOT NULL,
	movie_id INTEGER NOT NULL,
	shipment_date DATE,
	FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
		ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (movie_id) REFERENCES Movie(movie_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
