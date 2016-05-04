ALTER TABLE DuckDb.`opportunity`
  ADD `payment_method` varchar(45) NOT NULL;

UPDATE DuckDb.`opportunity`
SET payment_method="Cash";