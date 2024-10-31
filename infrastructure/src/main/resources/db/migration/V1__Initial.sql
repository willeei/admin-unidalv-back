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
    CONSTRAINT fk_service_id FOREIGN KEY (service_id) REFERENCES services (id) ON DELETE CASCADE
);
CREATE TABLE services (
    id CHAR(32) NOT NULL,
    name VARCHAR(255) NOT NULL,
    point INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    deleted_at DATETIME(6) NULL,
    PRIMARY KEY (id)
);
CREATE TABLE teens (
    id CHAR(32) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_date VARCHAR(12) NOT NULL,
    member BOOLEAN NOT NULL,
    active BOOLEAN NOT NULL,
    discipleship BOOLEAN NOT NULL,
    phone VARCHAR(12) NOT NULL,
    guardian_phone VARCHAR(12) NOT NULL,
    guardian_name VARCHAR(255) NOT NULL,
    gender VARCHAR(7) NOT NULL,
    enrollment_date DATETIME(6) NOT NULL,
    re_enrollment_date DATETIME(6) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    deleted_at DATETIME(6) NULL
);