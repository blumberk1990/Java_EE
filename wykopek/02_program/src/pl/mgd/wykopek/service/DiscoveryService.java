package pl.mgd.wykopek.service;

import java.sql.Timestamp;
import java.util.Date;

import pl.mgd.wykopek.dao.DAOFactory;
import pl.mgd.wykopek.dao.DiscoveryDAO;
import pl.mgd.wykopek.model.Discovery;
import pl.mgd.wykopek.model.User;

public class DiscoveryService {

	public void addDiscovery(String name, String description, String url, User user) {
		Discovery discovery = createDiscovery(name, description, url, user);
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDao = factory.getDiscoveryDAO();
		discoveryDao.create(discovery);
	}
	
	private Discovery createDiscovery(String name, String description, String url, User user) {
		Discovery discovery = new Discovery();
		discovery.setName(name);
		discovery.setDescription(description);
		discovery.setUrl(url);
		User userCopy = new User(user);
		discovery.setUser(userCopy);
		discovery.setTimestamp(new Timestamp(new Date().getTime()));
		return discovery;
	}
}
