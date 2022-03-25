package bbc.news.elections.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConstituencyResult {
    private final Integer id;
    private final String name;
    private final Integer seqNo;
    private final List<PartyResult> partyResults;

    public ConstituencyResult(
            // JsonProperty annotations required for Jackson deserialisation during testing
            @JsonProperty("id")  Integer id,
            @JsonProperty("name")  String name,
            @JsonProperty("seqNo")  Integer seqNo,
            @JsonProperty("partyResults")  List<PartyResult> partyResults)
    {
        this.id = id;
        this.name = name;
        this.seqNo = seqNo;
        this.partyResults = partyResults;
    }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public Integer getSeqNo() { return seqNo; }

    public List<PartyResult> getPartyResults() { return partyResults; }
}