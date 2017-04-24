package eu.ubis.fiimdb.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ubis.fiimdb.controller.ActorBean;
import eu.ubis.fiimdb.model.Actor;

public class ActorBeanTest extends ActorBean {

	@Test
	public void testGetActorByName() {
		String name="Morgan Freeman";
		ActorBean actorBean = new ActorBean();
		actorBean.getActorByName(name);
		Actor actor = new Actor();
		actor = actorBean.getActor();
		assertNotNull(actor);
	}
}
