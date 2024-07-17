### Task

The product owner has asked that we implement the scoreboard endpoint.

#### First Past the Post

For each constituency a winner can be declared if we have votes for at least one party in that constituency. 
The winning party is which ever party received a plurality of votes, e.g. whoever receives the most votes.

Someone wins in the UK if they receive half of the constituency seats in Parliament (325).
So if a party has received 325 or more seats it can be declared the winner overall.

Note: 325 isn't a proper majority of 650 but the speaker of the house makes the effective number of voting MPs 649.

The scoreboard should show:
- The seats for each party
- The overall winner (i.e. the party with 325 or more seats) if there is one

There is a case class [Scoreboard](app/model/PartyResult.scala) which you should alter to show the above results and an
endpoint controller to put your implementation: [ResultsController](app/controllers/ResultsController.scala).   You will also need to complete 
the [tests](test/controllers/ResultsControllerSpec.scala) as part of your implementation.

Bonus information for the scoreboard:
- The total votes for each party
- The total share of the vote for each party. So the percentage of votes for each party.
