package pl.mgd.wykopek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.mgd.wykopek.model.User;
import pl.mgd.wykopek.util.ConnectionProvider;

public class UserDAOImpl implements UserDAO {

	private static final String CREATE_USER = "INSERT INTO user(username, email,is_active, password) VALUES(:username, :email, :active, :password);";
	private static final String READ_USER_BY_ID = "SELECT user_id, username, email, is_active, password FROM user WHERE user_id = :id";
	private static final String READ_USER_BY_USERNAME = "SELECT user_id, username, email, is_active, password FROM user WHERE username = :username";
	
	private NamedParameterJdbcTemplate namedTemplate;
	
	public UserDAOImpl() {
		namedTemplate = new NamedParameterJdbcTemplate(ConnectionProvider.getDsInstance());
	}
	
	@Override
	public User create(User user) {
		User resultUser = new User(user);
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(resultUser);
		int affectedRows = namedTemplate.update(CREATE_USER, paramSource, holder);
		if(affectedRows > 0) {
			resultUser.setId(holder.getKey().longValue());
			setPrivigiles(resultUser);
		}
		return resultUser;
	}
	
	private void setPrivigiles(User user) {
		final String userRoleQuery = "INSERT INTO user_role(username) VALUES(:username)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
		namedTemplate.update(userRoleQuery, paramSource);
	}

	@Override
	public User read(Long primaryKey) {
		User resultUser = null;
		SqlParameterSource paramSource = new MapSqlParameterSource("id", primaryKey);
		resultUser = namedTemplate.queryForObject(READ_USER_BY_ID, paramSource, new UserRowMapper());
		return resultUser;
	}

	@Override
	public boolean update(User updateObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		User resultUser = null;
		SqlParameterSource paramSource = new MapSqlParameterSource("username", username);
		resultUser = namedTemplate.queryForObject(READ_USER_BY_USERNAME, paramSource, new UserRowMapper());
		return resultUser;
	}

	private class UserRowMapper implements RowMapper<User> {
		
		@Override
		public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			User user = new User();
			user.setId(resultSet.getLong("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
			return user;
		}
	}

		
	
}
