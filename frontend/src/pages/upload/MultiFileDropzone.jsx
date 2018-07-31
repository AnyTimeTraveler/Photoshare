import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Dropzone from 'react-dropzone';
import FileUploadPreview from './FileUploadPreview';
import FileUploadDropArea from '../../components/presentational/forms/FileUploadDropArea';

export default class MultiFileDropzone extends Component {
    static propTypes = {
        files: PropTypes.object.isRequired,
        onDrop: PropTypes.func.isRequired,
        onRemove: PropTypes.func.isRequired,
    };

    constructor() {
        super();
        this.state = {
            dropzoneActive: false,
        };
    }

    onDragEnter() {
        this.setState({
            dropzoneActive: true,
        });
    }

    onDragLeave() {
        this.setState({
            dropzoneActive: false,
        });
    }

    onDrop(files) {
        const handleFolder = (handler, file) => {
            console.log('File', file);

            if (file.size == 0) {
                const entry = file.webkitGetAsEntry();
                const folderReader = entry.createReader();

                const read = (reader, self, data) => {
                    if (data.length !== 0) {
                        data.forEach(newFile => handler(handler, newFile));
                        reader.readEntries(self.bind(this, self));
                    }
                };
                const prefilledReader = read.bind(this, read);

                folderReader.readEntries(prefilledReader);
            } else {
                this.props.onDrop(file);
            }
        };

        files.forEach(file => handleFolder(handleFolder, file));
        this.setState({
            dropzoneActive: false,
        });
    }

    render() {
        const { dropzoneActive } = this.state;
        const { files, onRemove } = this.props;

        const dropzoneStyle = {
            width: '100%',
            height: '100%',
        };

        const overlayStyle = {
            width: '100%',
            height: '100%',
            padding: '2.5em 0',
            background: 'rgba(0,0,0,0.5)',
            textAlign: 'center',
            color: '#fff',
        };

        return <Dropzone
            disableClick={true}
            style={dropzoneActive ? overlayStyle : dropzoneStyle}
            // accept={'image/*, video/*'}
            onDrop={this.onDrop.bind(this)}
            onDragEnter={this.onDragEnter.bind(this)}
            onDragLeave={this.onDragLeave.bind(this)}
        >
            <div>
                <h1>File Upload: Just drop the files or folders in here</h1>
                <div>
                    {files.map((file, index) =>
                        <FileUploadPreview key={`preview-${index}`} file={file} progress={null} onCancel={() => onRemove(index)}/>
                    )}
                    <div>
                        <FileUploadDropArea onSelect={event => this.onDrop(event.files)}/>
                        <div style={{ marginTop: 10 }}/>
                    </div>
                </div>
            </div>
        </Dropzone>;
    }
}
