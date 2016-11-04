package sv;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Jorge Martinez on 4/4/16.
 */

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface LocalizableRepository extends MongoRepository<Localizable, String> {

    //List<User> findByLastName(@Param("name") String name);
    List<Localizable> findByLocationNear(Point p, Distance d);
    String deleteByLocation(double[] location);
}
