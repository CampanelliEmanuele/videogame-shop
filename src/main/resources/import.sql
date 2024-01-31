-- types
INSERT INTO types (name, description) VALUES ('Action', 'Games focused on physical challenges and hand-eye coordination.');
INSERT INTO types (name, description) VALUES ('Strategy', 'Games that emphasize thinking and planning.');

INSERT INTO videogames (name, price, description, photo) VALUES ('Assassin\'s Creed II', 59.99, 'Immerse yourself in the Renaissance era as Ezio Auditore, a young nobleman turned assassin seeking vengeance. Traverse stunning historical cities, engage in intense stealth missions, and unravel a gripping tale of conspiracy and revenge.', 'https://www.giantbomb.com/a/uploads/scale_medium/9/93770/2392977-assassins_creed_ii_05_artwork.jpg');
INSERT INTO videogames (name, price, description, photo) VALUES ('Cyberpunk 2077', 49.99, 'Immerse yourself in the dystopian future of Night City.', 'https://www.codiciprodotto.it/wp-content/uploads/2021/01/Cyberpunk-2077-PC-COVER.jpg');
INSERT INTO videogames (name, price, description, photo) VALUES ('Kingdom Hearts III', 49.99, 'Embark on a magical journey with Sora, Donald, and Goofy to save various Disney and Pixar worlds.', 'https://cdn11.bigcommerce.com/s-kqbs9oimhc/images/stencil/1280x1280/products/907/2157/88c11e0a782ef4e5738ffce90c125e15__57986.1675454818.jpg?c=1');
INSERT INTO videogames (name, price, description, photo) VALUES ('Dragon Ball Z Budokai Tenkaichi 3', 39.99, 'Embark on an epic journey with Goku and his friends as they strive to become the strongest fighters and defend the Earth against powerful foes.', 'https://i.pinimg.com/564x/64/e7/7e/64e77ea57e593ca330f47ed1ccb682ad.jpg');
INSERT INTO videogames (name, price, description, photo) VALUES ('Grand Theft Auto V', 29.99, 'Enter the sprawling city of Los Santos in this crime-filled adventure.', 'https://hips.hearstapps.com/digitalspyuk.cdnds.net/13/14/gaming-gta5-cover.jpeg?resize=980:*');
INSERT INTO videogames (name, price, description, photo) VALUES ('Minecraft', 19.99, 'Unleash your creativity in the pixelated world of blocks and adventures.', 'https://upload.wikimedia.org/wikinews/en/7/7a/Minecraft_game_cover.jpeg');

INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-20', 'Supplier F', 7.49, 200, 2);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-19', 'Supplier G', 6.99, 120, 3);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-25', 'Supplier A', 10.99, 100, 1);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-22', 'Supplier D', 8.75, 90, 2);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-23', 'Supplier C', 11.49, 120, 1);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-21', 'Supplier E', 12.99, 150, 2);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-18', 'Supplier H', 15.25, 75, 3);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-17', 'Supplier I', 11.99, 110, 3);
INSERT INTO stocks (purchase_date, supplier_name, price, quantity, videogame_id) VALUES ('2024-01-24', 'Supplier B', 9.99, 80, 1);

INSERT INTO limits (id, videogame_lower_bound, videogame_middle_bound) VALUES(1, 100, 200);

INSERT INTO purchases (purchase_date, quantity, videogame_id) VALUES ('2024-01-29', 100, 3);
INSERT INTO purchases (purchase_date, quantity, videogame_id) VALUES ('2024-01-28', 27, 1);
INSERT INTO purchases (purchase_date, quantity, videogame_id) VALUES ('2023-12-27', 90, 2);
INSERT INTO purchases (purchase_date, quantity, videogame_id) VALUES ('2024-01-26', 3, 3);
INSERT INTO purchases (purchase_date, quantity, videogame_id) VALUES ('2023-12-25', 1, 1);
INSERT INTO purchases (purchase_date, quantity, videogame_id) VALUES ('2024-01-24', 2, 2);
INSERT INTO purchases (purchase_date, quantity, videogame_id) VALUES ('2023-12-23', 1, 3);
INSERT INTO purchases (purchase_date, quantity, videogame_id) VALUES ('2024-01-22', 3, 1);
INSERT INTO purchases (purchase_date, quantity, videogame_id) VALUES ('2023-12-21', 1, 2);