import React, { Component } from 'react';
import { connect } from 'react-redux';
import Box from '../../components/presentational/forms/Box';
import MultiFileDropzone from './MultiFileDropzone';
import { uploadAddFiles, uploadRemoveFile } from '../../state/upload/actions';

@connect(
    state => ({
        files: state.upload.files,
    }),
    {
        addFiles: uploadAddFiles,
        removeFile: uploadRemoveFile,
    })
export default class Upload extends Component {
    render() {
        const { files, addFiles, removeFile } = this.props;

        return <Box style={{ margin: '5%' }}>
            <MultiFileDropzone files={files} onDrop={addFiles} onRemove={removeFile}/>
        </Box>;
    }
}
