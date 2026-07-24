//  npx  jest  reducers/user.test.js
import  reducer, {
    initialState , 
    LOG_IN_REQUEST, LOG_IN_SUCCESS, LOG_IN_FAILURE,
    LOG_OUT_REQUEST, LOG_OUT_SUCCESS, LOG_OUT_FAILURE,
    SIGN_UP_REQUEST, SIGN_UP_SUCCESS,  SIGN_UP_FAILURE,
    LOAD_USERS_REQUEST, LOAD_USERS_SUCCESS, LOAD_USERS_FAILURE,
    UPDATE_NICKNAME_REQUEST,  UPDATE_NICKNAME_SUCCESS, UPDATE_NICKNAME_FAILURE,
    DELETE_USER_REQUEST, DELETE_USER_SUCCESS, DELETE_USER_FAILURE , 
    CHECK_EMAIL_REQUEST, CHECK_EMAIL_SUCCESS, CHECK_EMAIL_FAILURE , 
    CHECK_NICKNAME_REQUEST, CHECK_NICKNAME_SUCCESS, CHECK_NICKNAME_FAILURE
} from './user';

describe( 'user reducer' ,  ()=>{
    it( 'should handle LOG_IN_REQUEST'   , ()=>{
        const state = reducer( initialState , { type:LOG_IN_REQUEST } );
        expect(state.isLoading).toBe(true);
    }); 

    it( 'should handle LOG_IN_SUCCESS'   , ()=>{
        const fakeUser = {  id:1, email:'1@1' , nickname:'first'};
        const state = reducer( initialState , { type:LOG_IN_SUCCESS , data:fakeUser } );
        expect(state.me).toBe(fakeUser);
    }); 

    it( 'should handle LOG_IN_FAILURE'   , ()=>{
        // const reducer  = ( state = initialState , action )=>{ 
        const state = reducer( initialState        , { type:LOG_IN_FAILURE  , error:'로그인 실패' } );
        // return {   ...state ,     isLoading:false,   error: action.error?.message ||action.error,    };
        expect(state.error).toBe('로그인 실패');
        expect(state.isLoading).toBe(false);
    }); 
    /////////////////////////////////////////////////////////////////////
    it( 'should handle LOG_OUT_REQUEST'   , ()=>{
        const state = reducer( initialState , { type:LOG_OUT_REQUEST } ); 
        //return {  ...state ,  isLoading:true, error: null    }; 
        expect(state.isLoading).toBe(true);
    }); 

    it( 'should handle LOG_OUT_SUCCESS'   , ()=>{
        const state = reducer( initialState , { type:LOG_OUT_SUCCESS } );
        //return    {  ...state , isLoading:false,  me: null  };
        expect(state.me).toBeNull();
        expect(state.isLoading).toBe(false);
    }); 

    it( 'should handle LOG_OUT_FAILURE'   , ()=>{
        // const reducer = ( state = initialState , action )=>{ 
        const state      = reducer( initialState , { type:LOG_OUT_FAILURE , error:'로그아웃 실패'} );
        //    return {       ...state ,    isLoading:false,   error: action.error?.message ||action.error,     };
        expect(state.error).toBe('로그아웃 실패');
        expect(state.isLoading).toBe(false);
    }); 
    
    /////////////////////////////////////////////////////////////////////
    it( 'should handle SIGN_UP_SUCCESS'   , ()=>{
        const state = reducer( initialState , { type:SIGN_UP_SUCCESS } );  
        //    return {  ...state , isLoading:false,  signUpDone: true  };
        expect(state.isLoading).toBe(false);
        expect(state.signUpDone).toBe(true);
    });  
    it( 'should handle LOAD_USERS_SUCCESS'   , ()=>{
        const fakeUsers =  [ {  id:1, email:'1@1' , nickname:'first'} ];
        const state = reducer( initialState , { type:LOAD_USERS_SUCCESS , data:fakeUsers} );  
        //           return {  ...state , isLoading:false,  users: action.data  };
        expect(state.users).toEqual(fakeUsers);
    }); 

    it( 'should handle UPDATE_NICKNAME_SUCCESS'   , ()=>{
        const prevState = {
            ...initialState,
            me: {id:1, nickname: 'old' } , 
            users : [{id:1, nickname: 'old' }] 
        };
        // const reduce = ( state = initialState , action )=>{ 
        const state = reducer( prevState   , { type:UPDATE_NICKNAME_SUCCESS , data:{ id:1, nickname:'new'} } );  
        expect(state.me.nickname).toBe('new'); 
        expect(state.users[0].nickname).toBe('new');
    });  
    // {...state ,    isLoading:false,    
    // me: state.me &&  state.me.id === action.data.id  
    //     ? {  ...state.me,   nickname:action.data.nickname} : state.me ,
    // users: state.users.map( (u)=> 
    //     u.id === action.data.id ? {  ...u , nickname: action.data.nickname } : u }
        

    it( 'should handle DELETE_USER_SUCCESS'   , ()=>{
        const  prevState = {
            ...initialState ,  me: {id: 1 } , users:[ {id: 1 } , {id: 2 } ]
        } 

        const state = reducer( prevState , { type:DELETE_USER_SUCCESS  , data: {id: 1 }} );  
        expect(state.users).toEqual( [ {id: 2 } ] );
        expect(state.me).toBeNull();
    });  
 
    // {...state , 
    //   isLoading:false,   
    //   users: state.users.filter(  (u)=> u.id !== action.data.id ), 
    //   me: state.me?.id === action.data.id ? null : state.me ,  } 

/////////////////////////////////////////////////////////////////////
    // 이메일 중복 확인 테스트 추가
    it('should handle CHECK_EMAIL_REQUEST', () => {
        const state = reducer(initialState, { type: CHECK_EMAIL_REQUEST });
        expect(state.checkEmailLoading).toBe(true);
        expect(state.checkEmailDone).toBe(false);
    });

    it('should handle CHECK_EMAIL_SUCCESS', () => {
        const fakeData = { isAvailable: true };
        const state = reducer(initialState, { type: CHECK_EMAIL_SUCCESS, data: fakeData });
        expect(state.checkEmailLoading).toBe(false);
        expect(state.isEmailAvailable).toBe(true);
        expect(state.checkEmailDone).toBe(true);
    });

    it('should handle CHECK_EMAIL_FAILURE', () => {
        const error = '에러 발생';
        const state = reducer(initialState, { type: CHECK_EMAIL_FAILURE, error: error });
        expect(state.checkEmailLoading).toBe(false);
        expect(state.checkEmailError).toBe(error);
    });

    //////////////////////////////// 닉네임
    it('should handle CHECK_NICKNAME_REQUEST', () => {
        const state = reducer(initialState, { type: CHECK_NICKNAME_REQUEST });
        expect(state.checkNicknameLoading).toBe(true);
    });

    it('should handle CHECK_NICKNAME_SUCCESS', () => {
        const fakeData = { isAvailable: true };
        const state = reducer(initialState, { type: CHECK_NICKNAME_SUCCESS, data: fakeData });
        expect(state.checkNicknameLoading).toBe(false);
        expect(state.isNicknameAvailable).toBe(true);
        expect(state.checkNicknameDone).toBe(true);
    });

    it('should handle CHECK_NICKNAME_FAILURE', () => {
        const error = '닉네임 체크 에러';
        const state = reducer(initialState, { type: CHECK_NICKNAME_FAILURE, error: error });
        expect(state.checkNicknameLoading).toBe(false);
        expect(state.checkNicknameError).toBe(error);
    });

});