package bbc.news.elections.controllers;

import bbc.news.elections.model.ConstituencyResult;
import bbc.news.elections.model.Scoreboard;
import bbc.news.elections.service.ResultNotFoundException;
import bbc.news.elections.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ResultsController {

    private final ResultService resultService;

    public ResultsController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/result/{id}")
    ConstituencyResult getResult(@PathVariable Integer id) {
        ConstituencyResult result = resultService.getResult(id);
        if (result == null) {
            throw new ResultNotFoundException(id);
        }
        return resultService.getResult(id);
    }

    @PostMapping("/result")
    ResponseEntity<String> newResult(@RequestBody ConstituencyResult result) {
        if (result.getId() != null) {
            resultService.newResult(result);
            return ResponseEntity.created(URI.create("/result/"+result.getId())).build();
        }
        return ResponseEntity.badRequest().body("Id was null");
    }

    @GetMapping("/scoreboard")
    Scoreboard getScoreboard() {
        return new Scoreboard();
    }
}
