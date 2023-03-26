package com.seniorproject.first.prototype.controller;

import com.seniorproject.first.prototype.entity.Experiment;
import com.seniorproject.first.prototype.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExperimentController {

    @Autowired
    private ExperimentService experimentService;

    @PostMapping("/create-experiment")
    public Experiment createExperiment(@RequestBody Experiment experiment){
        return experimentService.createExperiment(experiment);
    }

    @GetMapping("myCreatedExperiments/{id}")
    public Experiment getMyCreatedExperimentById(@PathVariable("id") Long experimentId){
        return experimentService.getMyCreatedExperimentById(experimentId);
    }

    @GetMapping("UserPendingJoinExperiments")
    public List<Experiment> getMyPendingJoinExperiments(){
        return experimentService.getMyPendingJoinExperiments();
    }

    @GetMapping("UserJoinedExperiments")
    public List<Experiment> getMyJoinedExperiments(){
        return experimentService.getMyJoinedExperiments();
    }

    @GetMapping("UserTakenExperiments")
    public List<Experiment> getMyTakenExperiments(){
        return experimentService.getMyTakenExperiments();
    }
}
