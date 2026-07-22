import { useSelector, useDispatch } from 'react-redux'; // 전역상태, 상태알림
import { useState, useEffect } from 'react'; // 변수 상태 변경, 이벤트 변경
import { useRouter } from 'next/router'; // 경로
import { SIGN_UP_REQUEST, RESET_SIGNUP_DONE, CHECK_EMAIL_REQUEST } from '../reducers/user';

// useSelector  - 전역상태
// useState     - 변수
// useEffect    - 변경감지
// useDispatch  - 스토어알림
// useRouter    - 경로

export default function JoinPage() {
    //1. 코드
    const dispatch = useDispatch();
    const router = useRouter();
    const {me, isLoading, error, signUpDone, isAvailable, emailCheckMessage} = useSelector((state) => state.user);
    // 변수, 변수 셋팅 함수
    // 3. 변수 상태 변경 - react dom (useState)
    const [email, setEmail] = useState(''); // let email = ''
    const [password, setPassword] = useState(''); // let password = ''
    const [nickname, setNickname] = useState(''); // let nickname = ''

    // 회원가입 요청 액션 Dispatch
    const onSubmit = (e) => {
        e.preventDefault();
        // 공백 제외하고 값이 있는지 없는지
        if(!email.trim()){
            alert('이메일을 입력해주세요.');
            return;
        }
        if(!password.trim()){
            alert('비밀번호를 입력해주세요.');
            return;
        }
        if(!nickname.trim()){
            alert('닉네임을 입력해주세요.');
            return;
        }

        // 2. Store 액션알림 useDispatch
        dispatch({type:SIGN_UP_REQUEST, data:{email, password, nickname}});
    };

    // 이메일 중복 확인
    const checkEmail = () =>{
        if(!email.trim()){
            alert('이메일을 입력해주세요.');
            return;
        }
        dispatch({ type: CHECK_EMAIL_REQUEST, data: { email } });
    }
    // 닉네임 중복 확인
    const checkNickname = () =>{
       
    }

    // 로그인시 me 값이 있다면
    useEffect(()=>{ // 경로변경
        if(me){
            router.push({
                pathname: "/users"
            })
        }
    }, [me, router]);

    //5. 상태 변화 감지
    useEffect(()=>{ // 경로변경
        dispatch({ type: RESET_SIGNUP_DONE });
        if(signUpDone){
            router.push({
                pathname: '/login',
                query: { signUpSuccess: 'true' } //회원가입 성공여부 주소표시창줄
            });
        }
    }, [signUpDone, router]);
    

    //2. 뷰 - 렌더링 <></>, 공백, 닫기 태그
    return (
        <div className="container my-4">
            <h3 className="mb-3">회원가입</h3>
            <form className="w-50 mx-auto" onSubmit={onSubmit}>
            {/* 이메일 입력 */}
            <div className="input-group mb-3">
                <input type="email" className="form-control" placeholder="이메일" title="이메일입력"
                       value={email} onChange={(e)=>{setEmail(e.target.value);}}/>
                <button type="button" className="btn btn-secondary" onClick={() => checkEmail()}>중복확인</button>
            </div>
            {emailCheckMessage && isAvailable === true && <div className="alert alert-success">{emailCheckMessage}</div>}
            {emailCheckMessage && isAvailable === false && <div className="alert alert-danger">{emailCheckMessage}</div>}
            {/* 비밀번호 입력 */}
            <div className="mb-3">
                <input type="password" className="form-control" placeholder="비밀번호" title="비밀번호입력"
                       value={password} onChange={(e)=>{setPassword(e.target.value);}}/>
            </div>
            {/* 닉네임 입력 */}
            <div className="input-group mb-3">
                <input type="text" className="form-control" placeholder="닉네임" title="닉네임입력"
                       value={nickname} onChange={(e)=>{setNickname(e.target.value);}}/>
                <button type="button" className='btn btn-secondary' onClick={() => checkNickname()}>중복확인</button>
            </div>
            {/* 버튼 입력 */}
            <div className="mb-3">
                <button type="submit" className="btn btn-primary w-100" disabled={isLoading}>회원가입</button>
            </div>
            </form>
            {/* 에러 메시지 */}
            {error && <div className="alert alert-danger mt-3">{error}</div>}
        </div>
    );
}