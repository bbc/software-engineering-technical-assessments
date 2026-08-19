import express, { Express, Request, Response } from "express";
import {
  getResult,
  newResult,
  reset as resetScores,
  scoreboard,
} from "./resultsController";

const server: Express = express();
server.use(express.json());

function validateResult(resultCandidate: any): boolean {
  if (typeof resultCandidate !== "object" || resultCandidate === null)
    return false;
  const { id, candidate, votes } = resultCandidate;
  return (
    typeof id === "number" &&
    typeof candidate === "string" &&
    typeof votes === "number" &&
    id != null
  );
}

server.get("/result/:id", (req: Request, res: Response): void => {
  const id = Array.isArray(req.params.id) ? req.params.id[0] : req.params.id;
  res.send(getResult(parseInt(id, 10)));
});

server.post("/result", (req: Request, res: Response): void => {
  if (!validateResult(req.body)) {
    res.status(400).send("Invalid result format.");
    return;
  }
  res.send(newResult(req.body));
});

server.get("/scoreboard", (req: Request, res: Response): void => {
  res.send(scoreboard());
});

export { server, resetScores };
