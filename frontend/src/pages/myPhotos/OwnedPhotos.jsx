import React, { Component } from 'react';
import { connect } from 'react-redux';
import gql from 'graphql-tag';
import apolloClient from '../../state/ApolloClient';
import ImageList from '../../components/presentational/image/ImageList';
import SingleImageView from '../../components/presentational/image/SingleImageView';
import {
    curentCollectionSetItems,
    curentCollectionSetCurrentItem,
    curentCollectionNextItem,
    curentCollectionPreviousItem,
    curentCollectionDeselect,
} from '../../state/currentCollection/actions';

const LIST_PHOTOS = gql`
    {
        getOwnedPhotos {
            id
            filename
        }
    }
`;


@connect(state => ({
    items: state.currentCollection.items,
    currentIndex: state.currentCollection.currentIndex,
}), {
    setItems: curentCollectionSetItems,
    setCurrentItem: curentCollectionSetCurrentItem,
    nextItem: curentCollectionNextItem,
    previousItem: curentCollectionPreviousItem,
    deselect: curentCollectionDeselect,
})
export default class OwnedPhotos extends Component {
    constructor(props) {
        super(props);

        this.state = {
            error: null,
        };
    }

    componentDidMount() {
        this.fetchOwnedPhotos();
    }

    fetchOwnedPhotos() {
        apolloClient.query({
            query: LIST_PHOTOS,
            variables: {},
        })
            .then(data => this.props.setItems(data.data.getOwnedPhotos))
            .catch(error => this.setState({ error }));
    }

    render() {
        const {
            items,
            currentIndex,
            setCurrentItem,
            nextItem,
            previousItem,
            deselect,
        } = this.props;
        const { error } = this.state;

        if (error) {
            return <p>Error: {JSON.stringify(error)}</p>;
        }

        if (!items) {
            return <p>Loading...</p>;
        }

        return <div
            style={{
                width: '100%',
                height: '100%',
            }}>
            {currentIndex != null ?
                <SingleImageView
                    id={items[currentIndex].id}
                    index={currentIndex}
                    filename={items[currentIndex].filename}
                    onNext={nextItem}
                    onPrevious={previousItem}
                    onLeave={deselect}
                /> :
                <ImageList ids={items} onClick={setCurrentItem.bind(this)}/>
            }
        </div>;
    }
}
