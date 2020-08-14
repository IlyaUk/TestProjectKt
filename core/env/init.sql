create table if not exists testdb.borrower
(
	id bigint auto_increment
		primary key,
	bonus_amount double null,
	bonus_currency_id bigint null,
	address_id bigint null,
	personal_data_id bigint null,
	user_account_id bigint null,
	work_id bigint null,
	version int null,
	creation_date datetime null,
	profile_last_updated_date datetime null,
	promo_code varchar(64) null,
	terrorist bit null,
	has_online_banking bit null,
	inform_bank_password tinyint(1) null,
	constraint address_id
		unique (address_id),
	constraint personal_data_id
		unique (personal_data_id),
	constraint user_account_id
		unique (user_account_id),
	constraint work_id
		unique (work_id)
);