/*
 * 테이블 생성에 문제 발생 시 테이블을 지우기 위해 미리 작성
 */
DROP TABLE comments;
DROP SEQUENCE comments_seq;
DROP TABLE attach_files;
DROP SEQUENCE attach_files_seq;
DROP TABLE board;
DROP SEQUENCE board_seq;
DROP TABLE board_type;

/*
 * 게시판 구분(종류)용 테이블 생성
 */
CREATE TABLE board_type(
	id NUMBER,
	name NVARCHAR2(256)
);

ALTER TABLE board_type ADD CONSTRAINT board_type_id_PK PRIMARY KEY(id);
ALTER TABLE board_type MODIFY name CONSTRAINT board_type_name_NN NOT NULL;

COMMENT ON COLUMN board_type.id IS '게시판 구분 식별 번호';
COMMENT ON COLUMN board_type.name IS '게시판 종류(이름)';

INSERT INTO board_type VALUES(1, '자유 게시판');
INSERT INTO board_type VALUES(2, '유머 게시판');
INSERT INTO board_type VALUES(3, '정치/사회 게시판');

/*
 * 게시판 테이블 생성
 */
CREATE SEQUENCE board_seq NOCACHE;
CREATE TABLE board(
	id NUMBER,
	btype NUMBER,
	aid NUMBER,
	title NVARCHAR2(256),
	contents NCLOB,
	vcnt NUMBER DEFAULT 0,
	gcnt NUMBER DEFAULT 0,
	bcnt NUMBER DEFAULT 0,
	cdate DATE DEFAULT SYSDATE,
	udate DATE DEFAULT SYSDATE,
	nodel CHAR(1) DEFAULT 'n',
	deleted CHAR(1) DEFAULT 'n'
);

ALTER TABLE board ADD CONSTRAINT board_id_PK PRIMARY KEY(id);
ALTER TABLE board ADD CONSTRAINT board_btype_FK FOREIGN KEY(btype) REFERENCES board_type(id);
ALTER TABLE board ADD CONSTRAINT board_aid_FK FOREIGN KEY(aid) REFERENCES account(id);
ALTER TABLE board MODIFY title CONSTRAINT board_title_NN NOT NULL;
ALTER TABLE board ADD CONSTRAINT board_nodel_CK CHECK(nodel IN('n', 'y'));
ALTER TABLE board ADD CONSTRAINT board_deleted_CK CHECK(deleted IN('n', 'y'));

COMMENT ON COLUMN board.id IS '게시판 식별 번호';
COMMENT ON COLUMN board.btype IS '게시판 구분 식별 번호';
COMMENT ON COLUMN board.aid IS '게시판 작성자 식별 번호';
COMMENT ON COLUMN board.title IS '게시판 제목';
COMMENT ON COLUMN board.contents IS '게시판 내용';
COMMENT ON COLUMN board.vcnt IS '게시판 조회수';
COMMENT ON COLUMN board.gcnt IS '게시판 추천수';
COMMENT ON COLUMN board.bcnt IS '게시판 비추천수';
COMMENT ON COLUMN board.cdate IS '게시판 생성일';
COMMENT ON COLUMN board.udate IS '게시판 수정일';
COMMENT ON COLUMN board.nodel IS '게시판 삭제금지 구분(y 인 경우 작성자도 삭제 안됨)';
COMMENT ON COLUMN board.deleted IS '게시판 삭제 구분(y 인 경우 삭제된 것으로 간주)';

/*
 * 첨부파일용 테이블
 */
CREATE SEQUENCE attach_files_seq NOCACHE;
CREATE TABLE attach_files(
	id NUMBER,
	bid NUMBER,
	name NVARCHAR2(512),
	path NVARCHAR2(1024),
	url NVARCHAR2(1024)
);

