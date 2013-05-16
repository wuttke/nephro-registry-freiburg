create table icd10 (
	icd10 varchar(8) primary key not null,
	trimmedcode varchar(8) not null,
	category varchar(8) not null,
	label varchar(400) not null
);