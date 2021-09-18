create table document
(
    id uuid not null
        constraint document_pk
            primary key,
    document_data varchar
);
