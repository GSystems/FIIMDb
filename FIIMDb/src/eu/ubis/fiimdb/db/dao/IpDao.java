package eu.ubis.fiimdb.db.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="fiimdb", name="ip")
public class IpDao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ip;
	private int count;
	
	@ManyToOne
	@JoinTable(name="user_ip",
		joinColumns = @JoinColumn(name="id_ip", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name="id_user", referencedColumnName="id"))
	private UserDao user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public UserDao getUser() {
		return user;
	}
	public void setUser(UserDao user) {
		this.user = user;
	}
}
