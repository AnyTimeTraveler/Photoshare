import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import gql from 'graphql-tag';
import { Mutation } from 'react-apollo';
import { authLoginReset, authLoginSuccess, authSetField } from '../../state/auth/actions';
import LoginForm from './LoginForm';

const LOGIN = gql`
    mutation login($username: String!, $password: String!) {
        login(username: $username, passwordHash: $password) {
            id
        }
    }
`;


@connect(state => ({
    username: state.auth.loginForm.username,
    password: state.auth.loginForm.password,
    isLoggedin: state.auth.user != null,
}), {
    loginSuccess: authLoginSuccess,
    resetLogin: authLoginReset,
    setField: authSetField,
})
export default class Login extends Component {
    static propTypes = {
        from: PropTypes.string,
    };

    componentDidMount() {
        this.props.resetLogin();
    }

    render() {
        const { username, password, isLoggedin, loginSuccess } = this.props;
        // noinspection RequiredAttributes

        return <Mutation mutation={LOGIN}>{(login, { data }) => {
            const success = data && data.login && data.login.id != null;

            if (success) {
                loginSuccess(data.login);
            }

            return <LoginForm
                loginAction={() => login({
                    variables: {
                        username,
                        password,
                    },
                })}
                setField={this.props.setField}
                username={username}
                password={password}
                isSuccess={isLoggedin}
            />;
        }
        }
        </Mutation>;
    }
}
