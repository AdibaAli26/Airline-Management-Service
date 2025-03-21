INSERT INTO flights (flight_number, origin, destination, departure_time, arrival_time, departure_date) VALUES
('AA101', 'New York', 'Los Angeles', '2023-12-01 10:00:00', '2023-12-01 13:00:00', '2023-12-01'),
('UA202', 'Chicago', 'Miami', '2023-12-02 12:00:00', '2023-12-02 15:00:00', '2023-12-02');

INSERT INTO schedules (flight_id, date, available_seats) VALUES
(1, '2023-12-01', 100),
(2, '2023-12-02', 150);
