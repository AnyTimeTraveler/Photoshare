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
        return (
            <div>
                <input type={'text'} name={'search'} value={'IM A SEARCH FIELD'}/>
                <div>
                    {this.props.whoami}
                </div>
            </div>
        );
    }
}

export default graphql(query)(TopBar);
