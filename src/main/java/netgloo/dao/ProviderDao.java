package netgloo.dao;

import org.springframework.data.repository.CrudRepository;

import netgloo.models.Provider;

public interface ProviderDao extends CrudRepository<Provider, String>{

}
