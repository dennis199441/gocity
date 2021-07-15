import { render, screen } from '@testing-library/react';
import ProductForm from '../../main/secure/ProductForm';
import ReactRouter from 'react-router'

test('renders DataTable', () => {
  jest.spyOn(ReactRouter, 'useParams').mockReturnValue({ id: 1 });
  render(<ProductForm />);
  const saveElement = screen.getByText(/Save/i);
  const deleteElement = screen.getByText(/Delete/i);
  const cancelElement = screen.getByText(/Cancel/i);
  const paperElement = screen.getByTestId("product-form-paper");
  // const dataElement = screen.getByTestId("data-table-grid");
  expect(saveElement).toBeInTheDocument();
  expect(deleteElement).toBeInTheDocument();
  expect(cancelElement).toBeInTheDocument();
  expect(paperElement).toBeInTheDocument();
});