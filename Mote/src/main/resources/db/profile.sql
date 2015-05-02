use motedb;

/*
insert into clprofile(id,first_name, last_name, middle_name, birth_date, email_address, gender, user_name, password, cover_picture_url, is_alumni, graduation_year, about_me, activities, interest, profile_picture_url, religion, college_id) 
values(1,"San", "Sam", "S", "1976-01-01", "s@s.com", "M", "san","san","img/san.png", "N", 0, "I am a pro athlete", "Football, cycling", "sports, studying", "img/profiles/1.png", "Asian", 2);

insert into clprofile(id,first_name, last_name, middle_name, birth_date, email_address, gender, user_name, password, cover_picture_url, is_alumni, graduation_year, about_me, activities, interest, profile_picture_url, religion, college_id) 
values(2,"Tan", "Tam", "T", "1976-01-01", "t@t.com", "M", "tan","tan","img/tan.png", "N", 0, "I am a pro athlete", "Football, cycling", "sports, studying", "img/profiles/2.png", "Asian", 3);

insert into clprofile(id,first_name, last_name, middle_name, birth_date, email_address, gender, user_name, password, cover_picture_url, is_alumni, graduation_year, about_me, activities, interest, profile_picture_url, religion, college_id) 
values(3,"Kan", "Kam", "K", "1976-01-01", "k@k.com", "M", "kan","kan","img/kan.png", "N", 0, "I am a pro athlete", "Football, cycling", "sports, studying", "img/profiles/3.png", "Asian", 4);

insert into clprofile(id,first_name, last_name, middle_name, birth_date, email_address, gender, user_name, password, cover_picture_url, is_alumni, graduation_year, about_me, activities, interest, profile_picture_url, religion, college_id) 
values(4,"Wan", "Wam", "W", "1976-01-01", "w@w.com", "M", "wan","wan","img/wan.png", "N", 0, "I am a pro athlete", "Football, cycling", "sports, studying", "img/profiles/4.png", "Asian", 5);

insert into clprofile(id,first_name, last_name, middle_name, birth_date, email_address, gender, user_name, password, cover_picture_url, is_alumni, graduation_year, about_me, activities, interest, profile_picture_url, religion, college_id) 
values(5,"user", "user", "S", "1976-01-01", "s@s.com", "M", "user","user","img/san.png", "N", 0, "I am a pro athlete", "Football, cycling", "sports, studying", "img/profiles/5.png", "Asian", 2);

insert into clprofile(id,first_name, last_name, middle_name, birth_date, email_address, gender, user_name, password, cover_picture_url, is_alumni, graduation_year, about_me, activities, interest, profile_picture_url, religion, college_id) 
values(6,"John", "Marshall", "S", "1976-01-01", "s@s.com", "M", "demo","demo","img/demo.png", "N", 0, "I am a pro athlete", "Football, cycling", "sports, studying", "img/profiles/5.png", "Asian", 38);
*/

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(1, "san.samz@mtemail.com", "San", "Samz", "sansamz", "password", "img/profiles/1.png", 2);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_namamz profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(2, "tan.tamz@mtemail.com", "Tan", "Tamz", "tantamz", "password", "img/profiles/2.png", 3);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(3, "kan.kamz@mtemail.com", "Kan", "Kamz", "kankamz", "password", "img/profiles/3.png", 4);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(4, "wan.wamz@mtemail.com", "Wan", "Wamz", "wanwamz", "password", "img/profiles/4.png", 5);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(5, "user.userz@mtemail.com", "User", "Userz", "ususerz", "password", "img/profiles/5.png", 2);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(6, "kevin.schaffer@mtemail.com", "Kevin", "Schaffer", "kschaff", "password", "img/profiles/6.png", 38);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(7, "ryan.ohneiser@mtemail.com", "Ryan", "Ohneiser", "rohneis", "password", "img/profiles/7.png", 38);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(8, "sanjay.sao@mtemail.com", "Sanjay", "Sao", "sanjsao", "password", "img/profiles/8.png", 38);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(9, "gibran.castillo@mtemail.com", "Gibran", "Castillo", "gecasti", "password", "img/profiles/9.png", 38);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(10, "gordon.cummings@mtemail.com", "Gordon", "Cummings", "gcummin", "password", "img/profiles/10.png", 38);

insert into clprofile(profile_id, profile_email, profile_first_name, profile_last_name, profile_user_name, profile_password, profile_picture_url, profile_college_id) 
values(11, "jesus.islas@mtemail.com", "Jesus", "Islas", "jeislas", "password", "img/profiles/11.png", 38);

insert into profile_has_friend values(1, 2);
insert into profile_has_friend values(2, 1);
insert into profile_has_friend values(3, 1);
insert into profile_has_friend values(4, 1);
insert into profile_has_friend values(1, 3);
insert into profile_has_friend values(4, 1);
insert into profile_has_friend values(7, 6);
insert into profile_has_friend values(8, 6);
insert into profile_has_friend values(9, 10);
insert into profile_has_friend values(10, 7);

