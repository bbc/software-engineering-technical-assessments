import { fireEvent, render, screen, waitFor, within } from '@testing-library/react';
import Scoreboard from './Scoreboard';
import dataFetcher from '../dataFetcher';

jest.mock('../dataFetcher');

test('renders Results', async () => {
  dataFetcher.mockImplementationOnce(() => {
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

  render(<Scoreboard />);

  await waitFor(() => {
    const resultParty = within(screen.getByRole('table')).getByText(/Giraffe Party/i);
    expect(resultParty).toBeInTheDocument();
  });
});

test('renders error state', async () => {
  dataFetcher.mockImplementationOnce(() => {
    throw new Error();
  });

  render(<Scoreboard />);

  await waitFor(() => {
    const errorElement = screen.getByText(/Error/i);
    expect(errorElement).toBeInTheDocument();
  });
});

test('fetches results again when refresh button clicked', async () => {
  dataFetcher.mockImplementationOnce(() => {
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

  dataFetcher.mockImplementationOnce(() => {
    return Promise.resolve({
      isComplete: false,
      results: [
        {
          'party': 'Giraffe Party',
          'candidateId': 2,
          'votes': '12345'
        }
      ]
    })
  });

  render(<Scoreboard />);

  expect(dataFetcher).toBeCalledTimes(1);
  await waitFor(() => {
    const votes = screen.getByText(/9900/i);
    expect(votes).toBeInTheDocument();
  });

  const refreshButton = screen.getByText(/Refresh/i);
  fireEvent.click(refreshButton);

  expect(dataFetcher).toBeCalledTimes(2);
  await waitFor(() => {
    const votesAfterRefresh = screen.getByText(/12345/i);
    expect(votesAfterRefresh).toBeInTheDocument();
  });
});
