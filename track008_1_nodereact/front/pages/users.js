import { useSelector, useDispatch } from 'react-redux'; // 전역상태, 상태알림
import { useState, useEffect } from 'react'; // 변수 상태 변경, 이벤트 변경
import { useRouter } from 'next/router'; // 경로
import { DELETE_USER_REQUEST, LOAD_USER_REQUEST, LOAD_USER_SUCCESS, LOG_OUT_REQUEST, UPDATE_NICKNAME_REQUEST } from '../reducers/user';

export default function UsersPage() {
    const dispatch = useDispatch();
    const router = useRouter();

    // reducer에서 필요한 상태 가져오기
    const {me, isLoading, error, users} = useSelector((state)=> state.user);

    // 닉네임 수정 상태관리
    const [editId, setEditId] = useState(null);
    const [newNickname, setNewNickname] = useState('');

    const onLogout = () => {
        dispatch({type: LOG_OUT_REQUEST});
    }
    
    const onEdit = (id) => setEditId(id);
    const onUpdateNickname = (id) => {
        dispatch({type: UPDATE_NICKNAME_REQUEST, data: {id, nickname: newNickname}});
        setEditId(null);
        setNewNickname('');
    }
    
    const onDelete = (id) => {
        dispatch({type: DELETE_USER_REQUEST, data: {id}});
    }

    // 로그인 여부 체크 및 사용자 목록 불러오기
    useEffect(() => {
        if(!me){
            router.push('/login');
        } else {
            dispatch({ type: LOAD_USER_REQUEST});
        }
    }, [me, router]);    

    // 로그아웃 -> me 가 null 되면 로그인 페이지로 이동
    useEffect(() => {
        if(me === null){
            router.push('/login');
        } 
    }, [me, router]); 
    
    
    return (
        <div className="container mb-3">
            <h3 className="mb-3">사용자 목록</h3>
            {/* 로딩/에러 상태 표시 */}
            {isLoading && <div className="alert alert-info">로딩중...</div>}
            {error && <div className="alert alert-danger">{error}</div>}
            {/* 사용자 목록 테이블 */}
            <div className="mb-3">
                <table className="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">이메일</th>
                        <th scope="col">닉네임</th>
                        <th scope="col">UPDATE/DELETE</th>
                    </tr>
                    </thead>
                    <tbody>
                        {users.map((user) => (
                            <tr key={user.id}>
                            <td>{user.email}</td>
                            <td>{editId === user.id
                                    ? <input className='form-control' value={newNickname === '' ? user.nickname : newNickname} onChange={(e)=>setNewNickname(e.target.value)} placeholder='새닉네임입력'/>
                                    : user.nickname
                                }</td>
                            <td>
                                {editId === user.id
                                    ? <button className="btn btn-primary me-3" onClick={()=>onUpdateNickname(user.id)}>수정 완료</button>
                                    : <button className="btn btn-primary me-3" onClick={()=>onEdit(user.id)}>수정</button>
                                }
                                
                                <button className="btn btn-danger" onClick={()=>onDelete(user.id)}>삭제</button>
                            </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            {/* 로그아웃 버튼 */}
            {me && (<div className="mb-3 text-start">
                <button type="submit" className="btn btn-secondary" onClick={onLogout}>로그아웃</button>
            </div>)}
        </div>
    );
}