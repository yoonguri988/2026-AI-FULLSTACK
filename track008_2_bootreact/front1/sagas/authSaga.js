// sagas/authSaga.js
import {all, call, put, takeLatest} from 'redux-saga/effects';
import axios from 'axios';
import {
    signupRequest, signupSuccess, signupFailure,
    fetchUserRequest, fetchUserSuccess, fetchUserFailure,
    resetUserState,
}from '../reducers/authReducer';

const USER_API_BASE = 'http://localhost:8080/api/users'

// === 회원 가입 POST /api/users ===
export const signupApi = (userData) => axios.post(USER_API_BASE, userData);
export function* signup(action) {
    try {
        const result = yield call(signupApi, action.payload);
        yield put(signupSuccess(result.data));

    } catch(err){
        yield put(signupFailure(err.response?.data?.message || err.message));
    }
}
function* watchSignup() {yield takeLatest(signupRequest.type, signup)}

// === 단건 조회 GET /api/users/{id} ===
export const fetchUserApi = (id) => axios.get(`${USER_API_BASE}/${id}`);
//2) 
export function* fetchUser(action) {
    try {
        // action = {type:user/fetchUserRequest , payload: 1}
        const result = yield call(fetchUserApi, action.payload);
        yield put(fetchUserSuccess(result.data));

    } catch(err){
        yield put(fetchUserFailure(err.response?.data?.message || err.message));
    }
}
//1) 여러번 요청와도 1번만
function* watchFetchUser() {yield takeLatest(fetchUserRequest.type, fetchUser)}

export default function *authSaga() {
    yield all([
        call(watchSignup),
        call(watchFetchUser),
    ]);
}