ALTER TABLE attach_files ADD CONSTRAINT attach_files_id_PK PRIMARY KEY(id);
ALTER TABLE attach_files ADD CONSTRAINT attach_files_bid_FK FOREIGN KEY(bid) REFERENCES board(id);
ALTER TABLE attach_files MODIFY name CONSTRAINT attach_files_name_NN NOT NULL;
ALTER TABLE attach_files MODIFY path CONSTRAINT attach_files_path_NN NOT NULL;
ALTER TABLE attach_files MODIFY url CONSTRAINT attach_files_url_NN NOT NULL;

COMMENT ON COLUMN attach_files.id IS '첨부파일 식별 번호';
COMMENT ON COLUMN attach_files.bid IS '어떤 게시판의 첨부 파일인지 식별하기 위한 게시판 식별 번호';
COMMENT ON COLUMN attach_files.name IS '첨부파일의 파일명';
COMMENT ON COLUMN attach_files.path IS '첨부파일의 실제 저장 경로(위치)';
COMMENT ON COLUMN attach_files.url IS '첨부파일의 접근 URL 주소(경로)';

/*
 * 댓글용 테이블
 */
CREATE SEQUENCE comments_seq NOCACHE;
CREATE TABLE comments(
	id NUMBER,
	bid NUMBER,
	aid NUMBER,
	contents NVARCHAR2(1024),
	gcnt NUMBER DEFAULT 0,
	bcnt NUMBER DEFAULT 0,
	cdate DATE DEFAULT SYSDATE,
	udate DATE DEFAULT SYSDATE,
	deleted CHAR(1) DEFAULT 'n'
);

ALTER TABLE comments ADD CONSTRAINT comments_id_PK PRIMARY KEY(id);
ALTER TABLE comments ADD CONSTRAINT comments_bid_FK FOREIGN KEY(bid) REFERENCES board(id);
ALTER TABLE comments ADD CONSTRAINT comments_aid_FK FOREIGN KEY(aid) REFERENCES account(id);
ALTER TABLE comments MODIFY contents CONSTRAINT comments_contents_NN NOT NULL;
ALTER TABLE comments ADD CONSTRAINT comments_deleted_CK CHECK(deleted IN('n', 'y'));

COMMENT ON COLUMN comments.id IS '댓글 식별 번호';
COMMENT ON COLUMN comments.bid IS '어떤 게시글의 댓글인지 식별하기 위한 번호';
COMMENT ON COLUMN comments.aid IS '누가 작성한 댓글인지 식별하기 위한 번호';
COMMENT ON COLUMN comments.contents IS '댓글 내용';
COMMENT ON COLUMN comments.gcnt IS '댓글 추천수';
COMMENT ON COLUMN comments.bcnt IS '댓글 비추천수';
COMMENT ON COLUMN comments.cdate IS '댓글 작성일';
COMMENT ON COLUMN comments.udate IS '댓글 수정일';
COMMENT ON COLUMN comments.deleted IS '댓글 삭제 구분값(실제 삭제 처리는 하지 않고 값만 y로 변경)';

SELECT DBMS_LOB.SUBSTR(contents, DBMS_LOB.GETLENGTH(contents)) as contents FROM board;
SELECT DBMS_LOB.SUBSTR(contents, 3) as contents FROM board;
SELECT TO_CHAR(contents) as contents FROM board;


SELECT * FROM board;
SELECT * FROM comments;

UPDATE board
   SET deleted = 'y'
 WHERE id = 6
   AND nodel = 'n';
SELECT a.id,
	a.btype,
	b.name AS bname,
	a.aid,
	c.nickname AS aname,
	a.title,
	a.cdate,
	a.vcnt
  FROM board a
  JOIN board_type b
  	ON a.btype = b.id
  JOIN account c
  	ON a.aid = c.id;
  	
SELECT * FROM board_type;
SELECT * FROM account;

SELECT a.id
	 , a.bid
	 , a.aid
	 , (SELECT nickname FROM account WHERE id = a.aid) AS aname
	 , a.contents
	 , a.gcnt
	 , a.bcnt
	 , a.cdate
	 , a.udate
	 , a.deleted
 FROM comments a
WHERE bid = 6
ORDER BY a.id DESC;