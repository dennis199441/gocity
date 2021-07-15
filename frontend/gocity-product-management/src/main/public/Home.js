import doge from '../../static/doge.png';
import { useHistory } from 'react-router-dom';
import Button from '@material-ui/core/Button';
import { isLogin } from '../utils'
import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles(() => ({
  startButton: {
    background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
    border: 0,
    borderRadius: 3,
    boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
    color: 'white',
    height: 48,
    padding: '0 30px',
  }
}));

export default function Home() {
  
  const styles = useStyles();
  
  let history = useHistory();

  const handleStart = () => {
    history.push("/secure/products");
  }

  if (!isLogin()) {
    return null;
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={doge} className="App-logo" alt="logo" />
        <h2><b><i>Product Management Application</i></b></h2>
        <Button onClick={handleStart} variant="contained" color="primary" className={styles.startButton}>Click to Start</Button>
      </header>
    </div>
  )
}