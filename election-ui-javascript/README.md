# Mock Election UI

If you are not familiar with how elections work in the UK, please see this short BBC video https://www.youtube.com/watch?v=cRxUhGetEPQ

This repository contains a fake 'in progress' elections UI.

Before your election we would like you to get a 'local' environment set up to run this application.

## Setup

Requires: 
* node (18) - available from https://nodejs.org/en/download/
* npm (9.x) - will be installed with node 18, or see https://docs.npmjs.com/downloading-and-installing-node-js-and-npm

```sh
# Install dependencies
npm ci
# (Optional) bundle the code
npm run build
# Start the application
npm start
```

Notes:
1) You will see some console warnings when running the 'build' and 'start' step. Assuming the application starts up correctly these can be ignored.
2) The fakeAPI directory contains a mock of an external API, for the purposes of the assessment. Inside the fakeAPI directory you will find a README that describes the interface and outputs of the API, but you should not need to change the mock API itself.

At this point we don't ask you to do any more until your booked assessment time. 

You're ready for your assessment as long as the following are true:
- You can open this folder in your preferred code editor
- You can run all the tests (with `npm test`) and 1 fails and 9 pass
- You can run `npm start` and see a styled "Elections 2021" webpage in your browser at http://localhost:3000/

__Warning:__  If you make any changes to the code, please ensure you return it to its initial (HEAD) state before your assessment.

## Assessment Time

In the assessment we will ask you to screenshare your working environment and talk through the following with us:

1) What does this system do? What are its key features?
2) New Feature requests! - The election product team have some feature requests that we would like you to take a look at. They can be found in `tasks.md`.

If you have any problems with any of this, please get in touch via your recruitment contact.

Thanks :) 



