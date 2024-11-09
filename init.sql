-- Prüfen, ob die Datenbank existiert und erstellen, wenn sie nicht vorhanden ist
DO
$$
BEGIN
   -- Überprüfen, ob die Datenbank existiert
   IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'line_segments_db') THEN
      -- Wenn sie nicht existiert, erstellen
      PERFORM pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'line_segments_db';
      CREATE DATABASE line_segments_db;
   END IF;
END
$$;

-- Sicherstellen, dass die richtige Datenbank verwendet wird
\c line_segments_db;

-- Tabelle erstellen, wenn sie noch nicht existiert
CREATE TABLE IF NOT EXISTS line_segments (
    id BIGSERIAL PRIMARY KEY,
    x1 DOUBLE PRECISION NOT NULL,
    y1 DOUBLE PRECISION NOT NULL,
    x2 DOUBLE PRECISION NOT NULL,
    y2 DOUBLE PRECISION NOT NULL,
    length DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Optionale Indexierung zur Verbesserung der Performance bei Abfragen nach `length`
CREATE INDEX IF NOT EXISTS idx_line_segments_length ON line_segments(length);
