import { fetchResultData, fetchCandidateData } from '../fakeAPI'; // Lets imagine this is an external service that we are calling via https

async function fetchResults() {
  const results = await fetchResultData();
  const candidateMap = fetchCandidateData();

  return results;
}

export default fetchResults;
