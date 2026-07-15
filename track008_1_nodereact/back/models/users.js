// require
const dbConfig = require('../config/db'); // user, password, connectString
const oracledb = require('oracledb');
const bcrypt = require('bcrypt');

// oracle 초기화
oracledb.initOracleClient();
const options = {outFormat: oracledb.OUT_FORMAT_OBJECT, autoCommit: true};


// 각 기능 sql
// 1. create - insert
// insert into APPUSER (APP_USER_ID, EMAIL, PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT) 
// values (APPUSER_SEQ.NEXTVAL, :email, :password, :nickname, :mobile, :mbtiTypeId, :ufile, :createdAt)
async function createUser(email, password, nickname, mobile, mbtiTypeId, ufile) {
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const hashedPassword = await bcrypt.hash(password, 10);
        const result = await conn.execute(`
            INSERT INTO APPUSER (APP_USER_ID, EMAIL, PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT) 
            VALUES (APPUSER_SEQ.NEXTVAL, :email, :password, :nickname, :mobile, :mbtiTypeId, :ufile, SYSDATE)
            `, {email, password:hashedPassword, nickname, mobile, mbtiTypeId, ufile}, options); // sql, 사용자입력값, 옵션
    }catch(err){
        console.log(`createUser Error`, err);
    }finally {
        if(conn) await conn.close();
    }
}
// 2. 사용자 조회 - email
// SELECT APP_USER_ID, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT 
// FROM APPUSER 
// WHERE EMAIL = :email
async function findUserByEmail(email) {
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute(`
            SELECT APP_USER_ID, PASSWORD, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT 
            FROM APPUSER 
            WHERE EMAIL = :email
            `, {email}, options); // 실행
        return result.rows[0]; // 결과 처리
    }catch(err){
        console.log(`findUserByEmail Error`, err);
    }finally {
        if(conn) await conn.close();
    }
}
// 3. 사용자 조회 - id
async function findUserById(id) {
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute(`
            SELECT APP_USER_ID, EMAIL, PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT 
            FROM APPUSER 
            WHERE APP_USER_ID = :id
            `, {id}, options); // 실행
        return result.rows[0]; // 결과 처리
    }catch(err){
        console.log(`findUserById Error`, err);
    }finally {
        if(conn) await conn.close();
    }
}
// SELECT APP_USER_ID, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT 
// FROM APPUSER 
// WHERE APP_USER_ID = :id
// 4. 로그인 - pass / 로그아웃
async function verifyUser(email, password) {
    const user = await findUserByEmail(email);
    if(!user) return null;

    const match = await bcrypt.compare(password, user.PASSWORD);
    if(!user) return null;

    return {
        id: user.APP_USER_ID, 
        email: user.EMAIL,
        nickname: user.NICKNAME,
    }
}
// 5. 전체 조회
// SELECT APP_USER_ID, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT 
// FROM APPUSER 
// ORDER BY CREATED_AT DESC
async function getAllUsers() {
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute(`
            SELECT APP_USER_ID, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT 
            FROM APPUSER 
            ORDER BY CREATED_AT DESC
            `, {}, options); // 실행
        return result.rows; // 결과 처리
    }catch(err){
        console.log(`getAllUsers Error`, err);
    }finally {
        if(conn) await conn.close();
    }
}
// 6. 닉네임수정
// UPDATE APPUSER 
// SET NICKNAME = :nickname 
// WHERE APP_USER_ID = :id
async function updateUserNickname(nickname, id) {
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute(`
            UPDATE APPUSER 
            SET NICKNAME = :nickname 
            WHERE APP_USER_ID = :id
            `, {nickname, id}, options); // 실행
    }catch(err){
        console.log(`updateUserNickname Error`, err);
    }finally {
        if(conn) await conn.close();
    }
}
// 7. 사용자 삭제
// DELETE FROM APPUSER 
// WHERE APP_USER_ID = :id
async function deleteUser(id) {
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute(`
            DELETE FROM APPUSER 
            WHERE APP_USER_ID = :id
            `, {id}, options); // 실행
    }catch(err){
        console.log(`deleteUser Error`, err);
    }finally {
        if(conn) await conn.close();
    }
}
// 8. 닉네임조회
// SELECT APP_USER_ID, EMAIL, NICKNAME 
// FROM APPUSER 
// WHERE NICKNAME = :nickname
async function findUserByNickname(nickname) {
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute(`
            SELECT APP_USER_ID, EMAIL, NICKNAME 
            FROM APPUSER 
            WHERE NICKNAME = :nickname
            `, {nickname}, options); // 실행
        return result.rows[0]; // 결과 처리
    }catch(err){
        console.log(`findUserByNickname Error`, err);
    }finally {
        if(conn) await conn.close();
    }
}

// export
module.exports = {  createUser ,  findUserByEmail ,  findUserById, 
                    verifyUser , getAllUsers , updateUserNickname , deleteUser , findUserByNickname};