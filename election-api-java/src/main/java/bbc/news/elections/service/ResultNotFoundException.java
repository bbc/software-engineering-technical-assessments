package bbc.news.elections.service;

public class ResultNotFoundException extends RuntimeException {
    private Integer resultId;

    public ResultNotFoundException(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getId()  {
        return resultId;
    }
}
