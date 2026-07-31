import { Descriptions, Card, Button } from "antd";    

import {useSelector, useDispatch} from 'react-redux';
import {useRouter} from "next/router";
import {fetchUserRequest, resetUserState} from "../reducers/authReducer";
import React, { useState, useEffect } from "react";

export default function MyPage() {

    const {user} = useSelector((state)=>state.auth);

    const router = useRouter();

    if(!user) {
        return (<div style={{maxWidth: 600, margin: "40px auto"}}>
            <p>로그인된 사용자가 없습니다.</p>
            <Button type="primary" onClick={()=>router.push("/signup")}>
                회원가입 하러가기
            </Button>
        </div>);
    }

    

    return (<div style={{maxWidth: 600, margin: "40px auto"}}>
        <Card title="마이페이지 (회원정보)">
            <Descriptions title="User Info" bordered column={1}>
            <Descriptions.Item label="회원번호">{user.id}</Descriptions.Item>
            <Descriptions.Item label="이메일">{user.email}</Descriptions.Item>
            <Descriptions.Item label="닉네임">{user.nickname}</Descriptions.Item>
            </Descriptions>
        </Card>
    </div>);
}