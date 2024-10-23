import express, { Express, Request, Response } from "express";
import {
  getResult,
  newResult,
  reset as resetScores,
  scoreboard,
} from "./resultsController";

const server: Express = express();
server.use(express.json());

server.get("/result/:id", (req: Request, res: Response): void => {
  res.send(getResult(parseInt(req.params.id, 10)));
});

server.post("/result", (req: Request, res: Response): void => {
  res.send(newResult(req.body));
});

server.get("/scoreboard", (req: Request, res: Response): void => {
  res.send(scoreboard());
});

export { server, resetScores };
