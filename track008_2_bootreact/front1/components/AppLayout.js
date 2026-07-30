// components/AppLayout.js                # 재사용이 가능한 UI 컴포넌트 폴더
import { Layout, Menu, Input, Row, Col, Drawer, Button, Grid } from "antd";  
import { MenuOutlined, SearchOutlined } from "@ant-design/icons";  

import {useSelector, useDispatch} from 'react-redux'; // 전역상태, 액션
import { useRouter } from 'next/router'; // 경로이동
import { useEffect, useState } from 'react'; // 이벤트 변경 감지, 변수
import Link from 'next/link';

const {Header, Content} = Layout;
const {useBreakpoint} = Grid;

const menuItems = [
    { key: "new",     label: <Link href="/posts/new">✏️ NEW POST</Link> },
    { key: "profile", label: <Link href="/mypage">👤 MYPAGE </Link> },
    { key: "home",    label: <Link href="/signup">🏠 JOIN</Link> },
];


// 2. 부품
// Header / Drawer
function AppLayout() {
    return (
       <Layout>
        {/* Header */}
        <Header style={{display: "flex"}}>
            <Row align="middle" justify="space-between" style={{width: "100%"}}>
                <Col>
                    <Link href="/">
                        <a style={{color: "#fff", fontWeight:"bold", fontSize:"18px"}}>THEJOA703 (POST VER)</a>
                    </Link>
                </Col>
                <Col flex="auto">
                    <Menu
                    theme="dark"
                    mode="horizontal"
                    items={menuItems}
                    />
                </Col>
            </Row>
        </Header>
        <Content>1231231231313</Content>
       </Layout>
    );
}

export default AppLayout;

// Layout: https://ant.design/components/layout 
// Menu: https://ant.design/components/menu 
// Input: https://ant.design/components/input 
// Drawer: https://ant.design/components/drawer 
// Grid(Row/Col): https://ant.design/components/grid 
// Button: https://ant.design/components/button