import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import {
  DataGrid,
  GridToolbarContainer,
  GridToolbarColumnsButton,
  GridToolbarFilterButton,
  GridToolbarExport,
} from '@material-ui/data-grid';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import { httpGet, formatDate } from '../../utils';

const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
  },
  paper: {
    width: '80%',
    height: '100%',
    marginLeft: '10%',
    marginBottom: '10%',
    minWidth: 750,
  },
  toolbar: {
    marginTop: '20px'
  },
  button: {
    background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
    border: 0,
    borderRadius: 3,
    boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
    color: 'white',
    height: 48,
    padding: '0 30px',
    marginTop: '20px',
  }
}));

function createData(id, name, description, category, creationDate, updateDate, lastPurchasedDate) {
  creationDate = formatDate(creationDate);
  updateDate = formatDate(updateDate);
  lastPurchasedDate = formatDate(lastPurchasedDate);
  return { id, name, description, category, creationDate, updateDate, lastPurchasedDate };
}

export default function DataTable() {

  let history = useHistory()
  const classes = useStyles();
  const [pageSize, setPageSize] = useState(5);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    async function getAllProducts() {
      const body = await httpGet("http://127.0.0.1:8080/products");
      if (body) {
        let products = body.map((product) => {
          return createData(product.id, product.name, product.description, product.category.categoryName, product.creationDate, product.updateDate, product.lastPurchasedDate)
        });
        setProducts(products);
      }
    };
    getAllProducts()
  }, []);

  const handlePageSizeChange = (params) => {
    setPageSize(params.pageSize);
  };

  const handleCellClick = (event) => {
    let productId = event.id;
    history.push(`/secure/products/${productId}`);
  }

  const handleCreate = () => {
    history.push(`/secure/products/create`);
  }

  const CustomToolBar = () => {
    return (
      <GridToolbarContainer className={classes.toolbar}>
        <GridToolbarColumnsButton />
        <GridToolbarFilterButton />
        <GridToolbarExport />
      </GridToolbarContainer>
    );
  }

  return (
    <div data-testid="data-table-paper" style={{ height: 600, width: '100%' }}>
      <Paper className={classes.paper}>
          <DataGrid
            pageSize={pageSize}
            onPageSizeChange={handlePageSizeChange}
            columns={[
              { field: 'id', headerName: 'ID' },
              { field: 'name', headerName: 'Name', width: 200 },
              { field: 'description', headerName: 'Description', width: 300 },
              { field: 'category', headerName: 'Category', width: 150 },
              { field: 'creationDate', headerName: 'Creation Date', width: 200 },
              { field: 'updateDate', headerName: 'Update Date', width: 200 },
              { field: 'lastPurchasedDate', headerName: 'Last Purchased Date', width: 250 },
            ]}
            rows={products}
            components={{
              Toolbar: CustomToolBar,
            }}
            rowsPerPageOptions={[5, 10, 20]}
            onCellClick={handleCellClick}
            sortModel={[
              {
                field: 'id',
                sort: 'desc',
              },
            ]}
            filterModel={{
              items: [
                { columnField: 'category', operatorValue: 'contains', value: '' },
              ],
            }}
          />
        <Button className={classes.button} onClick={handleCreate}>Create</Button>
      </Paper>
    </div>
  );
}
