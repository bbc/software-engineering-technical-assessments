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

During your assessment we will ask you to work though a set of tasks which will be given to you on the 
day.  Please don't change the code base before you start.

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
