package org.freedu.challengeapp;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CallengeService {
    private List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1L;

    public CallengeService(){

    }

    public List<Challenge> getAllChallenges(){
        return challenges;
    }

    public Boolean addChallenge(Challenge challenge){
        if(challenge!= null){
            challenge.setId(nextId++);
            challenges.add(challenge);
            return true;
        }else {
            return false;
        }

    }

    public Challenge getChallenges(String month) {
        for(Challenge challenge:challenges){
            if(challenge.getMonth().equalsIgnoreCase(month)){
                return challenge;
            }
        }
        return null;
    }


    public boolean updateChallenge(Long id, Challenge updateChallenge) {
        for (Challenge challenge:challenges){
            if(challenge.getId().equals(id)){
                challenge.setMonth(updateChallenge.getMonth());
                challenge.setDescription(updateChallenge.getDescription());
                return true;
            }
        }
        return false;
    }

    public boolean deleteChallenge(Long id) {
        for (Challenge challenge:challenges){
            if(challenge.getId().equals(id)){
                challenges.remove(challenge);
                return true;
            }
        }
        return false;
    }
}
