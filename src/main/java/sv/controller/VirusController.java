package sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import sv.model.Ball;
import sv.model.Bank;
import sv.model.User;
import sv.model.Virus;
import sv.repository.BallsRepository;
import sv.repository.BanksRepository;
import sv.repository.VirusRepository;

@RestController
@RequestMapping("/virus")
public class VirusController {

    @Autowired
    public BanksRepository banksRepository;

    @Autowired
    public VirusRepository virusRepository;

    private void randomGen(Bank b, BanksRepository repo) {
        Random r = new Random();
        double[] location = new double[2];
        location[0] = b.getLocation()[0] + r.nextGaussian() / 1000;
        location[1] = b.getLocation()[1] + r.nextGaussian() / 1000;
        virusRepository.insert(new Virus(location, b));
    }

    @Async
    @Scheduled(fixedRate = 60000)
    public void addVirus() {
        List<Bank> l = banksRepository.findAll();
        for(Bank b : l){
            if(!b.hasVirus())
                randomGen(b, banksRepository);
        }
    }

}
