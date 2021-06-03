// Important!
// This file returns static data in a crude attempt to mock an external API for the purpose of the test
// It maintains a count which is used to increment vote totals.
// It can be ignored for the purposes of the assessment

const metadata = {
  metadata: {
    isResult: false
  }
}

const defaultData = [
  {
    'party': 'Adder Party',
    'candidate': 'Baldrick',
    'votes': '1056'
  },
  {
    'party': 'Independent',
    'candidate': 'Lord Buckethead',
    'votes': '6900'
  },
  {
    'party': 'Independent',
    'candidate': 'Count Binface',
    'votes': '9900'
  }
];

let callCount = 0;
const dubiouslyUpdateVoteCount = (item, multiplier = 0) => parseInt(item.votes) + (100 * multiplier);
const dubiouslyIncrementCount = (count) => count < 6 ? count + 1 : count;

const fetchResults = () => {
  callCount = dubiouslyIncrementCount(callCount);
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const apiData = defaultData.map((item) => {
        return { ...item, votes: dubiouslyUpdateVoteCount(item, callCount) };
      }, []);

      resolve(apiData);
    }, 500);
  }
)};

export default fetchResults;
