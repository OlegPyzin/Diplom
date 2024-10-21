-- При создании таблиц были допущены опечатки
-- исправим
-- ==============================================================================
alter table if exists buses.drivers rename column date_modifyed to date_modified;

alter table if exists buses.buses rename column date_modifyed to date_modified;

alter table if exists financial.ways rename column date_modifyed to date_modified;

alter table if exists financial.description_ways rename column date_modifyed to date_modified;
-- ==============================================================================
-- После исправления поправим исходный файл createtables.sql
