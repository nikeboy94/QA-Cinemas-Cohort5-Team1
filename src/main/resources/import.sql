INSERT INTO User(email, name, password, salt) VALUES('a1@email.com', 'Name 1', 'pa$$word 1', 'salt1');
INSERT INTO User(email, name, password, salt) VALUES('a2@email.com', 'Name 2', 'pa$$word 2', 'salt2');
INSERT INTO Movie(movieID, title, genre, releaseDate, classification, posterUrl, trailerUrl, rating, runtime, description) VALUES (1,'Spider-Man 2', 'Action', '2004-05-01', '12A', 'https://images-na.ssl-images-amazon.com/images/M/MV5BMzY2ODk4NmUtOTVmNi00ZTdkLTlmOWYtMmE2OWVhNTU2OTVkXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,729,1000_AL_.jpg', 'https://www.youtube.com/embed/enmFqm_N_ZE', 4.6, 130, 'film description...');
INSERT INTO Movie(movieID, title, genre, releaseDate, classification, posterUrl, trailerUrl, rating, runtime, description) VALUES (2,'Harry Potter and the Philosophers Stone', 'Fantasy', '2001-08-16', '12A', 'https://images-na.ssl-images-amazon.com/images/M/MV5BNjQ3NWNlNmQtMTE5ZS00MDdmLTlkZjUtZTBlM2UxMGFiMTU3XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_.jpg', 'movie-trailer.com', 4.6, 130, 'film description...');
INSERT INTO Screen(id, screenType, screenDesc) VALUES(1, 'screen type', 'screen description');
INSERT INTO Showing(showingId, screenId, movieId, dateTime) VALUES(1, 1, 1, '2017-01-13');
INSERT INTO Seat(seatId, screenId) VALUES (1,1);
INSERT INTO Ticket(ticketId, price, orderId, seatId, showingId, email) VALUES(1,'1','a', 1, 1, 'a1@email.com');