import { fetchResultData, fetchCandidateData } from '../fakeAPI'; // Let's imagine this is an external service that we are calling via https

async function fetchResults() {
  const {results, isComplete} = await fetchResultData();
  const candidateData =  fetchCandidateData();
  
  const newResults = results.map((item, index) => {
    return {...item, name: candidateData?.[index]?.name}
  })

  const theResults = {isComplete, results:  newResults};

  return theResults
}

export default fetchResults;
