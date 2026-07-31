// pages/posts/new.js
import { Button, Card, Form, Input, message } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { createPostRequest, resetPostState } from "../../reducers/postReducer";
import { useEffect } from "react";
import { useRouter } from "next/router";

function newPostPage(){
    //1. 유저정보 가져오기
    const router = useRouter();
    const dispatch = useDispatch();
    const {posts, currentPost, error, success, loading} = useSelector((state)=> state.post);
    const {user} = useSelector((state)=> state.auth);

    //2. 게시글 작성 (dispatch(createPostRequest(dto)):이벤트발생알림)
    const handleCreatePost = (values) =>{
        //기능
        const dto = {
            content: values.content,
            userId: user.id,
        };
        dispatch(createPostRequest(dto));
        message.success("게시글 작성요청완료");
        router.push("/")
    }

    // View
    return (
        <Card title="게시글 작성" style={{maxWidth: 600, margin: "40px auto"}}>
            <Form layout="vertical" onFinish={handleCreatePost}>
            {/** 게시글 입력 Form.Item > Input */}
            <Form.Item 
                label="내용" name="content" 
                rules={[ {required: true, message: '내용을 입력하세요'}]}>
                <Input.TextArea rows={4} placeholder="게시글 내용을 입력하세요"/>
            </Form.Item>
            <Button type="primary" htmlType="submit">게시글 작성</Button>
            {error && <p style={{color:"red"}}>{error}</p>}
            </Form>
        </Card>
    );
}

export default newPostPage;