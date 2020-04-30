package pl.mgd.wykopek.dao;

import pl.mgd.wykopek.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {

	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId);
}
