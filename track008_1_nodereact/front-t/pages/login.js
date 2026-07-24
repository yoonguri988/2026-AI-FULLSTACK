import { useDispatch  , useSelector } from 'react-redux';  // 액션발생 , redux store에서 사용자상태
import { useState     , useEffect }   from 'react'; // 특정이벤트-상태
import { useRouter }  from 'next/router';
import { LOG_IN_REQUEST } from '../reducers/user'; // 로그인 액션

export default function LoginPage(){ 
    const  dispatch = useDispatch();
    const  router   = useRouter();
    const { me ,isLoading, error }  = useSelector( (state)=> state.user );  //✅  user 상태조회

    useEffect(() => {
        // ✅ 쿼리에 signUpSuccess가 있으면 알림창 표시
        if (router.query.signUpSuccess === 'true') {
            alert('회원가입이 완료되었습니다. 로그인해주세요.');
            
            // 알림을 띄운 후 쿼리를 제거하여, 새로고침 시 알림이 다시 뜨지 않게 합니다.
            router.replace('/login', undefined, { shallow: true });
        }
    }, [router.query]); // router.query가 바뀔 때마다 실행


    // ✅ 입력값 상태관리
    const [email, setEmail] = useState('');  //email 변수, email 셋팅함수
    const [password, setPassword] = useState(''); 

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
        // ✅ 로그인 요청 액션 dispatch
        dispatch({ type: LOG_IN_REQUEST , data:{email, password} });
    };

    // ✅ 로그인 성공시 사용자 목록페이지로 이동
    useEffect( ()=>{
        if(me) router.push('/users');  //replace 주소표시창줄바꿈 / 히스토리에 추가 x , push 히스토리에 추가 o
    } , [me, router]);    
 
    //렌더링
    return ( <div className="container mt-4">
    <h2 className="mb-3">로그인</h2> 
    <form  onSubmit={onSubmit}    className="w-50 mx-auto">
      {/* 이메일 입력 */}
      <div className="mb-3">
        <input type="email" className="form-control"  placeholder="이메일" value={email} 
           onChange={ (e)=> {setEmail(e.target.value); console.log('email>',e.target.value); } }   />
      </div> 
      {/* 비밀번호 입력 */}
      <div className="mb-3">
        <input type="password" className="form-control" placeholder="비밀번호" value={password} 
           onChange={ (e)=> {setPassword(e.target.value); console.log('password>',e.target.value); } }  />
      </div> 
      {/* 로그인 버튼 */}
      <button type="submit" className="btn btn-primary w-100"  disabled={isLoading}>  
            로그인  
      </button>
    </form> 
    {/*  에러 메시지 */}
    {error && <div className="alert alert-danger mt-3"> {error} </div> }
  </div>  );
}

