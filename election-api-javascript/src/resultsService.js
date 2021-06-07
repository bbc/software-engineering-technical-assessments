
function resultStore() {

    const store = [];

    function getResult(idToGet) {
        return store.find(({ id }) => id === idToGet);
    }

    function newResult(result) {
        store.push(result);
    }

    function getAll() {
        return store;
    }

    function reset() {
        store = [];
    }

    return {
        getResult,
        newResult,
        getAll,
        reset
    }
}

module.exports.default = resultStore;
