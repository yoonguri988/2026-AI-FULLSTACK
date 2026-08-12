//1. import, require [ ]
// useSelector  - 전역상태
// useDispatch  - 스토어알림
// useState     - 변수
// useEffect    - 이벤트변경감지
// useRouter    - 경로
import { useEffect, useRef } from "react";
import { useRouter } from "next/router";
import { useDispatch } from "react-redux";
import axios from 'axios';
import { loginSuccess } from "../../reducers/authReducer";


//2. 부품 export
export default function OAuth2CallbackPage() {
    const router = useRouter(); //경로이동
    const dispatch = useDispatch(); //스토어알림

    useEffect(() => {
        if(!router.isReady) return;
        const {accessToken} = router.query;
        if(accessToken){
            try{
                localStorage.setItem("accessToken" , accessToken);  
                fetchUser(accessToken);  
            }catch(err){
                console.error( "OAuth2 callback error:", err);
                router.push("/login");
            }    
        }
    }, [router.query, router.isReady]);

    const fetchUser = async (accessToken) => {
        try {
            const res = await axios.get("http://localhost:8080/auth/me", {
                headers: { Authorization: `Bearer ${accessToken}` },
                withCredentials: true, // 쿠키 전송용
            });
            const user = res.data;
            dispatch(loginSuccess({user, accessToken}))
            router.push("/mypage");
        } catch(err){
            console.error("User fetch error:", err);
            router.push("/login");
        }
    };
    return (<p>소셜 로그인 처리 중입니다.</p>);
}