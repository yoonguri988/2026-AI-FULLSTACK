const dbConfig = require('../config/db');  //user, password, connectString
const oracledb = require('oracledb'); // oracledb
const bcrypt   = require('bcrypt'); // 암호화

// Oracle Instant Client 초기화 (환경에 맞게 경로 수정)
//oracledb.initOracleClient({ libDir: "C:\\oracle\\instantclient_11_2" });
oracledb.initOracleClient();

// 공통옵션 : 결과를 객체 형태로 변환, 자동커밋 
const options = { outFormat: oracledb.OUT_FORMAT_OBJECT , autoCommit:true };


// 회원가입 (Create)
// INSERT INTO APPUSER
// ( APP_USER_ID, EMAIL, PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT) 
// VALUES
// ( APPUSER_SEQ.NEXTVAL, :email, :password, :nickname, :mobile, :mbtiTypeId, :ufile, SYSDATE)
async function createUser(email,  password,  nickname,  mobile,  mbtiTypeId,  ufile){
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const  hashedPassword = await  bcrypt.hash(password, 10);
        const result = await conn.execute( 
            `INSERT INTO APPUSER 
            ( APP_USER_ID, EMAIL, PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT) 
            VALUES  
            ( APPUSER_SEQ.NEXTVAL, :email, :password, :nickname, :mobile, :mbtiTypeId, :ufile, SYSDATE)` , 
            {email,  password: hashedPassword,  nickname,  mobile,  mbtiTypeId,  ufile} , 
            options  ); //실행
        //결과처리
    }catch(err){
        console.log('createUser  Error' , err);
        throw err;
    }finally{
        if(conn)  await conn.close();
    } 
}


// 사용자조회-Email
// SELECT APP_USER_ID, EMAIL,PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, CREATED_AT 
// FROM APPUSER  WHERE EMAIL= :email
async function findUserByEmail(email){
        let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute( 
            `SELECT APP_USER_ID, EMAIL,PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, CREATED_AT 
            FROM APPUSER  WHERE EMAIL= :email` , 
            {email} , 
            options  
        ); //실행
        //결과처리
        return result.rows[0];
    }catch(err){
        console.log('findUserByEmail  Error' , err);
        throw err;
    }finally{
        if(conn)  await conn.close();
    } 
}

// 사용자조회-id
// SELECT APP_USER_ID, EMAIL, PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, CREATED_AT 
// FROM APPUSER  WHERE APP_USER_ID= :id
async function findUserById(id){
        let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute( 
            `SELECT APP_USER_ID, EMAIL, PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT
             FROM APPUSER WHERE APP_USER_ID = :id` , 
             {id} , 
             options  ); //실행
        //결과처리
        return result.rows[0];
    }catch(err){
        console.log('findUserById  Error' , err);
        throw err;
    }finally{
        if(conn)  await conn.close();
    } 
}

// 로그인 (비밀번호 체크)
async function verifyUser(email , password){
    const user = await  findUserByEmail(email);
    if(!user) return null;

    const match = await  bcrypt.compare(password , user.PASSWORD);
    if(!match) return null;

    return {
        id: user.APP_USER_ID,
        email: user.EMAIL,
        nickname: user.NICKNAME,
    }
}

// 로그아웃은 db에서 처리할게 X → 라우터에서 req.session.destroy()로 세션삭제

// 전체사용자조회(테스트용)
// SELECT APP_USER_ID, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, CREATED_AT FROM APPUSER
async function getAllUsers(){
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute( 
            `SELECT APP_USER_ID, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, CREATED_AT FROM APPUSER` , 
            {} , 
            options  ); //실행
        //결과처리
        return result.rows; 
    }catch(err){
        console.log('getAllUsers  Error' , err);
        throw err;
    }finally{
        if(conn)  await conn.close();
    }  
}

// 사용자 닉네임 수정 (Update)
// UPDATE APPUSER SET NICKNAME= :nickname WHERE APP_USER_ID= :id
async function updateUserNickname(id , nickname){
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute( 
            `UPDATE APPUSER SET NICKNAME = :nickname WHERE APP_USER_ID = :id` , 
            {nickname , id} , 
            options  ); //실행
        //결과처리
    }catch(err){
        console.log('updateUserNickname  Error' , err);
        throw err;
    }finally{
        if(conn)  await conn.close();
    } 

}

// 사용자 삭제 (Delete)
// DELETE FROM APPUSER WHERE APP_USER_ID = :id
async function deleteUser(id){
    let conn;
    try{
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute( 
            `DELETE FROM APPUSER WHERE APP_USER_ID = :id` , 
            { id } , 
            options  
        ); //실행
        //결과처리
    }catch(err){
        console.log('deleteUser  Error' , err);
        throw err;
    }finally{
        if(conn)  await conn.close();
    } 
}

/////////////////////////////////////////////  추가
// 닉네임으로 사용자 조회 (중복 검사 용도)
async function findUserByNickname(nickname) {
    let conn;
    try {
        conn = await oracledb.getConnection(dbConfig);
        const result = await conn.execute(
            `SELECT APP_USER_ID, NICKNAME FROM APPUSER WHERE NICKNAME = :nickname`,
            { nickname },
            options
        );
        return result.rows[0]; // 중복이 있다면 해당 객체 반환, 없으면 undefined
    } catch (err) {
        console.log('findUserByNickname Error', err);
        throw err;
    } finally {
        if (conn) await conn.close();
    }
}

 
// 닉네임 일부분(포함) 검색 함수 추가
async function findUsersByNicknameKeyword(keyword) {
    let conn;
    try {
        conn = await oracledb.getConnection(dbConfig);
        // ORACLE LIKE 검색 패턴: %키워드%
        const result = await conn.execute(
            `SELECT APP_USER_ID, NICKNAME , EMAIL FROM APPUSER WHERE NICKNAME LIKE '%' || :keyword || '%'`,
            { keyword },
            options
        );
        return result.rows; // 여러 명이 검색될 수 있으므로 rows 배열 반환
    } catch (err) {
        console.log('findUsersByNicknameKeyword Error', err);
        throw err;
    } finally {
        if (conn) await conn.close();
    }
}

module.exports = {
    createUser,
    findUserByEmail,
    findUserByNickname,  //############ 추가
    findUsersByNicknameKeyword,
    verifyUser,
    getAllUsers,
    updateUserNickname,
    deleteUser,
    findUserById
    
};