//pages/_app.js
import 'bootstrap/dist/css/bootstrap.min.css';  // Bootstrap CSS
import Layout  from '../components/Layout';    // 공용 레이아웃 컴포넌트
import '../styles/globals.css';                // 글로벌 CSS
import { wrapper }  from '../store/configureStore'; // Redux store연결

function MyApp( { Component   , pageProps  }){  
        //현재 렌더링할 컴포넌트 , 해당페이지에 전달되는 초기 props
    return (
        <Layout>
            {/* 각 페이지 컴포넌트 */}
            <Component {...pageProps}/>
        </Layout>
    );
} 
// next-redux-wrapper로 Redux  store연결
export default  wrapper.withRedux( MyApp );
