/** 
 * middlewares/isAuthenticated.js       로그인 인증 미들웨어   
 * -------------------------------------------------------
 * 로그인 후 인증된 사용자만 접근가능한 api 적용
 * 인증이 되지 않은경우 401응답 확인
 */   

function isAuthenticated(req, res, next) {
  if (req.isAuthenticated && req.isAuthenticated()) {  //인증성공
     //passport에 있는 메서드 - 세션이 있으면 true, 없으면 false
        return next();  // 인증이되면 다음단계로 
    }
    return res.status(401).json({ message: '로그인이 필요합니다.' });  //인증실패
}

module.exports = isAuthenticated;   //isAuthenticated
