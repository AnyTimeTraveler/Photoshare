import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

export default class NavBarLink extends Component {
    static propTypes = {
        children: PropTypes.any.isRequired,
        to: PropTypes.string.isRequired,
    };

    render() {
        return <Link to={this.props.to} className={'navbar-item'}>{this.props.children}</Link>;
    }
}
