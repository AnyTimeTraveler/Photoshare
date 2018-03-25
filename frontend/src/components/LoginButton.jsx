import React, { Component } from 'react';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';

class LoginButton extends Component {
    onClick() {
        this.props.mutate({
            variables: {
                username: 'simon',
                passwordHash: 'testpass',
            },
        })
            .then(({ data }) => {
                console.log('got data', data);
            })
            .catch(error => {
                console.log('there was an error sending the query', error);
            });
    }

    render() {
        return <input type={'button'} onClick={this.onClick.bind(this)} value={'Login'}/>;
    }
}

const submitRepository = gql`
    mutation login($username: String!, $passwordHash: String!) {
        login(username: $username, passwordHash: $passwordHash) {
            username
        }
    }
`;

export default graphql(submitRepository)(LoginButton);
