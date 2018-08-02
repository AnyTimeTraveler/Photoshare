import { booleanControlReducer, collectReducers, objectControlReducer, resetFieldReducer, setFieldReducer } from '../../fieldReducers';
import { combineReducers } from 'redux';
import { AUTH_LOGIN_ERROR, AUTH_LOGIN_SUCCESS, AUTH_LOGOUT_ERROR, AUTH_LOGOUT_SUCCESS, AUTH_SET_FIELD, AUTH_SET_CHECKED } from './actions';

export default combineReducers({
    user: collectReducers(
        resetFieldReducer([
            AUTH_LOGIN_ERROR,
            AUTH_LOGOUT_SUCCESS,
            AUTH_LOGOUT_ERROR,
        ], null),
        setFieldReducer([
            AUTH_LOGIN_SUCCESS,
        ], null, 'user'),
    ),
    hasChecked: booleanControlReducer({
        AUTH_SET_CHECKED: true,
    }, false),
    loginForm: objectControlReducer([ AUTH_SET_FIELD ], {
        username: '',
        password: '',
    }),
});
