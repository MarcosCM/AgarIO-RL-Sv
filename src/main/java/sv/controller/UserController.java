package sv.controller;

import sv.model.User;
import sv.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jorge Martinez on 4/4/16.
 */

@RestController
@RequestMapping("/location")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public final List<User> getLocations(
            @RequestParam("lat") String lat,
            @RequestParam("lon") String lon,
            @RequestParam("r") double radio) {

        return this.userRepository.findByLocationNear(
                new Point(Double.valueOf(lat), Double.valueOf(lon)),
                new Distance(radio, Metrics.KILOMETERS));

    }

}
