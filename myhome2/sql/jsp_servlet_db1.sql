SELECT SYSDATE FROM DUAL;

-- 새로운 계정 생성 및 권한 부여
CREATE USER web_admin IDENTIFIED BY admin;
GRANT DBA, CONNECT TO web_admin;

-- 생성된 계정 확인
SELECT * FORM all_users;