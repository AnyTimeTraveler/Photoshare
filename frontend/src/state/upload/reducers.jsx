import { combineReducers } from 'redux';
import { UPLOAD_ADD_FILES, UPLOAD_REMOVE_FILE, UPLOAD_SET_FILES } from './actions';

const files = (state = [], action) => {
    switch (action.type) {
    case UPLOAD_ADD_FILES:
        return state.concat(action.files);
    case UPLOAD_REMOVE_FILE:
        return state.filter((a, i) => i != action.index);
    case UPLOAD_SET_FILES:
        return action.files;
    default:
        return state;
    }
};

export default combineReducers({
    files,
});
