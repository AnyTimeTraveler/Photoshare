import React from 'react';
import { Redirect, Route } from 'react-router-dom';
import PropTypes from 'prop-types';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';

class ProtectedRoute extends Route {
    static propTypes = {
        component: PropTypes.func.isRequired,
        path: PropTypes.string.isRequired,
        exact: PropTypes.bool,
    };

    render() {
        if (auth.isAuthenticated) {
            return super.render();
        }
        return <Redirect
            to={{
                pathname: auth.loginPath,
                props: { from: this.props.location },
            }}/>;
    }
}

const whoami = gql`
    query whoami {
        whoami {
            id
        }
    }
`;

export default graphql(whoami)(ProtectedRoute);
