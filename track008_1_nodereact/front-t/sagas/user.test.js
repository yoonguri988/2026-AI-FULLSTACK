//  npx  jest  sagas/user.test.js
import{call, put}  from 'redux-saga/effects';
import {
    login, signUp, loadUsers, logout, updateNickname, deleteUser,
    loginApi, signUpApi, loadUsersApi, logoutApi, updateNicknameApi, deleteUserApi , 
   // checkEmail, checkEmailApi , checkNickname, checkNicknameApi
} from '../sagas/user';

import  reducer, {
    initialState ,   LOG_IN_REQUEST, LOG_IN_SUCCESS, LOG_IN_FAILURE,
    LOG_OUT_REQUEST, LOG_OUT_SUCCESS, LOG_OUT_FAILURE,  SIGN_UP_REQUEST, SIGN_UP_SUCCESS,  SIGN_UP_FAILURE,
    LOAD_USERS_REQUEST, LOAD_USERS_SUCCESS, LOAD_USERS_FAILURE,
    UPDATE_NICKNAME_REQUEST,  UPDATE_NICKNAME_SUCCESS, UPDATE_NICKNAME_FAILURE,
    DELETE_USER_REQUEST, DELETE_USER_SUCCESS, DELETE_USER_FAILURE ,
    CHECK_EMAIL_REQUEST, CHECK_EMAIL_SUCCESS, CHECK_EMAIL_FAILURE , 
    CHECK_NICKNAME_REQUEST, CHECK_NICKNAME_SUCCESS, CHECK_NICKNAME_FAILURE // ✅ 추가
} from '../reducers/user';
 

