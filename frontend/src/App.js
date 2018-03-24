import React, {Component} from 'react';
import {ApolloClient} from 'apollo-client';
import {ApolloProvider} from 'react-apollo';
import {InMemoryCache} from 'apollo-cache-inmemory';
import {HttpLink} from 'apollo-link-http';
import {Router} from 'react-router';

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
                <Router/>
            </div>
        </ApolloProvider>;
    }
}

export default App;
