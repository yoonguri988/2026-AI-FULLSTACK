// sagas/postSaga.js
import {all, call, put, takeLatest} from 'redux-saga/effects';
import axios from 'axios';
import {} from '../reducers/postReducer';

export default function *postSaga() {
    yield all();
}