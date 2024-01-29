-- types
INSERT INTO types (name, description) VALUES ('Action', 'Games focused on physical challenges and hand-eye coordination.');
INSERT INTO types (name, description) VALUES ('Strategy', 'Games that emphasize thinking and planning.');

INSERT INTO videogames (name, price, description, photo) VALUES ('Assassin\'s Creed: Valhalla', 59.99, 'Explore the Viking Age in this action-packed adventure.', 'https://m.media-amazon.com/images/I/71WbI7557yL.jpg');
INSERT INTO videogames (name, price, description, photo) VALUES ('Cyberpunk 2077', 49.99, 'Immerse yourself in the dystopian future of Night City.', 'https://www.codiciprodotto.it/wp-content/uploads/2021/01/Cyberpunk-2077-PC-COVER.jpg');
INSERT INTO videogames (name, price, description, photo) VALUES ('Kingdom Hearts III', 49.99, 'Embark on a magical journey with Sora, Donald, and Goofy to save various Disney and Pixar worlds.', 'https://www.everyeye.it/public/immagini/18092018/kingdom-hearts-3_notizia.jpg');
INSERT INTO videogames (name, price, description, photo) VALUES ('FC 24', 39.99, 'Experience the excitement of virtual football with realistic gameplay.', 'https://fifauteam.com/images/covers/fc24/hd/standard.webp');
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

