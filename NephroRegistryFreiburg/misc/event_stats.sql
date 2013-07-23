-- Events nach Typ mit/ohne Datum
select 
 type_id,
count(eventstartdate_date) as date_present_count, 
 count(*) as event_count
from event
group by type_id;


-- Nephrektomie kombiniert mit Datum
select 
 count(distinct subject_id) as event_count
from event
where (type_id = 3 or type_id=4) and not (eventstartdate_date is null);

-- Nephrektomie kombiniert ohne Datum
select 
 count(distinct subject_id) as event_count
from event
where (type_id = 3 or type_id=4);

-- Tod

select count(*) from subject where not (dateofdeath_date is null);
select count(*) from subject where death = TRUE;