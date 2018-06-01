import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class Button extends Component {
    static propTypes = {
        children: PropTypes.node.isRequired,
    };

    render() {
        return <div className={'box'} {...this.props}>{this.props.children}</div>;
    }
}
