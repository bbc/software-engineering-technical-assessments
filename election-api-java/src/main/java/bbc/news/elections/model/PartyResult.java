package bbc.news.elections.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PartyResult {
   private final String party;
   private final Integer votes;
   private final BigDecimal share;

   public PartyResult(
           // JsonProperty annotations required for Jackson deserialisation during testing
           @JsonProperty("party")  String party,
           @JsonProperty("votes")  Integer votes,
           @JsonProperty("share")  BigDecimal share)
   {
      this.party = party;
      this.votes = votes;
      this.share = share;
   }

   public String getParty() { return party; }

   public Integer getVotes() { return votes; }

   public BigDecimal getShare() { return share; }
}


