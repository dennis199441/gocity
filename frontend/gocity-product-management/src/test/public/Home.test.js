import { render, screen } from '@testing-library/react';
import { Home } from '../../main/public';

test('renders Product Management Application', () => {
  render(<Home />);
  const linkElement = screen.getByText(/Product Management Application/i);
  expect(linkElement).toBeInTheDocument();
});

test('renders Click to Start page', () => {
  render(<Home />);
  const linkElement = screen.getByText(/Click to Start/i);
  const imgElement = screen.getByAltText('logo');
  expect(imgElement).toBeInTheDocument();
  expect(linkElement).toBeInTheDocument();

});
