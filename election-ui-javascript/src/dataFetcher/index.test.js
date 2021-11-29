import fetchResults from '../dataFetcher';
import { fetchResultData, fetchCandidateMap } from '../fakeAPI';

jest.mock('../fakeAPI');

const mockFakeApi = () => {
  fetchResultData.mockImplementationOnce(() => {
    return Promise.resolve({
      isComplete: false,
      results: [
        {
          'party': 'Independent',
          'votes': '9900',
          'candidateId': 1
        }
      ]
    })
  });
  fetchCandidateMap.mockImplementationOnce(() => {
    return [
      {
        id: 1,
        name: 'Baldrick'
      }
    ]
  })
}

test('returns an Object', async () => {
    mockFakeApi();
    const resultData = await fetchResults();
    expect(typeof resultData).toBe('object');
});

test('response contains a result array', async () => {
  mockFakeApi();
  const resultData = await fetchResults();
  expect(Array.isArray(resultData.results)).toBe(true);
});
