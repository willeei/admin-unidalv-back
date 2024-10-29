CREATE TABLE presences (
    id CHAR(32) NOT NULL PRIMARY KEY,
    day CHAR(2) NOT NULL,
    month VARCHAR(15) NOT NULL,
    year VARCHAR(6) NOT NULL,
    week_year VARCHAR(4) NOT NULL,
    week_month VARCHAR(4) NOT NULL,
    type VARCHAR(15) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    teen_id CHAR(32) NOT NULL,
    service_id CHAR(32) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    deleted_at DATETIME(6) NULL,
    CONSTRAINT fk_teen_id FOREIGN KEY (teen_id) REFERENCES teens (id) ON DELETE CASCADE,
    CONSTRAINT fk_service_id FOREIGN KEY (worship_id) REFERENCES services (id) ON DELETE CASCADE
);