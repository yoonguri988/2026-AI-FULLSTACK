import userReducer,
{
    signupRequest, signupSuccess, signupFailure,
    fetchUserRequest, fetchUserSuccess, fetchUserFailure,
    resetUserState,
} from '../authReducer';

describe('user slice reduce', () => {
    const initialState = {
        user: null,     // 단건 조회된 사용자 정보
        loading: false, // 로딩상태
        error: null,    // 에러메시지
        success: false, // 성공여부
    };
    it('signupRequest', () => {
        const state = userReducer(initialState, signupRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();
        expect(state.success).toBe(false);
    });
    it('signupSuccess', () => {
        const userData = {id:1, email:'a@a'};
        const state = userReducer(initialState, signupSuccess(userData) );
        // 1. signupSuccess(userData) 실행하면 - {id:1, email:'a@a'}
        // 2. 리듀서내부(툴킷)에서 {type:signupSuccess, payload:userData} 객체만들기
        // 3. 리듀서의 signupSuccess: (state,action)=>{} 액션받아서 처리
        expect(state.loading).toBe(false); 
        expect(state.user).toEqual(userData); // state.user = action.payload
        expect(state.success).toBe(true);
    });
    it('signupFailure', () => {
        const state = userReducer(initialState, signupFailure("회원가입 실패") );
        // 1. signupFailure("회원가입 실패") 실행하면 - 
        expect(state.loading).toBe(false);
        expect(state.error).toBe("회원가입 실패");
    });
    it('fetchUserRequest', () => {
        const state = userReducer(initialState, fetchUserRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();
        expect(state.success).toBe(false);
    });
    it('fetchUserSuccess', () => {
        const userData = {id:1, email:'a@a'};
        const state = userReducer(initialState, fetchUserSuccess(userData) );

        expect(state.loading).toBe(false); 
        expect(state.user).toEqual(userData);
        expect(state.success).toBe(true);
    });
    it('fetchUserFailure', () => {
        const state = userReducer(initialState, signupFailure("사용자 단건 조회 실패") );
        expect(state.loading).toBe(false);
        expect(state.error).toBe("사용자 단건 조회 실패");
    });
    it('resetUserState', () => {
        // 상태 꼬임
        const prev = {user:{id:1}, loading: true, error:'error', success: true};
        const state = userReducer(prev, resetUserState() );
        expect(state.loading).toBe(false);
        expect(state.error).toBe(null);
        expect(state.success).toBe(false);
    });
});