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