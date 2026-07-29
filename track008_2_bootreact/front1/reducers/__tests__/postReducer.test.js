import postReducer, {
    fetchPostsRequest, fetchPostsSuccess, fetchPostsFailure,
    fetchPostDetailRequest, fetchPostDetailSuccess, fetchPostDetailFailure,
    createPostRequest, createPostSuccess, createPostFailure,
    updatePostRequest, updatePostSuccess, updatePostFailure,
    deletePostRequest, deletePostSuccess, deletePostFailure,
    resetPostState
} from '../postReducer';

describe('post slice reduce', () => {
    const initialState = {
        posts: [],
        currentPost: null,
        loading: false,
        error: null,  
        success: false,
    };
    // === 전체 게시글 ===
    it('fetchPostsRequest & fetchPostsSuccess', () => {
        let state = postReducer(initialState, fetchPostsRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();
        expect(state.success).toBe(false);

        const posts = [{id:1, content:'첫번째게시글'}, {id:2, email:'두번째게시글'}];
        state = postReducer(initialState, fetchPostsSuccess(posts) );
        expect(state.loading).toBe(false); 
        expect(state.posts).toEqual(posts);
        expect(state.success).toBe(true);
    });
    it('fetchPostsFailure', () => {
        const state = postReducer(initialState, fetchPostsFailure("전체 게시글 조회 실패") );
        expect(state.loading).toBe(false);
        expect(state.error).toBe("전체 게시글 조회 실패");
    });
    // === 단건 게시글 ===
    it('fetchPostDetailRequest & fetchPostDetailSuccess', () => {
        let state = postReducer(initialState, fetchPostDetailRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();
        expect(state.success).toBe(false);

        const currentPost = {id:1, content:'첫번째게시글'};
        state = postReducer(initialState, fetchPostDetailSuccess(currentPost) );
        expect(state.loading).toBe(false); 
        expect(state.currentPost).toEqual(currentPost);
        expect(state.success).toBe(true);
    });
    it('fetchPostDetailFailure', () => {
        const state = postReducer(initialState, fetchPostDetailFailure("단건 게시글 조회 실패") );
        expect(state.loading).toBe(false);
        expect(state.error).toBe("단건 게시글 조회 실패");
    });
    // === 게시글 작성 ===
    it('createPostRequest & createPostSuccess', () => {
        let state = postReducer(initialState, createPostRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();
        expect(state.success).toBe(false);

        const prev = { ...initialState, posts: [{id:1, content:'첫번째게시글'}], };
        const newPost = {id:2, content:'두번째게시글'};
        state = postReducer(prev, createPostSuccess(newPost) );
        expect(state.loading).toBe(false); 
        expect(state.posts).toEqual([newPost, ...prev.posts]);
        expect(state.success).toBe(true);
    });
    it('createPostFailure', () => {
        const state = postReducer(initialState, createPostFailure("게시글 작성 실패") );
        expect(state.loading).toBe(false);
        expect(state.error).toBe("게시글 작성 실패");
    });
    // === 게시글 수정 ===
    it('updatePostRequest & updatePostSuccess', () => {
        let state = postReducer(initialState, updatePostRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();
        expect(state.success).toBe(false);

        const prev = { ...initialState, posts: [{id:1, content:'첫번째게시글'}], };
        const updPost = {id:1, content:'게시글내용수정'};
        state = postReducer(prev, updatePostSuccess(updPost) );
        expect(state.loading).toBe(false); 
        expect(state.posts).toEqual([updPost]);
        expect(state.currentPost).toEqual(updPost);
        expect(state.success).toBe(true);
    });
    it('updatePostFailure', () => {
        const state = postReducer(initialState, updatePostFailure("게시글 수정 실패") );
        expect(state.loading).toBe(false);
        expect(state.error).toBe("게시글 수정 실패");
    });
    // === 게시글 삭제 ===
    it('deletePostRequest & deletePostSuccess', () => {
        let state = postReducer(initialState, deletePostRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();
        expect(state.success).toBe(false);

        const prev = { ...initialState, posts: [{id:1, content:'첫번째게시글'}], };

        const delPost = {id:1};
        state = postReducer(prev, deletePostSuccess(delPost) );
        expect(state.loading).toBe(false); 
        expect(state.posts.length).toBe(0);
        //expect(state.posts).toEqual([]);
        expect(state.success).toBe(true);
    });
    it('deletePostFailure', () => {
        const state = postReducer(initialState, deletePostFailure("게시글 삭제 실패") );
        expect(state.loading).toBe(false);
        expect(state.error).toBe("게시글 삭제 실패");
    });
    it('resetPostState', () => {
        const prev = {posts:[{id:1}], currentPost:{id:1}, loading: true, error:'error', success: true};
        const state = postReducer(prev, resetPostState() );
        expect(state.loading).toBe(false);
        expect(state.posts).toEqual([]);
        expect(state.currentPost).toEqual(null);
        expect(state.error).toBe(null);
        expect(state.success).toBe(false);
    });
});