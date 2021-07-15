import React, { useState, useEffect } from 'react';
import doge from '../../static/doge.png';
import { useParams } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import { httpGet, httpPost, httpPut, httpDelete } from '../utils';
import Paper from '@material-ui/core/Paper';
import Divider from '@material-ui/core/Divider';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';

const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
  },
  icon: {
    height: '50px',
    marginTop: '-100px',
  },
  title: {
    paddingTop: '10px'
  },
  paper: {
    width: '80%',
    height: '100%',
    marginLeft: '10%',
    marginBottom: '10%',
    minWidth: 750
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: '80%',
    paddingTop: '10px',
    paddingBottom: '20px'
  },
  button: {
    background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
    border: 0,
    borderRadius: 3,
    boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
    height: 48,
    color: 'white',
    margin: '20px',
    marginTop: '20px',
  },
  selection: {
    width: '80%',
    paddingTop: '30px',
    paddingBottom: '30px'
  }
}));

function createCategory(id, categoryName) {
  return { id, categoryName };
}

export default function ProductForm() {

  let { id } = useParams();
  const classes = useStyles();
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [categoryId, setCategoryId] = useState(0);
  const [categoryName, setCategoryName] = useState('');
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const categoriesBody = await httpGet(`http://127.0.0.1:8080/categories`);
      if (categoriesBody) {
        let categories = categoriesBody.map((c) => {
          return createCategory(c.id, c.categoryName);
        });
        setCategories(categories);
      }

      if (!isNaN(id)) {
        const productBody = await httpGet(`http://127.0.0.1:8080/products/${id}`);
        if (productBody) {
          setName(productBody.name);
          setDescription(productBody.description);
          setCategoryId(productBody.category.id);
          setCategoryName(productBody.category.categoryName);
        }
      }
    };
    fetchData();
  }, [id]);

  const createProduct = async (data) => {
    const result = await httpPost(`http://127.0.0.1:8080/products`, data);
    return result;
  }

  const updateProduct = async (id, data) => {
    data.id = id;
    const result = await httpPut(`http://127.0.0.1:8080/products/${id}`, data);
    return result;
  }

  const deleteProduct = async (id) => {
    const result = await httpDelete(`http://127.0.0.1:8080/products/${id}`);
    return result;
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    let data = {
      name: name,
      description: description,
      category: {
        id: categoryId,
        categoryName: categoryName
      }
    }

    let result;
    if (!isNaN(id)) {
      result = updateProduct(id, data)
    } else {
      result = createProduct(data);
    }

    if (!result) {
      alert("Fail to create/update product. Please try again later.");
    }

    redirectToProductList();
  }

  const handleCancel = (event) => {
    redirectToProductList();
  }

  const handleCategoryChange = (event) => {
    let [id, name] = event.target.value.split("@");
    setCategoryId(id);
    setCategoryName(name);
  };

  const handleDelete = async () => {
    if (window.confirm("Are you sure you want to delete this product?")) {
      const result = await deleteProduct(id);
      if (result) {
        redirectToProductList();
      }
    }
  }

  const redirectToProductList = () => {
    window.location.href = '/#/secure/products';
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={doge} className={classes.icon} alt="logo" />
        <h2><b><i>Product Form</i></b></h2>
        <div data-testid="product-form-paper" style={{ height: 600, width: '100%' }}>
          <Paper className={classes.paper}>
            <div className={classes.title}>
              {isNaN(id) ? <h3><b><i>Create Product</i></b></h3> : <h3><b><i>Update Product</i></b></h3>}
            </div>
            <form className={classes.container} onSubmit={handleSubmit} >
              <TextField
                id="product-name"
                label="Name"
                style={{ margin: 8 }}
                margin="normal"
                InputLabelProps={{
                  shrink: true,
                }}
                className={classes.textField}
                value={name}
                onInput={e => setName(e.target.value)}
              />
              <TextField
                id="product-description"
                label="Description"
                style={{ margin: 8 }}
                margin="normal"
                InputLabelProps={{
                  shrink: true,
                }}
                className={classes.textField}
                value={description}
                onInput={e => setDescription(e.target.value)}
              />

              <TextField
                id="product-category"
                select
                label="Category"
                align="left"
                value={categoryId + "@" + categoryName}
                className={classes.selection}
                onChange={handleCategoryChange}
                helperText="Please select your category"
                variant="filled"
              >
                {categories.map((option) => (
                  <MenuItem key={option.id} value={option.id + "@" + option.categoryName}>
                    {option.categoryName}
                  </MenuItem>
                ))}
              </TextField>

              <Divider spacing={1} />

              <Button
                type="submit"
                className={classes.button}
              >
                Save
              </Button>
              {!isNaN(id) ?
                <Button
                  type="button"
                  className={classes.button}
                  onClick={handleDelete}
                >
                  Delete
                </Button> : null}
              <Button
                type="button"
                onClick={handleCancel}
                className={classes.button}
              >
                Cancel
              </Button>
            </form>
          </Paper>
        </div>
      </header>
    </div >
  )
}