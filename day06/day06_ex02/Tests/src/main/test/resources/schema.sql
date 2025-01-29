CREATE TABLE IF NOT EXISTS product
(
    id
    BIGINT
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
(
    START
    WITH
    1
    INCREMENT
    BY
    1
) PRIMARY KEY,
    name VARCHAR
(
    255
),
    price NUMERIC
(
    10,
    2
)
    );