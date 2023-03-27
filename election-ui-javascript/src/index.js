import React from "react";
import * as ReactDOMClient from "react-dom/client";
import "./index.css";
import Scoreboard from "./components/Scoreboard";

const root = ReactDOMClient.createRoot(
  document.getElementById("root")
);

root.render(
  <React.StrictMode>
    <Scoreboard />
  </React.StrictMode>
);
