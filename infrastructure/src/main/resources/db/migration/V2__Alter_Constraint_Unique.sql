ALTER TABLE presences
ADD CONSTRAINT unique_day_month_year_teen_service UNIQUE (day, month, year, teen_id, service_id);