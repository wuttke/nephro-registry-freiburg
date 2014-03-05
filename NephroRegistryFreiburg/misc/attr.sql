delete from subjectattribute;
delete from subjectcategory;
delete from subject;
delete from attributetype;

load data local infile 'C:\\Users\\Wuttke\\git\\nephro-registry-freiburg\\NephroRegistryFreiburg\\misc\\zystennieren-attributes.csv'
into table attributetype
character set 'latin1'
fields terminated by ';'
lines terminated by '\n'
ignore 1 lines;


load data local infile '/home/wuttke/git/nephro-registry-freiburg/NephroRegistryFreiburg/misc/zystennieren-attributes.csv'
into table attributetype
character set 'latin1'
fields terminated by ';'
lines terminated by '\n'
ignore 1 lines;
