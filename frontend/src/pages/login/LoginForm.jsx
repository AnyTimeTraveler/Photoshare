import React, { Component } from 'react';
import PropTypes from 'prop-types';

import Button from '../../components/presentational/forms/Button';
import TextInputField from '../../components/presentational/forms/TextInputField';
import PasswordInputField from '../../components/presentational/forms/PasswordInputField';


export default class LoginForm extends Component {
    static propTypes = {
        loginAction: PropTypes.func.isRequired,
        username: PropTypes.string.isRequired,
        password: PropTypes.string.isRequired,
    };


    render() {
        const { username, password } = this.props;

        return <div>
            <TextInputField label={'Username'} value={username} onChange={value => this.props.setField('username', value)}/>
            <PasswordInputField label={'Password'} value={password} onChange={value => this.props.setField('password', value)}/>
            <Button onClick={this.props.loginAction}>Login</Button>
        </div>;
    }
}
