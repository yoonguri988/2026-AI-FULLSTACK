/**
 * sagas/user.js
 * ------------------------------------------------
 * 사용자 관련 비동기 작업을 처리하는 saga
 * - 로그인, 로그아웃, 회원가입, 사용자 조회, 닉네임 수정, 사용자 삭제
 * - axios로 API를 호출 -> 성공/살패에 따라 reducer로 액션 전달
 */

import {all, fork, call, put, takeLatest} from 'redux-saga/effects'; //saga 기본 함수
import axios from 'axios'; // http 요청 라이브러리
import reducer, {
    initialState, 
    LOG_IN_REQUEST, LOG_IN_SUCCESS, LOG_IN_FAILURE,
    LOG_OUT_REQUEST, LOG_OUT_SUCCESS, LOG_OUT_FAILURE,
    SIGN_UP_REQUEST, SIGN_UP_SUCCESS, SIGN_UP_FAILURE,
    LOAD_USER_REQUEST, LOAD_USER_SUCCESS, LOAD_USER_FAILURE,
    UPDATE_NICKNAME_REQUEST, UPDATE_NICKNAME_SUCCESS, UPDATE_NICKNAME_FAILURE,
    DELETE_USER_REQUEST, DELETE_USER_SUCCESS, DELETE_USER_FAILURE,
    LOAD_MY_INFO_REQUEST, LOAD_MY_INFO_SUCCESS, LOAD_MY_INFO_FAILURE,
    CHECK_EMAIL_REQUEST, CHECK_EMAIL_SUCCESS, CHECK_EMAIL_FAILURE,
    CHECK_NICKNAME_REQUEST, CHECK_NICKNAME_SUCCESS, CHECK_NICKNAME_FAILURE,
} from '../reducers/user'; // 액션 타입 불러오기

const client = axios.create({
    baseURL: 'http://localhost:3065', //API 서버 주소
    withCredentials: true, // 쿠키/세션 인증 포함
});

// 1. all - 여러 saga 동시에 실행
// 2. fork - [비동기]로 saga 실행
// 3. call - api를 호출하고 결과를 기다림(blocking) > 동기
// 4. put - redux 액션을 dispatch
// 5. takeLatest - 특정 액션을 감지하고 가장 마지막 액션만 처리

// ---------- 로그인 ----------
// watchLogin
// post : /user/login    (requestBody)
export function loginApi(data) {
    return client.post('/user/login', data);
}
export function* login(action) {
    try {
        const result = yield call(loginApi, action.data); // API 호출, 결과물
        const user =  {
            id: result.data.APP_USER_ID,
            email: result.data.EMAIL,
            nickname: result.data.NICKNAME,
        }
        yield put({type:LOG_IN_SUCCESS, data: user}); // 성공 액션 dispatch
        // return { ...state, isLoading: false, me: action.data }; 
    } catch(err){
        yield put({type:LOG_IN_FAILURE, error: err.response?.data || err.message}); // 실패 액션 dispatch
        // return { ...state, isLoading: false, error: action.error?.message || action.error };
    }
}
function* watchLogin() {
    yield takeLatest(LOG_IN_REQUEST, login);
    // LOG_IN_REQUEST 액션 발생 -> 여러번 요청시 가장 마지막 요청 처리 1개
    // return { ...state, isLoading: true, error: null }; 
}

// ---------- 로그아웃 ----------
// watchLogout
// post : /user/logout 
export function logoutApi(data) {
    return client.post('/user/logout', data);
}
export function* logout(action) {
    try {
        yield call(logoutApi); // API 호출, 결과물
        yield put({type:LOG_OUT_SUCCESS}); // 성공 액션 dispatch
        // return { ...state, isLoading: false, me: null }; 
    } catch(err){
        yield put({type:LOG_OUT_FAILURE, error: err.response?.data || err.message}); // 실패 액션 dispatch
        // return { ...state, isLoading: false, error: action.error?.message || action.error };
    }
}
function* watchLogout() {
    yield takeLatest(LOG_OUT_REQUEST, logout);
    // LOG_OUT_REQUEST 액션 발생 -> 여러번 요청시 가장 마지막 요청 처리 1개
    // return { ...state, isLoading: true, error: null }; 
}

