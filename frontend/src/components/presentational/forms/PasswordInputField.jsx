import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Input from './Input';

export default class PasswordInputField extends Component {
    static propTypes = {
        label: PropTypes.string.isRequired,
        value: PropTypes.string.isRequired,
        onChange: PropTypes.func,
    };

    render() {
        return <div className={'field'}>
            <label className={'label'}>{this.props.label}</label>
            <div className={'control'}>
                <Input type={'password'} value={this.props.value} onChange={this.props.onChange} placeholder={this.props.placeholder}/>
            </div>
        </div>;
    }
}
