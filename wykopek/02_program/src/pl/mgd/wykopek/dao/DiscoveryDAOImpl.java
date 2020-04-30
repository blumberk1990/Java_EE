package pl.mgd.wykopek.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.mgd.wykopek.model.Discovery;
import pl.mgd.wykopek.util.ConnectionProvider;

public class DiscoveryDAOImpl implements DiscoveryDAO {

	private static final String CREATE_DISCOVERY = 
			"INSERT INTO discovery(name, description, url, user_id, date, up_vote, down_vote)"
					+ "VALUES(:name, :description, :url, :user_id, :date, :up_vote, :down_vote);";
	
	private NamedParameterJdbcTemplate namedTemplate;
	
	public DiscoveryDAOImpl() {
		namedTemplate = new NamedParameterJdbcTemplate(ConnectionProvider.getDsInstance());
	}
	
	@Override
	public Discovery create(Discovery discovery) {
		Discovery resultDiscovery = new Discovery(discovery);
		KeyHolder holder = new GeneratedKeyHolder();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		System.out.println("discovery name: " + discovery.getName());
		paramMap.put("name", discovery.getName());
 		paramMap.put("description", discovery.getDescription());
 		paramMap.put("url", discovery.getUrl());
 		paramMap.put("user_id", discovery.getUser().getId());
 		paramMap.put("date", discovery.getTimestamp());
 		paramMap.put("up_vote", discovery.getUpVote());
 		paramMap.put("down_vote", discovery.getDownVote());
 		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
 		int affectedRows = namedTemplate.update(CREATE_DISCOVERY, paramSource, holder);
 		if(affectedRows>0) {
 			resultDiscovery.setId(holder.getKey().longValue());
 		}
		return resultDiscovery;
	}

	@Override
	public Discovery read(Long primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Discovery updateObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Discovery> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
