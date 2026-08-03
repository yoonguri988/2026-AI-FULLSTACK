import {createSlice, current} from '@reduxjs/toolkit';

//1. 초기화 상태 (공용)
const initialState = {
    posts: [], //전체 게시글 목록
    currentPost: null, // 단건 조회된 상세 게시글
    loading: false, // 로딩상태
    error: null,    // 에러메시지
    success: false, // 성공여부
};

//2. 상태변화
const postReducer = createSlice({
    name: "post",
    initialState,
    reducers: {
        // === 전체 게시글 ===
        fetchPostsRequest: (state)=>{
            state.loading = true;
            state.error = null;
            state.success = false;
        },
        fetchPostsSuccess: (state,action)=>{
            state.loading = false;
            state.posts = action.payload; 
            state.success = true;
        },
        fetchPostsFailure: (state,action)=>{
            state.loading = false;
            state.error = action.payload;
            state.success = false;
        },
        // === 단건 게시글 ===
        fetchPostDetailRequest: (state)=>{
            state.loading = true;
            state.error = null;
            state.success = false;
        },
        fetchPostDetailSuccess: (state,action)=>{
            state.loading = false;
            state.currentPost = action.payload; 
            state.success = true;
        },
        fetchPostDetailFailure: (state,action)=>{
            state.loading = false;
            state.error = action.payload;
            state.success = false;
        },
        // === 게시글 작성 ===
        createPostRequest: (state)=>{
            state.loading = true;
            state.error = null;
            state.success = false;
        },
        createPostSuccess: (state,action)=>{
            state.loading = false;
            state.posts = [action.payload, ...state.posts]; // 새글을 상단 목록에 추가
            state.success = true;
        },
        createPostFailure: (state,action)=>{
            state.loading = false;
            state.error = action.payload;
            state.success = false;
        },
        // === 게시글 수정 ===
        updatePostRequest: (state)=>{
            state.loading = true;
            state.error = null;
            state.success = false;
        },
        updatePostSuccess: (state,action)=>{
            state.loading = false;
            state.posts = state.posts.map(post =>
                post.id === action.payload.id ? action.payload : post
            )
            state.currentPost = action.payload; 
            state.success = true;
        },
        updatePostFailure: (state,action)=>{
            state.loading = false;
            state.error = action.payload;
            state.success = false;
        },
        // === 게시글 삭제 ===
        deletePostRequest: (state)=>{
            state.loading = true;
            state.error = null;
            state.success = false;
        },
        deletePostSuccess: (state,action)=>{
            state.loading = false;
            state.posts = state.posts.filter(post =>
                post.id !== action.payload
            )
            state.currentPost = null; 
            state.success = true;
        },
        deletePostFailure: (state,action)=>{
            state.loading = false;
            state.error = action.payload;
            state.success = false;
        },

        // === 상태 초기화 ===
        resetPostState: (state) =>{
            state.posts = [];
            state.currentPost = null;
            state.loading = false;
            state.error = null;
            state.success = false;
        },

    }
});

//3. action
export const {
    fetchPostsRequest, fetchPostsSuccess, fetchPostsFailure,
    fetchPostDetailRequest, fetchPostDetailSuccess, fetchPostDetailFailure,
    createPostRequest, createPostSuccess, createPostFailure,
    updatePostRequest, updatePostSuccess, updatePostFailure,
    deletePostRequest, deletePostSuccess, deletePostFailure,
    resetPostState
} = postReducer.actions;

//4. export
export default postReducer.reducer;