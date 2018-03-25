import React, { Component } from 'react';
import PropTypes from 'prop-types';


export default class Image extends Component {
    static propTypes = {
        src: PropTypes.string.isRequired,
    };

    render() {
        return <img src={this.props.src}/>;
    }
}
