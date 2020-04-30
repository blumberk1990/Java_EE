package pl.mgd.wykopek.service;

import pl.mgd.wykopek.dao.DAOFactory;
import pl.mgd.wykopek.dao.UserDAO;
import pl.mgd.wykopek.model.User;

public class UserService {

	public void addUser(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setActive(true);
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDao = factory.getUserDAO();
		userDao.create(user);
	}
	
	public User getUserById(long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDao = factory.getUserDAO();
		User user = userDao.read(userId);
		return user;
	}
	
	public User getUserByUsername(String username) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDao = factory.getUserDAO();
		User user = userDao.getUserByUsername(username);
		return user;
	}
}
