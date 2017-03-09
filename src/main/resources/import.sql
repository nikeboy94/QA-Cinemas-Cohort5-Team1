INSERT INTO User(email, name, password, salt) VALUES('a1@email.com', 'Name 1', 'pa$$word 1', 'salt1');
INSERT INTO User(email, name, password, salt) VALUES('a2@email.com', 'Name 2', 'pa$$word 2', 'salt2');
INSERT INTO Movie(movieID, title, genre, releaseDate, classification, posterUrl, trailerUrl, rating, runtime, description) VALUES (1,'Title 1', 'Comedy', '2017-01-13', '12', 'movie-poster.com', 'movie-trailer.com', 4.6, 120, 'film description...');
INSERT INTO Screen(id, screenType, screenDesc) VALUES(1, 'screen type', 'screen description');
INSERT INTO Showing(showingId, screenId, movieId, dateTime) VALUES(1, 1, 1, '2017-01-13');
INSERT INTO Seat(seatId, screenId) VALUES (1,1);
INSERT INTO Ticket(ticketId, price, orderId, seatId, showingId, email) VALUES(1,'1','a', 1, 1, 'a1@email.com');