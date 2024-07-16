CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS vector_store (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    content text,
    metadata json,
    embedding vector(1536)
    );

CREATE TABLE IF NOT EXISTS ImageCaption (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name TEXT,
    caption TEXT,
    embedding vector(1536)
);
CREATE INDEX ON imagecaption USING HNSW (embedding vector_cosine_ops);
CREATE INDEX ON vector_store USING HNSW (embedding vector_cosine_ops);