DROP TABLE IF EXISTS scala_sequences;
CREATE TABLE IF NOT EXISTS scala_sequences (	
		sequence_name varchar(255),
		next_val int,
	PRIMARY KEY  (sequence_name, next_val)
)COMMENT='contient les sequences des beans du plugin scala ';

DROP TABLE IF EXISTS scala_artifact;
CREATE TABLE IF NOT EXISTS `scala_artifact` (
  `id` int(11) NOT NULL,
  `artifactid` varchar(255) NOT NULL,
  `groupid` varchar(255) NOT NULL,
  `version` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS scala_dependencies;
CREATE TABLE IF NOT EXISTS `scala_dependencies` (
  `id` int(11) NOT NULL,
  `idArtifact` int(11) NOT NULL,
  `groupid` varchar(255) NOT NULL,
  `artifactid` varchar(255) NOT NULL,
  `version` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);
