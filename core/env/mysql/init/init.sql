USE testdb

create table if not exists borrower
(
	id bigint auto_increment primary key,
	bonus_amount double null,
	bonus_currency_id bigint null,
	address_id bigint null
);