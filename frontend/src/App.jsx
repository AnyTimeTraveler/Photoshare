import React from 'react';
import { ApolloProvider } from 'react-apollo';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { AppContainer } from 'react-hot-loader';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import MyPhotos from './pages/myPhotos';
import NavBar from './components/presentational/navbar/NavBar';
import Settings from './pages/settings';
import SearchResults from './pages/searchResults';

import Upload from './pages/upload';
import Login from './pages/login';
import ProtectedRoute from './components/ProtectedRoute';
import reducers from './state/reducers';
import apolloClient from './state/ApolloClient';

/* eslint-disable no-underscore-dangle */
// noinspection JSUnresolvedVariable
const store = createStore(
    reducers,
    {},
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__(),
);/* eslint-enable */

class App extends React.Component {
    render() {
        return <AppContainer>
            <ApolloProvider client={apolloClient}>
                <Provider store={store}>
                    <BrowserRouter>
                        <div>
                            <NavBar/>
                            <Switch>
                                <Route path={'/login'} component={Login}/>
                                <ProtectedRoute path={'/'} exact={true} component={MyPhotos}/>
                                <ProtectedRoute path={'/upload'} component={Upload}/>
                                <ProtectedRoute path={'/search'} component={SearchResults}/>
                                <ProtectedRoute path={'/settings'} component={Settings}/>
                            </Switch>
                        </div>
                    </BrowserRouter>
                </Provider>
            </ApolloProvider>
        </AppContainer>;
    }
}

export default App;
