import React, { useState, useEffect } from 'react';
import Logo from './logo/logo';
import Scorecard from './Scorecard';
import './Scoreboard.css';

const sampleData = [
  {
    'party': 'Green',
    'candidate': 'Ann Other',
    'votes': '1056'
  }
];

function Scoreboard() {
  const [results, setResults] = useState([]);

  useEffect(() => {
    setResults(sampleData);
  });

  return (
    <div className="Scoreboard">
      <header className="Election-scoreboard-header">
        <Logo language="en" />
      </header>
      <main>
        <Scorecard result={results} />
      </main>
    </div>
  );
}

export default Scoreboard;
