import React, { useEffect } from "react"; // 이벤트 변경 감지, useState (변수)
import { Provider, useDispatch, useSelector } from "react-redux"; //스토어 알림, 전역상태
import { Row, Col, Form, Input, Button, Spin, message } from "antd";  
import { useRouter } from "next/router"; // 경로
import { loginRequest } from "../reducers/authReducer";
import axios from "axios";

export default function LoginPage() { 
    // useDispatch, useRouter 초기화
    const dispatch = useDispatch();
    const router = useRouter();
    // useSelector 사용해서 user 상태 가져오기
    const {user, loading, error} = useSelector((state)=>state.auth);
    // 로그인 버튼 누르고 나면, dispatch (loginRequest)
    const handleLogin = (values) =>{
        console.log(values);
        dispatch(loginRequest({...values, provider:'local'}));;
    }
    useEffect(()=>{
        // 로그인 성공시 OO님 환영합니다. 메시지 띄우고 마이페이지 이동
        if(user && user.email){
            message.success(`${user.nickname}님 환영합니다.`);
            router.push(`/mypage`);
        }
    }, [user, router, dispatch]);

    {/* justify 이용해서 중앙 배치, 위쪽에 여백 40 */}
    return (
        <Row justify="center" style={{marginTop: 40}}>
            {/* 반응형 처리 xs 제일 작은 모바일 24 sm 16 md 8 */}
            <Col xs={24} xm={16} md={8}> 
                {loading && <Spin />}
                {error && <p style={{color: "red"}}>{error}</p>}
                    <Form  layout="vertical" onFinish={handleLogin}> 
                        <Form.Item 
                            label="이메일"
                            name="email" 
                            rules={[{required: true, message: "이메일을 입력하세요."}]}
                        >
                            <Input placeholder="aaa@email.com"/>
                        </Form.Item>

                        <Form.Item 
                            label="비밀번호"
                            name="password" 
                            rules={[{required: true, message: "비밀번호를 입력하세요."}]}
                        >
                            <Input.Password placeholder="******" />
                        </Form.Item>         

                        <div style={{ textAlign: 'center', marginTop: 20 }}>
                            <Button 
                                type="primary" 
                                htmlType="submit"   
                                style={{ width: '200px', height: '50px' }}
                            >
                                로그인
                            </Button>
                        </div>
                    </Form> 
            </Col>
        </Row>
    );
}

export async function getServerSideProps() {
  return { props: {} };
}
/*
1. 로그인한 상태: 글쓰기 / 마이페이지 / 로그아웃
2. 로그인 안한 상태: 로그인 / 회원가입
*/