/**
 * sagas/user.js
 * ----------------------------------------------
 * 사용자 관련 비동기 작업을 처리하는 saga
 * - 로그인, 로그아웃, 회원가입, 사용자 조회, 닉네임 수정, 사용자 삭제
 * - axios로 API를 호출 → 성공/실패에 따라 reducer로 액션 전달
 */
import { all, fork , call, put, takeLatest} from 'redux-saga/effects'; //  saga 기본 함수들
import axios from 'axios';  //  HTTP요청 라이브러리  
import  reducer, {
    initialState , 
    LOG_IN_REQUEST, LOG_IN_SUCCESS, LOG_IN_FAILURE,
    LOG_OUT_REQUEST, LOG_OUT_SUCCESS, LOG_OUT_FAILURE,
    SIGN_UP_REQUEST, SIGN_UP_SUCCESS,  SIGN_UP_FAILURE,
    LOAD_USERS_REQUEST, LOAD_USERS_SUCCESS, LOAD_USERS_FAILURE,
    UPDATE_NICKNAME_REQUEST,  UPDATE_NICKNAME_SUCCESS, UPDATE_NICKNAME_FAILURE,
    DELETE_USER_REQUEST, DELETE_USER_SUCCESS, DELETE_USER_FAILURE , 
    CHECK_EMAIL_REQUEST, 
    CHECK_EMAIL_SUCCESS, 
    CHECK_EMAIL_FAILURE,
    CHECK_NICKNAME_SUCCESS,
    CHECK_NICKNAME_FAILURE,
    CHECK_NICKNAME_REQUEST,
    SEARCH_USERS_REQUEST,   
    SEARCH_USERS_SUCCESS,
    SEARCH_USERS_FAILURE,
} from '../reducers/user'; ///////////////////////////  액션 타입 불러오기 - 추가 구현 하세요.

//  axios 클라이언트 기본설정
const client = axios.create({
    baseURL: 'http://localhost:3065', // API 서버 주소
    withCredentials: true,  // 쿠키/세션 인증 포함
});

// ------------------ 로그아웃 -----------------
//  post: /user/logout  
export function logoutApi(){
    return client.post('/user/logout');  //  로그인 API 호출    http://localhost:3065/user/logout
}
export function* logout(){
    try{
        yield call(  logoutApi );  // API 호출 - (비동기 → call) 
        yield put(  { type: LOG_OUT_SUCCESS  } );  // 성공 액션 dispatch
        // return {  ...state , isLoading:false,  me: null  };
    }catch(err){
        yield put(  { type: LOG_OUT_FAILURE , error: err.response?.data || err.message} );  //  실패 액션 dispatch
        //  return {    ...state ,    isLoading:false,   error: action.error?.message ||action.error,  }; 
    }
}
function * watchLogout(){
    yield takeLatest(LOG_OUT_REQUEST,  logout);  
    // 1. LOG_OUT_REQUEST 액션발생 , takeLatest (여러번요청 → 가장마지막요청처리1)
    // return {  ...state ,  isLoading:true, error: null    }; 
}



// ------------------ 로그인 ------------------
// post: /user/login    - data
export function loginApi(data){
    return client.post('/user/login' , data);  // 로그인 API 호출    http://localhost:3065/user/register
}
export function* login(action){
    try{
        const result = yield call(  loginApi ,  action.data );  // API 호출 - (비동기 → call) action.data : email, password
        const user = {
            id: result.data.APP_USER_ID,
            email: result.data.EMAIL,
            nickname:result.data.NICKNAME
        };
        yield put(  { type: LOG_IN_SUCCESS , data:user } );  // 성공 액션 dispatch
        //return {  ...state , isLoading:false,  me: action.data  };
    }catch(err){
        yield put(  { type: LOG_IN_FAILURE , error: err.response?.data || err.message} );  // 실패 액션 dispatch
        //  return {    ...state ,    isLoading:false,   error: action.error?.message ||action.error,  }; 
    }
}
function * watchLogin(){
    yield takeLatest(LOG_IN_REQUEST,  login);  
    // 1. LOG_IN_REQUEST 액션발생 , takeLatest (여러번요청 → 가장마지막요청처리1)
    // return {  ...state ,  isLoading:true, error: null    }; 
}




