package bbc.news.elections.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConstituencyResult {
    final Integer id;
    final String name;
    final Integer seqNo;
    final List<PartyResult> partyResults;

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
}