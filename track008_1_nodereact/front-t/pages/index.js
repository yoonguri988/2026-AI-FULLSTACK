// pages/index.js

import { useEffect } from 'react';  // 특정동작 실행 사용
import { useRouter } from 'next/router';  // 이동하기

export  default function Home(){
    const router = useRouter(); // 이동하기 
    useEffect(()=>{  // 특정동작
        router.replace('/users');  //현재경로  /users (뒤로가기 남지않음.)
    } , [router]);  // router라는 객체가 변경시  useEffect 실행
    return  null;
}

// npm run dev