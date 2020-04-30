package pl.mgd.wykopek.dao;

import java.util.List;

import pl.mgd.wykopek.model.Discovery;

public interface DiscoveryDAO extends GenericDAO<Discovery, Long>{
	List<Discovery> getAll();
}
