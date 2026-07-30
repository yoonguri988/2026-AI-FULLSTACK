import {call, put} from 'redux-saga/effects';
import axios from 'axios';
import {
    fetchPostsRequest, fetchPostsSuccess, fetchPostsFailure,
    fetchPostDetailRequest, fetchPostDetailSuccess, fetchPostDetailFailure,
    createPostRequest, createPostSuccess, createPostFailure,
    updatePostRequest, updatePostSuccess, updatePostFailure,
    deletePostRequest, deletePostSuccess, deletePostFailure,
    resetPostState
} from "../../reducers/postReducer"
import {
    fetchPosts, fetchPostDetail,
    createPost, updatePost, deletePost,
} from '../postSaga';

jest.mock('axios');

describe('post saga', () => {
    afterEach(()=> {jest.clearAllMocks()});
    // === 전체 게시글 조회 ===
    it('fetchPosts success', ()=>{
        const gen = fetchPosts(fetchPostsRequest());
        //1. 1단계 API 호출 (CALL) = 화면 요청
        expect(gen.next().value.type).toBe('CALL');

        //2. api 성공했다는 가정하에 결과값을 전달 = 결과물 받기
        const mockData = [{ id: 1, content: 'post 1' }];
        const mockResponse = { data: mockData };
        const putStep = gen.next(mockResponse).value;

        //3. 2단계 성공액션 디스패치 = 결과물 받기
        expect(putStep).toEqual(put(fetchPostsSuccess(mockResponse.data)));
        expect(gen.next().done).toBe(true); //제너레이터 완전 종료
    });
    // === 게시글단건조회 ===
    it('fetchPostDetail success', ()=>{
        const gen = fetchPostDetail(fetchPostDetailRequest());
        //1. 1단계 API 호출 (CALL) = 화면 요청
        expect(gen.next().value.type).toBe('CALL');

        //2. api 성공했다는 가정하에 결과값을 전달 = 결과물 받기
        const mockResponse = {data: { id: 1, content: 'post 1' }}
        const putStep = gen.next(mockResponse).value;

        //3. 2단계 성공액션 디스패치 = 결과물 받기
        expect(putStep).toEqual(put(fetchPostDetailSuccess(mockResponse.data)));
        expect(gen.next().done).toBe(true); //제너레이터 완전 종료
    });
    // === 게시글작성 ===
    it('createPost success', ()=>{
        const postData = { id: 1, content: 'post 1' };
        const gen = createPost(createPostRequest(postData));

        //1. 1단계 API 호출 (CALL)
        expect(gen.next().value.type).toBe('CALL');

        //2. api 성공했다는 가정하에 결과값을 전달
        const mockResponse = {data: { id: 1, content: 'post 1' }};
        const putStep = gen.next(mockResponse).value;

        //3. 2단계 성공액션 디스패치
        expect(putStep).toEqual(put(createPostSuccess(mockResponse.data)));
        expect(gen.next().done).toBe(true); //제너레이터 완전 종료
    });
    // === 게시글수정 ===
    it('updatePost success', ()=>{
        const postData = { id: 1, content: 'post 1' };
        const gen = updatePost(updatePostRequest(postData));

        //1. 1단계 API 호출 (CALL)
        expect(gen.next().value.type).toBe('CALL');

        //2. api 성공했다는 가정하에 결과값을 전달
        const mockResponse = {data: { id: 1, content: 'post 1' }};
        const putStep = gen.next(mockResponse).value;

        //3. 2단계 성공액션 디스패치
        expect(putStep).toEqual(put(updatePostSuccess(mockResponse.data)));
        expect(gen.next().done).toBe(true); //제너레이터 완전 종료
    });
    // === 게시글삭제 ===
    it('deletePost success', ()=>{
        const postData = { id: 1, content: 'post 1' };
        const gen = deletePost(deletePostRequest(postData));

        //1. 1단계 API 호출 (CALL)
        expect(gen.next().value.type).toBe('CALL');

        //2. api 성공했다는 가정하에 결과값을 전달
        const mockResponse = {data: {}};
        const putStep = gen.next(mockResponse).value;

        //3. 2단계 성공액션 디스패치
        expect(putStep).toEqual(put(deletePostSuccess(mockResponse.data)));
        expect(gen.next().done).toBe(true); //제너레이터 완전 종료
    });
});