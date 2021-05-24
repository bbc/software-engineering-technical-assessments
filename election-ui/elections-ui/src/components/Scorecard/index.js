import './Scorecard.css';

const defaultData = [{
  party: '',
  candidate: '',
  votes: 0
}];

function Scorecard({result}) {

  console.log(result);

  if (!result || result.length === 0) {
    result = defaultData;
  }

  return (
    <div className="Scorecard">
        <h2>Results</h2>
        <table>
          <thead>
            <tr>
              <th>Party</th>
              <th>Candidate</th>
              <th>Votes</th>
            </tr>
          </thead>
          <tr>
            <td>{result[0].party}</td>
            <td>{result[0].candidate}</td>
            <td>{result[0].votes}</td>
          </tr>
        </table>
    </div>
  );
}

export default Scorecard;