// ---------- 회원가입 ----------
// watchSignup
// post : /user/register (requestBody)
export function signUpApi(data) {
    return client.post('/user/register', data);
}
export function* signUp(action) {
    try {
        yield call(signUpApi, action.data); // API 호출, 결과물
        yield put({type:SIGN_UP_SUCCESS}); // 성공 액션 dispatch
        // return { ...state, isLoading: false, signUpDone: true }; 
    } catch(err){
        yield put({type:SIGN_UP_FAILURE, error: err.response?.data || err.message}); // 실패 액션 dispatch
        // return { ...state, isLoading: false, error: action.error?.message || action.error };
    }
}
function* watchSignup() {
    yield takeLatest(SIGN_UP_REQUEST, signUp);
    // SIGN_UP_REQUEST 액션 발생 -> 여러번 요청시 가장 마지막 요청 처리 1개
    // return { ...state, isLoading: true, error: null }; 
}

// ---------- 사용자 조회 ----------
// watchLoadUsers
// get  : /user/
export function loadUsersApi(data) {
    return client.get('/user/', data);
}
export function* loadUsers(action) {
    try {
        const result = yield call(loadUsersApi); // API 호출, 결과물
        const users = result.data.map((u)=>({
            id: u.APP_USER_ID,
            email: u.EMAIL,
            nickname: u.NICKNAME,
        }));
        yield put({type:LOAD_USER_SUCCESS, data: users}); // 성공 액션 dispatch
        // return { ...state, isLoading: false, users: action.data };
    } catch(err){
        yield put({type:LOAD_USER_FAILURE, error: err.response?.data || err.message}); // 실패 액션 dispatch
        // return { ...state, isLoading: false, error: action.error?.message || action.error };
    }
}
function* watchLoadUsers() {
    yield takeLatest(LOAD_USER_REQUEST, loadUsers);
    // LOG_OUT_REQUEST 액션 발생 -> 여러번 요청시 가장 마지막 요청 처리 1개
    // return { ...state, isLoading: true, error: null }; 
}

// ---------- 닉네임 수정 ----------
// watchUpdateNickname
// patch: /user/{id}/nickname 
export function updateNicknameApi(data) {
    return client.patch(`/user/${data.id}/nickname`, {nickname: data.nickname});
}
export function* updateNickname(action) {
    try {
        const result = yield call(updateNicknameApi, action.data); // API 호출, 결과물
        yield put({type:UPDATE_NICKNAME_SUCCESS, 
                   data: {id: action.data.id, nickname: action.data.nickname}}); // 성공 액션 dispatch
        // return { ...state, isLoading: false, 
        //     me: state.me && state.me.id === action.data.id
        //     ? { ...state.me, nickname: action.data.nickname } 
        //     : state.me,
        //     users: state.users.map((u)=> u.id === action.data.id? {...u, nickname: action.data.nickname}:u),
        // };
    } catch(err){
        yield put({type:UPDATE_NICKNAME_FAILURE, error: err.response?.data || err.message}); // 실패 액션 dispatch
        // return { ...state, isLoading: false, error: action.error?.message || action.error };
    }
}
function* watchUpdateNickname() {
    yield takeLatest(UPDATE_NICKNAME_REQUEST, updateNickname);
    // LOG_OUT_REQUEST 액션 발생 -> 여러번 요청시 가장 마지막 요청 처리 1개
    // return { ...state, isLoading: true, error: null }; 
}


