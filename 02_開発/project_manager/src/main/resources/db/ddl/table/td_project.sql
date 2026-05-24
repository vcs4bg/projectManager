CREATE TABLE IF NOT EXISTS td_project (
	project_id BIGINT PRIMARY KEY,
	project_code VARCHAR(10) NOT NULL,
	project_name VARCHAR(200) NOT NULL,
	client_id BIGINT NOT NULL,
	order_date TIMESTAMP NOT NULL,
	delivery_date TIMESTAMP,
	description TEXT,
	create_date TIMESTAMP NOT NULL,
	created_by BIGINT NOT NULL,
	update_date TIMESTAMP NOT NULL,
	updated_by BIGINT NOT NULL
);

COMMENT ON TABLE td_project IS 'プロジェクト';
COMMENT ON COLUMN td_project.project_id IS 'プロジェクトID';
COMMENT ON COLUMN td_project.project_code IS 'プロジェクトコード';
COMMENT ON COLUMN td_project.project_name IS 'プロジェクト名';
COMMENT ON COLUMN td_project.client_id IS '顧客ID';
COMMENT ON COLUMN td_project.order_date IS '受注日';
COMMENT ON COLUMN td_project.delivery_date IS '納品日';
COMMENT ON COLUMN td_project.description IS '概要';
COMMENT ON COLUMN td_project.create_date IS '登録日時';
COMMENT ON COLUMN td_project.created_by IS '登録ユーザー';
COMMENT ON COLUMN td_project.update_date IS '更新日';
COMMENT ON COLUMN td_project.updated_by IS '更新ユーザー';