// ------------------ 회원가입 ----------------
// post: /user/register  - data
export function signUpApi(data){
    return client.post('/user/register' , data);  // 회원가입 API 호출    http://localhost:3065/user/register
}
export function* signUp(action){
    try{
        yield call(  signUpApi ,  action.data );  // API 호출 - 결과물
        yield put(  { type: SIGN_UP_SUCCESS } );  // 성공 액션 dispatch
        // return {  ...state , isLoading:false,  signUpDone: true  };
    }catch(err){
        yield put(  { type: SIGN_UP_FAILURE , error: err.response?.data || err.message} );  // 실패 액션 dispatch
        //  return {    ...state ,    isLoading:false,   error: action.error?.message ||action.error,  }; 
    }
}
function * watchSignUp(){
    yield takeLatest(SIGN_UP_REQUEST,  signUp);  
    // 1. SIGN_UP_REQUEST 액션발생 , takeLatest (여러번요청 → 가장마지막요청처리1)
    // return {  ...state ,  isLoading:true, error: null    }; 
}


// ------------------ 전체 사용자 조회 ----------   watchLoadUsers
// get: /user/   

export function loadUsersApi(){
    return client.get('/user');  // 사용자 목록 API 호출    http://localhost:3065/user
}
export function* loadUsers(){
    try{
        const result = yield call(  loadUsersApi  );  // API 호출 - (비동기 → call)  
        const users = result.data.map((u) => ({
            id: u.APP_USER_ID,
            email: u.EMAIL,
            nickname:u.NICKNAME
        })) ;
        yield put(  { type: LOAD_USERS_SUCCESS , data: users } );  // 성공 액션 dispatch
        //return {  ...state , isLoading:false,  me: action.data  };
    }catch(err){
        yield put(  { type: LOAD_USERS_FAILURE , error: err.response?.data || err.message} );  // 실패 액션 dispatch
        //  return {    ...state ,    isLoading:false,   error: action.error?.message ||action.error,  }; 
    }
}
function * watchLoadUsers(){
    yield takeLatest(LOAD_USERS_REQUEST,  loadUsers);  
    // 1. LOAD_USERS_REQUEST 액션발생 , takeLatest (여러번요청 → 가장마지막요청처리1)
    // return {  ...state ,  isLoading:true, error: null    }; 
}



// ------------------ 닉네임 수정 --------------   watchUpdateNickname
//patch: /user/{id}/nickname  - data
export function updateNicknameApi(data){
    return client.patch(`/user/${data.id}/nickname` , {nickname:data.nickname});  // 회원가입 API 호출    http://localhost:3065/user/{id}/nickname
}
export function* updateNickname(action){
    try{
        yield call(  updateNicknameApi ,  action.data );  // API 호출 - 결과물
        yield put(  { type: UPDATE_NICKNAME_SUCCESS  , data: { id: action.data.id , nickname: action.data.nickname}} );     // 성공 액션 dispatch
        //    return {    ...state ,   isLoading:false,   
        //          me: state.me &&  state.me.id === action.data.id   ? {  ...state.me,   nickname:action.data.nickname} : state.me , 
        //       users: state.users.map( (u)=>    u.id === action.data.id ? {  ...u , nickname: action.data.nickname } : u),   }
    }catch(err){
        yield put(  { type: UPDATE_NICKNAME_FAILURE , error: err.response?.data || err.message} );  // 실패 액션 dispatch
        //  return {    ...state ,    isLoading:false,   error: action.error?.message ||action.error,  }; 
    }
}
function * watchUpdateNickname(){
    yield takeLatest(UPDATE_NICKNAME_REQUEST,  updateNickname);  
    // 1. DELETE_USER_REQUEST 액션발생 , takeLatest (여러번요청 → 가장마지막요청처리1)
    // return {  ...state ,  isLoading:true, error: null    }; 
}


