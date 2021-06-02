/*
 * 테이블 생성에 문제 발생 시 테이블을 지우기 위해 미리 작성
 */
DROP TABLE account;
DROP SEQUENCE account_seq;

/*
 * 회원 정보 테이블 생성
 */
CREATE SEQUENCE account_seq NOCACHE;
CREATE TABLE account(
	id NUMBER,
	nickname NVARCHAR2(64),
	username NVARCHAR2(64),
	password NVARCHAR2(64),
	email NVARCHAR2(128),
	gender CHAR(1),
	age NUMBER,
	joindate DATE DEFAULT SYSDATE,
	logindate DATE DEFAULT SYSDATE,
	expiredate DATE DEFAULT NULL
);

ALTER TABLE account ADD CONSTRAINT account_id_PK PRIMARY KEY(id);
ALTER TABLE account MODIFY nickname CONSTRAINT account_nickname_NN NOT NULL;
ALTER TABLE account MODIFY username CONSTRAINT account_username_NN NOT NULL;
ALTER TABLE account MODIFY password CONSTRAINT account_password_NN NOT NULL;
ALTER TABLE account MODIFY email CONSTRAINT account_email_NN NOT NULL;
ALTER TABLE account ADD CONSTRAINT account_gender_CK CHECK(gender IN('m', 'w'));
ALTER TABLE account MODIFY age CONSTRAINT account_age_NN NOT NULL;

COMMENT ON COLUMN account.id IS '사용자 계정 식별 번호';
COMMENT ON COLUMN account.nickname IS '사용자 계정 닉네임';
COMMENT ON COLUMN account.username IS '사용자 이름';
COMMENT ON COLUMN account.password IS '사용자 로그인 암호';
COMMENT ON COLUMN account.email IS '사용자 이메일 주소(로그인 아이디로 사용)';
COMMENT ON COLUMN account.gender IS '사용자 성별';
COMMENT ON COLUMN account.age IS '사용자 나이';
COMMENT ON COLUMN account.joindate IS '사용자 가입일';
COMMENT ON COLUMN account.logindate IS '사용자 접속일';
COMMENT ON COLUMN account.expiredate IS '사용자 탈퇴일';

SELECT * FROM ACCOUNT;