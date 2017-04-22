package eu.ubis.fiimdb.controller;

import eu.ubis.fiimdb.model.Director;
import eu.ubis.fiimdb.service.DirectorService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class DirectorBean {
	
	private static DirectorService directorService = ServiceFactory.getDirectorService();
	private static Director director;
	
	public Director getDirector() {
		return director;
	}
	
	public void getDirectorByName(String name) {
		director = directorService.getDirectorByName(name);
	}
}
