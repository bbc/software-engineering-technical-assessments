# Scoreboard

The product owner has asked that we implement the scoreboard endpoint.

You can imagine a scoreboard showing the situation on the night of an election, as results arrive at the BBC constituency-by-constituency.

There is a class [Scoreboard](src/main/java/bbc/news/elections/model/Scoreboard.java) ready to be used and an
endpoint controller: [ResultsController](src/main/java/bbc/news/elections/controllers/ResultsController.java).
You will also need to [complete the tests](src/test/java/bbc/news/elections/ElectionsApiApplicationIntegrationTests.java) as part of 
your implementation.

# Task 1: count each party's "seats"

Show how many constituencies (known as "seats") each party has won.

For each constituency a winner can be declared:

1. if we have votes for that constituency
2. The winning party is which ever party received a plurality of votes, e.g. whichever party receives the most votes. (The winning party does *not* need to win a majority, just more than any other candidate)

# Task 2: show the overall winner of the election

A party wins an election in the UK if they receive half of the constituency seats in Parliament (325).
So if a party has received 325 or more seats it can be declared the winner overall.

(Note: 325 isn't a proper majority of 650 but the speaker of the house makes the effective number of voting MPs 649)

# Task 3: (bonus)

Bonus information for the scoreboard:

- The total votes for each party
- The total share of the vote for each party. So the percentage of votes for each party.

# Task 4: (bonus) other voting systems

- Absolute majority required. Someone needs 50% + 1 votes or a run off is triggered (check the data that's probably all constituencies)
- Allocate the seats from the total declarations based on % of vote share
