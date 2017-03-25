package eu.ubis.fiimdb.db.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.db.entity.GenreEntity;
import eu.ubis.fiimdb.db.entity.MovieEntity;

public class MovieRepository {
	private static final String GET_ALL_MOVIES_SQL = "SELECT m.id as id_movie, m.release_date, m.name, m.rating, m.length, m.casting, m.director, m.description, m.writer, g.id as id_genre, g.type"
			+ " FROM movie m " + "JOIN movie_genre mg ON m.id = mg.id_movie " + "JOIN genre g ON g.id = mg.id_genre";

	public List<MovieEntity> getAllMovies() {
		Connection con = ConnectionHelper.getConnection();
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		
		try {
			ResultSet resultSet = con.createStatement().executeQuery(GET_ALL_MOVIES_SQL);
			
			while (resultSet.next()) {
				MovieEntity movie = new MovieEntity();
				
				movie.setId(resultSet.getInt("id_movie"));
				movie.setReleaseDate(resultSet.getDate("release_date"));
				movie.setName(resultSet.getString("name"));
				movie.setRating(resultSet.getDouble("rating"));
				movie.setLength(resultSet.getInt("length"));
				movie.setCasting(resultSet.getString("casting"));
				movie.setDirector(resultSet.getString("director"));
				if (resultSet.getString("description") != null) {
					movie.setDescription(resultSet.getString("description"));
				} else {
					movie.setDescription("");
				}
				
				movie.setWriter(resultSet.getString("writer"));

//				GenreEntity genre = new GenreEntity();
//				genre.setId(resultSet.getInt("id_genre"));
//				genre.setType(resultSet.getString("type"));
//
//				MovieEntity movieEntity = new MovieEntity();
//				movieEntity.getGenre().add(genre);
//				movie.getGenre().add(genre);
//				
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}

	public List<MovieEntity> search(String criteria, String value) {
		Connection con = ConnectionHelper.getConnection();
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		String query = GET_ALL_MOVIES_SQL + " WHERE ";
		switch (criteria) {
		case "name":
			query += "m.name LIKE '%" + value + "%'";
			break;
		case "genre":
			query += "g.type LIKE '%" + value + "%'";
			break;
		case "year":
			query += "year(m.release_date) = " + Integer.parseInt(value);
			break;
		default:
			query = GET_ALL_MOVIES_SQL;
			break;
		}
		try {
			ResultSet resultSet = con.createStatement().executeQuery(query);
			while (resultSet.next()) {
				MovieEntity movie = new MovieEntity();
				movie.setId(resultSet.getInt("id_movie"));
				movie.setReleaseDate(resultSet.getDate("release_date"));
				movie.setName(resultSet.getString("name"));
				movie.setRating(resultSet.getDouble("rating"));
				movie.setLength(resultSet.getInt("length"));
				movie.setCasting(resultSet.getString("casting"));
				movie.setDirector(resultSet.getString("director"));
				if (resultSet.getString("description") != null) {
					movie.setDescription(resultSet.getString("description"));
				} else {
					movie.setDescription("");
				}
				movie.setWriter(resultSet.getString("writer"));
				
//				GenreEntity genre = new GenreEntity();
//				genre.setId(resultSet.getInt("id_genre"));
//				genre.setType(resultSet.getString("type"));
//
//				MovieEntity movieEntity = new MovieEntity();
//				movies = new ArrayList<MovieEntity>();
//				movieEntity.getGenre().add(genre);
//				movie.getGenre().add(genre);
//				
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}
}