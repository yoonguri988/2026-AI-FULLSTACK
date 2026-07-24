/**
 * reducers/user.js
 * ----------------------------------------------------
 *  사용자 관련 상태(user state)를 관리하는 리듀서
 * - 로그인, 로그아웃, 회원가입, 사용자 목록, 닉네임 수정, 사용자 삭제
 * - 각 액션 타입 정의 + 초기 상태 + reducer 함수
 */
// 액션 타입 정의
export const  LOG_IN_REQUEST = 'LOG_IN_REQUEST';  //로그인 요청
export const  LOG_IN_SUCCESS = 'LOG_IN_SUCCESS';  //로그인 성공
export const  LOG_IN_FAILURE = 'LOG_IN_FAILURE';  //로그인 실패

export const  LOG_OUT_REQUEST = 'LOG_OUT_REQUEST';  //로그아웃 요청
export const  LOG_OUT_SUCCESS = 'LOG_OUT_SUCCESS';  //로그아웃 성공
export const  LOG_OUT_FAILURE = 'LOG_OUT_FAILURE';  //로그아웃 실패

export const  SIGN_UP_REQUEST = 'SIGN_UP_REQUEST';  //회원가입 요청
export const  SIGN_UP_SUCCESS = 'SIGN_UP_SUCCESS';  //회원가입 성공
export const  SIGN_UP_FAILURE = 'SIGN_UP_FAILURE';  //회원가입 실패

export const  LOAD_USERS_REQUEST = 'LOAD_USERS_REQUEST';  //사용자 목록 요청
export const  LOAD_USERS_SUCCESS = 'LOAD_USERS_SUCCESS';  //사용자 목록 성공
export const  LOAD_USERS_FAILURE = 'LOAD_USERS_FAILURE';  //사용자 목록 실패

export const  UPDATE_NICKNAME_REQUEST = 'UPDATE_NICKNAME_REQUEST';  //닉네임 수정 요청
export const  UPDATE_NICKNAME_SUCCESS = 'UPDATE_NICKNAME_SUCCESS';  //닉네임 수정 성공
export const  UPDATE_NICKNAME_FAILURE = 'UPDATE_NICKNAME_FAILURE';  //닉네임 수정 실패

export const  DELETE_USER_REQUEST = 'DELETE_USER_REQUEST';  //사용자 삭제 요청
export const  DELETE_USER_SUCCESS = 'DELETE_USER_SUCCESS';  //사용자 삭제 성공
export const  DELETE_USER_FAILURE = 'DELETE_USER_FAILURE';  //사용자 삭제 실패


// 이메일 중복 확인 액션 타입
export const CHECK_EMAIL_REQUEST = 'CHECK_EMAIL_REQUEST';
export const CHECK_EMAIL_SUCCESS = 'CHECK_EMAIL_SUCCESS';
export const CHECK_EMAIL_FAILURE = 'CHECK_EMAIL_FAILURE';

// 닉네임 타입 추가
export const CHECK_NICKNAME_REQUEST = 'CHECK_NICKNAME_REQUEST';
export const CHECK_NICKNAME_SUCCESS = 'CHECK_NICKNAME_SUCCESS';
export const CHECK_NICKNAME_FAILURE = 'CHECK_NICKNAME_FAILURE';

// 사용자 닉네임 검색 액션 타입
export const SEARCH_USERS_REQUEST = 'SEARCH_USERS_REQUEST';
export const SEARCH_USERS_SUCCESS = 'SEARCH_USERS_SUCCESS';
export const SEARCH_USERS_FAILURE = 'SEARCH_USERS_FAILURE';

 
//  초기 상태 정의
export  const initialState={
    me: null,         // 로그인 사용자 정보 { id, email, nickname}
    users: [],        // 전체 사용자 목록 [ { id, email, nickname} ]
    isLoading:false,  // API 요청 중 여부
    error: null,      // 에러메시지
    signUpDone: false ,  // 회원가입 완료 여부
    
    checkEmailLoading: false,
    checkEmailDone: false,
    checkEmailError: null,
    isEmailAvailable: null, // true: 사용가능, false: 중복

    checkNicknameLoading: false,
    checkNicknameDone: false,
    checkNicknameError: null,
    isNicknameAvailable: null, // true: 사용가능, false: 중복

    searchResults: [],        //  검색된 사용자 목록
    searchUsersLoading: false,
    searchUsersDone: false,
    searchUsersError: null,
}; 

