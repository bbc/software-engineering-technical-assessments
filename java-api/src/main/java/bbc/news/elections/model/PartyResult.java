package bbc.news.elections.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PartyResult {
   String party;
   Integer votes;
   BigDecimal share;
}


