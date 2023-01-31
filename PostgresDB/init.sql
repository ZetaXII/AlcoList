-- INIT ROLES
insert into public.role(name) values ('WAITER'),('BARTENDER'),('MANAGER');

-- INIT USERS
INSERT INTO public.user_account(delete_date, email, main_role, name, password, surname, uuid) VALUES
-- pwd ciao123
(null, 'admin@alcolist.it', 'MANAGER', 'Admin', 'cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0', 'Alcolist', '0ax6659a-8ae0-11ed-a1eb-0242ac120001'),
-- pwd: ciao
(null, 'bartender@alcolist.it', 'BARTENDER', 'Bart', 'b133a0c0e9bee3be20163d2ad31d6248db292aa6dcb1ee087a2aa50e0fc75ae2', 'Ender', '1fa5825f-8dc3-4357-8e3b-11b87fd292e5'),
-- pwd utente
(null, 'waiter@alcolist.it', 'WAITER', 'Came', '44c2dbe2c24719aae64ed42989c9e3f1e504474d0f4871bc26bab6695f95d912', 'Riere', '2ax6659a-8dc3-4357-a1eb-0242ac120001'),
-- pwd password
(null, 'andy@alcolist.it', 'BARTENDER', 'Andrea', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'Giusto', '3ax6659a-8ae0-11ed-a1eb-11b87c120001'),
-- pwd nuova
(null, 'miky@alcolist.it', 'WAITER', 'Michele', '7a02758fc055f33516228d85aae1013704625a6bc82fd91b0f9578ec441073ca', 'Buono', '4ax6659a-8ae0-11ed-a1eb-11b87c120001'),
-- pwd diciotto
(null, 'credici@alcolist.it', 'WAITER', 'Prendo', '19d5652442f89e60302fab3feb34e30ab7f784f957fa984c2c0899ee1b97cc1b', 'Trenta', '5ax6659a-8ae0-11ed-a1eb-11b87c120001');

-- INIT ACCOUNT ROLES
INSERT INTO public.user_account_roles(user_account_id, roles_id)
	VALUES (1, 1),
	VALUES (1, 2),
	VALUES (1, 3),
	VALUES (2, 2),
	VALUES (3, 1),
	VALUES (4, 1),
	VALUES (4, 2),
	VALUES (5, 1),
	VALUES (6, 1);

-- INIT TABLES
INSERT INTO public.tables(number, uuid, isfree) values
('1', '01c6659a-8ae0-11ed-a1eb-0242ac120001', 'true'),
('2', '09a7ae50-8ae0-11ed-a1eb-0242ac620024', 'true'),
('3', '14648296-8ae0-11ed-a1eb-0242ac320122', 'true'),
('4', '964f25e1-f435-40f8-b999-8cb197eddqc3', 'true'),
('5', '41d339c1-b5db-4286-bf50-195bd621239a', 'true'),
('6', '4215ce82-bf79-4cbe-9084-fcd1e0a46e25', 'true'),
('7', 'a1b7b172-a16c-4a2b-b213-62ff1324c848', 'true'),
('8', '1358344c-68ac-4a69-8fb3-ed85cdf11a78', 'true'),
('9', '2e871784-4a65-4676-a944-a7s911q491f8', 'true'),
('10', '918bed1d-8915-4049-ab55-9c12d13621r4', 'true'),
('11', '57ef8f40-d47e-43bf-aa36-c873055ae321', 'true'),
('12', '12jh3h43-d47e-43bf-aa36-c873095ae309', 'true');

