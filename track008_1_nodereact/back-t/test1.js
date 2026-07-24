const  { createUser,  findUserByEmail,  verifyUser, 
     getAllUsers,  updateUserNickname,   deleteUser,  findUserById , findUserByNickname
} = require('./models/users');

async function runTests(){
   try{
    //1. 회원가입
    await  createUser( 't@t',  't',  'tt',  '0101111111',  1,  '1.png');  
    console.log('✅ createUser 성공');

    //2. 이메일로 조회
    const userByEmail =  await  findUserByEmail( 't@t'); 


    
    console.log('✅ findUserByEmail 결과: ', userByEmail);

    //3. 로그인검증 (비밀번호 맞는 경우)
    const loginUser =  await  verifyUser( 't@t' , 't');  
    console.log('✅ verifyUser 결과(비밀번호 맞는 경우): ', loginUser);

    //4. 로그인검증 (비밀번호 틀린 경우)
    const failLogin =  await  verifyUser( 't@t' , 'wrong');  
    console.log('✅ verifyUser 결과(비밀번호 틀린 경우): ', failLogin);
    
    //5. 전체조회   getAllUsers
    const allUsers = await  getAllUsers();  
    console.log('✅ getAllUsers 결과:' ,allUsers );
    
    //6. 닉네임수정 updateUserNickname 
    await  updateUserNickname(userByEmail.APP_USER_ID  ,'AA');  
    const updateUser = await findUserById(userByEmail.APP_USER_ID);
    console.log('✅ updateUserNickname 결과:' ,updateUser );

    //7. 삭제      deleteUser 
    await  deleteUser(userByEmail.APP_USER_ID );  
    console.log('✅ deleteUser 성공');
    const deletedUser = await findUserById(userByEmail.APP_USER_ID);
    console.log('✅ findUserById (삭제 후)' , deletedUser);

   }catch(err){  console.error('❌ 테스트 중 오류 발생:' , err); }

    //////////////////////////////////////////////////////////////////////////////////////////
    // 닉네임 중복 테스트 추가
    // 1. 이미 'tt'라는 닉네임을 가진 사용자가 있다고 가정 (회원가입 직후)
    await createUser('test2@test.com', 'pw', 'tt', '0101111111', 1, '1.png');
    console.log('✅ createUser(tt) 성공');

    // 2. 닉네임 중복 검사 실행
    const duplicateNickname = await findUserByNickname('tt');
    if (duplicateNickname) {
        console.log('✅ findUserByNickname 결과: 닉네임 중복 확인됨 (값 있음)');
    } else {
        console.log('❌ findUserByNickname 결과: 닉네임이 없다고 나옴');
    }

    // 3. 존재하지 않는 닉네임 검사
    const newNickname = await findUserByNickname('uniqueName123');
    if (!newNickname) {
        console.log('✅ findUserByNickname 결과: 닉네임 사용 가능 (값 없음)');
    }

}
runTests();

//  node   test1.js