describe( 'user saga' ,  ()=>{
    it( 'login success'   , ()=>{
        const action = {type: LOG_IN_REQUEST , data: {email:'z@z' , password:'z'}};
        const gen = login(action);
        expect(gen.next().value).toEqual(call(loginApi , action.data));
        
        const  apiResponse = {APP_USER_ID:1 , EMAIL:'z@z' , NICKNAME:'zzz'};  // 테스트데이터
        expect( gen.next( {data:apiResponse}  ).value  ).toEqual(
            put({
                type: LOG_IN_SUCCESS,
                data:{id:1 , email:'z@z' , nickname:'zzz' }
            })
        ); 
    }); 
    /////
    it( 'logout success'   , ()=>{
        const action = {type: LOG_OUT_REQUEST };  //#1. 액션타입
        const gen = logout(action); // #2. logout  기능확인

        expect(gen.next().value).toEqual(call(logoutApi));  // #3. 결과물확인 -
        expect(gen.next({}).value).toEqual(put({ type: LOG_OUT_SUCCESS }));   

        const  error = { response : {data : '로그아웃 실패'}};  // 테스트데이터  
        expect( gen.throw(error).value  ).toEqual( put({type: LOG_OUT_FAILURE, error:'로그아웃 실패' }) );  //실패
    }); 
    //////
    it( 'signup success'   , ()=>{
        const action = {type: SIGN_UP_REQUEST , data:{ email:'z@z' , password:'z'  , nickname: 'zzz'}};  //#1. 액션타입
        const gen = signUp(action);             // #2. singnUp  기능확인

        expect(gen.next().value).toEqual(call(signUpApi , action.data));  // #3. 결과물확인  
        expect(gen.next({}).value).toEqual(put({ type: SIGN_UP_SUCCESS }));   

        const  error = { response : {data : '이미 존재하는 이메일'}};  // 테스트데이터  
        expect( gen.throw(error).value  ).toEqual( put({type: SIGN_UP_FAILURE, error:'이미 존재하는 이메일' }) );  //실패
    }); 

    it('signup success', () => {
        // 닉네임(nickname)을 데이터에 추가해 줍니다.
        const action = { type: SIGN_UP_REQUEST, data: { email: 'z@z', password: 'z', nickname: 'zzz' } };  
        const gen = signUp(action);             

        expect(gen.next().value).toEqual(call(signUpApi, action.data));  
        expect(gen.next({}).value).toEqual(put({ type: SIGN_UP_SUCCESS }));   

        const error = { response: { data: '이미 존재하는 이메일' } };  
        expect(gen.throw(error).value).toEqual(put({ type: SIGN_UP_FAILURE, error: '이미 존재하는 이메일' }));  
    });


   //////
    it( 'loadUsers success'   , ()=>{
        const action = {type: LOAD_USERS_REQUEST };  // #1. 액션타입
        const gen = loadUsers(action);               // #2. loadUsers  기능확인

        expect(gen.next().value).toEqual(call(loadUsersApi));  // #3. 결과물확인  

        const fakeUers = [{APP_USER_ID:1 , EMAIL:'z@z' , NICKNAME:'zzz'}]

        expect(gen.next({data : fakeUers}).value)
            .toEqual(put({ type: LOAD_USERS_SUCCESS, data:[ {id:1, email:'z@z' , nickname:'zzz'}]}));   

        const  error = { response : {data : '조회 실패'}};  // 테스트데이터  
        expect( gen.throw(error).value  ).toEqual( put({type: LOAD_USERS_FAILURE, error:'조회 실패' }) );  //실패
    }); 
 
   //////
    it( 'updateNickname success'   , ()=>{
        const action = {type: UPDATE_NICKNAME_REQUEST , data:{  id:1, nickname:'new'} };  // #1. 액션타입
        const gen = updateNickname(action);               // #2. loadUsers  기능확인

        expect(gen.next().value).toEqual(call(updateNicknameApi , action.data));  // #3. 결과물확인  localhost:3065/
        expect(gen.next({}).value).toEqual(put({ type: UPDATE_NICKNAME_SUCCESS, data:  {id:1,  nickname:'new'} }));   

        const  error = { response : {data : '수정 실패'}};  // 테스트데이터  
        expect( gen.throw(error).value  ).toEqual( put({type: UPDATE_NICKNAME_FAILURE, error:'수정 실패' }) );  //실패
    }); 
  //////
    it( 'deleteUser success'   , ()=>{
        const action = {type: DELETE_USER_REQUEST , data:{  id:1 } };  // #1. 액션타입
        const gen = deleteUser(action);               // #2. loadUsers  기능확인

        expect(gen.next().value).toEqual(call(deleteUserApi , action.data.id));  // #3. 결과물확인  localhost:3065/
        expect(gen.next({}).value).toEqual(put({ type: DELETE_USER_SUCCESS, data:  {id:1} }));   

        const  error = { response : {data : '존재안함'}};  // 테스트데이터  
        expect( gen.throw(error).value  ).toEqual( put({type: DELETE_USER_FAILURE, error:'존재안함' }) );  //실패
    }); 
////
it('checkEmail success', () => {
        const email = 'z@z';
        const action = { type: CHECK_EMAIL_REQUEST, data: email };
        const gen = checkEmail(action);

        expect(gen.next().value).toEqual(call(checkEmailApi, email));

        const apiResponse = { data: { isAvailable: true, message: '사용 가능한 이메일입니다.' } };
        expect(gen.next(apiResponse).value).toEqual(
            put({
                type: CHECK_EMAIL_SUCCESS,
                data: apiResponse.data
            })
        );

        const error = { response: { data: '서버 오류' } };
        expect(gen.throw(error).value).toEqual(
            put({ type: CHECK_EMAIL_FAILURE, error: '서버 오류' })
        );
    });

///////////////////////////////////////////////////////////////
it('checkNickname success', () => {
    const nickname = 'tester';
    const action = { type: CHECK_NICKNAME_REQUEST, data: nickname };
    const gen = checkNickname(action);

    // API 호출 확인
    expect(gen.next().value).toEqual(call(checkNicknameApi, nickname));

    // 성공 결과 확인
    const apiResponse = { data: { isAvailable: true, message: '사용 가능한 닉네임입니다.' } };
    expect(gen.next(apiResponse).value).toEqual(
        put({
            type: CHECK_NICKNAME_SUCCESS,
            data: apiResponse.data
        })
    );

    // 실패 케이스 확인
    const error = { response: { data: '서버 오류' } };
    expect(gen.throw(error).value).toEqual(
        put({ type: CHECK_NICKNAME_FAILURE, error: '서버 오류' })
    );
});

});

