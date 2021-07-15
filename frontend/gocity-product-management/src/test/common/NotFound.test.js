import { render, screen } from '@testing-library/react';
import NotFound from '../../main/common/NotFound';

test('renders 404 - Not Found page', () => {
  render(<NotFound />);
  const linkElement = screen.getByText(/404 - Not Found/i);
  expect(linkElement).toBeInTheDocument();
});

test('renders Go Home page', () => {
  render(<NotFound />);
  const linkElement = screen.getByText(/Go Home/i);
  expect(linkElement).toBeInTheDocument();
});
