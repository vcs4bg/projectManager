CREATE SEQUENCE IF NOT EXISTS sq_project_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE sq_project_id OWNED BY td_project.project_id;

ALTER TABLE td_project
    ALTER COLUMN project_id SET DEFAULT nextval('sq_project_id');

COMMENT ON SEQUENCE sq_project_id IS 'プロジェクトID採番シーケンス';
