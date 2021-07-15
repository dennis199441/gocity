import { render, screen } from '@testing-library/react';
import DataTable from '../../../main/secure/components/DataTable';

test('renders DataTable', () => {
  render(<DataTable />);
  const linkElement = screen.getByText(/Create/i);
  const paperElement = screen.getByTestId("data-table-paper");
  expect(linkElement).toBeInTheDocument();
  expect(paperElement).toBeInTheDocument();
});