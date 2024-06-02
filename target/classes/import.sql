INSERT INTO users (username, password, enabled) VALUES('apps@horarios.com','$2a$10$FarJ8kWpI41DsEr0fWxERe5KgbAynpFFVJfmDY5WSaiwMpCfQAVTi',TRUE);
INSERT INTO authorities (user_id, authority) VALUES(1,'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES(1,'ROLE_USER');

