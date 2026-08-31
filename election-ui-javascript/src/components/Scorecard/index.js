import './Scorecard.css';

function Scorecard({data}) {
  const {results, isComplete} = data ; 
  if (!results || results.length === 0) {
    return <div>No results</div>;
  }

  let scores = [];


//gets the winner
//const winner = results.reduce((max, { votes }) => Math.max(max, votes), 0);
const votesArr = results.map(({votes}) => votes);
const winner = Math.max(...votesArr); 

const isWinner =  (votes ,comp, winner ) => comp && votes === winner ;



  for (let i=0; i < results.length; i++) {
    const theWinner = isWinner(results[i].votes, isComplete, winner);
    scores.push(
      <tr key={i}>
        <td>{results[i].party}</td>
        <td>{results[i].name}  </td>
        <td className={theWinner && 'winner'}>{results[i].votes} {theWinner && <span className={'badge'}>The winner</span>}</td>
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
