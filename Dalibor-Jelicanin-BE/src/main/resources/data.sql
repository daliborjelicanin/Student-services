INSERT INTO subject (name, description, no_of_esp, year_of_study, semester) VALUES 
('Programiranje 1', 'p 1', 5, 1, 'Summer'),
('Matematika 1', 'm 1', 6, 1, 'Winter'),
('Principi programiranja', 'pp', 6, 2, 'Winter'),
('Finansije i racunovodstvo', 'fmir', 5, 2, 'Summer'),
('Programski jezici', 'pj 1', 5, 3, 'Summer'),
('Teorija sistema', 'ts', 5, 3, 'Winter'),
('Projektovanje softvera', 'ps 1', 6, 4, 'Winter'),
('Napredne Java tehnologije', 'njt 1', 4, 4, 'Summer');


INSERT INTO	city (postal_code, name) VALUES
(11000, 'Beograd'),
(13000, 'Pancevo'),
(22000, 'Subotica'),
(36210, 'Vrnjacka Banja');

INSERT INTO	title (id, title_name) VALUES
(1, 'Teaching_assistant'),
(2, 'Assistant_Professor'),
(3, 'Associate_Professor'),
(4, 'Professor');

INSERT INTO student (index_number, index_year, firstName, lastName, email, address, current_year_of_study, postal_code) VALUES
('0015', 2016, 'Marko', 'Markovic', 'marko@gmail.com', 'Bulevar Mihaila Pupina 10', 1, 11000),
('0123', 2016, 'Ana', 'Anic', 'ana@gmail.com', 'Zicka 5', 2, 22000),
('0245', 2016, 'Pera', 'Peric', 'pera@gmail.com', 'Vojvode Stepe 120', 3, 36210),
('0111', 2016, 'Maja', 'Majic', 'maja@gmail.com', 'Vojvode Stepe 115', 4, 11000);

INSERT INTO professor(first_name, last_name, email, address, phone, reelection_date, postal_code, title) VALUES
('Zika', 'Zikic', 'zika@gmail.com', 'Jove Ilica 88', '065123123', '2016-02-15', '36210', '4'),
('Jana', 'Janic', 'jana@gmail.com', 'Jove Ilica 99', '063123123', '2018-05-24', '22000', '2'),
('Jovana', 'Jovanic', 'jovana@gmail.com', 'Knez Mihailova 15', '062123123', '2020-11-02', '11000', '1');

INSERT INTO professor_engagement (professor_id, subject_id) VALUES
(1,1), (1,3), (1,4), (2,4), (2,1), (3,3), (3,4);

INSERT INTO exam_period (active, completion_date, name, start_date) VALUES
(TRUE, '2021-05-25', 'May 2021', '2021-05-08'),
(FALSE, '2021-06-20', 'June 2021', '2021-06-01'),
(FALSE, '2021-07-18', 'July 2021', '2021-06-25');

INSERT INTO exam (date, exam_period_id, professor_id, subject_id) VALUES
('2021-04-20', 1, 1, 1),
('2021-04-20', 1, 1, 2),
('2021-06-10', 3, 3, 5),
('2021-06-10', 2, 2, 7),
('2021-06-10', 3, 3, 3);

INSERT INTO exam_registration(student_id, exam_id, date) VALUES
(1, 1, '2021-04-20'), (1, 2, '2021-04-20'), (3, 1, '2021-04-20'),
(3, 3, '2021-04-20'), (4, 4, '2021-04-20');






