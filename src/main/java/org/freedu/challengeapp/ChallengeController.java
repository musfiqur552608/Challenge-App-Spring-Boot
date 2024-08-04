package org.freedu.challengeapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    private CallengeService challengeService;
    public ChallengeController(CallengeService challengeService){
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges(){
        return new ResponseEntity<>(challengeService.getAllChallenges(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge){
        boolean isChallengeAdded = challengeService.addChallenge(challenge);
        if(isChallengeAdded){
            return new ResponseEntity<>("Challenge added successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Challenge Not added successfully", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{month}")
    public ResponseEntity<Challenge> getChallenges(@PathVariable String month){
        Challenge challenge = challengeService.getChallenges(month);
        if (challenge!=null){
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updateChallenge){
        boolean isChallengeUpdated=challengeService.updateChallenge(id, updateChallenge);
        if(isChallengeUpdated){
            return new ResponseEntity<>("Challenge updated successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Challenge Not updated successfully", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id){
        boolean isChallengeDeleted=challengeService.deleteChallenge(id);
        if(isChallengeDeleted){
            return new ResponseEntity<>("Challenge deleted successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Challenge Not deleted successfully", HttpStatus.NOT_FOUND);
        }
    }
}
