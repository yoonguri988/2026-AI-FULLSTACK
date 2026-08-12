//pages/mypage.js
//1. 
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
    Card, Avatar, Spin, Descriptions, Form, Input, Button, Upload, List, Tabs, message,
} from "antd";
import { useRouter } from "next/router";
import { updateNicknameRequest, updateProfileImageRequest } from "../reducers/authReducer";
import { UploadOutlined } from "@ant-design/icons";

import { END } from "redux-saga";
import { loadUserRequest } from "../reducers/authReducer";
import { wrapper } from "../store/configureStore";

//2. 부품 + export
export default function MyPage() {
    const dispatch = useDispatch();
    const router = useRouter();
    // Redux에서 회원가입시 저장된 사용자 정보 가져오기 - user
    const { user } = useSelector((state) => state.auth);

    const [fileList, setFileList] = useState([]);

    const handleUpdateNickname = (values) => {
        dispatch(updateNicknameRequest({ userId: user.id, nickname: values.nickname }))
    };

    if (!user) {
        return (
            <div style={{ maxWidth: 600, margin: "40px auto" }}>
                <p>로그인된 사용자 없습니다.</p>
                <Button type="primary" onClick={() => router.push("/signup")} >
                    회원가입 하러가기
                </Button>
            </div>
        );
    }

    ///////////////
    return (
        <div style={{ maxWidth: 600, margin: "40px auto" }}>
            <Card title="마이페이지 (회원 정보)">
                <div style={{ display: "flex", alignItems: "center", gap: "20px" }}>
                    <Avatar src={`http://localhost:8080/${user.ufile}`} size={130}>{user.nickname?.[0]}</Avatar>
                    <Descriptions title="User Info" bordered column={1}>
                        <Descriptions.Item label="회원 번호">{user.id}</Descriptions.Item>
                        <Descriptions.Item label="이메일">{user.email}</Descriptions.Item>
                        <Descriptions.Item label="닉네임">{user.nickname}</Descriptions.Item>
                    </Descriptions>
                </div>
                {/* 닉네임 수정 */}
                <Form
                    layout="inline"
                    style={{ marginBottom: 20, marginTop: 40 }}
                    onFinish={handleUpdateNickname}
                >
                    <Form.Item
                        name="nickname"
                        rules={[
                            { required: true, message: '닉네임을 입력해주세요.' },
                        ]}
                    >
                        <Input placeholder="새 닉네임" />
                    </Form.Item>
                    <Button type="primary" htmlType="submit">닉네임 변경</Button>
                </Form>
                {/* 프로필이미지 수정 */}
                <Form layout="inline" style={{marginBottom: 20}}>
                    <Form.Item name="profileImage">
                        <Upload
                            beforeUpload={() => false}
                            fileList={fileList}
                            onChange={({ fileList: newFileList }) => setFileList(newFileList)}
                            maxCount={1}>
                            <Button icon={<UploadOutlined />}>이미지 선택</Button>
                        </Upload>
                    </Form.Item>
                    <Button type="primary" onClick={()=>{
                        if(!user || fileList.length === 0){
                            message.warning('변경할 이미지를 선택해주세요');
                            return;
                        }
                        const file = fileList[0]?.originFileObj;
                        dispatch(updateProfileImageRequest({userId: user.id, file}));
                        setFileList([]);
                    }}>프로필 이미지 변경</Button>
                </Form>
            </Card>
        </div>
    );
}
// 이 코드를 넣는 이유는 서버 사이드 렌더링(SSR) 과정에서 Redux-Saga의 비동기 API 요청이 완료될 때까지 서버가 출력을 멈추고 기다리게 만들기 위해서입니다.
// 1. 이 코드가 없을 때 (일반적인 상태)

// 2. 이 코드를 넣었을 때 (정상적인 SSR 사가 연동)
// 1) store.dispatch(loadUserRequest(...)): "서버야, 쿠키를 담아서 사용자 정보 가져오는 사가 작업을 시작해줘!"라고 명령합니다.
// 2) store.dispatch(END): Redux-Saga에게 "이제 서버사이드에서 더 이상 대기할 사가 액션은 없으니 채널을 닫아(End)라"고 신호를 줍니다.
// 3) await store.sagaTask.toPromise(): 백그라운드에서 돌아가던 사가 작업(API 통신 및 리덕스 상태 업데이트)이 완전히 끝날 때까지 서버가 다음 단계로 넘어가지 않고 멈춰서 기다리게 만듭니다.
// 4) 결과: 사가가 API 응답을 받아 user 정보를 스토어에 완벽하게 채워 넣은 뒤에야 비로소 store.getState()를 통해 유저가 있는지 안전하게 확인할 수 있습니다.

//BEFORE
// 1) 사용자가 마이페이지(/mypage) 주소로 접속합니다.
// 2) 서버에서 getServerSideProps가 실행되면서 loadUserRequest 액션을 디스패치합니다.
// 3) 문제점: Redux-Saga는 비동기(Asynchronous)로 동작하기 때문에, 
//           서버는 사가가 백그라운드에서 백엔드 API(/auth/me)를 부르든 말든 기다리지 않고 
//           곧바로 return { props: {} }를 실행해버립니다.
// 4) 결과적으로 브라우저는 유저 정보가 아직 담기지 않은 텅 빈 스토어 상태로 페이지를 먼저 그려버리게 되므로, 
//    새로고침 시 로그인 정보가 안 뜬 것처럼 보이거나 깜빡임 현상이 발생합니다.

// 직접 API를 부르지 않고 , loadUserRequest 디스패치해서 사가완료 기다림
export const getServerSideProps = wrapper.getServerSideProps((store) => async (ctx) => { 
  //1. 요청헤더의 쿠키를 담아서 사용자정보를 조회하기 위한 사가 액션 디스패치
  store.dispatch(loadUserRequest({ cookie: ctx.req.headers.cookie || "" }));
  //2. 서버사이드에서 사가작업이 끝날때까지 대기
  store.dispatch(END); // 채널 닫아 신호
  await store.sagaTask.toPromise(); // 
  //3. 스토어 상태를 확인해서 유저정보 없으면 로그인 페이지로 리다이렉트
  const state = store.getState();
  const user = state.auth.user;

  if (!user || !user.nickname) {
    return {
      redirect: {
        destination: "/login",  
        permanent: false,  
      },
    };
  } 

  return { props: {} };
});