// ---------- 사용자 삭제 ----------
// watchDeleteUser
// delete: /user/{id} 
export function deleteUserApi(id){
    return client.delete(`/user/${id}`);
}
export function* deleteUser(action) {
    try {
        const result = yield call(deleteUserApi, action.data.id); // API 호출, 결과물
        yield put({type:DELETE_USER_SUCCESS, data: {id: action.data.id}}); // 성공 액션 dispatch
        // return { ...state, isLoading: false,  
        //     me: state.me && state.me.id === action.data.id
        //     ? null
        //     : state.me,
        //     users: state.users.filter((u) => u.id !== action.data.id)
        // };  
    } catch(err){
        yield put({type:DELETE_USER_FAILURE, error: err.response?.data || err.message}); // 실패 액션 dispatch
        // return { ...state, isLoading: false, error: action.error?.message || action.error };
    }
}
function* watchDeleteUser() {
    yield takeLatest(DELETE_USER_REQUEST, deleteUser);
    // LOG_OUT_REQUEST 액션 발생 -> 여러번 요청시 가장 마지막 요청 처리 1개
    // return { ...state, isLoading: true, error: null }; 
}

export function loadMyInfoApi() {
    return client.get('/user'); // 세션 쿠키 기반으로 "나"를 조회하는 API
}
export function* loadMyInfo() {
    try {
        const result = yield call(loadMyInfoApi);
        const user = {
            id: result.data.APP_USER_ID,
            email: result.data.EMAIL,
            nickname: result.data.NICKNAME,
        };
        yield put({ type: LOAD_MY_INFO_SUCCESS, data: user });
    } catch (err) {
        yield put({ type: LOAD_MY_INFO_FAILURE, error: err.response?.data || err.message });
    }
}
function* watchLoadMyInfo() {
    yield takeLatest(LOAD_MY_INFO_REQUEST, loadMyInfo);
}

// ---------- 이메일 중복 확인 ----------
// watchCheckEmail
// : /user/{id} 
export function checkEmailApi(email) {
    return client.post('/user/check-email',  null, { params: { email } });
}
export function* checkEmailInfo(action) {
    try {
        const { email } = action.data;
        const result = yield call(checkEmailApi, email);
        yield put({
            type: CHECK_EMAIL_SUCCESS,
            data: {
                isAvailable: result.data.isAvailable,
                message: result.data.message,
            },
        });
    } catch (err) {
        // 409 등 에러 응답도 isAvailable: false로 판단해야 하는 경우가 많음
        const errData = err.response?.data;
        if (errData) {
            yield put({
                type: CHECK_EMAIL_FAILURE,
                data: {
                    isAvailable: errData.isAvailable ?? false,
                    message: errData.message,
                },
            });
        } else {
            yield put({ type: CHECK_EMAIL_FAILURE, error: err.message });
        }
    }
}
function* watchCheckEmail() {
    yield takeLatest(CHECK_EMAIL_REQUEST, checkEmailInfo);
}

// 닉네임 중복 확인
export function checkNicknameApi(nickname) {
    return client.post('/user/check-nickname',  null, { params: { nickname } });
}
export function* checkNicknameInfo(action) {
    try {
        const { nickname } = action.data;
        const result = yield call(checkNicknameApi, nickname);
        yield put({
            type: CHECK_NICKNAME_SUCCESS,
            data: {
                isAvailable: result.data.isAvailable,
                message: result.data.message,
            },
        });
    } catch (err) {
        // 409 등 에러 응답도 isAvailable: false로 판단해야 하는 경우가 많음
        const errData = err.response?.data;
        if (errData) {
            yield put({
                type: CHECK_NICKNAME_FAILURE,
                data: {
                    isAvailable: errData.isAvailable ?? false,
                    message: errData.message,
                },
            });
        } else {
            yield put({ type: CHECK_NICKNAME_FAILURE, error: err.message });
        }
    }
}
function* watchCheckNickname() {
    yield takeLatest(CHECK_NICKNAME_REQUEST, checkNicknameInfo);
}

export default function* userSaga() {
    yield all([
        fork(watchLogin),
        fork(watchLogout),
        fork(watchSignup),
        fork(watchLoadUsers),
        fork(watchUpdateNickname),
        fork(watchDeleteUser),
        fork(watchLoadMyInfo),
        // 이메일 중복 확인
        fork(watchCheckEmail),
        // 닉네임 중복 확인
        fork(watchCheckNickname),
    ]);
}