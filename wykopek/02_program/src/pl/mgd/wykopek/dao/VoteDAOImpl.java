package pl.mgd.wykopek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.mgd.wykopek.model.Vote;
import pl.mgd.wykopek.model.VoteType;
import pl.mgd.wykopek.util.ConnectionProvider;

public class VoteDAOImpl implements VoteDAO {

	private static final String CREATE_VOTE = 
			/*"INSERT INTO vote(discovery_id, user_id, date, type) "
					+ "VALUES (:discovery_id, user_id, date, type);";*/
			"INSERT INTO vote(discovery_id, user_id, date, type) "
				+ "VALUES (:discovery_id, :user_id, :date, :type);";
	
	private static final String READ_VOTE = 
			"SELECT vote_id, discovery_id, user_id, date, type "
					+ "FROM vote WHERE vote_id = :vote_id";
	
	private static final String READ_VOTE_BY_DISCOVERY_USER_IDS = 
			"SELECT vote_id, discovery_id, user_id, date, type "
					+ "FROM vote WHERE user_id = :user_id AND discovery_id = :discovery_id;";
	
	private static final String UPDATE_VOTE = 
			"UPDATE vote SET date = :date, type = :type WHERE vote_id = :vote_id;";
	
	private NamedParameterJdbcTemplate namedTemplate;
	
	public VoteDAOImpl() {
		namedTemplate = new NamedParameterJdbcTemplate(ConnectionProvider.getDsInstance());
	}
	
	@Override
	public Vote create(Vote vote) {
		Vote resultVote = new Vote(vote);
		KeyHolder holder = new GeneratedKeyHolder();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("discovery_id", resultVote.getDiscoveryId());
		paramMap.put("user_id", resultVote.getUserId());
		paramMap.put("date", resultVote.getDate());
		paramMap.put("type", resultVote.getVoteType().toString());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int affectedRows = namedTemplate.update(CREATE_VOTE, paramSource, holder);
		if(affectedRows > 0) {
			resultVote.setId(holder.getKey().longValue());
		}
		return resultVote;
	}

	@Override
	public Vote read(Long primaryKey) {
		SqlParameterSource paramSource = new MapSqlParameterSource("vote_id", primaryKey);
		Vote resultVote = namedTemplate.queryForObject(READ_VOTE, paramSource, new VoteRowMapper());
		return resultVote;
	}

	@Override
	public boolean update(Vote vote) {
		Vote copyVote = new Vote(vote);
		boolean result = false;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("date", copyVote.getDate());
		paramMap.put("type", copyVote.getVoteType().toString());
		paramMap.put("vote_id", copyVote.getId());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int affectedRows = namedTemplate.update(UPDATE_VOTE, paramSource);
		if(affectedRows > 0) {
			result = true;
		}	
		return result;
	}

	@Override
	public boolean delete(Long key) {
		return false;
	}

	@Override
	public List<Vote> getAll() {
		return null;
	}

	@Override
	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user_id", userId);
		paramMap.put("discovery_id", discoveryId);
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		Vote vote = null;
		try {
			vote = namedTemplate.queryForObject(READ_VOTE_BY_DISCOVERY_USER_IDS, paramSource, new VoteRowMapper());
		} catch (EmptyResultDataAccessException e) {
			//vote not found
		}
		return vote;
	}

	
	private class VoteRowMapper implements RowMapper<Vote> {

		@Override
		public Vote mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Vote vote = new Vote();
			vote.setId(resultSet.getLong("vote_id"));
			vote.setUserId(resultSet.getLong("user_id"));
			vote.setUserId(resultSet.getLong("discovery_id"));
			vote.setDate(resultSet.getTimestamp("date"));
			vote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
			return vote;
		}	
	}
	
}
