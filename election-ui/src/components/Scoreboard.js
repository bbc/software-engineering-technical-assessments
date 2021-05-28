import React, { useState, useEffect } from 'react';
import Logo from './logo/logo';
import fetchData from '../dataFetcher';
import Scorecard from './Scorecard';
import './Scoreboard.css';

function Scoreboard() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [results, setResults] = useState([]);

  async function getData() {
    try {
      setLoading(true);
      const resultData = await fetchData();
      setResults(resultData);
      setLoading(false);
    } catch (e) {
      setError(true);
    }
  }

  useEffect(() => {
    getData();
  }, []);

  return (
    <div className="Scoreboard">
      <header className="Election-scoreboard-header">
        <Logo language="en" />
      </header>
      <main>
        {
          loading ? <h2>Loading...</h2> :
          error ? <h1>Error</h1> :
          <>
            <h1>Results</h1>
            <Scorecard results={results} />
            <a className="Scoreboard-refresh" onClick={getData}>Refresh</a>
          </>
        }
      </main>
    </div>
  );
}

export default Scoreboard;
