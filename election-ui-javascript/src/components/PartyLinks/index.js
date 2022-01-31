import './PartyLinks.css';

const PARTIES = [
  { name: 'Adder Party', url: 'https://en.wikipedia.org/wiki/Vipera_berus'},
  { name: 'Python Party', url: 'https://en.wikipedia.org/wiki/Pythonidae'},
  { name: 'Cobra Party', url: 'https://en.wikipedia.org/wiki/Elapidae'},
  { name: 'Anaconda Party', url: 'https://en.wikipedia.org/wiki/Green_anaconda'},
  { name: 'Boa Party', url: 'https://en.wikipedia.org/wiki/Boa_constrictor'},
  { name: 'Rattle Party', url: 'https://en.wikipedia.org/wiki/Rattlesnake'},
]

function PartyLinks() {
  return (
    <ul className="Party-links">
      {PARTIES.map((party) =>
        (<li className="Party-link" key={party.name}>
          <a href={party.url}>{party.name}</a>
        </li>)
      )}
    </ul>
  );
}

export default PartyLinks;