-- INIT PRODOTTI
INSERT INTO public.product(alcoholic_percentage, category, is_present, ml, name, pathfileimg, uuid) values 
(40, 'GIN', true, 700, 'BOMBEI', null, 'bc8d67ea-8ada-11ed-a1eb-0242ac120002'),
('42', 'GIN', true, 700, 'MARE', null,'fd43db20-8ada-11ed-a1eb-0242ac120002'),
('39', 'AMARO', true, 750, 'FERNET BRANCA', null, 'bddbd3fe-8add-11ed-a1eb-0242ac120002'),
('35', 'AMARO', true, 750, 'DEL CAPO', null, 'c566c494-8add-11ed-a1eb-0242ac120002'),
('45', 'AMARO', true, 750, 'PETRUS', null, 'c9f78098-8add-11ed-a1eb-0242ac120002'),
('40', 'AMARO', true, 750, 'UNICUM', null, 'ce3d563c-8add-11ed-a1eb-0242ac120002'),
('18', 'VERMUT', true, 750, 'MARTINI RISERVA SPECIALE RUBINO', null, 'd47c9f30-8add-11ed-a1eb-0242ac120002'),
('16', 'VERMUT', true, 750, 'COCCHI', null, 'dace3790-8add-11ed-a1eb-0242ac120002'),
('18', 'VERMUT', true, 750, 'DEL PROFESSORE BIANCO', null, 'e03830dc-8add-11ed-a1eb-0242ac120002'),
('25', 'BITTER', true, 750, 'CAMPARI', null, 'e41580a6-8add-11ed-a1eb-0242ac120002'),
('28.5', 'BITTER', true, 700, 'MARTINI RISERVA SPECIALE', null, 'e8cd8170-8add-11ed-a1eb-0242ac120002'),
('44.5', 'BITTER', true, 200, 'ANGOSTURA', null, 'f0a75650-8add-11ed-a1eb-0242ac120002'),
('0', 'ACQUA TONICA', true, 1000, 'SCHWEPPES', null, 'f7727578-8add-11ed-a1eb-0242ac120002'),
('0', 'ACQUA TONICA', true, 200, 'FEVER-TREE INDIAN PREMIUM', null, 'fff31810-8add-11ed-a1eb-0242ac120002'),
('0', 'SODA', true, 200, 'FEVER-TREE SODA WATER', null, 'ec58472c-fcaa-481b-9345-1976a9458565'),
('0', 'SODA', true, 200, 'SCHWEPPES SODA', null, '184dc332-1d22-48a7-b478-8ebc23fd1323'),
('18', 'VINO', true, 750, 'MARSALA', null, 'fbb019b0-8add-11ed-a1eb-0242ac120002'),
('13', 'VINO', true, 750, 'AGLIANO', null, '04aaaf30-8ade-11ed-a1eb-0242ac120002'),
('13', 'VINO', true, 750, 'FALANGHINA', null, '0953118a-8ade-11ed-a1eb-0242ac120002'),
('12', 'PROSECCO', true, 750, 'MOET IMPERIAL', null, '0c7af328-8ade-11ed-a1eb-0242ac120002'),
('12', 'PROSECCO', true, 750, 'FERRARI', null, '0fd8a79a-8ade-11ed-a1eb-0242ac120002'),
('11', 'PROSECCO', true, 750, 'VAL Di OCA', null, '1551a924-8ade-11ed-a1eb-0242ac120002'),
('31', 'TEQUILA', true, 750, 'LICOR 43', null, '1820fbdc-8ade-11ed-a1eb-0242ac120002'),
('48', 'TEQUILA', true, 750, 'JOSE CUERVO CLASSICO', null, '1d238334-8ade-11ed-a1eb-0242ac120002'),
('0', 'SCIROPPO', true, null, 'DI ZUCCHERO', null, '1d999999-8ade-11ed-a1eb-0242ac120002'),
('0', 'SUCCO', true, null, 'DI LIME', null, '1d888888-8ade-11ed-a1eb-0242ac120002'),
('0', 'ALBUME', true, null, 'DI UOVO', null, '1d777777-8ade-11ed-a1eb-0242ac120002'),
('45', 'WHISKEY', true, 700, 'BOURBON', null, '1d666666-8ade-11ed-a1eb-0242ac120002'),
('0', 'SCIROPPO', true, null, 'DI ZUCCHERO', null, '1d555555-8ade-11ed-a1eb-0242ac120002'),
('0', 'SUCCO', true, null, 'DI ARANCIA', null, '1d444444-8bce-11ed-a1eb-0242ac120002'),
('0', 'SCIROPPO', true, null, 'DI GRANATINA', null, '1d333333-8ade-11ed-a1eb-0242ac120002'),
('40', 'VODKA', true, 750, 'ABSOLUTE', null, '1d444444-8ace-11ed-a1eb-0242ac120002'),
('0', 'GINGER', true, null, 'BEER', null, '1d333333-8ade-121d-a1eb-0242ac120002');

