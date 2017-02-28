package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.OfferManager;

public interface OfferManagerDao extends CrudRepository<OfferManager, Integer>{
	public OfferManager findByOfferManagerId(Integer id);

}
