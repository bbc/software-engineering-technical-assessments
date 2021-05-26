import { render, screen } from '@testing-library/react';
import Scoreboard from './Scoreboard';

test('renders learn react link', () => {
  render(<Scoreboard />);
  const linkElement = screen.getByText(/Results/i);
  expect(linkElement).toBeInTheDocument();
});