--INIT COCKTAILS
INSERT INTO public.cocktail(description, flavour, inmenu, isalcoholic, isiba, name, pathfileimg, price, uuid, sold) values
('Descrizione americano', 'Amaro', 'true', 'true', 'true', 'Americano', 'https://www.pausacaffeblog.it/wp/wp-content/uploads/2017/02/cocktailamericano.png', '6', '02c6659a-8ae0-11ed-a1eb-0242ac120002',0),
('Descrizione negroni', 'Amaro', 'true', 'true', 'true', 'Negroni', 'https://www.carpano.com/wp-content/uploads/2016/11/negroni1.png', '5.5', '09a7ae50-8ae0-11ed-a1eb-0242ac120002',0),
('Descrizione gin tonic', 'Amaro', 'true', 'true', 'true', 'Gin Tonic', 'https://www.lalocandadeifeudi.it/wp-content/uploads/2022/05/gin-tonic.png', '6', '14648296-8ae0-11ed-a1eb-0242ac120002',0),
('Descrizione Tequila sour', 'Dolce', 'true', 'true', 'true', 'Tequila Sour', 'https://assets.bonappetit.com/photos/5c2e710bf212512d0e6cef0a/1:1/w_2560%2Cc_limit/Mezcal-Sour.png', '7', '964f23e1-f435-40f8-b999-8cb197edd4c3',0),
('Descrizione new york sour', 'Dolce', 'true', 'true', 'true', 'New York Sour', 'https://thetastingalliance.com/wp-content/uploads/New-York-Sour.png', '8', '41d399c1-b5db-4286-bf50-195bd621439a',0),
('Il Tequila Sunrise è un cocktail alcolico internazionale riconosciuto ufficialmente dalla International Bartenders Association a base di Tequila.', 'Dolce', 'true', 'true', 'true', 'Tequila Sunrise', 'https://tequilasanjose.com/en/wp-content/uploads/sites/2/2021/04/tequilasunrise.png', '7.5','42b5ce82-bf79-4cbe-9084-fcd1e0a46e25',0),
('Il Moscow mule è un cocktail dal sapore pungente, fresco e dissetante: perfetto per le serate estive è semplice da preparare anche a casa. Ci occorrono solo 3 ingredienti: vodka, succo di lime e ginger beer. Il gusto particolare di questo drink è dato proprio dal mix di lime e zenzero.', 'Aspro', 'true', 'true', 'true', 'Moscow Mule', 'http://www.elbarin.it/wp-content/uploads/2019/11/MoscowMule-BIG.png', '7.5', 'a1b7bc72-a16c-4a2b-b213-62ff1fd4c848',0),
('Descrizione del vino rosso', 'Amaro', 'true', 'true', 'false', 'Calice di Vino Rosso', 'https://www.enotecaalbuonvinotorino.com/wp-content/uploads/2018/12/calice-rosso.png', '4.5', '2f871784-4a65-4676-a944-a7b910c491f8',0),
('Descrizione del vino bianco', 'Amaro', 'true', 'true', 'false', 'Calice di Vino Bianco', 'https://bormiolirocco.com/upload/magazine/wysiwyg/2019-11-22-071404-2b8e0d1e6e4930cb354650224c74e992.png', '4.5', '958bed1d-8915-4049-ab55-9c52d13621e9',0),
('Moët Impérial è lo champagne iconico della Maison. Creato nel 1869, incarna lo stile unico di Moët & Chandon, uno stile che si distingue per il suo fruttato brillante, il suo palato seducente e la sua elegante maturità.', 'Secco', 'true', 'true', 'false', 'Calice di Prosecco', 'https://d2j6dbq0eux0bg.cloudfront.net/images/17725047/1125459536.jpg', '3', '574f8f40-d47e-43bf-aa36-c879055ae315',0),
('Armonico ed equilibrato, con un lieve fondo di frutta matura e piacevoli sensazioni di crosta di pane.', 'Secco', 'true', 'true', 'false', 'Calice di Prosecco', 'https://d2j6dbq0eux0bg.cloudfront.net/images/17725047/1125459536.jpg', '3', 'faf21883-ddde-4bfc-9ddf-d8b519df87ea',0),
('Amaro del capo è un liquore di erbe frutto di un’antica ricetta Calabrese. Il vecchio Amaro del Capo racchiude in sé i principi attivi di ben 29 Erbe benefiche, servito ad una temperatura di -20 è un ottimo digestivo, grazie anche ai suoi ingredienti principali: Arancia amara, Ginepro, Liquirizia, Miele.', 'Amaro', 'true', 'true', 'false', 'Amaro Del Capo', 'https://enotecadelfrate.b-cdn.net/wp-content/uploads/2018/05/Vecchio-Amaro-del-Capo.png', '2.5', 'ab500ecd-38d3-4492-a338-133925887fef',0),
('Il Petrus Boonekamp è un amaro di origine olandese, creato nel 1777 a Leidschendam dal liquorista Petrus Boonekamp, la cui esistenza è dimostrata da fonti locali.', 'Amaro', 'true', 'true', 'false', 'Amaro Petrus', 'https://70cl.it/2522-large_default/amaro-petrus-boonekamp-70cl.jpg', '2.5', '8e67b1ed-c931-45eb-a3ee-2b45a1a38eb1',0);

