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

    private final ResultService resultService;

    public ResultsController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/result/{id}")
    ConstituencyResult getResult(@PathVariable Integer id) {
        ConstituencyResult result = resultService.GetResult(id);
        if (result == null) {
            throw new ResultNotFoundException(id);
        }
        return resultService.GetResult(id);
    }

    @PostMapping("/result")
    ResponseEntity<String> newResult(@RequestBody ConstituencyResult result) {
        if (result.getId() != null) {
            resultService.NewResult(result);
            return ResponseEntity.created(URI.create("/result/"+result.getId())).build();
        }
        return ResponseEntity.badRequest().body("Id was null");
    }

    @GetMapping("/scoreboard")
    Scoreboard getScoreboard() {
        return new Scoreboard();
    }
}
