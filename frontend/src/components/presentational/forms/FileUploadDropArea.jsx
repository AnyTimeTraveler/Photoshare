import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class FileUploadDropArea extends Component {
    static propTypes = {
        type: PropTypes.string,
        onClick: PropTypes.func,
    };

    static defaultProps = {
        type: 'button',
    };

    render() {
        return <div className={'file has-name'}>
            <label className={'file-label'}>
                <input className={'file-input'} type={'file'} name={'resume'}/>
                <span className={'file-cta'}>
                    <span className={'file-icon'}>
                        <i className={'fas fa-upload'}/>
                    </span>
                    <span className={'file-label'}>
                        Choose a file…
                    </span>
                </span>
                <span className={'file-name'}>
                    Screen Shot 2017-07-29 at 15.54.25.png
                </span>
            </label>
        </div>;
    }
}
