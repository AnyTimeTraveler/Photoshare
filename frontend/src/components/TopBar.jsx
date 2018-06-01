import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class TopBar extends Component {
    render() {
        return <nav className={'navbar'}> {/* is-transparent*/}
            <div className={'navbar-brand'}>
                <Link to={'/'} className={'navbar-item'}>
                    <img src={'https://bulma.io/images/bulma-logo.png'} alt={'Bulma: a modern CSS framework based on Flexbox'} width={'112'} height={'28'}/>
                </Link>
                <div className={'navbar-burger burger'} data-target={'navbarExampleTransparentExample'}>
                    <span/>
                    <span/>
                    <span/>
                </div>
            </div>

            <div className={'navbar-menu'}>
                <div className={'navbar-start'}>
                    <Link to={'/'}>
                        Home
                    </Link>
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

export default TopBar;
