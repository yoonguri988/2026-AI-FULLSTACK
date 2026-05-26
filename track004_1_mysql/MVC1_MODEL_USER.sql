-- mysql>
-- mysql> desc users;
-- +----------+--------------+------+-----+-------------------+-------------------+
-- | Field    | Type         | Null | Key | Default           | Extra             |
-- +----------+--------------+------+-----+-------------------+-------------------+
-- | uno      | int          | NO   | PRI | NULL              | auto_increment    |
-- | nickname | varchar(20)  | NO   |     | NULL              |                   |
-- | bpass    | varchar(50)  | NO   |     | NULL              |                   |
-- | email    | varchar(100) | NO   |     | NULL              |                   |
-- | mobile   | varchar(50)  | NO   |     | NULL              |                   |
-- | udate    | timestamp    | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
-- | bip      | varchar(50)  | NO   |     | NULL              |                   |
-- +----------+--------------+------+-----+-------------------+-------------------+
-- 7 rows in set (0.00 sec)

CREATE TABLE USERS (
 UNO INT AUTO_INCREMENT PRIMARY KEY,
 NICKNAME VARCHAR(20) NOT NULL,
 BPASS VARCHAR(20) NOT NULL,
 EMAIL VARCHAR(20) NOT NULL,
 MOBILE VARCHAR(20) NOT NULL,
 UDATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 BIP VARCHAR(50) NOT NULL 
);

DESC USERS;

SELECT * FROM USERS;

INSERT INTO USERS (NICKNAME, BPASS, EMAIL, MOBILE, BIP) VALUES ('관리자', '1234', 'admin@example.com', '01012341234', '168.192.0.4');


SELECT * FROM USERS;

SELECT * FROM USERs WHERE NICKNAME='second';