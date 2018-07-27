import React, { Component } from 'react';
import PropTypes from 'prop-types';
import PreviewBox from './PreviewBox';

export default class ImageList extends Component {
    static propTypes = {
        onClick: PropTypes.func.isRequired,
        items: PropTypes.arrayOf(PropTypes.shape({
            id: PropTypes.number.isRequired,
            resolutions: PropTypes.array.isRequired,
            filename: PropTypes.string.isRequired,
        })).isRequired,
    };

    render() {
        const { items, onClick } = this.props;

        return <div className={'columns is-multiline'}>
            {items.map((image, i) =>
                <div key={`preview-${i}`} className={'column is-2-widescreen is-4-desktop is-6-tablet is-12-mobile'}>
                    <PreviewBox id={image.id} url={image.resolutions[0].url} filename={image.filename} onClick={onClick}/>
                </div>
            )}
        </div>;
    }
}
