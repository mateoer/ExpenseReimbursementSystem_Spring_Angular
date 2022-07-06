
--ALTER TABLE vehicle ALTER COLUMN type DROP NOT NULL;

INSERT INTO vehicle(license_plate, vehicle_type)
VALUES ('123-ABC', '12');