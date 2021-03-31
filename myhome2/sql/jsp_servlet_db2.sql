CREATE TABLE member_t (
    userid varchar2(20) primary key,
    password varchar2(25),
    email varchar2(100),
    joindate date default sysdate
);
commit;
SELECT * FROM member_t;
INSERT INTO member_t (userid, password, email)
    VALUES ('admin', 'admin', 'admin@admin.com');
INSERT INTO member_t (userid, password, email)
    VALUES ('user1', 'user1', 'user1@domain.com');
commit;

CREATE SEQUENCE visit_seq;
CREATE TABLE visit_t (
    id number primary key,
    author varchar2(20) not null,
    context varchar2(2000) not null,
    create_date date default sysdate
);
INSERT INTO visit_t VALUES(visit_seq.NEXTVAL, 'admin', '환영합니다.', SYSDATE);
INSERT INTO visit_t VALUES(visit_seq.NEXTVAL, 'admin', '방명록 테스트', SYSDATE);
INSERT INTO visit_t VALUES(visit_seq.NEXTVAL, 'user1', '반갑습니다.', SYSDATE);
commit;