-- INIT INGREDIENTI
INSERT INTO public.ingredient(is_optional, is_present, quantity, uuid, cocktail, product) values
-- AMERICANO 
('false', 'true', '30', '01c6659a-8ae0-11ed-a1eb-0242ac120002', '1', '10'),
('false', 'true', '30', '09a7ae50-8ae0-11ed-a1eb-0242ac620002', '1', '7'),
('false', 'true', '10', '14648296-8ae0-11ed-a1eb-0242ac320002', '1', '15'),
-- NEGRONI
('false', 'true', '30', '964f25e1-f435-40f8-b999-8cb197edd4c3', '2', '10'),
('false', 'true', '30', '41d339c1-b5db-4286-bf50-195bd621439a', '2', '2'),
('false', 'true', '30', '4215ce82-bf79-4cbe-9084-fcd1e0a46e25', '2', '7'),
-- GIN TONIC
('false', 'true', '50', 'a1b7b172-a16c-4a2b-b213-62ff1fd4c848', '3', '2'),
('false', 'true', '10', '1358344c-68ac-4a69-8fb3-ed85cf411a78', '3', '13'),
-- TEQUILA SOUR
('false', 'true', '100', '2e871784-4a65-4676-a944-a7s910c491f8', '4', '23'),
('false', 'true', '25', '918bed1d-8915-4049-ab55-9c12d13621e9', '4', '25'),
('false', 'true', '50', '57ef8f40-d47e-43bf-aa36-c873055ae315', '4', '26'),
('false', 'true', '30', '12jh3h43-d47e-43bf-aa36-c873095ae315', '4', '27'),
-- NEW YOURK SOUR
('false', 'true', '60', 'fede1245-4a65-4676-a944-a7s910c491f8', '5', '28'),
('false', 'true', '2', 'maria231-8915-4049-ab55-9c12d13621e9', '5', '29'),
('false', 'true', '30', 'mario214-d47e-43bf-aa36-c873055ae315', '5', '26'),
('false', 'true', '15', 'melania1-d47e-43bf-aa36-c873095ae315', '5', '17'),
('false', 'true', '30', 'melany31-d47e-43bf-aa36-c873095ae315', '5', '27'),
-- TEQUILA SUNRISE
('false', 'true', '120', 'aaaaa214-d47e-43bf-aa36-c873055ae315', '6', '30'),
('false', 'true', '60', 'ccccccc1-d47e-43bf-aa36-c873095ae315', '6', '24'),
('false', 'true', '20', 'bababab1-d47e-43bf-aa36-c873095ae315', '6', '31'),
-- MOSCOW MULE
('false', 'true', '45', '2f871784-4a65-4676-a944-a7b910c491f8', '7', '32'),
('false', 'true', '15', '958bed1d-8915-4049-ab55-9c52d13621e9', '7', '26'),
('false', 'true', '10', '574f8f40-d47e-43bf-aa36-c879055ae315', '7', '33'),
-- CALICE DI VINO ROSSO
('false', 'true', '50', 'faf21883-ddde-4bfc-9ddf-d8b519df87ea', '8', '18'),
-- CALICE DI VINO BIANCO
('false', 'true', '50', 'ab500ecd-38d3-4492-a338-133925887fef', '9', '19'),
-- PROSECCO MOET
('false', 'true', '250', '8e67b1ed-c931-45eb-a3ee-2b45a1a38eb1', '10', '20'),
-- PROSECCO FERRARI
('false', 'true', '250', '8af3b1ed-c931-45eb-a3ee-2b45a1a38eb1', '11', '21'),
-- AMARO DEL CAPO
('false', 'true', '40', '1ertb1ed-c931-45eb-a3ee-2b45a1a38eb1', '12', '4'),
-- PETRUS
('false', 'true', '40', '7poib1ed-c931-45eb-a3ee-2b45a1a38eb1', '13', '5');

