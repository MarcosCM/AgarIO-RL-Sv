package sv.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sv.model.Ball;
import sv.model.Bank;
import sv.repository.BallsRepository;
import sv.repository.BanksRepository;

@RestController
@RequestMapping("/ball")
public class BallsController {

	public static Logger logger = LoggerFactory.getLogger(BallsController.class);
	
	@Autowired
	public BanksRepository banksRepository;
	
	@Autowired
	public BallsRepository ballsRepository;
	
	@RequestMapping(method = RequestMethod.GET)
    public final List<Ball> getLocations(
            @RequestParam("lat") String lat,
            @RequestParam("lon") String lon,
            @RequestParam("r") double radio) {
		logger.debug("repo is null? " + (ballsRepository == null));;

        return this.ballsRepository.findByLocationNear(
                new Point(Double.valueOf(lat), Double.valueOf(lon)),
                new Distance(radio, Metrics.KILOMETERS));

    }
	
	public static void randomGen(Bank b, BallsRepository ballsRepository, int nBalls){
		Random r = new Random();
		for(int i=0; i<nBalls; i++){
			double[] location = new double[2];
			location[0] = b.getLocation()[0] + r.nextGaussian() / 1000;
			location[1] = b.getLocation()[1] + r.nextGaussian() / 1000;
			ballsRepository.insert(new Ball(location, b));
		}
	}
	
	@Async
	@Scheduled(fixedRate = 5000)
	public void periodicTask(){
		// insert banks
		/*List<Bank> banks = new ArrayList<Bank>();
		banks.add(new Bank(new double[]{41.4193647, 2.2057816}));
		banks.add(new Bank(new double[]{41.41872, 2.2083499}));
		banks.add(new Bank(new double[]{41.4159282, 2.2044352}));
		banks.add(new Bank(new double[]{41.4171904, 2.2125153}));
		banks.add(new Bank(new double[]{41.415541, 2.2151506}));
		banks.add(new Bank(new double[]{41.4198554, 2.2096574}));
		banksRepository.insert(banks);*/
		
		List<Bank> l = banksRepository.findAll();
		for(Bank b : l){
			if(b.getNumBalls() < 15)
				randomGen(b, ballsRepository, 15);
		}
	}
}
