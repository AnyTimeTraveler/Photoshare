export const AUTH_LOGIN_SUCCESS = 'AUTH_LOGIN_SUCCESS';
export const AUTH_LOGIN_ERROR = 'AUTH_LOGIN_ERROR';
export const AUTH_LOGIN_RESET = 'AUTH_LOGIN_RESET';
export const AUTH_LOGOUT_SUCCESS = 'AUTH_LOGOUT_SUCCESS';
export const AUTH_LOGOUT_ERROR = 'AUTH_LOGOUT_ERROR';
export const AUTH_SET_FIELD = 'AUTH_SET_FIELD';
export const AUTH_SET_CHECKED = 'AUTH_SET_CHECKED';

export const authLoginSuccess = user => ({
    type: AUTH_LOGIN_SUCCESS,
    user,
});
export const authLoginError = () => ({ type: AUTH_LOGIN_ERROR });
export const authLoginReset = () => ({ type: AUTH_LOGIN_RESET });
export const authLogoutSuccess = () => ({ type: AUTH_LOGOUT_SUCCESS });
export const authLogoutError = () => ({ type: AUTH_LOGOUT_ERROR });
export const authSetField = (field, value) => ({
    type: AUTH_SET_FIELD,
    field,
    value,
});
export const authSetChecked = () => ({ type: AUTH_SET_CHECKED });
