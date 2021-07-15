import doge from '../../static/doge.png';
import { makeStyles } from '@material-ui/core/styles';
import DataTable from './components/DataTable';

const useStyles = makeStyles((theme) => ({
  icon: {
    height: '50px',
    marginTop: '-100px',
  }
}));

export default function ProductList() {
  const classes = useStyles()
  return (
    <div data-testid="product-list" className="App">
      <header className="App-header">
        <img src={doge} className={classes.icon} alt="logo" />
        <h2><b><i>Product List</i></b></h2>
        <DataTable />
      </header>
    </div>
  )
}