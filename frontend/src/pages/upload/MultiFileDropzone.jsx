import React, { Component } from 'react';
import PropTypes from 'prop-types';
import FileUploadPreview from './FileUploadPreview';
import FileUploadDropArea from '../../components/presentational/forms/FileUploadDropArea';

export default class MultiFileDropzone extends Component {
    static propTypes = {
        files: PropTypes.array.isRequired,
        onDrop: PropTypes.func.isRequired,
        onRemove: PropTypes.func.isRequired,
    };

    constructor() {
        super();
        this.state = {
            dropzoneActive: false,
        };
    }

    onDragEnter(event) {
        event.preventDefault();
        console.log('DRAG IN!');
        this.setState({
            dropzoneActive: true,
        });
    }

    onDragLeave(event) {
        event.preventDefault();
        console.log('DRAG OUT!');
        this.setState({
            dropzoneActive: false,
        });
    }

    onDrop(event) {
        event.preventDefault();
        console.log('DROP!');
        const { items } = event.dataTransfer;

        for (let i = 0; i < items.length; i++) {
            // webkitGetAsEntry is where the magic happens
            const item = items[i].webkitGetAsEntry();

            if (item) {
                this.traverseFileTree(item);
            }
        }

        this.setState({
            dropzoneActive: false,
        });
    }

    traverseFileTree(item, path) {
        path = path || '';
        if (item.isFile) {
            // Get file
            item.file(file => {
                this.uploadFile(path, file);
            });
        } else if (item.isDirectory) {
            console.log('Folder:', path + item.name);
            // Get folder contents
            const dirReader = item.createReader();

            dirReader.readEntries(entries => {
                for (let i = 0; i < entries.length; i++) {
                    this.traverseFileTree(entries[i], `${path + item.name}/`);
                }
            });
        }
    }

    uploadFile(path, file) {
        console.log('File:', path + file.name);

        const xhr = new XMLHttpRequest();

        xhr.open('POST', '/api/files/upload', true);
        xhr.onload = () => {
            if (xhr.status > 200 && xhr.status < 300) {
                // File(s) uploaded.
                console.log('Uploaded!');
            } else {
                console.error('Upload failed!', xhr);
            }
        };
        const formData = new FormData();

        formData.append('file', file, file.name);
        xhr.send(formData);
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

        return <div
            style={dropzoneActive ? overlayStyle : dropzoneStyle}
            // accept={'image/*, video/*'}
            onDrop={this.onDrop.bind(this)}
            onDragEnter={this.onDragEnter.bind(this)}
            onDragOver={this.onDragEnter.bind(this)}
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
        </div>;
    }
}
