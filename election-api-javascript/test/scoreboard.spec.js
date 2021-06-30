const fs = require('fs');
const request = require('supertest');
const { default: expressServer, resetScores } = require('../src/server');

const resultsSamplesPath = './test/resources/sample-election-results';

function loadAndPostResultFile(server, num) {
    const fileNumber = new String(parseInt(num, 10)).padStart(3, '0');
    const result = fs.readFileSync(`${resultsSamplesPath}/result${fileNumber}.json`);
    return server.post('/result').send(JSON.parse(result));
}

async function loadResults(server, quantity) {
    const results = [];
    for (let count = 0; count < quantity; count += 1) {
        results.push(await loadAndPostResultFile(server, count + 1));
    }
    return results;
}

function fetchScoreboard(server) {
    return server.get('/scoreboard');
}

describe('Scoreboard Tests', () => {
    let server;

    beforeEach(() => {
        resetScores();
        server = request(expressServer);
    });

    test('first 5', async () => {
        await loadResults(server, 5);
        const scoreboard = await fetchScoreboard(server);
        expect(scoreboard).not.toBeNull();
		// assert LD == 1
		// assert LAB = 4
		// assert winner = noone
    });

    test('first 100', async () => {
        await loadResults(server, 100);
        const scoreboard = await fetchScoreboard(server);
        expect(scoreboard).not.toBeNull();
		// assert LD == 12
		// assert LAB == 56
		// assert CON == 31
		// assert winner = noone
    });

    test('first 554', async () => {
        await loadResults(server, 554);
        const scoreboard = await fetchScoreboard(server);
        expect(scoreboard).not.toBeNull();
		// assert LD == 52
		// assert LAB = 325
		// assert CON = 167
		// assert winner = LAB
    });

    test('test all results', async () => {
        await loadResults(server, 650);
        const scoreboard = await fetchScoreboard(server);
        expect(scoreboard).not.toBeNull();
		// assert LD == 62
		// assert LAB == 349
		// assert CON == 210
		// assert winner = LAB
		// assert sum = 650
    });
});
