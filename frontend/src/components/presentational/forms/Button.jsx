import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class Button extends Component {
    static propTypes = {
        type: PropTypes.string,
        onClick: PropTypes.func,
        children: PropTypes.string.isRequired,
    };

    static defaultProps = {
        type: 'button',
    };

    render() {
        return <input
            className={'button'}
            type={this.props.type}
            onClick={this.props.onClick}
            value={this.props.children}/>;
    }
}
