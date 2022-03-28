package bbc.news.elections.model;

import java.math.BigDecimal;

public class PartyResult {
   private String party;
   private Integer votes;
   private BigDecimal share;

   public PartyResult() {
   }

   public String getParty() { return party; }

   public Integer getVotes() { return votes; }

   public BigDecimal getShare() { return share; }

   public void setParty(String party) {
      this.party = party;
   }

   public void setVotes(Integer votes) {
      this.votes = votes;
   }

   public void setShare(BigDecimal share) {
      this.share = share;
   }
}


