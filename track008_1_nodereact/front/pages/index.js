// pages/index.js

import { useEffect } from 'react'; // 특정 동작 실행
import { useRouter } from 'next/router';

export default function Home() {
  //1. 코드
  const router = useRouter();
  useEffect(()=>{
    router.replace('/users'); // /users 뒤로가기가 남지 않음
  }, [router]); // router가 변경시 useEffect 실행

  //2. view
  return null;
}

// useSelector - 전역상태
// useEffect - 변경 감지
// dispatch - 액션 발생

//// ver-1
// export default function Home() {
//   return <h1>REACT PROJECT 정상실행</h1>;
// }

// npm run dev