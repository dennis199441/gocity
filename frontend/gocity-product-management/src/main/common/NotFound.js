import React from 'react';
import doge from '../../static/doge.png';

const NotFound = () => (
  <div className="App">
    <header className="App-header">
      <img src={doge} className="App-logo" alt="logo" />
      <p>404 - Not Found!</p>
      <a className="App-link" href="/" rel="noopener noreferrer">
        Go Home
      </a>
    </header>
  </div>
);

export default NotFound;