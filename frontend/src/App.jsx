import React from 'react';
import ApolloClient from 'apollo-boost';
import { ApolloProvider } from 'react-apollo';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import MyPhotos from './pages/myPhotos';
import TopBar from './components/TopBar';
import Settings from './pages/settings';
import SearchResults from './pages/searchResults';
import { createStore } from 'redux';

import Upload from './pages/upload';
import Login from './pages/login';
import ProtectedRoute from './components/ProtectedRoute';
import reducers from './state/reducers';
import { AppContainer } from 'react-hot-loader';


const client = new ApolloClient({
    uri: 'http://localhost:8080/graphql',
});


const store = createStore(
    reducers,
    {},
);

class App extends React.Component {
    render() {
        return <AppContainer>
            <ApolloProvider client={client} store={store}>
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
            </ApolloProvider>
        </AppContainer>;
    }
}

export default App;