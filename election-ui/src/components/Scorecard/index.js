import './Scorecard.css';

const defaultData = [{
  party: '',
  candidate: '',
  votes: 0
}];

function Scorecard({result}) {

  if (!result || result.length === 0) {
    result = defaultData;
  }

  return (
    <div className="Scorecard">
        <table>
          <thead>
            <tr>
              <th>Party</th>
              <th>Candidate</th>
              <th>Votes</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{result[0].party}</td>
              <td>{result[0].candidate}</td>
              <td>{result[0].votes}</td>
            </tr>
          </tbody>
        </table>
    </div>
  );
}

export default Scorecard;
