import React, { useState, useEffect } from 'react';
import Logo from './logo/logo';
import fetchData from '../dataFetcher';
import Scorecard from './Scorecard';
import './Scoreboard.css';


function Scoreboard() {
  const [results, setResults] = useState([]);

  useEffect(() => {
    const resultData = fetchData();
    setResults(resultData);
  }, []);

  return (
    <div className="Scoreboard">
      <header className="Election-scoreboard-header">
        <Logo language="en" />
      </header>
      <main>
        <h1>Results</h1>
        <Scorecard result={results} />
      </main>
    </div>
  );
}

export default Scoreboard;
