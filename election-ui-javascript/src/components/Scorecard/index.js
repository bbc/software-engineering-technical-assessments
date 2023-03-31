import './Scorecard.css';

function Scorecard({ results, vote}) {
  if (!results.results || results.results.results.length === 0) {
    return <div>No results</div>;
  }

  let scores = [];
  for (let i=0; i < results.results.results.length; i++) {
    scores.push(
      <tr key={i} style={{
        // change the background color of the row if the votes are the same as the winner
        backgroundColor: results.results.results[i].votes === vote && "yellow"
      }}>
        <td>{results.results.results[i].party}</td>
        <td>{
          results.candidateData.find(candidate => candidate.id === results.results.results[i].candidateId).name
        }</td>
        <td>{results.results.results[i].votes}</td>
      </tr>
    )
  }


  return (
    <div className="Scorecard">
        <table className="Scorecard-table">
          <thead>
            <tr>
              <th>Party</th>
              <th>Candidate</th>
              <th>Votes</th>
            </tr>
          </thead>
          <tbody>
            {scores}
          </tbody>
        </table>
    </div>
  );
}

export default Scorecard;
