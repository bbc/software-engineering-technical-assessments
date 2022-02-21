package bbc.news.elections.service;

import bbc.news.elections.model.ConstituencyResult;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapBasedRepository implements ResultService {

    private final Map<Integer,ConstituencyResult> results;

    public MapBasedRepository() {
        results = new ConcurrentHashMap<>();
    }

    @Override
    public ConstituencyResult GetResult(Integer id) {
        return results.get(id);
    }

    @Override
    public void NewResult(ConstituencyResult result) {
        results.put(result.getId(), result);
    }

    @Override
    public Map<Integer, ConstituencyResult> GetAll() {
        return results;
    }

    @Override
    public void reset() {
        results.clear();
    }
}
