import React, { Component } from 'react';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';

const query = gql`
    query whoami {
        whoami {
            username
        }
    }
`;

class TopBar extends Component {
    render() {
        const { data: { loading, error, whoami }} = this.props;
        let text;

        if (loading) {
            text = 'Loading...';
        } else if (error) {
            text = `${error}`;
        } else {
            text = whoami.username;
        }

        return (
            <div>
                <input type={'text'} name={'search'} value={'IM A SEARCH FIELD'} readOnly={true}/>
                <div>{text}</div>
            </div>
        );
    }
}

export default graphql(query)(TopBar);
