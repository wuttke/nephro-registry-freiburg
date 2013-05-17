DELETE FROM medicament;
LOAD DATA LOCAL INFILE 'C:\\Users\\Wuttke\\Desktop\\medicament.csv' 
INTO TABLE medicament 
CHARACTER SET 'latin1'
FIELDS TERMINATED BY ';' 
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(mid, atccode, offerer, pharmaceuticalform, tradename);



select mid, atc, offerer, pharmaceutical_form, tradename 
from medicament 
where recent = true and active = true and source = 'ABDATA'
order by mid
