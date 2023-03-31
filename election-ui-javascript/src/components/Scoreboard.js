import React, {useState,useEffect } from 'react';
import Logo from './logo/logo';
import fetchData from '../dataFetcher';
import Scorecard from './Scorecard';
import './Scoreboard.css';
import PartyLinks from "./PartyLinks";

function Scoreboard() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [results, setResults] = useState([]);
    const [winner, setWinner] = useState({
    party: '',
    votes: 0
  });
  async function getData() {
    try {
      setLoading(true);
      const resultData = await fetchData();
      setResults(resultData);
      setLoading(false);
        
      // set winner which have the max votes
      const winner = resultData.results.results.reduce((prev, current) => {
        return (prev.votes > current.votes) ? prev : current
      })
      
      setWinner({
        party: winner.party,
        votes: winner.votes
      });

    } catch (e) {
      setLoading(false);
      setError(true);
    }
  }

  useEffect(() => {
      getData();
    }, []);


    return (
      <div className="Scoreboard">
        <header className="Election-scoreboard-header">
          <div className='logo'>
          <Logo language="en" />
          </div>
        </header>
        <main>
          {
            loading ? <h2>Loading...</h2> :
            error ? <h1>Error</h1> :
            <>
              <h1>Results</h1>
              <Scorecard results={results} vote={winner.votes}/>
              { /* if the results not declared then the refresh, if declared then the one with the most votes to be printed */ } 
              {results.results.isComplete ? <h1 className = 'winner_res'> Congratulations to {winner.party} who have won the Elections 2021 with {winner.votes} votes.🎉 </h1> :  <a className = "Scoreboard-refresh" > Refresh </a>
              }
              <h1>Learn more about the parties...</h1>
              <PartyLinks />
              </>
          }
        </main>
      </div>
    );
  }
  
  export default Scoreboard;
