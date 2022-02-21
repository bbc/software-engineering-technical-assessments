import fetchResults from '../dataFetcher';
import { fetchResultData } from '../fakeAPI';

jest.mock('../fakeAPI');

const mockFakeApi = () => {
  fetchResultData.mockImplementationOnce(() => {
    return Promise.resolve({
      isComplete: false,
      results: [
        {
          'party': 'Giraffe Party',
          'candidateId': 2,
          'votes': '9900'
        }
      ]
    })
  });
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
