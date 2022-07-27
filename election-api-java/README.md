## Description

If you are not familiar with how elections work in the UK, please see this short BBC video https://www.youtube.com/watch?v=cRxUhGetEPQ

The results API presents a simple elections result service.

### Domain
The domain for the election represents some key concepts:
- _**constituencyId**_ a unique integer id to identify a location. E.g "Brent Central" is 90
- _**party**_ is a short 3, or 4, letter code for a party for instance LAB = Labour, CON = Conservative etc.
- _**votes**_ the number of votes gained by a party in a constituency
- _**share**_ the % share of the total votes the party received

### API
The API has 3 endpoints:
- GET `/result/{id}` to get an elections result for a given id.
- POST `/result` to add a new result
- GET `/scoreboard` to get the running totals. This is unimplemented.

### Task

During your assessment we will ask you to work though the task in `tasks.md` with a pair. Please do not work on or complete these prior to the assessment.

:warning:  If you make any changes to the code, please ensure you return it to it's initial (HEAD) state before your assessment.

## Prerequisites
- Java 11

### Please ensure that the project builds without error within your choice of IDE. This should require no changes to the files in this repository.
### To Build
macOS / 'nix

`./gradlew build` or `./mvnw install`

Windows:

`gradlew.bat build` or `./mvnw.cmd install`

### To Run
macOS / 'nix

`./gradlew bootRun` or `./mvnw spring-boot:run`

Windows:

`gradlew.bat bootRun` or `./mvnw.cmd spring-boot:run`

### To Test
macOS / 'nix

`./gradlew test` or `./mvnw test`

Windows:

`gradlew.bat test` or `./mvnw.cmd test`
