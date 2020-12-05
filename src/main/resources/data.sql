DROP TABLE IF EXISTS patients;

CREATE TABLE patients (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  birth_date DATE DEFAULT NULL
);

INSERT INTO patients (first_name, last_name, birth_date) VALUES
  ('Zlatan', 'Ibrahimovic', '1981-10-03'),
  ('Daniel', 'Maldini', '2001-10-11'),
  ('Kjaer', 'Simon', '1989-03-26');