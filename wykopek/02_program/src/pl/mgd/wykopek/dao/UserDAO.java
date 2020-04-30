package pl.mgd.wykopek.dao;

import java.util.List;

import pl.mgd.wykopek.model.User;

public interface UserDAO extends GenericDAO<User, Long>{

	List<User> getAll();
	User getUserByUsername(String username);
}
