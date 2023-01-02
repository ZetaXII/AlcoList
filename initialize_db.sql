-- INIT ROLES
insert into public.role(name) values ('WAITER'),('BARTENDER'),('MANAGER');

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



INSERT INTO public.cocktail(description, flavour, inmenu, isalcoholic, isiba, name, pathfileimg, price, uuid) values
('Descrizione americano', 'Amaro', 'true', 'true', 'true', 'Americano', null, '6', '02c6659a-8ae0-11ed-a1eb-0242ac120002'),
('Descrizione negroni', 'Amaro', 'true', 'true', 'true', 'Negroni', null, '5.5', '09a7ae50-8ae0-11ed-a1eb-0242ac120002'),
('Descrizione gin tonic', 'Amaro', 'true', 'true', 'true', 'Gin Tonic', null, '6', '14648296-8ae0-11ed-a1eb-0242ac120002'),
('Descrizione Tequila sour', 'Dolce', 'true', 'true', 'true', 'Tequila Sour', null, '7', '964f23e1-f435-40f8-b999-8cb197edd4c3'),
('Descrizione new york sour', 'Dolce', 'true', 'true', 'true', 'New York Sour', null, '8', '41d399c1-b5db-4286-bf50-195bd621439a'),
('Il Tequila Sunrise è un cocktail alcolico internazionale riconosciuto ufficialmente dalla International Bartenders Association a base di Tequila.', 'Dolce', 'true', 'true', 'true', 'Tequila Sunrise', null, '7.5','42b5ce82-bf79-4cbe-9084-fcd1e0a46e25'),
('Il Moscow mule è un cocktail dal sapore pungente, fresco e dissetante: perfetto per le serate estive è semplice da preparare anche a casa. Ci occorrono solo 3 ingredienti: vodka, succo di lime e ginger beer. Il gusto particolare di questo drink è dato proprio dal mix di lime e zenzero.', 'Aspro', 'true', 'true', 'true', 'Moscow Mule', null, '7.5', 'a1b7bc72-a16c-4a2b-b213-62ff1fd4c848'),
('Descrizione del vino rosso', 'Amaro', 'true', 'true', 'false', 'Calice di Vino Rosso', null, '4.5', '2f871784-4a65-4676-a944-a7b910c491f8'),
('Descrizione del vino bianco', 'Amaro', 'true', 'true', 'false', 'Calice di Vino Bianco', null, '4.5', '958bed1d-8915-4049-ab55-9c52d13621e9'),
('Moët Impérial è lo champagne iconico della Maison. Creato nel 1869, incarna lo stile unico di Moët & Chandon, uno stile che si distingue per il suo fruttato brillante, il suo palato seducente e la sua elegante maturità.', 'Secco', 'true', 'true', 'false', 'Calice di Prosecco', null, '3', '574f8f40-d47e-43bf-aa36-c879055ae315'),
('Armonico ed equilibrato, con un lieve fondo di frutta matura e piacevoli sensazioni di crosta di pane.', 'Secco', 'true', 'true', 'false', 'Calice di Prosecco', null, '3', 'faf21883-ddde-4bfc-9ddf-d8b519df87ea'),
('Amaro del capo è un liquore di erbe frutto di un’antica ricetta Calabrese. Il vecchio Amaro del Capo racchiude in sé i principi attivi di ben 29 Erbe benefiche, servito ad una temperatura di -20 è un ottimo digestivo, grazie anche ai suoi ingredienti principali: Arancia amara, Ginepro, Liquirizia, Miele.', 'Amaro', 'true', 'true', 'false', 'Amaro Del Capo', null, '2.5', 'ab500ecd-38d3-4492-a338-133925887fef'),
('Il Petrus Boonekamp è un amaro di origine olandese, creato nel 1777 a Leidschendam dal liquorista Petrus Boonekamp, la cui esistenza è dimostrata da fonti locali.', 'Amaro', 'true', 'true', 'false', 'Amaro Petrus', null, '2.5', '8e67b1ed-c931-45eb-a3ee-2b45a1a38eb1');





INSERT INTO public.ingredient(is_optional, quantity, uuid, cocktail, product) values
-- AMERICANO 
('false', '30', '01c6659a-8ae0-11ed-a1eb-0242ac120002', '1', '10'),
('false', '30', '09a7ae50-8ae0-11ed-a1eb-0242ac620002', '1', '7'),
('false', '10', '14648296-8ae0-11ed-a1eb-0242ac320002', '1', '15'),
-- NEGRONI
('false', '30', '964f25e1-f435-40f8-b999-8cb197edd4c3', '2', '10'),
('false', '30', '41d339c1-b5db-4286-bf50-195bd621439a', '2', '2'),
('false', '30', '4215ce82-bf79-4cbe-9084-fcd1e0a46e25', '2', '7'),
-- GIN TONIC
('false', '50', 'a1b7b172-a16c-4a2b-b213-62ff1fd4c848', '3', '2'),
('false', '10', '1358344c-68ac-4a69-8fb3-ed85cf411a78', '3', '13'),
-- TEQUILA SOUR
('false', '100', '2e871784-4a65-4676-a944-a7s910c491f8', '4', '23'),
('false', '25', '918bed1d-8915-4049-ab55-9c12d13621e9', '4', '25'),
('false', '50', '57ef8f40-d47e-43bf-aa36-c873055ae315', '4', '26'),
('false', '30', '12jh3h43-d47e-43bf-aa36-c873095ae315', '4', '27'),
-- NEW YOURK SOUR
('false', '60', 'fede1245-4a65-4676-a944-a7s910c491f8', '5', '28'),
('false', '2', 'maria231-8915-4049-ab55-9c12d13621e9', '5', '29'),
('false', '30', 'mario214-d47e-43bf-aa36-c873055ae315', '5', '26'),
('false', '15', 'melania1-d47e-43bf-aa36-c873095ae315', '5', '17'),
('false', '30', 'melany31-d47e-43bf-aa36-c873095ae315', '5', '27'),
-- TEQUILA SUNRISE
('false', '120', 'aaaaa214-d47e-43bf-aa36-c873055ae315', '6', '30'),
('false', '60', 'ccccccc1-d47e-43bf-aa36-c873095ae315', '6', '24'),
('false', '20', 'bababab1-d47e-43bf-aa36-c873095ae315', '6', '31'),
-- MOSCOW MULE
('false', '45', '2f871784-4a65-4676-a944-a7b910c491f8', '7', '32'),
('false', '15', '958bed1d-8915-4049-ab55-9c52d13621e9', '7', '26'),
('false', '10', '574f8f40-d47e-43bf-aa36-c879055ae315', '7', '33'),
-- CALICE DI VINO ROSSO
('false', '50', 'faf21883-ddde-4bfc-9ddf-d8b519df87ea', '8', '18'),
-- CALICE DI VINO BIANCO
('false', '50', 'ab500ecd-38d3-4492-a338-133925887fef', '9', '19'),
-- PROSECCO MOET
('false', '250', '8e67b1ed-c931-45eb-a3ee-2b45a1a38eb1', '10', '20'),
-- PROSECCO FERRARI
('false', '250', '8af3b1ed-c931-45eb-a3ee-2b45a1a38eb1', '11', '21'),
-- AMARO DEL CAPO
('false', '40', '1ertb1ed-c931-45eb-a3ee-2b45a1a38eb1', '12', '4'),
-- PETRUS
('false', '40', '7poib1ed-c931-45eb-a3ee-2b45a1a38eb1', '13', '5'); 