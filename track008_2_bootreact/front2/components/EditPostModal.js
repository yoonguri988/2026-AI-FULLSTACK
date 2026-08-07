// components/EditPostModal
import { UploadOutlined } from '@ant-design/icons';
import { Modal, Form, Input, Button, Select, Upload } from 'antd';
import { useEffect } from 'react';
export default function EditPostModal({
    visible, onCancel, editPost, onSubmit, uploadFiles, setUploadFiles
}) {
    return (<Modal title="글 수정" open={visible} onCancel={onCancel} footer={null} destroyOnClose  >
        <Form
            initialValues={{
                content: editPost?.content || '',
                hashtags: editPost?.hashtags
            }}
            onFinish={onSubmit}
            layout="vertical"
        >
            <Form.Item name="content" label="내용">
                <Input.TextArea rows={4} />
            </Form.Item>
            {/* 해시태그 입력 */}
            <Form.Item label="해시태그" name="hashtags">
                <Select mode="tags" style={{ width: "100%" }} placeholder="해시태그 입력 후 Enter"></Select>
            </Form.Item>
            {/* 이미지 업로드 */}
            <Form.Item name="profileImage" label="이미지 업로드">
                <Upload multiple
                    beforeUpload={() => false}
                    fileList={uploadFiles}
                    onChange={({ fileList: newFileList }) => setUploadFiles(newFileList)}
                    listType="picture-card">
                    <Button icon={<UploadOutlined />}>이미지 선택</Button>
                </Upload>
            </Form.Item>
            <Button type="primary" htmlType="submit">
                수정완료
            </Button>
        </Form>
    </Modal>);
}