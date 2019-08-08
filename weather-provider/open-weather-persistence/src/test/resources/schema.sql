CREATE TABLE IF NOT EXISTS CITIES (
	ID int PRIMARY KEY,
	NAME varchar(255),
	COUNTRY varchar(255)
);
CREATE INDEX IF NOT EXISTS CITY_NAME_INDEX on CITIES(NAME);