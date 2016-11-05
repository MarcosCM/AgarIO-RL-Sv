package sv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jorge Martinez on 4/4/16.
 */

@RestController
@RequestMapping("/location")
public class UserController {

	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private LocalizableRepository localizableRepository;

    @RequestMapping(method = RequestMethod.GET)
    public final List<Localizable> getLocations(
            @RequestParam("lat") String lat,
            @RequestParam("lon") String lon,
            @RequestParam("r") double r) {

        return this.localizableRepository.findByLocationNear(
                new Point(Double.valueOf(lat), Double.valueOf(lon)),
                new Distance(r, Metrics.KILOMETERS));

    }
    
    @RequestMapping(value = "/bestBank", method = RequestMethod.GET)
    public final Localizable getBestBank() {

    	List<Localizable> l = localizableRepository.findByType("bank");
    	Localizable best = null;
    	Long n = 0L;
    	Long aux;
    	for(Localizable lo : l){
    		if (best == null){
    			best = lo;
    			n = localizableRepository.countByLocationNear(new Point(lo.getLocation()[0], lo.getLocation()[1]), new Distance(0.3, Metrics.KILOMETERS));
    			continue;
    		}
    		
    		aux = localizableRepository.countByLocationNear(new Point(lo.getLocation()[0], lo.getLocation()[1]), new Distance(0.3, Metrics.KILOMETERS));
    		if (aux > n){
    			n = aux;
    			best = lo;
    		}
    	}
    	
    	return best;
    }
    
    @RequestMapping(value = "/{lat}/{lon}/", method = RequestMethod.DELETE)
    public final ResponseEntity<String> deleteBalls(
            @PathVariable("lat") String lat,
            @PathVariable("lon") String lon) {
    	
    	List<Localizable> l = localizableRepository.findAll();
    	Localizable del = null;
    	for(Localizable lo : l){
    		if (lo.getLocation()[0] == Double.valueOf(lat) && lo.getLocation()[1] == Double.valueOf(lon)){
    			del = lo;
    			break;
    		}
    	}
    	
    	//localizableRepository.deleteByLocation(new double[]{Double.valueOf(lat), Double.valueOf(lon)});
    	localizableRepository.delete(del);
        
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    public void randomGen(Localizable b, int nBalls){
		Random r = new Random();
		double[] location = new double[2];
		// balls
		for(int i=0; i<nBalls; i++){
			location[0] = b.getLocation()[0] + r.nextGaussian() / 1000;
			location[1] = b.getLocation()[1] + r.nextGaussian() / 1000;
			this.localizableRepository.insert(new Localizable(0, location, "ball"));
			location = new double[2];
		}
		
		// virus
		location[0] = b.getLocation()[0] + r.nextGaussian() / 1000;
		location[1] = b.getLocation()[1] + r.nextGaussian() / 1000;
		this.localizableRepository.insert(new Localizable(0, location, "virus"));
	}
	
	@Scheduled(fixedRate = 3000000)
	public void periodicTask(){
		if (!localizableRepository.findAll().isEmpty()) return;
		// insert banks
		List<Localizable> banks = new ArrayList<Localizable>();
		banks.add(new Localizable(0, new double[]{41.4193647, 2.2057816}, "bank"));
		banks.add(new Localizable(0, new double[]{41.41872, 2.2083499}, "bank"));
		banks.add(new Localizable(0, new double[]{41.4159282, 2.2044352}, "bank"));
		banks.add(new Localizable(0, new double[]{41.4171904, 2.2125153}, "bank"));
		banks.add(new Localizable(0, new double[]{41.415541, 2.2151506}, "bank"));
		banks.add(new Localizable(0, new double[]{41.4198554, 2.2096574}, "bank"));
		localizableRepository.insert(banks);
		
		for(Localizable b : banks){
			randomGen(b, 15);
		}
		
		List<Localizable> balls = new ArrayList<Localizable>();
		balls.add(new Localizable(5, new double[]{41.417548, 2.208535}, "ball"));
		balls.add(new Localizable(5, new double[]{41.417343, 2.208648}, "ball"));
		balls.add(new Localizable(5, new double[]{41.417146, 2.208460}, "ball"));
		balls.add(new Localizable(5, new double[]{41.417062, 2.208074}, "ball"));
		localizableRepository.insert(balls);
	}
}
