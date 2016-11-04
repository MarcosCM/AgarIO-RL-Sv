package sv.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

import sv.model.Virus;

@RepositoryRestResource(collectionResourceRel = "virus", path = "virus")
public interface VirusRepository extends MongoRepository<Virus, String> {

    List<Virus> findByLocationNear(Point p, Distance d);

}
