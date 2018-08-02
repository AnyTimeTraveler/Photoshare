class UploadManager {
    uploads = [];

    addUpload(items) {
        for (let i = 0; i < items.length; i++) {
            // webkitGetAsEntry is where the magic happens
            const item = items[i].webkitGetAsEntry();

            if (item) {
                this._traverseFileTree(item);
            }
        }
    }

    getUploads() {
        return this.uploads.map(upload => ({
            filename: upload.filename,
            progress: upload.progress,
            preview: upload.preview,
        }));
    }

    cancelUpload(upload) {
        // TODO
    }

    _traverseFileTree(item, oldPath) {
        const path = oldPath || '';

        if (item.isFile) {
            item.file(file => this._uploadFile(path, file));
        } else if (item.isDirectory) {
            console.log('Folder:', path + item.name);
            // Get folder contents
            const dirReader = item.createReader();

            dirReader.readEntries(entries =>
                entries.forEach(entry =>
                    this._traverseFileTree(entry, `${path + item.name}/`)
                )
            );
        }
    }

    _uploadFile(path, file) {
        console.log('file:', path + file.name);
        const reader = new FileReader();

        reader.onloadend = () => {
            this.props.addFiles({
                name: file.name,
                preview: reader.result,
                progress: 0,
            });
        };
        reader.readAsDataURL(file);

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
        xhr.addEventListener(
            'progress',
            e => {
                const done = e.position || e.loaded;
                const total = e.totalSize || e.total;

                onProgress && onProgress({ done,
                    total });
            },
            false
        );
        const formData = new FormData();

        formData.append('file', file, file.name);
        xhr.send(formData);
    }
}

export default new UploadManager();
