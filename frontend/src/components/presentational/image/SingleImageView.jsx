import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class SingleImageView extends Component {
    static propTypes = {
        id: PropTypes.number.isRequired,
        filename: PropTypes.string.isRequired,
        onNext: PropTypes.func.isRequired,
        onPrevious: PropTypes.func.isRequired,
        onLeave: PropTypes.func.isRequired,
    };

    render() {
        const { id, filename, onNext, onPrevious } = this.props;

        return <div className={'columns is-mobile box'}>
            <div
                className={'column is-1'}
                onClick={onPrevious}
                style={{
                    fontSize: 40,
                    cursor: 'pointer',
                }}>
                &lt;
            </div>
            <div className={'column is-10'}>
                <img
                    style={{
                        padding: '5%',
                        maxHeight: '100%',
                        maxWidth: '100%',
                    }}
                    src={`/api/files/${id}`}/>
                <h2 style={{ textAlign: 'center' }}>{filename}</h2>
            </div>
            <div
                className={'column is-1'}
                onClick={onNext}
                style={{
                    fontSize: 40,
                    cursor: 'pointer',
                }}>
                &gt;
            </div>
        </div>;
    }
}
