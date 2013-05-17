LOAD DATA LOCAL INFILE 'C:\\Users\\Wuttke\\Desktop\\icd10code.csv' 
INTO TABLE icd10code 
CHARACTER SET 'latin1'
FIELDS TERMINATED BY ',' ENCLOSED BY '"' 
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(code, trimmedcode, @dummy, name);



export table abda_icdccobject data outstream 'icd10code.csv'
