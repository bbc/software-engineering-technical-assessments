/**Add a comment on  lines R1 to R2Add diff commentMarkdown input:  edit mode selected.WritePreviewAdd a suggestionHeadingBoldItalicQuoteCodeLinkUnordered listNumbered listTask listMentionReferenceSaved repliesAdd FilesPaste, drop, or click to add filesCancelCommentStart a reviewReturn to code
 * @jest-environment jsdom
 */

import '@testing-library/jest-dom'
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
