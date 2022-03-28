package bbc.news.elections.model;

import java.util.List;

public class ConstituencyResult {
    private Integer id;
    private String name;
    private Integer seqNo;
    private List<PartyResult> partyResults;

    public ConstituencyResult() {
    }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public Integer getSeqNo() { return seqNo; }

    public List<PartyResult> getPartyResults() { return partyResults; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public void setPartyResults(List<PartyResult> partyResults) {
        this.partyResults = partyResults;
    }
}