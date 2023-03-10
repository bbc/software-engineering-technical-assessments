const express = require("express");
const {
  getResult,
  newResult,
  reset,
  scoreboard,
} = require("./resultsController");

const server = express();
server.use(express.json());

server.get("/result/:id", (req, res) => {
  res.send(getResult(parseInt(req.params.id, 10)));
});

server.post("/result", (req, res) => {
  res.send(newResult(req.body));
});

server.get("/scoreboard", (req, res) => {
  res.send(scoreboard());
});

module.exports = { default: server, resetScores: reset };
