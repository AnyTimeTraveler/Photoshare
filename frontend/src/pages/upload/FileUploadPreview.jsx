import React, { Component } from 'react';
import Dropzone from 'react-dropzone';
import FileUploadDropArea from '../../components/presentational/forms/FileUploadDropArea';
import Box from '../../components/presentational/forms/Box';


export default class Upload extends Component {
    render() {
        return <Box style={{ margin: '5%' }}>
            <Dropzone
                style={{
                    width: '100%',
                    height: '100%',
                }}>
                <FileUploadDropArea/>
                <div style={{ marginTop: 10 }}/>
            </Dropzone>
        </Box>;
    }
}
