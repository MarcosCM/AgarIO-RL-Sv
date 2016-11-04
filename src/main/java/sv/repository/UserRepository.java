package sv.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sv.model.User;

import java.util.List;

/**
 * Created by Jorge Martinez on 4/4/16.
 */

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface UserRepository extends MongoRepository<User, String> {

    //List<User> findByLastName(@Param("name") String name);
    List<User> findByLocationNear(Point p, Distance d);

}
