// Important!
// This file returns static data in a crude attempt to mock an external API for the purpose of the test
// You may want to use it as a reference, but you do not need to make any changes to this file.

const candidateData = [
  {
    id: 1,
    name: 'Baldrick'
  },
  {
    id: 2,
    name: 'Lord Buckethead'
  },
  {
    id:3,
    name: 'Count Binface'
  }
];

const defaultData = {
  metadata: {
    isComplete: false
  },
  results: [
    {
      'party': 'Hippo Party',
      'candidateId': 1,
      'votes': '1056'
    },
    {
      'party': 'Giraffe Party',
      'candidateId': 2,
      'votes': '6900'
    },
    {
      'party': 'Tiger Party',
      'candidateId': 3,
      'votes': '9900'
    }
  ]
}

// 😬
let callCount = 0;
const dubiouslyUpdateVoteCount = (item, multiplier = 0) => parseInt(item.votes) + (100 * multiplier);
const dubiouslyIncrementCount = (count) => count < 5 ? count + 1 : count;
const dubiouslySetResult = (metadata, count) => count >= 5 ? { ...metadata, isComplete: true } : metadata;

const fetchResultData = () => {
  callCount = dubiouslyIncrementCount(callCount);
  return new Promise((resolve, reject) => {
    setTimeout(() => {

      const metadata = dubiouslySetResult(defaultData.metadata, callCount);
      const apiResultData = defaultData.results.map((item) => {
        return { ...item, votes: dubiouslyUpdateVoteCount(item, callCount) };
      }, []);

      resolve({ ...metadata, results: apiResultData});
    }, 500);
  }
)};

const fetchCandidateData = () => candidateData;

export {
  fetchResultData,
  fetchCandidateData
}

export default fetchResultData;
