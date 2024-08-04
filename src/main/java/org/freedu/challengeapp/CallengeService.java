package org.freedu.challengeapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CallengeService {
//    private List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    ChallengeRepository challengeRepository;

    public CallengeService(){

    }

    public List<Challenge> getAllChallenges(){
        return challengeRepository.findAll();
    }

    public Boolean addChallenge(Challenge challenge){
        if(challenge!= null){
            challenge.setId(nextId++);
            challengeRepository.save(challenge);
            return true;
        }else {
            return false;
        }

    }

    public Challenge getChallenges(String month) {
        Optional<Challenge> challenge = challengeRepository.findByMonthIgnoreCase(month);

        return challenge.orElse(null);
    }


    public boolean updateChallenge(Long id, Challenge updateChallenge) {
        Optional<Challenge> challenge=challengeRepository.findById(id);
        if(challenge.isPresent()){
            Challenge challenge1=challenge.get();
            challenge1.setMonth(updateChallenge.getMonth());
            challenge1.setDescription(updateChallenge.getDescription());
            challengeRepository.save(challenge1);
            return true;
        }
        return false;
    }

    public boolean deleteChallenge(Long id) {
        Optional<Challenge> challenge=challengeRepository.findById(id);
        if(challenge.isPresent()){
            challengeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
