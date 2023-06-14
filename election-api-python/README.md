## Description

If you are not familiar with how elections work in the UK, please see this short BBC video https://www.youtube.com/watch?v=cRxUhGetEPQ

The results API presents a simple elections result service.

### Domain

**Constituency** results are POSTed to the API and stored. A constituency result looks like this:

```json
{
    "id": 1,
    "name": "Aberconwy",
    "partyResults": [
        { "party": "LAB", "votes": 8894 },
        { "party": "CON", "votes": 7924 },
        { "party": "LD", "votes": 5197 },
    ]
}
```

* _**id**_: the unique integer ID of the consituency.
* _**name**_: the name of the constituency.
* _**party**_: a unique 3 or 4 letter code representing a political party (LAB = Labour, CON = Conservative, etc).
* _**votes**_: the number of votes won.

### API

The API has 3 endpoints:

- GET `/result/{id}` to get the result for a given constituency ID.
- POST `/result` to add a new constituency result.
- GET `/scoreboard` to get the running totals. This is unimplemented.

### Task

During your assessment we will ask you to work though the task in `tasks.md` with a pair. Please do not work on or complete these prior to the assessment.

:warning:  If you make any changes to the code, please ensure you return it to it's initial (HEAD) state before your assessment.

## Setup

System requirements:

- Python 3.9 or higher

### To Build
`pip install -r requirements.txt`

### To Run
`python ./src/main.py`

or if you need to run it on another port,
`PORT=**** python ./src/main.py`

### To Test
`python ./src/test_scoreboard.py`

