package bbc.news.elections.service;

import bbc.news.elections.model.ConstituencyResult;

import java.util.Map;

public interface ResultService {
    ConstituencyResult getResult(Integer id);
    void newResult(ConstituencyResult result);
    Map<Integer,ConstituencyResult> getAll();
    void reset();
}
