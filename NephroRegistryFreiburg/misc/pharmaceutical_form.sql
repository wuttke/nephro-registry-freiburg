export table abda_daf_med_object data outstream 'pf.csv'

LOAD DATA LOCAL INFILE 'C:\\Users\\Wuttke\\Desktop\\pf.csv' 
INTO TABLE pharmaceuticalform 
CHARACTER SET 'latin1'
FIELDS TERMINATED BY ',' ENCLOSED BY '"' 
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(shortcut, name);
