import React, { Component } from 'react';
import './App.css';
import TopBar from './components/TopBar';

import { ApolloClient } from 'apollo-client';
import { ApolloProvider } from 'react-apollo';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { HttpLink } from 'apollo-link-http';
import LoginButton from './components/LoginButton';

// Graphiql is available on https://solapi.vankleef.me/.
const client = new ApolloClient({
    link: new HttpLink({
        uri: `http://localhost:8080/graphql/`,
    }),
    cache: new InMemoryCache(),
});

class App extends Component {
    render() {
        return <ApolloProvider client={client}>
            <div>
                <TopBar/>
                <h1 className="App-title">It works with webpack!</h1>
                <LoginButton/>
            </div>
        </ApolloProvider>;
    }
}

export default App;
