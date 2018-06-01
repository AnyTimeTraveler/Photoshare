import React, { Component } from 'react';
import NavBarLink from './presentational/navbar/NavBarLink';

export default class NavBar extends Component {
    render() {
        return <nav className={'navbar'}>
            <div className={'navbar-brand'}>
                <NavBarLink to={'/'}>
                    <img src={'https://bulma.io/images/bulma-logo.png'} alt={'Bulma: a modern CSS framework based on Flexbox'} width={'112'} height={'28'}/>
                </NavBarLink>
                <div className={'navbar-burger burger'} data-target={'navbarExampleTransparentExample'}>
                    <span/>
                    <span/>
                    <span/>
                </div>
            </div>

            <div className={'navbar-menu'}>
                <div className={'navbar-start'}>
                    <NavBarLink to={'/'}>Home</NavBarLink>
                    <NavBarLink to={'/upload'}>Upload</NavBarLink>
                    <NavBarLink to={'/search'}>Search</NavBarLink>
                    <NavBarLink to={'/settings'}>Settings</NavBarLink>
                </div>

                <div className={'navbar-end'}>
                    <div className={'navbar-item'}>
                        End!
                    </div>
                </div>
            </div>
        </nav>;
    }
}
