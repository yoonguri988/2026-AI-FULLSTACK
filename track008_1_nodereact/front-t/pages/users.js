import { useDispatch, useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import { 
  LOAD_USERS_REQUEST,       
  DELETE_USER_REQUEST,      
  UPDATE_NICKNAME_REQUEST,
  LOG_OUT_REQUEST,  
  SEARCH_USERS_REQUEST,  
} from '../reducers/user';


export default function UsersPage(){
    const dispatch = useDispatch();
    const router = useRouter();

    // searchResults 상태 추가로 가져오기
    const { users,  isLoading, error, me, searchResults, searchUsersDone } = useSelector((state) => state.user);

    useEffect(() => {
        if(!me){ router.push('/login'); }
        else{  dispatch({ type: LOAD_USERS_REQUEST});}
    }, [dispatch, me, router]);

    useEffect(() => {
        if(me === null){ router.push('/login'); }
    }, [me, router]);
 

    // Q3. 검색 키워드 상태 관리 추가
    const [keyword, setKeyword] = useState('');

    // 검색을 실행한 상태(searchUsersDone이 true)일 때만 검색 결과를 보여줌
    const displayUsers = searchUsersDone ? searchResults : users;

    // 검색 폼 제출
    const onSubmit = (e) => {
        e.preventDefault();
        if (!keyword.trim()) return;
        dispatch({ type: SEARCH_USERS_REQUEST, data: keyword });
    };

    const onLogout = () => {
        dispatch({type: LOG_OUT_REQUEST});
    }

    const [editId, setEditId] = useState(null);
    const [newNickname, setNewNickname] = useState(''); 
    const isDuplicated = users.some(u => u.nickname === newNickname && u.id !== editId);
    const onEdit = (id, currentNickname) => {
        setEditId(id);
        setNewNickname(currentNickname);
    }
    const onUpdateNickname = (id) => {
        dispatch({ type: UPDATE_NICKNAME_REQUEST, data: { id, nickname: newNickname } });
        setEditId(null);
        setNewNickname('');   
    };
    
    const onDelete = (id) => {
        dispatch({ type: DELETE_USER_REQUEST, data: { id, nickname: newNickname } });
    }; 

  
    return (
        <div className="container mt-4">
            <h1 className="mb-3">사용자 목록</h1>

            {/* 닉네임 검색 폼 추가 */}
            <form  className="input-group mb-3" onSubmit={onSubmit}>
                <input
                    type="text"
                    className="form-control"
                    placeholder="검색할 닉네임 입력"
                    value={keyword}
                    onChange={(e) => setKeyword(e.target.value)}
                />
                <button className="btn btn-outline-primary" type="submit">검색</button>
                {keyword && (
                    <button 
                        type="button" 
                        className="btn btn-outline-secondary" 
                        onClick={() => { setKeyword(''); dispatch({ type: LOAD_USERS_REQUEST }); }}
                    >
                        초기화
                    </button>
                )}
            </form>

            {isLoading && <div className="alert alert-info">로딩 중...</div>}
            {error && <div className="alert alert-danger">{error}</div>}

            <table className="table table-striped table-bordered table-hover">
                <thead>
                    <tr> <th>이메일</th> <th>닉네임</th> <th>액션</th> </tr>
                </thead>
                <tbody>
                    {displayUsers.map((u) => (
                        <tr key={u.id}>
                            <td>{u.email}</td>
                            <td>
                                {editId === u.id ? (
                                    <>
                                        <input
                                            className={`form-control ${isDuplicated ? 'is-invalid' : ''}`}
                                            value={newNickname}
                                            onChange={(e) => setNewNickname(e.target.value)}
                                            placeholder="새 닉네임"
                                        />
                                        {isDuplicated && (
                                            <div className="invalid-feedback">이미 사용 중인 닉네임입니다.</div>
                                        )}
                                    </>
                                ) : (
                                    u.nickname
                                )}
                            </td>
                            <td>
                                {editId === u.id ? (
                                    <button
                                        className="btn btn-primary btn-sm me-2"
                                        onClick={() => onUpdateNickname(u.id)}
                                        disabled={isDuplicated}
                                    >
                                        수정 완료
                                    </button>
                                ) : (
                                    <button
                                        className="btn btn-primary btn-sm me-2"
                                        onClick={() => onEdit(u.id, u.nickname)}
                                    >
                                        닉네임 수정
                                    </button>
                                )}

                                <button className="btn btn-danger btn-sm" onClick={() => onDelete(u.id)}>삭제</button>
                            </td>
                        </tr>
                    ))}
                    {displayUsers.length === 0 && (
                        <tr>
                            <td colSpan="3" className="text-center">검색 결과가 없습니다.</td>
                        </tr>
                    )}
                </tbody>
            </table>
            {/*  Q1 로그아웃구현 */}
            <div className="mt-3">
                <button type="submit" className="btn btn-secondary" onClick={onLogout}>로그아웃</button>
            </div>
        </div>
    );
}