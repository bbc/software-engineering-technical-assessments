### Task
The product owner has asked that we implement the scoreboard endpoint.

First version: First Past the Post

For each constituency a winner can be declared:
1. if we have votes for that constituency
2. The winning party is which ever party received a plurality of votes, e.g. whoever receives the most votes

Someone wins in the UK if they receive half of the constituency seats in Parliament (325).
So if a party has received 325 or more seats it can be declared the winner overall.

Note: 325 isn't a proper majority of 650 but the speaker of the house makes the effective number of voting MPs 649.

The scoreboard should show:
- The seats for each party
- The overall winner (i.e. the party with 325 or more seats) if there is one

There is a class [Scoreboard](src/main/java/bbc/news/elections/model/Scoreboard.java) ready to be used and an
endpoint controller: [ResultsController](src/main/java/bbc/news/elections/controllers/ResultsController.java).
You will also need to [complete the tests](src/test/java/bbc/news/elections/ElectionsApiApplicationIntegrationTests.java) as part of 
your implementation.

Bonus information for the scoreboard:
- The total votes for each party
- The total share of the vote for each party. So the percentage of votes for each party.

### Possible other implementations

- Absolute majority required. Someone needs 50% + 1 votes or a run off is triggered (check the data that's probably all constituencies)
- Allocate the seats from the total declarations based on % of vote share
