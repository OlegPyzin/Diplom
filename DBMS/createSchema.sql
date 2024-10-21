-- To create schemas
-- launch:
-- psql -h macppc minibuses oleg -f createSchema.sql
--
create schema if not exists "administrators"
    authorization oleg;

create schema if not exists "buses"
    authorization oleg;

create schema if not exists "financial"
    authorization oleg;

create schema if not exists "passengers"
    authorization oleg;
