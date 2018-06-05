import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import gql from 'graphql-tag';
import { Mutation } from 'react-apollo';
import { authLoginReset, authLoginSuccess, authSetField } from '../../state/auth/actions';
import LoginForm from './LoginForm';
import { Redirect } from 'react-router-dom';

const LOGIN = gql`
    mutation login($username: String!, $password: String!) {
        login(username: $username, passwordHash: $password) {
            id
            username
            email
            emailhash
            firstName
            lastName
        }
    }
`;


@connect(state => ({
    username: state.auth.loginForm.username,
    password: state.auth.loginForm.password,
    isLoggedin: state.auth.user != null,
}), {
    onLoginSuccess: authLoginSuccess,
    resetLogin: authLoginReset,
    setField: authSetField,
})
export default class Login extends Component {
    static propTypes = {
        redirect: PropTypes.string,
    };

    static defaultProps = {
        redirect: '/',
    };

    componentDidMount() {
        this.props.resetLogin();
    }

    render() {
        const { username, password, onLoginSuccess, redirect } = this.props;
        // noinspection RequiredAttributes

        return <Mutation mutation={LOGIN}>{
            (login, { data }) => {
                const success = data && data.login && data.login.id != null;

                if (success) {
                    onLoginSuccess(data.login);
                }

                return success ?
                    <Redirect to={redirect}/> :
                    <LoginForm
                        loginAction={() => login({
                            variables: {
                                username,
                                password,
                            },
                        })}
                        setField={this.props.setField}
                        username={username}
                        password={password}
                    />;
            }
        }
        </Mutation>;
    }
}
