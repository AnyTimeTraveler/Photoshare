import React, { Component } from 'react';
import { Button, Nav, Navbar, NavbarBrand, NavItem, NavLink } from 'reactstrap';
import Search from './Search';

class TopBar extends Component {
    render() {
        return (
            <div>
                <Navbar color="faded" light={true} expand="md">
                    <NavbarBrand href="/">Photoshare</NavbarBrand>
                    <Search/>
                    <Nav className="ml-auto" navbar={true}>
                        <NavItem>
                            <NavLink href="/upload" active={true}>Upload</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/settings" active={true}>Settings</NavLink>
                        </NavItem>
                    </Nav>
                    <Button color="primary">Logout</Button>
                </Navbar>
            </div>
        );
    }
}

export default TopBar;
