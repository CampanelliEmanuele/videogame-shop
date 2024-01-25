-- types
INSERT INTO types (name, description) VALUES ('Action', 'Games focused on physical challenges and hand-eye coordination.');
INSERT INTO types (name, description) VALUES ('Strategy', 'Games that emphasize thinking and planning.');

INSERT INTO videogames (name, price, description, photo) VALUES ('Videogame 1', 1, 'description 1', 'photo url');
INSERT INTO videogames (name, price, description, photo) VALUES ('Videogame 2', 2, 'description 2', 'photo url');
INSERT INTO videogames (name, price, description, photo) VALUES ('Videogame 3', 3, 'description 3', 'photo url');

INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-20', 'Supplier F', 7.49, 200, 2);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-19', 'Supplier G', 6.99, 120, 3);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-25', 'Supplier A', 10.99, 100, 1);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-22', 'Supplier D', 8.75, 90, 2);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-23', 'Supplier C', 11.49, 120, 1);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-21', 'Supplier E', 12.99, 150, 2);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-18', 'Supplier H', 15.25, 75, 3);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-17', 'Supplier I', 11.99, 110, 3);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-24', 'Supplier B', 9.99, 80, 1);

