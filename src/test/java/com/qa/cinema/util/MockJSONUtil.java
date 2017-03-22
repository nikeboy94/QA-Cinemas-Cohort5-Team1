package com.qa.cinema.util;

import java.util.ArrayList;
import java.util.List;
import com.qa.cinema.persistence.Movie;
import com.qa.cinema.persistence.Screen;
import com.qa.cinema.persistence.Showing;

public class MockJSONUtil {

	public static String JSONForShowing(List<Showing> showings) {
		StringBuilder showingSB = new StringBuilder();
		for (Showing showing : showings) {
			List<Screen> screen = new ArrayList<Screen>();
			List<Movie> movie = new ArrayList<Movie>();
			screen.add(showing.getScreen());
			movie.add(showing.getMovie());
			
			showingSB.append("{\"showingId\": \"" + showing.getShowingId()
				+ "\", \"screen\": " + JSONForScreen(screen)
				+ ", \"movie\": " + JSONForMovie(movie)
				+ ", \"dateTime\": \"" + showing.getDateTime() + "\"}");
			
			if (showings.indexOf(showing) != (showings.size() - 1)) {
				showingSB.append(", ");
			}
		}
		return showingSB.toString();
	}
	
	public static String JSONForScreen(List<Screen> screens) {
		StringBuilder screenSB = new StringBuilder();
		for (Screen screen : screens) {
			screenSB.append("{\"screenId\": \"" + screen.getId() 
				+ "\", \"screenType\": \"" + screen.getScreenType()
				+ "\", \"screenDesc\": \"" + screen.getScreenDesc() + "\"}");
			
			if (screens.indexOf(screen) != (screens.size() - 1)) {
				screenSB.append(", ");
			}
		}
		return screenSB.toString();
	}
	
	public static String JSONForMovie(List<Movie> movies) {
		StringBuilder movieSB = new StringBuilder();
		for (Movie movie : movies) {
			movieSB.append("{\"movieId\": \"" + movie.getMovieId()
				+ "\", \"title\": \"" + movie.getTitle()
				+ "\", \"genre\": \"" + movie.getGenre()
				+ "\", \"releaseDate\": \"" + movie.getReleaseDate()
				+ "\", \"classification\": \"" + movie.getClassification()
				+ "\", \"posterUrl\": \"" + movie.getPosterUrl()
				+ "\", \"trailerUrl\": \"" + movie.getTrailerUrl()
				+ "\", \"rating\": \"" + movie.getRating()
				+ "\", \"count\": \"" + movie.getCount()
				+ "\", \"runtime\": \"" + movie.getRuntime()
				+ "\", \"description\": \"" + movie.getDescription() + "\"}");
			
			if (movies.indexOf(movie) != (movies.size() - 1)) {
				movieSB.append(", ");
			}
		}
		return movieSB.toString();
	}
	
}
