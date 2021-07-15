import { HashRouter, Switch, Route } from "react-router-dom";
import './App.css';
import { SecureRoute, PublicRoute, NotFound } from './main/common';
import { Home } from './main/public';
import { ProductList, ProductForm } from './main/secure';

function App() {
  return (
    <HashRouter>
      <div>
        <Switch>
          <PublicRoute restricted={false} component={Home} path="/" exact />
          <SecureRoute component={ProductList} path="/secure/products" exact />
          <SecureRoute component={ProductForm} path="/secure/products/:id" exact />
          <SecureRoute component={ProductForm} path="/secure/products/create" exact />
          <Route component={NotFound} />
        </Switch>
      </div>
    </HashRouter>
  );
}

export default App;
