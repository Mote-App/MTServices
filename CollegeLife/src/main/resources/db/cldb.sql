use cldb;

CREATE TABLE clcollege (
    id          INTEGER      PRIMARY KEY,
    img_path    VARCHAR(250) NOT NULL,
    name        VARCHAR(250) NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clcollege` on clcollege(id);

CREATE TABLE clprofile (
    id                  INTEGER      	PRIMARY KEY, 
    first_name          VARCHAR(50)  	NOT NULL, 
    last_name           VARCHAR(50)  	NOT NULL, 
    middle_name         VARCHAR(50)  	NOT NULL, 
    birth_date          DATE         	NULL, 
    email_address       VARCHAR(50)  	NOT NULL, 
    gender              CHAR(1)  		NOT NULL, 
	user_name			VARCHAR(20)		NOT NULL,
    password            VARCHAR(50)  	NOT NULL, 
    cover_picture_url   VARCHAR(250) 	NOT NULL, 
    is_alumni           CHAR(1)      	DEFAULT 'N', 
    graduation_year     INTEGER      	NULL, 
    about_me            VARCHAR(250) 	NOT NULL, 
    activities          VARCHAR(250) 	NOT NULL, 
    interest            VARCHAR(250) 	NOT NULL, 
    profile_picture_url VARCHAR(250) 	NOT NULL, 
    religion            VARCHAR(50)  	NOT NULL,
    college_id          INTEGER        	REFERENCES clcollege(id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clprofile_id` on clprofile(id);
create index `clprofile_name` on clprofile(user_name);


/* College Life Friend Relation table */
CREATE TABLE clfriend_relation (
    id           INTEGER      PRIMARY KEY,
    are_friends  BOOLEAN      DEFAULT true,
    friend_id    INTEGER      REFERENCES clprofile(id),
    user_id      INTEGER      REFERENCES clprofile(id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clfriend_relation_id` on clfriend_relation(id);
create index `clfriend_relation_user_id` on clfriend_relation(user_id);


CREATE TABLE cltag (
    id        INTEGER      PRIMARY KEY,
    tag_type  VARCHAR(50)  NOT NULL,
    sub_tag   VARCHAR(250) NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `cltag` on cltag(id);


CREATE TABLE clpost (
	id					INTEGER			PRIMARY KEY,
	user_id				INTEGER      	REFERENCES clprofile(id),
	post_image_path  	VARCHAR(250) 	NOT NULL,
	post_date        	DATE         	NULL,
	caption          	VARCHAR(250) 	NOT NULL,
	likes            	INTEGER        	NULL	
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clpost` on clpost(id);
create index `clpost_user_id` on clpost(user_id);

CREATE TABLE clpost_tags (
	id 					INTEGER		PRIMARY KEY,
	post_id				INTEGER		REFERENCES clpost(id),
	tag_id				INTEGER		REFERENCES cltag(id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clpost_tags` on clpost_tags(id);
create index `clpost_tags_post_id` on clpost_tags(post_id);

CREATE TABLE clpost_custom_tags (
	id 					INTEGER		PRIMARY KEY,
	post_id				INTEGER		REFERENCES clpost(id),
	tag_name			VARCHAR(250)		NOT NULL,
	user_id				INTEGER 	REFERENCES clprofile(id)	
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clpost_custom_tags` on clpost_custom_tags(id);
create index `clpost_custom_tags_post_id` on clpost_custom_tags(post_id);

