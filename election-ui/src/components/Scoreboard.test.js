import { render, screen, waitFor, fireEvent } from '@testing-library/react';
import Scoreboard from './Scoreboard';
import dataFetcher from '../dataFetcher';

jest.mock('../dataFetcher');

test('renders Results', async () => {
  dataFetcher.mockImplementationOnce(() => {
    return Promise.resolve(
      [
        {
          'party': 'Independent',
          'candidate': 'Lord Buckethead',
          'votes': '9900'
        }
      ]
    )
  });

  render(<Scoreboard />);
  
  await waitFor(() => {
    const results = screen.getByText(/Independent/i);
    expect(results).toBeInTheDocument();
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
  render(<Scoreboard />);

  await waitFor(() => {
    const refreshButton = screen.getByText(/Refresh/i);
    fireEvent.click(refreshButton);
    expect(dataFetcher).toBeCalled();
  });
});
