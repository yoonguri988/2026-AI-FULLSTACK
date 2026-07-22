/**
 * reducer/user.js
 * ------------------------------------------------
 * 사용자 관련 상태(user state)를 관리하는 리듀서
 * - 로그인, 로그아웃, 회원가입, 사용자 목록, 닉네임 수정, 사용자 삭제
 * - 1. 각 액션 타입 정의 2. 초기상태 3.reducer 함수 LOG_IN_REQUEST
 */

// 1.
export const LOG_IN_REQUEST = 'LOG_IN_REQUEST'; // 로그인 요청
export const LOG_IN_SUCCESS = 'LOG_IN_SUCCESS'; // 로그인 성공
export const LOG_IN_FAILURE = 'LOG_IN_FAILURE'; // 로그인 실패

export const LOG_OUT_REQUEST = 'LOG_OUT_REQUEST'; // 로그인 요청
export const LOG_OUT_SUCCESS = 'LOG_OUT_SUCCESS'; // 로그인 성공
export const LOG_OUT_FAILURE = 'LOG_OUT_FAILURE'; // 로그인 실패

export const SIGN_UP_REQUEST = 'SIGN_UP_REQUEST'; // 회원가입 요청
export const SIGN_UP_SUCCESS = 'SIGN_UP_SUCCESS'; // 회원가입 성공
export const SIGN_UP_FAILURE = 'SIGN_UP_FAILURE'; // 회원가입 실패

export const LOAD_USER_REQUEST = 'LOAD_USER_REQUEST'; // 사용자목록 요청
export const LOAD_USER_SUCCESS = 'LOAD_USER_SUCCESS'; // 사용자목록 성공
export const LOAD_USER_FAILURE = 'LOAD_USER_FAILURE'; // 사용자목록 실패

export const UPDATE_NICKNAME_REQUEST = 'UPDATE_NICKNAME_REQUEST'; // 닉네임수정 요청
export const UPDATE_NICKNAME_SUCCESS = 'UPDATE_NICKNAME_SUCCESS'; // 닉네임수정 성공
export const UPDATE_NICKNAME_FAILURE = 'UPDATE_NICKNAME_FAILURE'; // 닉네임수정 실패

export const DELETE_USER_REQUEST = 'DELETE_USER_REQUEST'; // 사용자삭제 요청
export const DELETE_USER_SUCCESS = 'DELETE_USER_SUCCESS'; // 사용자삭제 성공
export const DELETE_USER_FAILURE = 'DELETE_USER_FAILURE'; // 사용자삭제 실패

export const RESET_SIGNUP_DONE = 'RESET_SIGNUP_DONE'; // 추가

export const LOAD_MY_INFO_REQUEST = 'LOAD_MY_INFO_REQUEST';
export const LOAD_MY_INFO_SUCCESS = 'LOAD_MY_INFO_SUCCESS';
export const LOAD_MY_INFO_FAILURE = 'LOAD_MY_INFO_FAILURE';

export const CHECK_EMAIL_REQUEST = 'CHECK_EMAIL_REQUEST';
export const CHECK_EMAIL_SUCCESS = 'CHECK_EMAIL_SUCCESS';
export const CHECK_EMAIL_FAILURE = 'CHECK_EMAIL_FAILURE';

//2. 초기상태
export const initialState = {
    me: null,          // 로그인 사용자 정보 {id, email, nickname}
    users: [],         // 전체 사용자 목록 [{id1, email1, nickname1}, {id2, email2, nickname2} ...]
    isLoading: false,  // api 요청 중 여부
    error: null,       // 에러 메시지
    signUpDone: false, // 회원가입 완료 여부
    isAvailable: false, // 중복확인 여부
    emailCheckMessage: null, // 중복확인 여부 메시지
};

//3. reducer 함수
const reducer = (state=initialState, action) => { // 현재상태, 요청액션
    switch(action.type){
        // 요청액션 -> 로딩시작
        case LOG_IN_REQUEST: 
        case LOG_OUT_REQUEST: 
        case SIGN_UP_REQUEST: 
        case LOAD_USER_REQUEST: 
        case UPDATE_NICKNAME_REQUEST: 
        case DELETE_USER_REQUEST:
        case LOAD_MY_INFO_REQUEST:
        case CHECK_EMAIL_REQUEST:
            return { ...state, isLoading: true, error: null}; 

        // 성공액션 -> 상태업데이트
        case LOG_IN_SUCCESS:
            return { ...state, isLoading: false, me: action.data }; 
        case LOG_OUT_SUCCESS:
            return { ...state, isLoading: false, me: null}; 
        case SIGN_UP_SUCCESS:
            return { ...state, isLoading: false, signUpDone: true }; 
        case LOAD_USER_SUCCESS:
            return { ...state, isLoading: false, users: action.data }; 
        case UPDATE_NICKNAME_SUCCESS:
            return { ...state, isLoading: false, 
                me: state.me && state.me.id === action.data.id
                ? { ...state.me, nickname: action.data.nickname } 
                : state.me,
                users: state.users.map((u)=> u.id === action.data.id? {...u, nickname: action.data.nickname}:u),
            }; 
        case DELETE_USER_SUCCESS:
            return { ...state, isLoading: false,  
                me: state.me && state.me.id === action.data.id
                ? null
                : state.me,
                users: state.users.filter((u) => u.id !== action.data.id)
            };  
        case LOAD_MY_INFO_SUCCESS:
            return { ...state, isLoading: false, me: action.data };
        case CHECK_EMAIL_SUCCESS:
            return {
                ...state,
                isLoading: false,
                isAvailable: action.data.isAvailable,
                emailCheckMessage: action.data.message,
            };

        // 실패액션 -> 에러메시지저장
        case LOG_IN_FAILURE: 
        case LOG_OUT_FAILURE: 
        case SIGN_UP_FAILURE: 
        case LOAD_USER_FAILURE: 
        case UPDATE_NICKNAME_FAILURE: 
        case DELETE_USER_FAILURE:
        case LOAD_MY_INFO_FAILURE:
            return { ...state, isLoading: false, error: action.error?.message || action.error };

        case CHECK_EMAIL_FAILURE:
            return {
                ...state,
                isLoading: false,
                isAvailable: action.data?.isAvailable ?? false,
                emailCheckMessage: action.data?.message ?? action.error,
            };


        case RESET_SIGNUP_DONE:
            return { ...state, signUpDone: false };

        // 기본값 -> 상태 변경 없음
        default:
            return state;
    }
};
export default reducer;

