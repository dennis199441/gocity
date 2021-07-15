import { render, screen } from '@testing-library/react';
import ProductList from '../../main/secure/ProductList';

test('renders Product List', () => {
    render(<ProductList />)
    const productList = screen.getByTestId("product-list");
    const title = screen.getByText(/Product List/i);
    expect(productList).toBeInTheDocument();
    expect(title).toBeInTheDocument();
});