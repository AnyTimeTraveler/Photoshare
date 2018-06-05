export const UPLOAD_SET_FILES = 'UPLOAD_SET_FILES';
export const UPLOAD_ADD_FILES = 'UPLOAD_ADD_FILES';
export const UPLOAD_REMOVE_FILE = 'UPLOAD_REMOVE_FILE';

export const uploadSetFiles = files => ({
    type: UPLOAD_SET_FILES,
    files,
});
export const uploadAddFiles = files => ({
    type: UPLOAD_ADD_FILES,
    files,
});
export const uploadRemoveFile = index => ({
    type: UPLOAD_REMOVE_FILE,
    index,
});
