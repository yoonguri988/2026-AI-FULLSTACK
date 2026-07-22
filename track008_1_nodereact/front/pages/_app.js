// pages/_app.js
import 'bootstrap/dist/css/bootstrap.min.css';   // bootstrap css
import Layout  from '../components/Layout';      // 공통 레이아웃 컴포넌트
import '../styles/globals.css';                  // 글로벌 css
import {wrapper} from '../store/configureStore'; // store 연결 추가 ## 추가
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { LOAD_MY_INFO_REQUEST } from '../reducers/user';

function MyApp({Component, pageProps}) { 
    // 현재 렌더링할 컴포넌트, 해당 페이지에 전달되는 초기 props

    // const dispatch = useDispatch();
    // useEffect(() => {
    //     dispatch({ type: LOAD_MY_INFO_REQUEST });
    // }, []);

    return (
        <Layout>
            {/* 각 페이지 컴포넌트 */}
            <Component {...pageProps} />
        </Layout>
    );
}

export default wrapper.withRedux( MyApp ); // next-redux-wrapper store 연결 ## 추가