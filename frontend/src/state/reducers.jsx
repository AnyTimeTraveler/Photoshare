import { combineReducers } from 'redux';
import auth from './auth/reducers';
import upload from './upload/reducers';
import currentCollection from './currentCollection/reducers';

export default combineReducers({
    auth,
    upload,
    currentCollection,
});
