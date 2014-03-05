    create table AttachmentType (
        id bigint not null auto_increment,
        label varchar(200),
        shortcut varchar(128),
        version integer,
        primary key (id)
    ) ENGINE=InnoDB;
    
    create table AttachmentType_AUD (
        id bigint not null,
        REV integer not null,
        revtype tinyint,
        label varchar(200),
        shortcut varchar(128),
        primary key (id, REV)
    ) ENGINE=InnoDB;
    
    create table BinaryAttachment (
        id bigint not null auto_increment,
        attachmentdate_date date,
        attachmentdate_precision integer,
        description varchar(2000),
        label varchar(200),
        version integer,
        attachmenttype_id bigint,
        subject_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;
    
    
    create table BinaryAttachment_AUD (
        id bigint not null,
        REV integer not null,
        revtype tinyint,
        attachmentdate_date date,
        attachmentdate_precision integer,
        description varchar(2000),
        label varchar(200),
        attachmenttype_id bigint,
        subject_id bigint,
        primary key (id, REV)
    ) ENGINE=InnoDB;
    
    alter table AttachmentType_AUD 
        add index FKFD3C366EE9152785 (REV), 
        add constraint FKFD3C366EE9152785 
        foreign key (REV) 
        references RevisionInfo (id);

    alter table BinaryAttachment 
        add index FK3E893824B11E28B2 (subject_id), 
        add constraint FK3E893824B11E28B2 
        foreign key (subject_id) 
        references Subject (id);

    alter table BinaryAttachment 
        add index FK3E89382444BDB9DF (attachmenttype_id), 
        add constraint FK3E89382444BDB9DF 
        foreign key (attachmenttype_id) 
        references AttachmentType (id);
        
    alter table BinaryAttachment_AUD 
        add index FKE954B3F5E9152785 (REV), 
        add constraint FKE954B3F5E9152785 
        foreign key (REV) 
        references RevisionInfo (id);
