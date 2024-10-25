CREATE TABLE if not exists car (
                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                     make TEXT NOT NULL,
                     vin TEXT NOT NULL UNIQUE,
                     license_plate TEXT NOT NULL UNIQUE,
                     year_of_manufacture INTEGER NOT NULL CHECK (year_of_manufacture >= 1900),
                     mileage INTEGER CHECK (mileage >= 0)
);

CREATE TABLE if not exists  renter (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        first_name TEXT NOT NULL,
                        last_name TEXT NOT NULL,
                        id_document TEXT NOT NULL UNIQUE,
                        driver_license TEXT NOT NULL UNIQUE
);

CREATE TABLE if not exists  rental (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        car_id INTEGER NOT NULL,
                        renter_id INTEGER NOT NULL,
                        start_date DATE NOT NULL,
                        end_date DATE NOT NULL,
                        price_per_day REAL CHECK (price_per_day >= 0),
                        total_price REAL CHECK (total_price >= 0),
                        FOREIGN KEY (car_id) REFERENCES Car(id),
                        FOREIGN KEY (renter_id) REFERENCES Renter(id)
);