// reducer 함수
const reducer = ( state = initialState , action )=>{ 
    switch(action.type){
        // 요청 액션  → 로딩 시작
        case  LOG_IN_REQUEST:
        case  LOG_OUT_REQUEST:
        case  SIGN_UP_REQUEST:
        case  LOAD_USERS_REQUEST:
        case  UPDATE_NICKNAME_REQUEST:
        case  DELETE_USER_REQUEST:
            return {  ...state ,  isLoading:true, error: null    }; 
        case CHECK_EMAIL_REQUEST:
            return {
                ...state,
                checkEmailLoading: true,
                checkEmailDone: false,
                checkEmailError: null,
                isEmailAvailable: null,
            };

        //  성공 액션  → 상태 업데이트
        case LOG_IN_SUCCESS:
            return {  ...state , isLoading:false,  me: action.data  };

        case LOG_OUT_SUCCESS:
            return {  ...state , isLoading:false,  me: null  };
            
        case SIGN_UP_SUCCESS:
            return {  ...state , isLoading:false,  signUpDone: true  };

        case LOAD_USERS_SUCCESS:
            return {  ...state , isLoading:false,  users: action.data ,
                 searchUsersDone: false,   searchResults: [],         };


        case CHECK_EMAIL_SUCCESS:
            return {
                ...state,
                checkEmailLoading: false,
                checkEmailDone: true,
                isEmailAvailable: action.data.isAvailable,  
            };
 
        case CHECK_EMAIL_FAILURE:
            return {
                ...state,
                checkEmailLoading: false,
                checkEmailError: action.error,
                isEmailAvailable: false,  
            };

        case UPDATE_NICKNAME_SUCCESS:
            return {  
                ...state , 
                isLoading:false,   
                me: state.me &&  state.me.id === action.data.id  
                  ? {  ...state.me,   nickname:action.data.nickname} 
                  : state.me , 
                users: state.users.map( (u)=> 
                    u.id === action.data.id ? {  ...u , nickname: action.data.nickname } : u
                 ),
 
                ////////////////////////////// 
                //  검색 결과 목록에서도 닉네임 업데이트 추가 구현하세요.
                searchResults: state.searchResults.map( (u)=>
                    u.id === action.data.id ? { ...u, nickname: action.data.nickname } : u
                ),
                
            };

        case DELETE_USER_SUCCESS:
            return {  
                ...state , 
                isLoading:false,   
                users: state.users.filter(  (u)=> u.id !== action.data.id ), 
                me: state.me?.id === action.data.id ? null : state.me , 
                ////////////////////////////// 
                //  검색 결과 목록에서도 닉네임 업데이트 추가  구현하세요.
            };
 
        ////////////////////////////// 
        // 닉네임 중복 확인 - CHECK_NICKNAME_REQUEST / CHECK_NICKNAME_SUCCESS / CHECK_NICKNAME_FAILURE  구현 하세요.
        case CHECK_NICKNAME_REQUEST:
            return {
                ...state,
                checkNicknameLoading: true,
                checkNicknameDone: false,
                checkNicknameError: null,
                isEmailAvailable: null,
            };
        case CHECK_NICKNAME_SUCCESS:
            return {
                ...state,
                checkNicknameLoading: false,
                checkNicknameDone: true,
                isNicknameAvailable: action.data.isAvailable,  
            };
        case CHECK_NICKNAME_FAILURE:
            return {
                ...state,
                checkNicknameLoading: false,
                checkNicknameError: action.error,
                isNicknameAvailable: false,  
            };

        //////////////////////////////
        //  닉네임 검색 요청  - SEARCH_USERS_REQUEST  / SEARCH_USERS_SUCCESS  / SEARCH_USERS_FAILURE  구현 하세요.
        case SEARCH_USERS_REQUEST:
            return {
                ...state,
                searchUsersLoading: true,
                searchUsersDone: false,
                searchUsersError: null,
            };
        case SEARCH_USERS_SUCCESS:
            return {
                ...state,
                searchUsersLoading: false,
                searchUsersDone: true,
                searchResults: action.data,
            };
        case SEARCH_USERS_FAILURE:
            return {
                ...state,
                searchUsersLoading: false,
                searchUsersError: action.error,
            };

        //  실패 액션  → 에러메시지 저장
        case  LOG_IN_FAILURE:
        case  LOG_OUT_FAILURE:
        case  SIGN_UP_FAILURE:
        case  LOAD_USERS_FAILURE:
        case  UPDATE_NICKNAME_FAILURE:
        case  DELETE_USER_FAILURE:
            return {  
                ...state ,  
                isLoading:false, 
                error: action.error?.message ||action.error,    
            }; 
        //  기본값 → 상태 변경 없음
        default:
            return state;
    }
 };
export default reducer;
