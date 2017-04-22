package eu.ubis.fiimdb.controller;

import eu.ubis.fiimdb.model.Actor;
import eu.ubis.fiimdb.service.ActorService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class ActorBean {
	
	private ActorService actorService = ServiceFactory.getActorService();
	private static Actor actor = new Actor();
	
	public Actor getActor() {
		return actor;
	}
	
	public void getActorByName(String name) {
		actor = actorService.getActorByName(name);
	}
}