-- INIT ORDINAZIONI
INSERT INTO public.ordination(date_creation, date_last_modified, numbers_of_cocktails, status, total, uuid, created_by, delivered_by, executed_by, id_tables)
VALUES ('2023-01-30 22:48:11.206', '2023-01-30 22:48:11.206', 4, 6, 27.5, 'a5f60a52-218b-4cc6-9723-9045cd586677', 1, null, null, 1),
VALUES ('2023-01-31 10:36:32.571', '2023-01-31 10:36:32.571', 4, 6, 22, '9f5d0c46-10c0-42f4-ac28-215fbf7f81c6', 3, null, null, 2),
VALUES ('2023-01-31 10:36:46.655', '2023-01-31 10:36:46.655', 2, 6, 13.5, '5910f178-b177-45d9-8d1f-dcab59d998cc', 3, null, null, 8),
VALUES ('2023-01-31 10:42:47.02', '2023-01-31 10:42:47.02', 6, 6, 16.5, 'c8bb068d-b2cf-46c9-b8fa-cfee01cb173e', 5, null, null, 9),

-- INIT COCKTAIL ORDINATI
INSERT INTO public.ordered_cocktail(quantity, cocktail, ordination)
	VALUES (1, 1, 1),
	VALUES (2, 4, 1),
	VALUES (1, 6, 1),
	VALUES (1, 2, 2),
	VALUES (2, 9, 2),
	VALUES (1, 7, 2),
	VALUES (1, 3, 3),
	VALUES (1, 6, 3),
	VALUES (2, 11, 4),
	VALUES (2, 13, 4),
	VALUES (1, 12, 4),
	VALUES (1, 10, 4);


