use cldb;

/* Mote College table */
CREATE TABLE clcollege (
    id          INTEGER      PRIMARY KEY AUTO_INCREMENT,
    img_path    VARCHAR(250) NOT NULL,
    name        VARCHAR(250) NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clcollege` on clcollege(id);


/* Mote user Profile table */
CREATE TABLE clprofile (
    id                  INTEGER      	PRIMARY KEY AUTO_INCREMENT,
    email				VARCHAR(60)		NOT NULL, 
    first_name          VARCHAR(50)  	NOT NULL, 
    last_name           VARCHAR(50)  	NOT NULL, 
    /*middle_name       VARCHAR(50)  	NOT NULL, 
    birth_date          DATE         	NULL, 
    email_address       VARCHAR(50)  	NOT NULL,*/ 
    //gender            CHAR(1)  		NOT NULL, 
	user_name			VARCHAR(20)		NOT NULL,
    password            VARCHAR(50)  	NOT NULL, 
    /*cover_picture_url VARCHAR(250) 	NOT NULL,*/ 
    //is_alumni         CHAR(1)      	DEFAULT 'N', 
    /*graduation_year   INTEGER      	NULL, 
    about_me            VARCHAR(250) 	NOT NULL, 
    activities          VARCHAR(250) 	NOT NULL, 
    interest            VARCHAR(250) 	NOT NULL,*/ 
    profile_picture_url VARCHAR(250) 	NOT NULL, 
    /*religion          VARCHAR(50)  	NOT NULL,*/
    college_id          INTEGER        	REFERENCES clcollege(id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clprofile_id` on clprofile(id);
create index `clprofile_name` on clprofile(user_name);


/* Mote Friend Relation table */
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


/* Mote Tag table */
CREATE TABLE cltag (
    id        INTEGER      PRIMARY KEY AUTO_INCREMENT,
    tag_type  VARCHAR(50)  NOT NULL,
    sub_tag   VARCHAR(250) NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `cltag` on cltag(id);


/*
 * Mote Post table
 * V - the number of 'views' on a post.
 * L - the number of 'likes' on a post.
 * Ns - the number of 'posts' in School Feed.
 * 
 * Cr - the number of 'people' registered from that school.
 * Cl - the number of 'likes' per post from that school.
 * Cpn - the number of 'post' from that school.
 * 
 * CrIdealAvg - the average number of 'people' registered per school.
 * ClIdealAvg - the average number of 'likes' per post from all school.
 * CpnAvg - the average number of 'post' from that school.
 */
CREATE TABLE clpost (
	id					INTEGER			PRIMARY KEY AUTO_INCREMENT,
	user_id				INTEGER      	REFERENCES clprofile(id),
	post_image_path  	VARCHAR(250) 	NOT NULL,
	post_date        	DATE         	NULL,
	caption          	VARCHAR(250) 	NOT NULL,
	views            	INTEGER        	NOT NULL DEFAULT 0,
	likes            	INTEGER        	NOT NULL DEFAULT 0,
	school_promote		BOOLEAN         DEFAULT false,
	national_promote	BOOLEAN         DEFAULT false,
	college_id          INTEGER        	REFERENCES clcollege(id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clpost` on clpost(id);
create index `clpost_user_id` on clpost(user_id);


CREATE TABLE clpost_tags (
	id 					INTEGER		PRIMARY KEY AUTO_INCREMENT,
	post_id				INTEGER		REFERENCES clpost(id),
	tag_id				INTEGER		REFERENCES cltag(id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clpost_tags` on clpost_tags(id);
create index `clpost_tags_post_id` on clpost_tags(post_id);

CREATE TABLE clpost_custom_tags (
	id 					INTEGER			PRIMARY KEY AUTO_INCREMENT,
	post_id				INTEGER			REFERENCES clpost(id),
	tag_name			VARCHAR(250		NOT NULL,
	user_id				INTEGER 		REFERENCES clprofile(id)	
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clpost_custom_tags` on clpost_custom_tags(id);
create index `clpost_custom_tags_post_id` on clpost_custom_tags(post_id);


CREATE TABLE clpost_users (
	id 					INTEGER		PRIMARY KEY AUTO_INCREMENT,
	post_id				INTEGER		REFERENCES clpost(id),
	user_id				INTEGER		REFERENCES clprofile(id),
	level				CHAR(1)
	
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clpost_users_post_id` on clpost_users(post_id);
create index `clpost_users_user_id` on clpost_users(user_id);


CREATE TABLE clpost_school (
	id					INTEGER		PRIMARY KEY AUTO_INCREMENT,
	user_id				INTEGER     REFERENCES clprofile(id),
	post_id				INTEGER		REFERENCES clpost(id),		
    college_id          INTEGER     REFERENCES clcollege(id),
    likes            	INTEGER     NOT NULL DEFAULT 0
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clpost_school` on clpost_school(id);
create index `clpost_school_user_id` on clpost_school(user_id);
create index `clpost_school_post_id` on clpost_school(post_id);
create index `clpost_school_college_id` on clpost_school(college_id);


CREATE TABLE clpost_national (
	id					INTEGER			PRIMARY KEY AUTO_INCREMENT,
	user_id				INTEGER      	REFERENCES clprofile(id),
	post_id				INTEGER			REFERENCES clpost(id),		
    college_id          INTEGER        	REFERENCES clcollege(id),
    likes            	INTEGER        	NOT NULL DEFAULT 0
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

create index `clpost_national` on clpost_national(id);
create index `clpost_national_user_id` on clpost_national(user_id);
create index `clpost_national_post_id` on clpost_national(post_id);
create index `clpost_national_college_id` on clpost_national(college_id);

