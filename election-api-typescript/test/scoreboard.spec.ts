import fs from "fs";
import request, { SuperTest, Test, Response } from "supertest";
import { server as expressServer, resetScores } from "../src/server";

const resultsSamplesPath = "./test/resources/sample-election-results";

const loadAndPostResultFile = (server: SuperTest<Test>, num: number): Test => {
  const fileNumber = new String(parseInt(`${num}`, 10)).padStart(3, "0");
  const result = fs.readFileSync(
    `${resultsSamplesPath}/result${fileNumber}.json`
  );
  return server.post("/result").send(JSON.parse(result.toString()));
};

const loadResults = async (
  server: SuperTest<Test>,
  quantity: number
): Promise<Response[]> => {
  const results: Response[] = [];
  for (let count = 0; count < quantity; count += 1) {
    results.push(await loadAndPostResultFile(server, count + 1));
  }
  return results;
};

const fetchScoreboard = (server: SuperTest<Test>) => {
  return server.get("/scoreboard");
};

describe("Scoreboard Tests", () => {
  const server = request(expressServer);

  beforeEach(() => {
    resetScores();
  });

  test("first 5", async () => {
    await loadResults(server, 5);
    const scoreboard = await fetchScoreboard(server);
    expect(scoreboard).not.toBeNull();
    // assert LD  have won 1 seat
    // assert LAB have won 4 seats
    // assert no winner
  });

  test("first 100", async () => {
    await loadResults(server, 100);
    const scoreboard = await fetchScoreboard(server);
    expect(scoreboard).not.toBeNull();
    // assert LD  have won 12 seats
    // assert LAB have won 56 seats
    // assert CON have won 31 seats
    // assert SGP have won 0  seats
    // assert no winner
    // Bonus Task (total votes):
    // assert SGP have 1071 votes in total
  });

  test("first 554", async () => {
    await loadResults(server, 554);
    const scoreboard = await fetchScoreboard(server);
    expect(scoreboard).not.toBeNull();
    // assert LD   have won 52  seats
    // assert LAB  have won 325 seats
    // assert CON  have won 167 seats
    // assert IKHH have won 1   seats
    // assert winner is LAB
    // Bonus Task (total votes):
    // assert IKHH have 18739 votes in total
  });

  test("test all results", async () => {
    await loadResults(server, 650);
    const scoreboard = await fetchScoreboard(server);
    expect(scoreboard).not.toBeNull();
    // assert LD   have won 62  seats
    // assert LAB  have won 349 seats
    // assert CON  have won 210 seats
    // assert SDLP have won 3   seats
    // assert winner is LAB
    // assert 650 seats counted
    // Bonus Task (total votes):
    // assert SDLP have 125626 votes in total
  });
});