// ------------------ 사용자 삭제 --------------   
//delete : /user/{id} 
export function deleteUserApi(id){
    return client.delete(`/user/${id}`);  // 회원가입 API 호출    http://localhost:3065/user/{id} 
}
export function* deleteUser(action){
    try{
        yield call(  deleteUserApi ,  action.data.id );  // API 호출 - 결과물
        yield put(  { type: DELETE_USER_SUCCESS  , data: { id: action.data.id}} );     // 성공 액션 dispatch
        //  return {    ...state ,    isLoading:false,   
        //               users: state.users.filter(  (u)=> u.id !== action.data.id ), 
        //                  me: state.me?.id === action.data.id ? null : state.me ,  };
    }catch(err){
        yield put(  { type: DELETE_USER_FAILURE , error: err.response?.data || err.message} );  // 실패 액션 dispatch
        //  return {    ...state ,    isLoading:false,   error: action.error?.message ||action.error,  }; 
    }
}
function * watchDeleteUser(){
    yield takeLatest(DELETE_USER_REQUEST,  deleteUser);  
    // 1. DELETE_USER_REQUEST 액션발생 , takeLatest (여러번요청 → 가장마지막요청처리1)
    // return {  ...state ,  isLoading:true, error: null    }; 
}

// ------------------ 이메일 중복 확인 ----------------
// get: /user/check-email?email=xxx
export function checkEmailApi(email) {
    return client.get(`/user/check-email?email=${email}`);
}

export function* checkEmail(action) {
    try {
        const result = yield call(checkEmailApi, action.data);
        yield put({ type: CHECK_EMAIL_SUCCESS, data: result.data }); // result.data에 { isAvailable: ... } 포함
    } catch (err) {
        yield put({ type: CHECK_EMAIL_FAILURE, error: err.response?.data || err.message });
    }
}

function* watchCheckEmail() {
    yield takeLatest(CHECK_EMAIL_REQUEST, checkEmail);
}

// ------------------ 닉네임 중복 확인 ----------------
// get: /user/check-nickname?nickname==xxx
///////////////////////////   - 추가 구현 하세요.
export function checkNicknameApi(nickname) {
    return client.get(`/user/check-nickname?nickname=${nickname}`);
}

export function* checkNickname(action) {
    try {
        const result = yield call(checkNicknameApi, action.data);
        yield put({ type: CHECK_NICKNAME_SUCCESS, data: result.data }); // result.data에 { isAvailable: ... } 포함
    } catch (err) {
        yield put({ type: CHECK_NICKNAME_FAILURE, error: err.response?.data || err.message });
    }
}

function* watchCheckNickname() {
    yield takeLatest(CHECK_NICKNAME_REQUEST, checkNickname);
}

// ------------------ 닉네임 부분 검색 ----------------
// get: /user/search-user?keyword=xxx
 ///////////////////////////   - 추가 구현 하세요.
export function searchUserApi(keyword) {
    return client.get(`/user/search-user?keyword=${keyword}`);
}

export function* searchUser(action) {
    try {
        const result = yield call(searchUserApi, action.data);
         const users = result.data.map((u) => ({
            id: u.APP_USER_ID,
            email: u.EMAIL,
            nickname:u.NICKNAME
        })) ;
        yield put(  { type: SEARCH_USERS_SUCCESS , data: users } );
    } catch (err) {
        yield put({ type: SEARCH_USERS_FAILURE, error: err.response?.data || err.message });
    }
}

function* watchSearchUser() {
    yield takeLatest(SEARCH_USERS_REQUEST, searchUser);
}

export default function* userSaga(){
    yield all([
        fork(watchLogin) ,          // 로그인 saga 실행  
        fork(watchLogout) ,         // 로그아웃 saga 실행   
        fork(watchSignUp) ,         // 회원가입 saga 실행  
        fork(watchLoadUsers) ,      // 사용자 조회 saga 실행   
        fork(watchUpdateNickname) , // 닉네임 수정 saga 실행  
        fork(watchDeleteUser) ,     // 사용자 삭제 saga 실행  
        fork(watchCheckEmail),
        ///////////////////////////   - 추가 구현 하세요.
        fork(watchCheckNickname),
        ///////////////////////////   - 추가 구현 하세요.
        fork(watchSearchUser),
    ]);
}

