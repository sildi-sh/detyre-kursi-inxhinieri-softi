-- Migration to add booking details and ensure ID type compatibility
ALTER TABLE booking
    MODIFY COLUMN id CHAR(36), -- Ensuring the ID fix we discussed earlier
    ADD COLUMN booking_date DATETIME,
    ADD COLUMN num_participants INT NOT NULL,
    ADD COLUMN total_price DOUBLE PRECISION;