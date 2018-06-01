import React from 'react';
import { connect } from 'react-redux';
import { Redirect, Route } from 'react-router-dom';
import PropTypes from 'prop-types';

@connect(state => state.auth)
export default class ProtectedRoute extends Route {
    static propTypes = {
        component: PropTypes.func.isRequired,
        path: PropTypes.string.isRequired,
        exact: PropTypes.bool,
    };

    render() {
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
