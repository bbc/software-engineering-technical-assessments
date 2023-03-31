import { render, screen, within } from '@testing-library/react';
import PartyLinks from '.';

test('renders party links as a list', async () => {
  render(<PartyLinks />);

  const partyLinks = screen.getByRole('list');
  expect(partyLinks).toBeInTheDocument();
});

test('renders all the party links', async () => {
  render(<PartyLinks />);

  const partyLinks = screen.getAllByRole('link', { name: /Party Link/ });
  expect(partyLinks.length).toBe(6);
});

test('renders a party link with the correct text and href', async () => {
  render(<PartyLinks />);

  const partyLink = screen.getByText('Hippo Party');
  const partyLinkAnchorTag = within(partyLink).getByRole('link');

  expect(partyLinkAnchorTag).toHaveAttribute('href', 'https://en.wikipedia.org/wiki/Hippopotamus');
});
