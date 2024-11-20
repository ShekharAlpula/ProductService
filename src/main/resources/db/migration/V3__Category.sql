ALTER TABLE product
    ADD amt INT NULL;

ALTER TABLE product
    MODIFY amt INT NOT NULL;

ALTER TABLE st_user
    MODIFY avg_rating INT NOT NULL;

ALTER TABLE st_user
    MODIFY dtype VARCHAR (31) NULL;