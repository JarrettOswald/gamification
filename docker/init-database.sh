#!/bin/bash
psql -U postgres <<-EOSQL

CREATE TABLE score_card (
    card_id BIGINT PRIMARY KEY,
    user_id BIGINT,
    attempt_id BIGINT,
    score_timestamp BIGINT,
    score INT
);

CREATE TYPE badge_type_enum AS ENUM ('BRONZE', 'SILVER', 'GOLD', 'FIRST_WON', 'LUCKY_NUMBER');

CREATE TABLE badge_card (
    badge_id SERIAL PRIMARY KEY,
    user_id BIGINT,
    badge_timestamp BIGINT,
    badge_type badge_type_enum
);

EOSQL