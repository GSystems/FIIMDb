package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.IpDao;
import eu.ubis.fiimdb.db.dao.UserDao;
import eu.ubis.fiimdb.model.Ip;

public class IpService {
	
	@SuppressWarnings("unchecked")
	public void saveIp(Ip ip, String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		List<IpDao> ipDao = new ArrayList<IpDao>();
		
		ipDao = entityManager.createQuery("select i from IpDao i JOIN i.user user WHERE user.username LIKE '" + username  + "'").getResultList();
		
		if(ipDao.size() >=1 ) {
			if(ipDao.get(0).getUser().getUsername().equals(ip.getUser())) {
					entityManager.getTransaction().begin();
					int newCount = ipDao.get(0).getCount() + 1;
					ipDao.get(0).setCount(newCount);
					entityManager.getTransaction().commit();
					entityManager.close();
					emf.close();
			} else
				newIp(ip, username);
		} else
			newIp(ip, username);
	}
	
	private void newIp(Ip ip, String username) {
		IpDao ipDao = new IpDao();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		UserDao user = (UserDao) entityManager.createQuery("select u from UserDao u where u.username LIKE '" + username + "'").getSingleResult();
		ipDao.setIp(ip.getIp());
		ipDao.setCount(1);
		ipDao.setUser(user);
		entityManager.getTransaction().begin();
		entityManager.persist(ipDao);
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
	}
}
