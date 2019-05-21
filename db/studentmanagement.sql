create table trn_student_info(
id number(10) constraint student_info_pk primary key,
name varchar2(70) not null,
email_id varchar2(70) constraint student_email_uk unique not null ,
phone_number number(10) constraint student_phoneno_uk unique not null,
user_name varchar2(70) constraint student_username_uk unique not null,
password varchar2(70) not null,
created_by number(10),
created_date timestamp,
modified_by number(10),
modified_date timestamp,
status number(1)
);
select * from trn_student_info;
commit;
alter table trn_student_info add status number(1);
drop table trn_student_info;
update trn_student_info set status=1 ;
create sequence student_id_seq start with 1 increment by 1;
