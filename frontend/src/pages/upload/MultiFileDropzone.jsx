import React, { Component } from 'react';
import Dropzone from 'react-dropzone';

export default class FullScreenDropZone extends Component {
    constructor() {
        super();
        this.state = {
            accept: 'image/*',
            files: [],
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
        console.log(files);
        this.setState({
            files,
            dropzoneActive: false,
        });
    }

    render() {
        const { accept, files, dropzoneActive } = this.state;
        const overlayStyle = {
            position: 'absolute',
            top: 0,
            right: 0,
            bottom: 0,
            left: 0,
            padding: '2.5em 0',
            background: 'rgba(0,0,0,0.5)',
            textAlign: 'center',
            color: '#fff',
        };

        return <Dropzone
            disableClick={true}
            style={{
                position: 'absolute',
                width: '100%',
                height: '100%',
                top: 0,
                bottom: 0,
                left: 0,
                right: 0,
            }}
            accept={accept}
            onDrop={this.onDrop.bind(this)}
            onDragEnter={this.onDragEnter.bind(this)}
            onDragLeave={this.onDragLeave.bind(this)}
        >
            { dropzoneActive && <div style={overlayStyle}>Drop files...</div> }
            <div>
                <h1>Upload</h1>
                {/* <label htmlFor={'mimetypes'}>Enter mime types you want to accept: </label>
                <input
                    type={'text'}
                    id={'mimetypes'}
                    onChange={this.applyMimeTypes.bind(this)}
                />*/}

                <h2>Dropped files</h2>
                <ul>
                    {files.map((f, i) =>
                        <li key={`file-${i}`}>{f.name} - {f.size} bytes <img
                            src={f.preview}
                            style={{
                                width: 50,
                                height: 50,
                            }}/> </li>
                    )}
                </ul>

            </div>
        </Dropzone>;
    }
}
