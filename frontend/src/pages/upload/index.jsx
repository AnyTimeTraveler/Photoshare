import React, { Component } from 'react';
import { DropzoneComponent } from 'react-dropzone-component';

export default class Upload extends Component {
    render() {
        const componentConfig = {
            postUrl: '/api/files/upload',
            showFiletypeIcon: true,
        };
        const djsConfig = {
            autoProcessQueue: true,
            method: 'POST',
        };
        const eventHandlers = { addedfile: file => console.log(file) };

        return <DropzoneComponent
            config={componentConfig}
            eventHandlers={eventHandlers}
            djsConfig={djsConfig}
        />;
    }
}
