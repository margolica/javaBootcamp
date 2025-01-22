INSERT INTO "user" (login, password)
VALUES ('user_01', '0000'),
       ('user_02', '0000'),
       ('user_03', '0000'),
       ('user_04', '0000'),
       ('user_05', '0000');

INSERT INTO chatroom (name, owner_id)
VALUES ('chatroom_01', '1'),
       ('chatroom_02', '2'),
       ('chatroom_03', '1'),
       ('chatroom_04', '1'),
       ('chatroom_05', '5');

INSERT INTO message (author_id, room_id, text, date)
VALUES ('1', '1', 'Hello', CURRENT_DATE),
       ('2', '2', 'Hello', CURRENT_DATE),
       ('5', '5', 'Hello', CURRENT_DATE),
       ('5', '1', 'Hello', CURRENT_DATE),
       ('5', '3', 'Hello', CURRENT_DATE),
       ('5', '4', 'Hello', CURRENT_DATE);