INSERT INTO households (eircode, number_of_occupants, max_number_of_occupants, is_owner_occupied)
    VALUES ('12345', 2, 4, true);
INSERT INTO pets (name, animal_type, breed, age, eircode)
    VALUES ('Fido', 'Dog', 'Labrador', 3, '12345');
INSERT INTO pets (name, animal_type, breed, age, eircode)
    VALUES ('Whiskers', 'Cat', 'Persian', 1, '12345');

INSERT INTO users (username, password, role, unlocked)
    VALUES ('admin', '$2a$12$uJ/OwzeQX8pdWwhQKMr.xuHWSPlLkhKgjM7POpPE5CCoy.0IZdgIe', 'ADMIN', true);
INSERT INTO users (username, password, role, unlocked)
    VALUES ('user', '$2a$12$uJ/OwzeQX8pdWwhQKMr.xuHWSPlLkhKgjM7POpPE5CCoy.0IZdgIe', 'USER', true);
