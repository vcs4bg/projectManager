@echo off

chcp 65001 > nul
set "PGCLIENTENCODING=UTF8"

set "SCRIPT_DIR=%~dp0"
set "DDL_DIR=%SCRIPT_DIR%ddl"
set "DML_DIR=%SCRIPT_DIR%dml"

set "PGPASSWORD=postgres"
psql -h localhost -p 5432 -U postgres -d postgres -c "DROP DATABASE project_manager;"
psql -h localhost -p 5432 -U postgres -d postgres -c "DROP ROLE IF EXISTS app_user;"
psql -h localhost -p 5432 -U postgres -d postgres -c "DROP ROLE IF EXISTS admin_user;"

psql -h localhost -p 5432 -U postgres -d postgres -c "CREATE ROLE admin_user WITH LOGIN PASSWORD 'admin_user';"
psql -h localhost -p 5432 -U postgres -d postgres -c "CREATE ROLE app_user WITH LOGIN PASSWORD 'app_user';"

psql -h localhost -p 5432 -U postgres -d postgres -c "CREATE DATABASE project_manager OWNER admin_user;"

psql -h localhost -p 5432 -U postgres -d project_manager -c "CREATE SCHEMA project_manager AUTHORIZATION admin_user;"
REM app_user と admin_user が project_manager スキーマを利用できるようにする
psql -h localhost -p 5432 -U postgres -d project_manager -c "GRANT USAGE ON SCHEMA project_manager TO app_user;"
psql -h localhost -p 5432 -U postgres -d project_manager -c "GRANT USAGE ON SCHEMA project_manager TO admin_user;"
REM app_user と admin_user の search_path を project_manager に設定する
psql -h localhost -p 5432 -U postgres -d project_manager -c "ALTER ROLE app_user SET search_path TO project_manager;"
psql -h localhost -p 5432 -U postgres -d project_manager -c "ALTER ROLE admin_user SET search_path TO project_manager;"
REM 今後 admin_user が作るテーブルに対して app_user へ CRUD 権限を自動付与する
psql -h localhost -p 5432 -U postgres -d project_manager -c "ALTER DEFAULT PRIVILEGES FOR ROLE admin_user IN SCHEMA project_manager GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO app_user;"
REM 今後 admin_user が作るシーケンスに対して app_user へ利用権限を自動付与する
psql -h localhost -p 5432 -U postgres -d project_manager -c "ALTER DEFAULT PRIVILEGES FOR ROLE admin_user IN SCHEMA project_manager GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO app_user;"

set "PGPASSWORD=admin_user"

if exist "%DDL_DIR%\table\*.sql" (
	for %%F in ("%DDL_DIR%\table\*.sql") do (
		echo Running %%~nxF
		psql -h localhost -p 5432 -U admin_user -d project_manager -v ON_ERROR_STOP=1 -f "%%~fF"
	)
)

if exist "%DDL_DIR%\index\*.sql" (
	for %%F in ("%DDL_DIR%\index\*.sql") do (
		echo Running %%~nxF
		psql -h localhost -p 5432 -U admin_user -d project_manager -v ON_ERROR_STOP=1 -f "%%~fF"
	)
)

if exist "%DDL_DIR%\sequence\*.sql" (
	for %%F in ("%DDL_DIR%\sequence\*.sql") do (
		echo Running %%~nxF
		psql -h localhost -p 5432 -U admin_user -d project_manager -v ON_ERROR_STOP=1 -f "%%~fF"
	)
)

if exist "%DDL_DIR%\view\*.sql" (
	for %%F in ("%DDL_DIR%\view\*.sql") do (
		echo Running %%~nxF
		psql -h localhost -p 5432 -U admin_user -d project_manager -v ON_ERROR_STOP=1 -f "%%~fF"
	)
)

REM 既存テーブルに対して app_user へ CRUD 権限を付与する
psql -h localhost -p 5432 -U admin_user -d project_manager -c "GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA project_manager TO app_user;"
REM 既存シーケンスに対して app_user へ利用権限を付与する
psql -h localhost -p 5432 -U admin_user -d project_manager -c "GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA project_manager TO app_user;"

set "PGPASSWORD=admin_user"

if exist "%DML_DIR%\*.csv" (
	for %%F in ("%DML_DIR%\*.csv") do (
		echo Loading %%~nxF
		psql -h localhost -p 5432 -U admin_user -d project_manager -v ON_ERROR_STOP=1 -c "\copy %%~nF FROM '%%~fF' WITH (FORMAT csv, HEADER true)"
	)
)
