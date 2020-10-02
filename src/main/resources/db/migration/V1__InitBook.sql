create table book
(
    id           bigint  not null
        constraint book_pkey
            primary key,
    author       varchar(100),
    description  text,
    image        varchar(255),
    isbn         varchar(20),
    print_year   integer,
    read_already boolean not null,
    title        varchar(100),
    date         date
);

create table book_vector
(
    id  integer
        constraint book_vector_book_id_fk
            references book,
    tsv tsvector
);


CREATE FUNCTION trg_tsv_trigger() RETURNS trigger AS
$$
BEGIN
    insert into book_vector (id, tsv) values (new.id, to_tsvector('english', COALESCE(NEW.description, '')));
    RETURN new;
END
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION trg_update_date() RETURNS TRIGGER AS
$BODY$
BEGIN
    IF OLD.read_already = false AND NEW.read_already = true THEN
        NEW.date := now();
    ELSIF OLD.read_already = true AND NEW.read_already = false THEN
        NEW.date := NULL;
    END IF;
    RETURN NEW;
END;
$BODY$
    LANGUAGE 'plpgsql';


CREATE TRIGGER trg_update_date
    BEFORE UPDATE
    ON book
    FOR EACH ROW
EXECUTE PROCEDURE trg_update_date();


CREATE TRIGGER trg_tsv_trigger
    AFTER INSERT OR UPDATE
    ON book
    FOR EACH ROW

EXECUTE PROCEDURE trg_tsv_trigger();

CREATE INDEX idx_gin_document
    ON book_vector
        USING gin ("tsv");




