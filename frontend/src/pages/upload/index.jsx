import React, { Component } from 'react';
import Box from '../../components/presentational/forms/Box';
import MultiFileDropzone from './MultiFileDropzone';
import UploadManager from '../../UploadManager';

export default class Upload extends Component {
    render() {
        return <Box style={{ margin: '5%' }}>
            <MultiFileDropzone uploadManager={UploadManager}/>;
        </Box>;
    }
}
