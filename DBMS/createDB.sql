-- To create database to use inside Diplom task
-- Launch at the command line:
-- psql -h macppc.local -U postgres -f createDB.sql
-- ================================================
-- after created DB "minibuses" can connnect to the database as user (role) oleg
--
create database "minibuses"
    with
    owner = oleg
    encoding = 'UTF8'
--    LOCALE_PROVIDER = 'icu'
--    LC_COLLATE = 'Russian_Russia.1251'
--    LC_TYPE = 'UTF-8'
    tablespace = pg_default
    connection limit = 10
    is_template = false;