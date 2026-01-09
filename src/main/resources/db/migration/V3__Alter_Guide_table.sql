-- Migration to add base_price column
ALTER TABLE guide
    ADD COLUMN base_price DOUBLE PRECISION;
