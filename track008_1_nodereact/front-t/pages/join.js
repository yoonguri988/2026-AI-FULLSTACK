
import { useDispatch  , useSelector } from 'react-redux';  // 액션발생 , redux store에서 사용자상태
import { useState     , useEffect }   from 'react'; // 특정이벤트-상태
import { useRouter }  from 'next/router';
import { SIGN_UP_REQUEST, CHECK_EMAIL_REQUEST, CHECK_NICKNAME_REQUEST } from '../reducers/user';

export default function JoinPage(){
    // 코드 
    const  dispatch = useDispatch();
    const  router   = useRouter();
    const { me, isLoading, error, signUpDone, isEmailAvailable, checkEmailLoading, isNicknameAvailable, checkNicknameLoading  } = useSelector((state) => state.user);

    // 입력값 상태관리
    const [email, setEmail]       = useState('');  //email 변수, email 셋팅함수
    const [password, setPassword] = useState(''); 
    const [nickname, setNickname] = useState(''); 

    // 이메일 중복 확인 핸들러
    const onCheckEmail = (e) => {
        e.preventDefault();
        if (!email.trim()) {
            alert('이메일을 입력해주세요.');
            return;
        }
        dispatch({ type: CHECK_EMAIL_REQUEST, data: email });
    };
    // Q2. 닉네임 중복 확인 핸들러 추가 - 구현하세요.
    const onCheckNickname = (e) => {
        e.preventDefault();
        if (!nickname.trim()) {
            alert('닉네임을 입력해주세요.');
            return;
        }
        dispatch({ type: CHECK_NICKNAME_REQUEST, data: nickname });
    };

    // 회원가입 요청 액션 dispatch
    const onSubmit = (e)=>{
        e.preventDefault();
        if(!email.trim()){
            console.log('이메일이 빈칸입니다.');
            alert('이메일을 입력해주세요');
            return;
        }
        
        if(!password.trim()){
            console.log('비밀번호가 빈칸입니다.');
            alert('비밀번호를 입력해주세요');
            return;
        }
        
        if(!nickname.trim()){
            console.log('닉네임이 빈칸입니다.');
            alert('닉네임을 입력해주세요');
            return;
        }

        dispatch({ type : SIGN_UP_REQUEST , data : { email, password, nickname}});
    };

    // 로그인 상태면 회원가입페이지 대신 사용자 목록으로 이동
    useEffect( ()=>{ if(me) router.push('/users');   } , [me, router]);    

    // 회원가입 성공시 로그인페이지로 이동
    useEffect(()=>{
        if(signUpDone){  // 경로변경
            //router.push({  pathname:'/login',  query:{  signUpSuccess : 'true' } });// 회원가입 성공여부 주소표시창줄
            //router.replace('/login?signUpSuccess=true');
            window.location.href='/login?signUpSuccess=true';   
        }
    } , [signUpDone , router]); 

    // 렌더링
    return (
    <div className="container mt-4">
        <h2 className="mb-3">회원가입</h2> 
        <form  onSubmit={onSubmit}  className="w-50 mx-auto">
        {/*  이메일 입력  */} 
        {/*  이메일 입력 및 중복 확인 버튼 */} 
        <div className="mb-3 input-group">
            <input 
                type="email" className="form-control" placeholder="이메일" value={email}
                onChange={(e) => setEmail(e.target.value)} 
            />
            <button 
                className="btn btn-outline-secondary" type="button" 
                onClick={onCheckEmail} disabled={checkEmailLoading}
            >
                {checkEmailLoading ? '확인 중...' : '중복 확인'}
            </button>
        </div>
        
        {/* 이메일 상태 메시지 표시 */}
        {isEmailAvailable === true && <div className="text-success mb-2">사용 가능한 이메일입니다.</div>}
        {isEmailAvailable === false && <div className="text-danger mb-2">이미 사용 중인 이메일입니다.</div>}

         {/*  비밀번호 입력 */}
        <div className="mb-3">
            <input type="password" className="form-control" placeholder="비밀번호"  value={password}
            onChange={ (e)=> setPassword(e.target.value) }   />
        </div>

        {/* 닉네임 입력 및 중복 확인 버튼 */}
        <div className="mb-3 input-group">
            <input 
                type="text" className="form-control" placeholder="닉네임" value={nickname}
                onChange={(e) => setNickname(e.target.value)} 
            />
            <button 
                className="btn btn-outline-secondary" type="button" 
                onClick={onCheckNickname} disabled={checkNicknameLoading}
            > {checkNicknameLoading ? '확인 중...' : '중복 확인'}
            </button>
        </div>

        {/* 닉네임 상태 메시지 표시 */}
        {isNicknameAvailable === true && <div className="text-success mb-2">사용 가능한 닉네임입니다.</div>}
        {isNicknameAvailable === false && <div className="text-danger mb-2">이미 사용 중인 닉네임입니다.</div>}

        {/* 회원가입 버튼 */}
        <button type="submit" className="btn btn-primary w-100"    disabled={isLoading}>   회원가입 </button>
        </form> 
        {/* 에러 메시지 */}
        {error && <div className="alert alert-danger mt-3"> {error} </div> }
  </div>
    );
}
