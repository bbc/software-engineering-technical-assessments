package bbc.news.elections.controllers;

import bbc.news.elections.model.ConstituencyResult;
import bbc.news.elections.model.PartyResult;
import bbc.news.elections.model.Scoreboard;
import bbc.news.elections.service.NotImplementedException;
import bbc.news.elections.service.ResultNotFoundException;
import bbc.news.elections.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ResultsController {

    private final ResultService results;

    public ResultsController(ResultService resultService) {
        this.results = resultService;
    }

    @GetMapping("/result/{id}")
    ConstituencyResult getResult(@PathVariable Integer id) {
        ConstituencyResult result = results.GetResult(id);
        if (result == null) {
            throw new ResultNotFoundException(id);
        }
        return results.GetResult(id);
    }

    @PostMapping("/result")
    ResponseEntity<String> newResult(@RequestBody ConstituencyResult result) {
        if (result.getId() != null) {
            results.NewResult(result);
            return ResponseEntity.created(URI.create("/result/"+result.getId())).build();
        }
        return ResponseEntity.badRequest().body("Id was null");
    }

    @GetMapping("/scoreboard")
    Scoreboard getScoreboard() {
        return new Scoreboard();
    }
}
