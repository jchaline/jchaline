DROP TABLE IF EXISTS pac_pacuser;
CREATE TABLE IF NOT EXISTS pac_pacuser (	
		id integer,
		guid varchar(255),
		nom varchar(255),
		prenom varchar(255),
		email varchar(255),
		prochainPac date,
		dateEntree date,
		dateSortie date,
		dernierPac date,
	PRIMARY KEY  (id)
)COMMENT='Contient les pacusers';

DROP TABLE IF EXISTS pac_sequences;
CREATE TABLE IF NOT EXISTS pac_sequences (	
		sequence_name varchar(255),
		next_val int,
	PRIMARY KEY  (sequence_name, next_val)
)COMMENT='contient les sequences des beans du plugin pac ';

DROP TABLE IF EXISTS pac_date;
CREATE TABLE IF NOT EXISTS pac_date(	
		id integer,
		idOwner varchar(255),
		type varchar(255),
		date date,
	PRIMARY KEY  (id)
)COMMENT='Contient les date de l''application';

DROP TABLE IF EXISTS pac_config;
CREATE TABLE IF NOT EXISTS pac_date(	
		id integer,
		dayFrequency integer,
		monthFrequency integer,
		dayWait integer,
		monthWait integer,
		team varchar(255),
		firstDate date,
	PRIMARY KEY  (id)
)COMMENT='Contient les date de l''application';

DROP TABLE IF EXISTS pac_artifact;
CREATE TABLE IF NOT EXISTS pac_artifact(	
		id integer,
		artifactId varchar(255),
		groupId varchar(255),
		versionId varchar(255),
	PRIMARY KEY  (id)
)COMMENT='Contient les artifacts';