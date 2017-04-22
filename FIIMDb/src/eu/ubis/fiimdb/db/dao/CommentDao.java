package eu.ubis.fiimdb.db.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="fiimdb", name="comment")
public class CommentDao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="comment_message")
	private String comment;
	private int id_user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name="movie_comment",
		joinColumns = @JoinColumn(name="id_comment", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name="id_movie", referencedColumnName="id"))
		
	private MovieDao movie;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getUserId() {
		return id_user;
	}
	public void setUserId(int id_user) {
		this.id_user = id_user;
	}
	public MovieDao getMovie() {
		return movie;
	}
	public void setMovie(MovieDao movie) {
		this.movie = movie;
	}	
}
