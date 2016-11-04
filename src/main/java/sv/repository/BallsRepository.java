package sv.repository;


import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import sv.model.Ball;

import java.util.List;

/**
 * Created by Jorge Martinez on 4/4/16.
 */

@RepositoryRestResource(collectionResourceRel = "balls", path = "balls")
public interface BallsRepository extends MongoRepository<Ball, String> {

    List<Ball> findByLocationNear(Point p, Distance d);

}
