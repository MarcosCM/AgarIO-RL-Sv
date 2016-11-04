package sv.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sv.model.Bank;

@RepositoryRestResource(collectionResourceRel = "banks", path = "banks")
public interface BanksRepository extends MongoRepository<Bank, String>{
	
}
