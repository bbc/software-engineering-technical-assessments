package bbc.news.elections.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ConstituencyResult {
    Integer id;
    String name;
    Integer seqNo;
    List<PartyResult> partyResults;
}