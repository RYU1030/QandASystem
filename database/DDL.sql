DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS questions;

/* Drop Sequences */

DROP SEQUENCE IF EXISTS question_id_seq;

/* Create Sequences */

CREATE SEQUENCE question_id_seq;

/* Create Tables */

CREATE TABLE answers
(
	-- 質問ID
	question_id int NOT NULL UNIQUE,
	-- シーケンス
	seq int NOT NULL UNIQUE,
	-- ハンドルネーム
	handle_name text NOT NULL,
	-- コンテンツ
	contents text NOT NULL,
	-- 登録日時
	regist_timestamp timestamp with time zone NOT NULL,
	UNIQUE (question_id, seq)
) WITHOUT OIDS;

CREATE TABLE questions
(
	-- 質問ID
	question_id int NOT NULL,
	-- ハンドルネーム
	handle_name text NOT NULL,
	-- タイトル
	title text NOT NULL,
	-- コンテンツ
	contents text NOT NULL,
	-- 緊急度
	-- 1：急いでます
	-- 2：困ってます
	-- 3：いつでも
	urgency int NOT NULL,
	-- 編集・削除キー
	edit_delete_key text,
	-- 登録日時
	regist_timestamp timestamp with time zone NOT NULL,
	-- 更新日時
	update_timestamp timestamp with time zone,
	PRIMARY KEY (question_id)
) WITHOUT OIDS;

/* Create Foreign Keys */

ALTER TABLE answers
	ADD FOREIGN KEY (question_id)
	REFERENCES questions (question_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

/* Comments */

COMMENT ON COLUMN answers.question_id IS '質問ID';
COMMENT ON COLUMN answers.seq IS 'シーケンス';
COMMENT ON COLUMN answers.handle_name IS 'ハンドルネーム';
COMMENT ON COLUMN answers.contents IS 'コンテンツ';
COMMENT ON COLUMN answers.regist_timestamp IS '登録日時';
COMMENT ON COLUMN questions.question_id IS '質問ID';
COMMENT ON COLUMN questions.handle_name IS 'ハンドルネーム';
COMMENT ON COLUMN questions.title IS 'タイトル';
COMMENT ON COLUMN questions.contents IS 'コンテンツ';
COMMENT ON COLUMN questions.urgency IS '緊急度
1：急いでます
2：困ってます
3：いつでも';
COMMENT ON COLUMN questions.edit_delete_key IS '編集・削除キー';
COMMENT ON COLUMN questions.regist_timestamp IS '登録日時';
COMMENT ON COLUMN questions.update_timestamp IS '更新日時';
