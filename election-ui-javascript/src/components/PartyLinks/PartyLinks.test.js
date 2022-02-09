import { render } from '@testing-library/react';
import PartyLinks from '.';

test('renders party links as a list', async () => {
  const { container } = render(<PartyLinks/>);

  const partyLinks = container.querySelector('.Party-links')
  expect(partyLinks.nodeName).toBe('UL');
});

test('renders all the party links', async () => {
  const { container } = render(<PartyLinks/>);

  const partyLink = container.querySelectorAll('li.Party-link')
  expect(partyLink.length).toBe(6);
});

test('renders a party link with the correct text and href', async () => {
  const { container } = render(<PartyLinks/>);

  const partyLinksAnchorTag = container.querySelector('.Party-link a')
  expect(partyLinksAnchorTag.textContent).toBe('Hippo Party');
  expect(partyLinksAnchorTag.href).toBe('https://en.wikipedia.org/wiki/Hippopotamus');
});
