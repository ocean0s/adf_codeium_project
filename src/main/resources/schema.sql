CREATE TABLE IF NOT EXISTS households (
    eircode VARCHAR(255) PRIMARY KEY,
    number_of_occupants INT,
    max_number_of_occupants INT,
    is_owner_occupied BOOLEAN
);

CREATE TABLE IF NOT EXISTS pets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    animal_type VARCHAR(255),
    breed VARCHAR(255),
    age INT,
    eircode VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   role VARCHAR(255) NOT NULL,
   unlocked BOOLEAN NOT NULL DEFAULT TRUE
);