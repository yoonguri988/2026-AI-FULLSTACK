// pages/index.js
import React, {} from 'react-redux';
import { useSelector, useDispatch } from 'react-redux';
import { fetchPostsRequest } from "../reducers/postReducer";
import {Card, Spin} from 'antd';
import { useEffect } from 'react';

export default function Home() {
    const dispatch = useDispatch();
    //1. 유저정보가져오기 - state.auth
    const {user} = useSelector((state)=>state.auth);
    //2. 게시글정보가져오기 - state.post
    const {posts, loading, error} = useSelector((state)=>state.post);
    // 페이지 처음뜰 때 게시글 조회 액션 - dispatch
    useEffect(()=>{
        dispatch(fetchPostsRequest());
    },[dispatch])
    return (
        <div>
            {/* 게시판 리스트 */}
            <h3>게시글: {posts.length}</h3>
            {posts.map((post, index)=>(
                <Card key={post.id||index} style={{marginBottom:"10px"}}>
                    <p>{post.content}</p>
                </Card>
            ))}
            {/* 수정 부품 */}
        </div>
    );
}