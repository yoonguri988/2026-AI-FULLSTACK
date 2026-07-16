// npx jest reducers/user.test.js

import reducer, {
    initialState, 
    LOG_IN_REQUEST, LOG_IN_SUCCESS, LOG_IN_FAILURE,
    LOG_OUT_REQUEST, LOG_OUT_SUCCESS, LOG_OUT_FAILURE,
    SIGN_UP_REQUEST, SIGN_UP_SUCCESS, SIGN_UP_FAILURE,
    LOAD_USER_REQUEST, LOAD_USER_SUCCESS, LOAD_USER_FAILURE,
    UPDATE_NICKNAME_REQUEST, UPDATE_NICKNAME_SUCCESS, UPDATE_NICKNAME_FAILURE,
    DELETE_USER_REQUEST, DELETE_USER_SUCCESS, DELETE_USER_FAILURE,
} from './user';

describe('user reducer', () => {
    //로그인 테스트
    it('LOG_IN_REQUEST', () => {
        const state = reducer(initialState, {type: LOG_IN_REQUEST});
        expect(state.isLoading).toBe(true);
    });// return { ...state, isLoading: true, error: null }; 
    it('LOG_IN_SUCCESS', () => {
        const fakeUser = {id: 1, email:'1@1', nickname:'first'};
        const state = reducer(initialState, {type: LOG_IN_SUCCESS, data: fakeUser});
        expect(state.me).toBe(fakeUser);
    }); // return { ...state, isLoading: false, me: action.data }; 
    it('LOG_IN_FAILURE', () => {
        const state = reducer(initialState, {type: LOG_IN_FAILURE, error: '로그인실패'});
        expect(state.error).toBe('로그인실패');
        expect(state.isLoading).toBe(false);
    }); // return { ...state, isLoading: false, error: action.error?.message || action.error };
    
    //로그아웃 테스트
    it('LOG_OUT_REQUEST', () => {
        const state = reducer(initialState, {type: LOG_OUT_REQUEST});
        expect(state.isLoading).toBe(true);
    });// return { ...state, isLoading: true, error: null }; 
    it('LOG_OUT_SUCCESS', () => {
        const state = reducer(initialState, {type: LOG_OUT_SUCCESS});
        expect(state.me).toBe(null);
    }); // return { ...state, isLoading: false, me: null };
    it('LOG_OUT_FAILURE', () => {
        const state = reducer(initialState, {type: LOG_OUT_FAILURE, error: '로그아웃실패'});
        expect(state.error).toBe('로그아웃실패');
        expect(state.isLoading).toBe(false);
    }); // return { ...state, isLoading: false, error: action.error?.message || action.error };

    //회원가입 테스트
    it('SIGN_UP_REQUEST', () => {
        const state = reducer(initialState, {type: SIGN_UP_REQUEST});
        expect(state.isLoading).toBe(true);
    });// return { ...state, isLoading: true, error: null }; 
    it('SIGN_UP_SUCCESS', () => {
        const state = reducer(initialState, {type: SIGN_UP_SUCCESS});
        expect(state.isLoading).toBe(false);
        expect(state.signUpDone).toBe(true);
    }); // return { ...state, isLoading: false, signUpDone: true };
    it('SIGN_UP_FAILURE', () => {
        const state = reducer(initialState, {type: SIGN_UP_FAILURE, error: '회원가입실패'});
        expect(state.error).toBe('회원가입실패');
    }); // return { ...state, isLoading: false, error: action.error?.message || action.error };
    
    //사용자목록 테스트
    it('LOAD_USER_REQUEST', () => {
        const state = reducer(initialState, {type: LOAD_USER_REQUEST});
        expect(state.isLoading).toBe(true);
    });// return { ...state, isLoading: true, error: null }; 
    it('LOAD_USER_SUCCESS', () => {
        const fakeUsers = [{id: 1, email:'1@1', nickname:'first'}, {id: 2, email:'2@2', nickname:'second'}]
        const state = reducer(initialState, {type: LOAD_USER_SUCCESS, data: fakeUsers});
        expect(state.users).toBe(fakeUsers);
    }); // return { ...state, isLoading: false, signUpDone: true };
    it('LOAD_USER_FAILURE', () => {
        const state = reducer(initialState, {type: LOAD_USER_FAILURE, error: '회원가입실패'});
        expect(state.error).toBe('회원가입실패');
    }); // return { ...state, isLoading: false, error: action.error?.message || action.error };
    
    //닉네임수정 테스트
    it('UPDATE_NICKNAME_REQUEST', () => {
        const state = reducer(initialState, {type: UPDATE_NICKNAME_REQUEST});
        expect(state.isLoading).toBe(true);
    });// return { ...state, isLoading: true, error: null }; 
    it('UPDATE_NICKNAME_SUCCESS', () => {
        const prev = {... initialState, me: {id:1, nickname:'old'}, users:[{id:1, nickname:'old'}]};
        const state = reducer(prev, {type:UPDATE_NICKNAME_SUCCESS, data: {id:1, nickname:'new'}});

        expect(state.me.nickname).toBe('new');
        expect(state.users[0].nickname).toBe('new');
    }); // return { ...state, isLoading: false, 
    //     me: state.me && state.me.id === action.data.id
        //     ? { ...state.me, nickname: action.data.nickname } 
        //     : state.me,
        //     users: state.users.map((u)=> u.id === action.data.id? {...u, nickname: action.data.nickname}:u),
        // }; 
        it('UPDATE_NICKNAME_FAILURE', () => {
            const state = reducer(initialState, {type: UPDATE_NICKNAME_FAILURE, error: '닉네임수정실패'});
            expect(state.error).toBe('닉네임수정실패');
        }); // return { ...state, isLoading: false, error: action.error?.message || action.error };
        
        //사용자삭제 테스트
        it('DELETE_USER_REQUEST', () => {
            const state = reducer(initialState, {type: DELETE_USER_REQUEST});
            expect(state.isLoading).toBe(true);
        });// return { ...state, isLoading: true, error: null }; 
        it('DELETE_USER_SUCCESS', () => {
            const prev = {... initialState, me: {id:1}, users:[{id:1}, {id:2}, {id:3}]};
            const state = reducer(prev, {type: DELETE_USER_SUCCESS, data: {id: 1}});
            expect(state.me).toBeNull();
            expect(state.users).toEqual([{id:2}, {id:3}]);
    });// return { ...state, isLoading: false,  
       //     me: state.me && state.me.id === action.data.id
       //     ? null
       //     : state.me,
       //     users: state.users.filter((u) => u.id !== action.data.id)
       // }; 
    it('DELETE_USER_FAILURE', () => {
         const state = reducer(initialState, {type: DELETE_USER_FAILURE, error: '사용자삭제실패'});
        expect(state.error).toBe('사용자삭제실패');
    });// return { ...state, isLoading: true, error: null }; 
    
    // 성공 테스트


    // 실패 테스트
});