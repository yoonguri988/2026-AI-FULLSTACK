//__test__/authRucer.test.js
import  userReducer, {signupRequest , signupSuccess , signupFailure,
    fetchUserRequest,  fetchUserSuccess ,  fetchUserFailure,  
    loginRequest, loginSuccess, loginFailure,
    logoutRequest, logoutSuccess, logoutFailure,
    updateNicknameRequest, updateNicknameSuccess, updateNicknameFailure,
    updateProfileImageRequest, updateProfileImageSuccess, updateProfileImageFailure,
    resetUserState,
} from '../authReducer';

describe('user slice reducer' , ()=>{
    const initialState={
        user: null ,     // 단건 조회된 사용자 정보
        loading: false,  // 로딩상태   
        error:   null,   // 에러메시지
        success: false,  // 성공여부 (오타 수정)
    };

    it('signupRequest' , ()=>{
        const state = userReducer( initialState , signupRequest() );
        // 1. signupRequest() 실행하면 - 인자 없음
        // 2. 리듀서 툴킷에서 { type: signupRequest, payload: undefined } 객체 만들기
        // 3. 리듀서의 signupRequest: (state, action) => {} 액션 받아서 처리
        expect(state.loading).toBe(true);  // state.loading = true
        expect(state.error).toBeNull();    // state.error = null
        expect(state.success).toBe(false); // state.success = false (오타 수정)
    });

    it('signupSuccess' , ()=>{     
        const userData = {id:1 , email:'1@1'};
        const state = userReducer( initialState , signupSuccess(userData) );
        // 1. signupSuccess(userData) 실행하면   - {id:1 , email:'1@1'};
        // 2. 리듀서 툴킷에서 { type: signupSuccess, payload: userData } 객체 만들기
        // 3. 리듀서의 signupSuccess: (state, action) => {} 액션 받아서 처리
        //    action = { type: signupSuccess, payload: userData }
        expect(state.loading).toBe(false);   // state.loading = false
        expect(state.user).toEqual(userData);// state.user = action.payload
        expect(state.success).toBe(true);   
    });

    it('signupFailure' , ()=>{     
        const state = userReducer( initialState , signupFailure('회원가입 실패') );
        // 1. signupFailure('회원가입 실패') 실행하면 - '회원가입 실패' 전달
        // 2. 리듀서 툴킷에서 { type: signupFailure, payload: '회원가입 실패' } 객체 만들기
        // 3. 리듀서의 signupFailure: (state, action) => {} 액션 받아서 처리
        //    action = { type: signupFailure, payload: '회원가입 실패' }
        expect(state.loading).toBe(false);    
        expect(state.error).toBe('회원가입 실패');  // state.error = action.payload
    });
    //////////////////////////////////////////// 로그인
    it('loginRequest' , ()=>{
        const state = userReducer( initialState , loginRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();  
        expect(state.success).toBe(false);
    });
    it('loginSuccess' , ()=>{
        const payload = {id:1, email:'1@1'};
        const state = userReducer( initialState , loginSuccess(payload) );

        expect(state.loading).toBe(false); 
        expect(state.user).toEqual(payload);
    });
    it('signupFailure' , ()=>{     
        const state = userReducer( initialState , loginFailure('로그인실패') );
        expect(state.loading).toBe(false);    
        expect(state.error).toBe('로그인실패');
    });
    //////////////////////////////////////////// 로그아웃
    it('loginRequest' , ()=>{
        const state = userReducer( initialState , logoutRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();  
        expect(state.success).toBe(false);
    });
    it('loginSuccess' , ()=>{
        const prev = {...initialState, user: {id: 1}};
        const state = userReducer( prev , logoutSuccess() );

        expect(state.loading).toBe(false); 
        expect(state.user).toBeNull();
    });
    it('logoutFailure' , ()=>{     
        const state = userReducer( initialState , logoutFailure('로그아웃실패') );
        expect(state.loading).toBe(false);    
        expect(state.error).toBe('로그아웃실패');
    });
    //////////////////////////////////////////// 닉네임변경
    it('updateNicknameRequest' , ()=>{
        const state = userReducer( initialState , updateNicknameRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();  
        expect(state.success).toBe(false);
    });
    it('updateNicknameSuccess' , ()=>{
        const payload = { id: 1, email:'1@1', nickname: "new"};
        const state = userReducer( initialState , updateNicknameSuccess(payload) );

        expect(state.loading).toBe(false); 
        expect(state.user).toEqual(payload);
    });
    it('updateNicknameFailure' , ()=>{     
        const state = userReducer( initialState , updateNicknameFailure('닉네임변경실패') );
        expect(state.loading).toBe(false);    
        expect(state.error).toBe('닉네임변경실패');
    });
    //////////////////////////////////////////// 프로필이미지 변경
    it('updateProfileImageRequest' , ()=>{
        const state = userReducer( initialState , updateProfileImageRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();  
        expect(state.success).toBe(false);
    });
    it('updateProfileImageSuccess' , ()=>{
        const payload = { id: 1, email:'1@1', nickname: "new", ufile:'1.png'};
        const state = userReducer( initialState , updateProfileImageSuccess(payload) );

        expect(state.loading).toBe(false); 
        expect(state.user).toEqual(payload);
    });
    it('updateProfileImageFailure' , ()=>{     
        const state = userReducer( initialState , updateProfileImageFailure('프로필이미지변경실패') );
        expect(state.loading).toBe(false);    
        expect(state.error).toBe('프로필이미지변경실패');
    });
    ////////////////////////////////////////////
    it('fetchUserSuccess' , ()=>{     
        const userData = {id:1 , email:'1@1'}; 
        const state    = userReducer(initialState , fetchUserSuccess(userData));
        //1. fetchUserSuccess(userData)  -  {id:1 , email:'1@1'} 전달
        //2. 리듀서 툴킷 - { type:fetchUserSuccess   , payload:userData }  객체만들기
        //3. 리듀서의   fetchUserSuccess : (state,action)=>{}  액션받아서처리
        // action = { type:fetchUserSuccess   , payload:userData }
        expect(state.user).toEqual(userData);  //state.user = action.payload
        expect(state.loading).toBe(false);
    });
    it('resetUserState' , ()=>{      
        const prev = {user:{id:1} , loading:true , error:'err' , success: true};// 상태꼬임
        const state = userReducer(prev, resetUserState());
        //1. resetUserState() 실행 - 인자없음
        //2. 리듀서 툴킷 - { type:resetUserState   , payload:undefined }  객체만들기
        //3. 리듀서의   resetUserState : (state,action)=>{}  액션받아서처리 - 상태초기화
        // action = { type:resetUserState   , payload:undefined }
        expect(state.loading).toBe(false);
        expect(state.error).toBe(null);
        expect(state.success).toBe(false);
    }); 
});
//  npm  test  authReducer