import React, { Component } from 'react';
import PropTypes from 'prop-types';
import FileUploadPreview from './FileUploadPreview';
import FileUploadDropArea from '../../components/presentational/forms/FileUploadDropArea';

export default class MultiFileDropzone extends Component {

    static propTypes = {
        uploadManager: PropTypes.object.isRequired,
    };

    constructor() {
        super();
        this.state = {
            dropzoneActive: false,
        };
    }

    onDragEnter(event) {
        event.preventDefault();
        this.setState({
            dropzoneActive: true,
        });
    }

    onDragLeave(event) {
        event.preventDefault();
        this.setState({
            dropzoneActive: false,
        });
    }

    onDrop(event) {
        event.preventDefault();
        const { items } = event.dataTransfer;

        this.props.uploadManager.addUpload(items);

        this.setState({
            dropzoneActive: false,
        });
    }

    render() {
        const { dropzoneActive } = this.state;

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

        return <div
            style={dropzoneActive ? overlayStyle : dropzoneStyle}
            onDrop={this.onDrop.bind(this)}
            onDragEnter={this.onDragEnter.bind(this)}
            onDragOver={this.onDragEnter.bind(this)}
            onDragLeave={this.onDragLeave.bind(this)}
        >
            <div>
                <h1>File Upload: Just drop the files or folders in here</h1>
                <div>
                    {this.props.uploadManager.getUploads().map((upload, index) =>
                        <FileUploadPreview
                            key={`preview-${index}`}
                            filename={upload.filename}
                            progress={upload.progress}
                            preview={upload.preview}
                            onCancel={() => this.props.uploadManager.cancel(upload)}
                        />
                    )}
                    <div>
                        <FileUploadDropArea onSelect={event => this.onDrop(event.files)}/>
                        <div style={{ marginTop: 10 }}/>
                    </div>
                </div>
            </div>
        </div>;
    }
}
