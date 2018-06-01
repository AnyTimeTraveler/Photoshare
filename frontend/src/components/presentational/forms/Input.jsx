import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class Input extends Component {
    static propTypes = {
        type: PropTypes.string.isRequired,
        onChange: PropTypes.func,
    };

    render() {
        return <input
            type={this.props.type}
            onChange={event => this.props.onChange(event.target.value)}/>;
    }
}
