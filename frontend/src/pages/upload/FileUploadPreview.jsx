import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Box from '../../components/presentational/forms/Box';

export default class FileUploadPreview extends Component {
    static propTypes = {
        filename: PropTypes.string.isRequired,
        progress: PropTypes.number.isRequired,
        preview: PropTypes.string.isRequired,
        onCancel: PropTypes.func.isRequired,
    };

    render() {
        const { file, onCancel, progress } = this.props;
        const progressClass = progress < 100 ? 'is-info' : 'is-success';

        return <Box>
            <div className={'level'}>
                <div className={'level-left'}>
                    <img
                        className={'level-item'}
                        src={file.preview}
                        style={{
                            width: 50,
                            height: 50,
                        }}/>
                    <div className={'level-item'}>{file.name}</div>
                    <progress className={`level-item progress is-medium ${progressClass}`} value={progress} max={'100'}>{progress}%</progress>
                </div>
                <div className={'level-right'}>
                    <a className={'level-item button is-danger'} onClick={onCancel}>
                        <span className={'icon'}>
                            <i className={'fas fa-times'}/>
                        </span>
                    </a>
                </div>
            </div>
        </Box>;
    }
}
