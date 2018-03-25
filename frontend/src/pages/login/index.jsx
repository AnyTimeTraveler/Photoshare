import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Button } from 'reactstrap';

export default class Login extends Component {
    static propTypes = {
        from: PropTypes.string,
    };

    render() {
        return <div>
            {auth.isAuthenticated ?
                <Button onClick={auth.signout}>Logout</Button> :
                <Button onClick={() => auth.authenticate(this.props.from)}>Login</Button>}
        </div>;
    }
}
