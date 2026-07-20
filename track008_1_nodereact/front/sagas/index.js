/**
 * saga/index.js
 * ------------------------------------------
 * 루트 사가(rootSaga) 설정파일
 * - 모든 개별 saga(userSaga 등)를 합쳐서 실행
 * - Redux-Saga의 all, fork를 사용해서 병렬 실행
 */

import {all, fork} from 'redux-saga/effects'; // 여러 saga를 동시에 실행하기 위해 함수
import userSaga from './user';

export default function* rootSaga(){
    yield all([
        fork(userSaga), //비동기 처리 - userSaga를 병렬실행
        //fork(postSaga), 예시
    ]); // 다 반환해 주세요
}