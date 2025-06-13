import './Scorecard.css';

function Scorecard({ results }) {
  if (!results || results.length === 0) {
    return <div>No results</div>;
  }

  let scores = [];
  for (let i=0; i < results.length; i++) {
    scores.push(
      <tr key={i}>
        <td>{results[i].party}</td>
        <td>{results[i].candidateId}</td>
        <td>{results[i].votes}</td>
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
