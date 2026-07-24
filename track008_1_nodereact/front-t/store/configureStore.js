/**
 * store/configureStore.js
 * ---------------------------------------
 * ✅  Redux Store 설정파일
 * - Redux Tookit의 configureStore 로 스토어 생성
 * - redux-saga 미들웨어 연결
 * - next-redux-wrapper 로 Next.js와 Redux 통합
 */

import { configureStore} from  '@reduxjs/toolkit';   // ✅ Redux Tookit 기본 스토어 생성함수
import  createSagaMiddleware from 'redux-saga';      // ✅ saga  미들웨어 생성 함수
import { createWrapper } from 'next-redux-wrapper';  // ✅ Next.js 와 Redux를 연결하는 wrapper
import reducer  from '../reducers';                  // ✅ 루트 리듀서 (reducers/index.js)
import rootSaga from '../sagas';                     // ✅ 루트 사가 (sagas/index.js)

// ✅ 스토어 생성 함수
export const  makeStore = ()=> {
   const sagaMiddleware =  createSagaMiddleware();   // ✅ saga 미들웨어 인스턴스 생성
   // ✅ Redux Tookit으로 스토어 생성
   const store = configureStore({
    reducer,                               // ✅ 루트 리듀서 연결         
    middleware: (getDefaultMiddleware) =>  
      getDefaultMiddleware({ 
        thunk: false,                    // ✅  thunk 비활성화 (saga 사용하므로 필요 없음)                       
        serializableCheck: false,        // ✅  직렬화검사 비활성화 (saga에서 처리가능)            
      }).concat(sagaMiddleware),         // ✅  saga 미들웨어 추가           
    devTools: process.env.NODE_ENV !== 'production',   // ✅  개발환경에서만  Redux Tookit 활성화
  });
   // ✅ saga 실행(rootSaga 연결)
   store.sagaTask = sagaMiddleware.run(rootSaga);
   return store;
};

// ✅ Next.js 와 Redux를 연결하는 wrapper 생성
export  const wrapper = createWrapper(  makeStore , {
    debug: process.env.NODE_ENV !== 'production'     // ✅ 개발 환경에서 디버그 모드 활성화
} );
