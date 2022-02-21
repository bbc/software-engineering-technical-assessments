package bbc.news.elections.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PartyResult {
   final String party;
   final Integer votes;
   final BigDecimal share;

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
}


