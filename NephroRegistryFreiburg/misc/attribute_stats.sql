mysql> select attributetype_id, attributetype.label as attributetype_name, count(*) 
from subjectattribute, attributetype 
where attributetype.id = subjectattribute.attributetype_id 
group by attributetype_id, attributetype_name 

into outfile 'C:\\Temp\\attributes.csv' 
fields terminated by ';' 
enclosed by '"' 
lines terminated by '\n';


select attributetype_id, attributetype.label as attributetype_name, count(*)
from subjectattribute, attributetype 
where attributetype.id = subjectattribute.attributetype_id and 
attributevalue = 'TRUE' and attributetype.datatype = 'TRISTATE_BOOLEAN' 
group by attributetype_id, attributetype_name
into outfile 'C:\\Temp\\attributes_true.csv'
fields terminated by ';'
enclosed by '"'
lines terminated by '\n';

select attributetype_id, attributetype.label as attributetype_name, count(*)
from subjectattribute, attributetype 
where attributetype.id = subjectattribute.attributetype_id and 
attributevalue = 'FALSE' and attributetype.datatype = 'TRISTATE_BOOLEAN' 
group by attributetype_id, attributetype_name
into outfile 'C:\\Temp\\attributes_false.csv'
fields terminated by ';'
enclosed by '"'
lines terminated by '\n';