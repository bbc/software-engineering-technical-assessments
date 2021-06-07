## Description
The results API presents a simple elections result service.

### Domain
The domain for the election represents some key concepts:
- _**consituencyId**_ a unique integer id to identify a location. E.g "Brent Central" is 90
- _**party**_ is a short 3, or 4, letter code for a party for instance LAB = Labour, CON = Conservative etc.
- _**votes**_ the number of votes gained by a party in a constituency
- _**share**_ the % share of the total votes the party received

### API
The API has 3 endpoints:
- GET `/result/{id}` to get an elections result for a given id.
- POST `/result` to add a new result
- GET `/scoreboard` to get the running totals. This is unimplemented.

### Task
Implementing the scoreboard endpoint.

First version: First Past the Post

For each constituency a winner can be declared:
1. if we have votes for that constituency
2. The winning party is which ever party received a plurality of votes, e.g. whoever receives the most votes

Someone wins in the UK if they receive half of the constituency seats in Parliament (325).
So if a party has received 325 or more seats it can be declared the winner overall.

Note: 325 isn't a proper majority of 650 but the speaker of the house makes the effective number of voting MPs 349.

The scoreboard should show:
- The seats for each party
- The overall winner if there is one

There is a function in the [endpoint controller](src/resultsController.js) to put your implementation. You will also need to [complete the tests](scoreboard.spec.js) according to the output format you create.

Bonus information for the scoreboard:
- The total votes for each party
- The total share of the vote for each party. So the percentage of votes for each party.


### Possible other implementations

- Absolute majority required. Someone needs 50% + 1 votes or a run off is triggered (check the data that's probably all constituencies)
- Allocate the seats from the total declarations based on % of vote share

## Prerequisites
- Node 14

### To Build

```bash
npm install
```

### To Run

```bash
npm start
```
or if you need to run it on another port,
```bash
PORT=9000 npm start
```

### To Test

```bash
npm test
```
