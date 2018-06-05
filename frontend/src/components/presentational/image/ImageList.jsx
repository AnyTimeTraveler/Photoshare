import React, { Component } from 'react';
import PropTypes from 'prop-types';
import PreviewBox from './PreviewBox';

export default class ImageList extends Component {
    static propTypes = {
        onClick: PropTypes.func.isRequired,
        ids: PropTypes.arrayOf(PropTypes.shape({
            id: PropTypes.number.isRequired,
            filename: PropTypes.string.isRequired,
        })).isRequired,
    };

    render() {
        const { ids, onClick } = this.props;

        return <div className={'columns is-multiline'}>
            {ids.map((image, i) =>
                <div key={`preview-${i}`} className={'column is-2-widescreen is-4-desktop is-6-tablet is-12-mobile'}>
                    <PreviewBox id={image.id} filename={image.filename} onClick={onClick}/>
                </div>
            )}
        </div>;
    }
}
