import React, { Component } from 'react';
import { ApolloClient } from 'apollo-client';
import { ApolloProvider } from 'react-apollo';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { HttpLink } from 'apollo-link-http';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import MyPhotos from './pages/myPhotos';
import TopBar from './components/TopBar';
import Settings from './pages/settings';
import SearchResults from './pages/searchResults';
import Upload from './pages/upload';
import Login from './pages/login';
import ProtectedRoute from './components/ProtectedRoute';

const client = new ApolloClient({
    link: new HttpLink({
        uri: `http://localhost:8080/graphql/`,
    }),
    cache: new InMemoryCache(),
});

class App extends Component {
    render() {
        return <ApolloProvider client={client}>
            <BrowserRouter>
                <div>
                    <TopBar/>
                    <Switch>
                        <Route path="/login" component={Login}/>
                        <ProtectedRoute path="/" exact={true} component={MyPhotos}/>
                        <ProtectedRoute path="/upload" component={Upload}/>
                        <ProtectedRoute path="/search" component={SearchResults}/>
                        <ProtectedRoute path="/settings" component={Settings}/>
                    </Switch>
                </div>
            </BrowserRouter>
        </ApolloProvider>;
    }
}

export default App;
