/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020-01-14 16:19:59                          */
/*==============================================================*/


drop table if exists FixtureBasicInfo;

drop table if exists FixtureIORecord;

drop table if exists FixtureMaintenanceRecord;

/*==============================================================*/
/* Table: FixtureBasicInfo                                      */
/*==============================================================*/
create table FixtureBasicInfo
(
   FixtureId            varchar(20) not null,
   FixtureName          varchar(200) not null,
   Family               varchar(200) not null,
   Model                varchar(200) not null,
   PartNo               varchar(200) not null,
   ManufacetureDay      varchar(20) not null,
   DocumentDate         varchar(20) not null,
   PurchaseDocumentNo   varchar(20) not null,
   Agent                varchar(20) not null,
   Location             varchar(20) not null,
   Photo                varchar(200) not null,
   primary key (FixtureId)
);

/*==============================================================*/
/* Table: FixtureIORecord                                       */
/*==============================================================*/
create table FixtureIORecord
(
   FixtureId            varchar(200) not null,
   Time                 varchar(200) not null,
   Fix_FixtureId        varchar(20),
   Recorder             varchar(200) not null,
   Handler              varchar(200) not null,
   Outbound             int not null,
   ProductionLine       varchar(200) not null,
   Name                 varchar(200) not null,
   Code                 varchar(200) not null,
   Location             varchar(20) not null,
   Model                varchar(200) not null,
   PartNo               varchar(200) not null,
   Family               varchar(200) not null,
   primary key (Time, FixtureId)
);

/*==============================================================*/
/* Table: FixtureMaintenanceRecord                              */
/*==============================================================*/
create table FixtureMaintenanceRecord
(
   FixtureId            varchar(200) not null,
   Date                 varchar(20) not null,
   Fix_FixtureId        varchar(20),
   Code                 varchar(20) not null,
   Name                 varchar(200) not null,
   Family               varchar(200) not null,
   Model                varchar(200) not null,
   PartNo               varchar(200) not null,
   ProblemDescription   varchar(200) not null,
   fixedDate            varchar(200) not null,
   primary key (FixtureId, Date)
);

alter table FixtureIORecord add constraint FK_IO foreign key (Fix_FixtureId)
      references FixtureBasicInfo (FixtureId) on delete restrict on update restrict;

alter table FixtureMaintenanceRecord add constraint FK_Maintenance foreign key (Fix_FixtureId)
      references FixtureBasicInfo (FixtureId) on delete restrict on update restrict;

