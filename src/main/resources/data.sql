INSERT INTO users (id, email, first_name, last_name, image_url, is_active, password)
VALUES (1, 'vesko@gmail.com', 'Ivan', 'Ivanov', null, 1, 'eb6a7756a62357ed0a9bee712ffa4b0825d0f9e098feba9ad2f2a1d2a611ac0cc3856b24bddf5cab');


INSERT INTO brands(id, name)
VALUES (1, 'Ford'),
       (2, 'Toyota');

INSERT INTO models(id, name, category, start_year, end_year, brand_id, image_url)
VALUES (1, 'Fiesta', 'CAR', 1976, null, 1, 'https://upload.wikimedia.org/wikipedia/commons/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg'),
       (2, 'Escort', 'CAR', 1968, 2000, 1, 'https://www.auto-data.net/images/f110/Ford-Escort-VII-Hatch-GAL-AFL.jpg'),
       (3, 'Yaris', 'CAR', 1999, 2000, 2, 'https://upload.wikimedia.org/wikipedia/commons/3/3e/2020_Toyota_Yaris_Design_HEV_CVT_1.5_Front.jpg');