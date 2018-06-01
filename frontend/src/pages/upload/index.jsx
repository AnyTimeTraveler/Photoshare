import React, { Component } from 'react';
import FileUploadDropArea from '../../components/presentational/forms/FileUploadDropArea';
import Box from '../../components/presentational/forms/Box';


export default class Upload extends Component {
    render() {
        return <Box style={{ margin: '5%' }}>
            <FileUploadDropArea/>
        </Box>;
    }
}
