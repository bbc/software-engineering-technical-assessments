import { render, screen, waitFor, fireEvent } from '@testing-library/react';
import Scoreboard from './Scoreboard';
import * as dataFetcher from '../dataFetcher';

test('renders Results', async () => {
  render(<Scoreboard />);
  
  await waitFor(() => {
    const results = screen.getByText(/Green/i);
    expect(results).toBeInTheDocument();
  });
});


test('renders error state', async () => {
  const dataFetcherSpy = jest.spyOn(dataFetcher, 'default');
  
  dataFetcherSpy.mockImplementationOnce(() => {
    throw new Error();
  });

  render(<Scoreboard />);

  await waitFor(() => {
    const errorElement = screen.getByText(/Error/i);
    expect(errorElement).toBeInTheDocument();
  });
});

test('fetches results again when refresh button clicked', async () => {
  const dataFetcherSpy = jest.spyOn(dataFetcher, 'default');

  render(<Scoreboard />);

  await waitFor(() => {
    const refreshButton = screen.getByText(/Refresh/i);
    fireEvent.click(refreshButton);
    expect(dataFetcherSpy).toBeCalled();
  });
});
