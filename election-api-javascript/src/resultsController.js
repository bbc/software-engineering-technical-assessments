const { default: resultStore } = require('./resultsService');

const store = resultStore();

function getResult(id) {
    return store.getResult(id);
}

function newResult(result) {
    store.newResult(result);
    return {};
}

function reset() {
    store.reset();
}

function scoreboard() {
    //  Left blank for you to fill in
}


module.exports = { getResult, newResult, reset, scoreboard };
