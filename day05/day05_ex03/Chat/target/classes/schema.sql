DROP TABLE chatroom CASCADE;
DROP TABLE message CASCADE;
DROP TABLE "user" CASCADE;

DROP SEQUENCE auto_increment_user_id;
DROP SEQUENCE auto_increment_chatroom_id;
DROP SEQUENCE auto_increment_Message_id;

CREATE SEQUENCE auto_increment_user_id;
CREATE SEQUENCE auto_increment_chatroom_id;
CREATE SEQUENCE auto_increment_Message_id;

CREATE TABLE "user"
(
    id       INTEGER DEFAULT nextval('auto_increment_user_id') PRIMARY KEY NOT NULL,
    login    VARCHAR(30) CHECK (LENGTH(login) BETWEEN 3 AND 50),
    password VARCHAR(30) CHECK (LENGTH(password) BETWEEN 4 AND 50)
);

CREATE TABLE chatroom
(
    id       INTEGER DEFAULT nextval('auto_increment_chatroom_id') PRIMARY KEY NOT NULL,
    name     VARCHAR(30)                                                       NOT NULL,
    owner_id INTEGER,
    CONSTRAINT fk_message_user_id FOREIGN KEY (owner_id) REFERENCES "user" (id)
);

CREATE TABLE message
(
    id        INTEGER DEFAULT nextval('auto_increment_message_id') PRIMARY KEY NOT NULL,
    author_id INTEGER,
    room_id   INTEGER,
    text      VARCHAR(500)                                                     NOT NULL,
    date      TIMESTAMPTZ,
    CONSTRAINT fk_message_user_id FOREIGN KEY (author_id) REFERENCES "user" (id),
    CONSTRAINT fk_message_chatroom_id FOREIGN KEY (room_id) REFERENCES chatroom (id)
);