CREATE SEQUENCE board_seq;
CREATE TABLE board_t (
    id number primary KEY,
    btype varchar2(10) not null,
    author varchar2(20) not null,
    title varchar2(200) not null,
    context NCLOB not null,
    create_date date default sysdate,
    update_date date default sysdate,
    view_cnt number default 0,
    like_cnt number default 0,
    dislike_cnt number default 0
);

INSERT INTO board_t
    VALUES(board_seq.NEXTVAL, 'notice', 'admin', '게시판 테스트',
    '테스트용 게시글 입니다.', SYSDATE, SYSDATE, 0, 0, 0);

SELECT * FROM board_t;