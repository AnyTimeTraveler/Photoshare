import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class FileUploadDropArea extends Component {
    static propTypes = {
        onSelect: PropTypes.func.isRequired,
    };

    render() {
        return <div className={'file has-name'}>
            <label className={'file-label'}>
                <input className={'file-input'} type={'file'} onChange={this.props.onSelect}/>
                <span className={'file-cta'}>
                    <span className={'file-icon'}>
                        <i className={'fas fa-upload'}/>
                    </span>
                    <span className={'file-label'}>
                        Choose a file…
                    </span>
                </span>
            </label>
        </div>;
    }
}
