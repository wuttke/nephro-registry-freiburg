DELETE FROM atccode;

LOAD DATA LOCAL INFILE 'C:\\Users\\Wuttke\\Desktop\\atccode.csv' 
INTO TABLE atccode 
CHARACTER SET 'latin1'
FIELDS TERMINATED BY ',' ENCLOSED BY '"' 
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(code, name, ddd);


export table abda_atc_med_object data outstream 'atccode.csv'