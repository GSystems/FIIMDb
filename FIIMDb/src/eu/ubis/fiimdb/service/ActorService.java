package eu.ubis.fiimdb.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.ActorDao;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.model.Actor;

public class ActorService {
	
	public Actor getActorByName(String name) {
		Actor actor = new Actor();
		ActorDao actorDao = new ActorDao();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		actorDao = (ActorDao) entityManager.createQuery("SELECT a FROM ActorDao a WHERE a.name LIKE '"+ name +"'").getSingleResult();
		actor = mapActorDaoToActor(actorDao);
		return actor;
	}
	
	private Actor mapActorDaoToActor(ActorDao actorDao) {
		Actor actor = new Actor();
		actor.setName(actorDao.getName());
		actor.setNationality(actorDao.getNationality());
		actor.setAge(actorDao.getAge());
		String movies = mapMovieListToActor(actorDao.getMovies());
		actor.setMovie(movies);
		return actor;
	}
	
	private String mapMovieListToActor(List<MovieDao> movieDao) {
		if(movieDao.size() <= 0) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for(MovieDao md : movieDao) {
			stringBuilder.append(md.getName());
			if(movieDao.indexOf(md) != movieDao.size() - 1) {
				stringBuilder.append(", ");
			}
		}
		return stringBuilder.toString();
	}
}
