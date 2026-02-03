DELETE FROM subscriptions;
DELETE FROM plans;

INSERT INTO plans (name, price) VALUES ('FREE', 0.00);
INSERT INTO plans (name, price) VALUES ('PREMIUM', 9.99);
INSERT INTO plans (name, price) VALUES ('PRO', 19.99);