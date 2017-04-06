package eu.ubis.fiimdb.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(schema="fiimdb", name="movie")
//@NamedQuery(name="getAllMovies", query="SELECT m FROM MovieDao m")
public class MovieDao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="release_date")
	private Date releaseDate;
	private String name;
	private double rating;
	private int length;
	private String casting;
	private String director;
	private String description;
	private String writer;
	
	@ManyToMany
	@JoinTable(name="movie_genre",
		joinColumns = @JoinColumn(name="id_movie", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name="id_genre", referencedColumnName="id"))
	private List<GenreDao> genres;
	
	public MovieDao() {
		this.genres = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCasting() {
		return casting;
	}

	public void setCasting(String casting) {
		this.casting = casting;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public List<GenreDao> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDao> genres) {
		this.genres = genres;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MovieDao)) {
			return false;
		}
		return ((MovieDao) obj).getId() == this.id;
	}
}
