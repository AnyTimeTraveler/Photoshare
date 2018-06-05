import React, { Component } from 'react';
import NavBarLink from './NavBarLink';

export default class UserIcon extends Component {
    render() {
        const { user } = this.props;

        return [
            <NavBarLink key={'navbar-username'} to={'/settings'}>{user.firstName} {user.lastName}</NavBarLink>,
            <img
                key={'navbar-usericon'}
                className={'navbar-item'}
                src={`https://s.gravatar.com/avatar/${user.emailhash}?s=52`}
                style={{
                    borderRadius: '50%',
                }}
            />,
        ];
    }
}
