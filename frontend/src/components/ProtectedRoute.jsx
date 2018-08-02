import React from 'react';
import { connect } from 'react-redux';
import { Redirect, Route } from 'react-router-dom';
import PropTypes from 'prop-types';

import apolloClient from '../ApolloClient';
import gql from 'graphql-tag';
import { authLoginSuccess, authSetChecked } from '../state/auth/actions';

const WHOAMI = gql`
    {
        whoami {
            id
            username
            email
            emailhash
            firstName
            lastName
        }
    }
`;

@connect(
    state => ({
        user: state.auth.user,
        hasChecked: state.auth.hasChecked,
    }),
    {
        setChecked: authSetChecked,
        setUser: authLoginSuccess,
    }
)
export default class ProtectedRoute extends Route {
    static propTypes = {
        component: PropTypes.func.isRequired,
        path: PropTypes.string.isRequired,
        exact: PropTypes.bool,
    };

    /**
     * On load, if there isn't alreay a user logged in, check if there is already a session in the backend and adopt it, if possible
     */
    componentDidMount() {
        if (!this.props.user) {
            apolloClient.query({
                query: WHOAMI,
                variables: {},
            })
                .then(data => {
                    if (data && data.data && data.data.whoami) {
                        this.props.setUser(data.data.whoami);
                    }
                })
                // eslint-disable-next-line no-console
                .catch(error => console.error('Error checking backend for existing session', error))
                .finally(() => this.props.setChecked());
        }
    }

    render() {
        if (!this.props.hasChecked) {
            return 'Loading...';
        }
        if (this.props.user) {
            return super.render();
        }
        return <Redirect
            to={{
                pathname: '/login',
                props: { from: this.props.location },
            }}/>;
    }
}
