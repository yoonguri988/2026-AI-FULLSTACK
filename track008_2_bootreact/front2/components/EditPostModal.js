import React from "react";
import { Button, Form, Input, Modal } from "antd";

export default function EditPostModal({
    visible, onCancel, editPost, onSubmit
}) {

    return (
        <Modal title="글 수정" open={visible} onCancel={onCancel} footer={null}>
            <Form initialValues={{
                content: editPost?.content,
            }} layout="vertical" onFinish={onSubmit}>
            {/** 내용 입력 Form.Item > Input.TextArea */}
            <Form.Item 
                label="내용" name="content">
                <Input.TextArea rows={4} />
            </Form.Item>
            <Button type="primary" htmlType="submit">
                수정 완료
            </Button>
            </Form>
        </Modal>
    )
}