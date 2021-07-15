import { render, screen } from '@testing-library/react';
import App from './App';

test('renders CLICK TO START page', () => {
  render(<App />);
  const linkElement = screen.getByText(/CLICK TO START/i);
  expect(linkElement).toBeInTheDocument();
});
