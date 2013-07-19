delete from attributetype;
load data local infile 'C:\\Users\\Wuttke\\git\\nephro-registry-freiburg\\NephroRegistryFreiburg\\misc\\zystennieren-attributes.csv'
into table attributetype
character set 'latin1'
fields terminated by ';'
lines terminated by '\n'
ignore 1 lines;

Query OK, 79 rows affected, 410 warnings (0.01 sec)
Records: 79  Deleted: 0  Skipped: 0  Warnings: 410