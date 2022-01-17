import { render, screen } from '@testing-library/react';
import Scorecard from '.';

const results = [
  {
    'party': 'Green',
    'candidateId': 2,
    'votes': '1056'
  }
];

test('renders results', async () => {
  render(<Scorecard results={results} />);

  const partyHeading = screen.getByText(/Party/i);
  const candidateHeading = screen.getByText(/Candidate/i);
  const votesHeading = screen.getByText(/Votes/i);

  const party = screen.getByText(/Green/i);
  const votes = screen.getByText(/1056/i);

  expect(partyHeading).toBeInTheDocument();
  expect(candidateHeading).toBeInTheDocument();
  expect(votesHeading).toBeInTheDocument();

  expect(party).toBeInTheDocument();
  expect(votes).toBeInTheDocument();
});

test('renders No Results if there are no results', async () => {
  render(<Scorecard />);
  const noResultsMessage = screen.getByText(/No results/i);
  expect(noResultsMessage).toBeInTheDocument();
});
