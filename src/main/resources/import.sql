-- Insert dummy data into account
INSERT INTO account(account_number, first_name, last_name, balance, currency_unit) VALUES (112345678, 'Mads', 'Berg', 2500000.0, 'DKK');
INSERT INTO account(account_number, first_name, last_name, balance, currency_unit) VALUES (153456789, 'Thomas', 'Andersen', 1500000.0, 'DKK');
INSERT INTO account(account_number, first_name, last_name, balance, currency_unit) VALUES (172345678, 'Frederik', 'Thomsen', 500000.0, 'DKK');
INSERT INTO account(account_number, first_name, last_name, balance, currency_unit) VALUES (123345678, 'Anne', 'Frederiksen', 3500000.0, 'DKK');
INSERT INTO account(account_number, first_name, last_name, balance, currency_unit) VALUES (193345678, 'Sanne', 'Pedersen', 1250000.0, 'DKK');


-- To generate uids use nextval('hibernate_sequence') cmd in place of value