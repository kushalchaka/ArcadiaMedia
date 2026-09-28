CREATE TABLE IF NOT EXISTS genres (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS media_items (
    id BIGSERIAL PRIMARY KEY,
    external_id VARCHAR(100) NOT NULL,
    external_source VARCHAR(30) NOT NULL,
    media_type VARCHAR(20) NOT NULL,
    title VARCHAR(300) NOT NULL,
    original_title VARCHAR(300),
    overview TEXT,
    release_date DATE,
    episodes INT,
    seasons INT,
    vote_average DECIMAL(3, 1),
    popularity DECIMAL(10, 3),
    status VARCHAR(30),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT uq_media_external_source UNIQUE (external_id, external_source)
);

CREATE TABLE IF NOT EXISTS media_genres (
    media_id BIGINT NOT NULL REFERENCES media_items(id) ON DELETE CASCADE,
    genre_id BIGINT NOT NULL REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (media_id, genre_id)
);

CREATE INDEX IF NOT EXISTS idx_media_type ON media_items(media_type);
CREATE INDEX IF NOT EXISTS idx_media_popularity ON media_items(popularity DESC);
CREATE INDEX IF NOT EXISTS idx_media_vote ON media_items(vote_average DESC);
