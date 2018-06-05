import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class PreviewBox extends Component {
    static propTypes = {
        onClick: PropTypes.func.isRequired,
        id: PropTypes.number.isRequired,
        filename: PropTypes.string.isRequired,
    };

    render() {
        const { onClick, id, filename } = this.props;

        return <div
            className={'card'}
            style={{
                cursor: 'pointer',
            }}
            onClick={() => onClick(id)}
        >
            <div className={'card-image'}>
                <figure className={'image is-4by3'}>
                    <img src={`/api/files/${id}`}/>
                </figure>
            </div>
            <div className={'card-content'}>
                <div className={'media'}>
                    <div className={'media-content'}>
                        <p>{filename}</p>
                    </div>
                </div>
            </div>
        </div>;
    }
}
