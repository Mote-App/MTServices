use cldb;

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

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(1,"San", "Sam", "M", "san","san", "N", "img/profiles/1.png", 2);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(2,"Tan", "Tam", "M", "tan","tan", "N", "img/profiles/2.png", 3);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(3,"Kan", "Kam", "M", "kan","kan", "N", "img/profiles/3.png", 4);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(4,"Wan", "Wam", "M", "wan","wan","N", "img/profiles/4.png", 5);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(5,"user", "user", "M", "user","user","N", "img/profiles/5.png", 2);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(6,"Kevin", "Schaffer", "M", "kevin","kevin","N", "img/profiles/6.jpg", 38);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(7,"Ryan", "Ohneiser", "M", "ryan","ryan","N", "img/profiles/7.jpg", 38);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(8,"Sanjay", "Sao", "M", "sanjay","sanjay","N", "img/profiles/8.jpg", 38);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(9,"Gibran", "Castillo", "M", "gibran","gibran","N", "img/profiles/9.jpg", 38);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(10,"Gordan", "Cummings", "M", "gordon","gordon","N", "img/profiles/10.jpg", 38);

insert into clprofile(id,first_name, last_name, gender, user_name, password, is_alumni, profile_picture_url, college_id) 
values(11,"Gordan", "Cummings", "M", "gordon","gordon","N", "img/profiles/10.jpg", 38);


insert into clfriend_relation values(1, true, 1, 1);
insert into clfriend_relation values(2, true, 2, 1);
insert into clfriend_relation values(3, true, 3, 1);
insert into clfriend_relation values(4, true, 4, 1);
insert into clfriend_relation values(5, true, 6, 6);
insert into clfriend_relation values(6, true, 7, 6);
insert into clfriend_relation values(7, true, 8, 6);
insert into clfriend_relation values(8, true, 9, 6);
insert into clfriend_relation values(9, true, 10, 6);
insert into clfriend_relation values(10, true, 11, 6);

insert into clpost values(1, 1, "img/posts/post1.jpg", "2014-11-01", "My first year at college", 20,'N','N');
insert into clpost values(2, 1, "img/posts/post2.jpg", "2014-11-01", "Amazing nature", 10,'N','N');
insert into clpost values(3, 1, "img/posts/post3.jpg", "2014-11-01", "Great Achievement", 50,'N','N');
insert into clpost values(4, 1, "img/posts/post4.jpg", "2014-11-01", "Winner", 5,'N','N');
insert into clpost values(5, 1, "img/posts/post5.jpg", "2014-11-01", "playfull", 1,'N','N');

insert into clpost values(6, 2, "img/posts/post6.jpg", "2014-11-01", "Life excitement", 20,'N','N');
insert into clpost values(7, 2, "img/posts/post7.jpg", "2014-11-01", "Fun and Excitement", 10,'N','N');
insert into clpost values(8, 2, "img/posts/post8.jpg", "2014-11-01", "Great Project", 50,'N','N');
insert into clpost values(9, 2, "img/posts/post9.jpg", "2014-11-01", "Creativity", 5,'N','N');
insert into clpost values(10, 2, "img/posts/post10.jpg", "2014-11-01", "Fired Up!!!", 1,'N','N');

insert into clpost values(11, 3, "img/posts/post11.jpg", "2014-11-01", "Life excitement", 20,'N','N');
insert into clpost values(12, 3, "img/posts/post12.jpg", "2014-11-01", "Fun and Excitement", 10,'N','N');
insert into clpost values(13, 3, "img/posts/post13.jpg", "2014-11-01", "Great Project", 50,'N','N');
insert into clpost values(14, 3, "img/posts/post14.jpg", "2014-11-01", "Creativity", 5,'N','N');
insert into clpost values(15, 3, "img/posts/post15.jpg", "2014-11-01", "Fired Up!!!", 1,'N','N');

insert into clpost values(16, 4, "img/posts/post16.jpg", "2014-11-01", "Life excitement", 20,'N','N');
insert into clpost values(17, 4, "img/posts/post17.jpg", "2014-11-01", "Fun and Excitement", 10,'N','N');
insert into clpost values(18, 4, "img/posts/post18.jpg", "2014-11-01", "Great Project", 50,'N','N');
insert into clpost values(19, 4, "img/posts/post19.jpg", "2014-11-01", "Creativity", 5,'N','N');
insert into clpost values(20, 4, "img/posts/post20.jpg", "2014-11-01", "Fired Up!!!", 1,'N','N');

insert into clpost values(21, 6, "img/posts/post21.jpg", "2015-02-01", "Enjoying games", 1,'N','N');
insert into clpost values(22, 7, "img/posts/post22.jpg", "2015-02-01", "Happyness in unity", 1,'N','N');
insert into clpost values(23, 8, "img/posts/post23.jpg", "2015-02-01", "What a crowd", 1,'N','N');
insert into clpost values(24, 9, "img/posts/post24.jpg", "2015-02-01", "College party crowd", 1,'N','N');
insert into clpost values(25, 10, "img/posts/post25.jpg", "2015-02-01", "College supporting community", 1,'N','N');
insert into clpost values(26, 11, "img/posts/post26.jpg", "2015-02-01", "College girls dream world", 1,'N','N');
insert into clpost values(27, 6, "img/posts/post27.jpg", "2015-02-01", "College power in unity", 1,'N','N');
insert into clpost values(28, 6, "img/posts/post28.jpg", "2015-02-01", "Proud of winning team", 1,'N','N');
insert into clpost values(29, 7, "img/posts/post29.jpg", "2015-02-01", "Business world", 1,'N','N');
insert into clpost values(30, 7, "img/posts/post30.jpg", "2015-02-01", "Cool cafeteria!!!", 1,'N','N');

insert into clpost_tags(id, post_id, tag_id) values(1, 1, 3); 
insert into clpost_tags(id, post_id, tag_id) values(2, 1, 7); 
insert into clpost_tags(id, post_id, tag_id) values(3, 2, 2); 
insert into clpost_tags(id, post_id, tag_id) values(4, 2, 9); 
insert into clpost_tags(id, post_id, tag_id) values(5, 3, 31); 
insert into clpost_tags(id, post_id, tag_id) values(6, 3, 32); 
insert into clpost_tags(id, post_id, tag_id) values(7, 4, 23); 
insert into clpost_tags(id, post_id, tag_id) values(8, 4, 13); 
insert into clpost_tags(id, post_id, tag_id) values(9, 5, 20); 
insert into clpost_tags(id, post_id, tag_id) values(10, 5, 21);

 
insert into clpost_tags(id, post_id, tag_id) values(11, 6, 3); 
insert into clpost_tags(id, post_id, tag_id) values(12, 6, 7); 
insert into clpost_tags(id, post_id, tag_id) values(13, 7, 2); 
insert into clpost_tags(id, post_id, tag_id) values(14, 7, 9); 
insert into clpost_tags(id, post_id, tag_id) values(15, 8, 31); 
insert into clpost_tags(id, post_id, tag_id) values(16, 8, 32); 
insert into clpost_tags(id, post_id, tag_id) values(17, 9, 23); 
insert into clpost_tags(id, post_id, tag_id) values(18, 9, 13); 
insert into clpost_tags(id, post_id, tag_id) values(19, 10, 20); 
insert into clpost_tags(id, post_id, tag_id) values(20, 10, 21);

insert into clpost_tags(id, post_id, tag_id) values(21, 11, 3); 
insert into clpost_tags(id, post_id, tag_id) values(22, 11, 7); 
insert into clpost_tags(id, post_id, tag_id) values(23, 12, 2); 
insert into clpost_tags(id, post_id, tag_id) values(24, 12, 9); 
insert into clpost_tags(id, post_id, tag_id) values(25, 13, 31); 
insert into clpost_tags(id, post_id, tag_id) values(26, 13, 32); 
insert into clpost_tags(id, post_id, tag_id) values(27, 14, 23); 
insert into clpost_tags(id, post_id, tag_id) values(28, 14, 13); 
insert into clpost_tags(id, post_id, tag_id) values(29, 15, 20); 
insert into clpost_tags(id, post_id, tag_id) values(30, 15, 21);

insert into clpost_tags(id, post_id, tag_id) values(31, 16, 3); 
insert into clpost_tags(id, post_id, tag_id) values(32, 16, 7); 
insert into clpost_tags(id, post_id, tag_id) values(33, 17, 2); 
insert into clpost_tags(id, post_id, tag_id) values(34, 17, 9); 
insert into clpost_tags(id, post_id, tag_id) values(35, 18, 31); 
insert into clpost_tags(id, post_id, tag_id) values(36, 18, 32); 
insert into clpost_tags(id, post_id, tag_id) values(37, 19, 23); 
insert into clpost_tags(id, post_id, tag_id) values(38, 19, 13); 
insert into clpost_tags(id, post_id, tag_id) values(39, 20, 20); 
insert into clpost_tags(id, post_id, tag_id) values(40, 20, 21);

insert into clpost_tags(id, post_id, tag_id) values(41, 21, 21);
insert into clpost_tags(id, post_id, tag_id) values(42, 21, 18);
insert into clpost_tags(id, post_id, tag_id) values(44, 22, 19);
insert into clpost_tags(id, post_id, tag_id) values(45, 22, 22);
insert into clpost_tags(id, post_id, tag_id) values(46, 23, 30);
insert into clpost_tags(id, post_id, tag_id) values(47, 23, 18);
insert into clpost_tags(id, post_id, tag_id) values(48, 24, 8);
insert into clpost_tags(id, post_id, tag_id) values(49, 24, 7);
insert into clpost_tags(id, post_id, tag_id) values(50, 25, 3);
insert into clpost_tags(id, post_id, tag_id) values(51, 25, 30);
insert into clpost_tags(id, post_id, tag_id) values(52, 26, 37);
insert into clpost_tags(id, post_id, tag_id) values(53, 26, 40);
insert into clpost_tags(id, post_id, tag_id) values(54, 27, 18);
insert into clpost_tags(id, post_id, tag_id) values(55, 27, 17);
insert into clpost_tags(id, post_id, tag_id) values(56, 27, 5);
insert into clpost_tags(id, post_id, tag_id) values(57, 28, 21);
insert into clpost_tags(id, post_id, tag_id) values(58, 28, 27);
insert into clpost_tags(id, post_id, tag_id) values(59, 29, 31);
insert into clpost_tags(id, post_id, tag_id) values(60, 30, 13);

insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(1, 1, "#stylish", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(2, 1, "#beauty", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(3, 2, "#amazing", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(4, 2, "#amazing", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(5, 3, "#amazing", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(6, 3, "#amazing", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(7, 4, "#amazing", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(8, 4, "#amazing", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(9, 5, "#amazing", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(10, 5, "#amazing", 1);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(11, 21, "#Great Crowd", 6);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(12, 21, "#enjoying together", 6);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(13, 22, "#hanging out", 7);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(14, 22, "#friends group", 7);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(15, 23, "#ocean", 8);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(16, 23, "#unity", 8);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(17, 24, "#young crowd", 9);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(18, 24, "#party", 9);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(19, 25, "#Kids support", 10);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(20, 25, "#Charity", 10);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(21, 26, "#beauty", 11);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(22, 26, "#happy", 11);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(23, 27, "#unity", 6);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(24, 27, "#working together", 6);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(25, 28, "#I win", 7);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(26, 28, "#screaming", 7);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(27, 29, "#business", 8);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(28, 29, "#entrepreneur", 8);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(29, 30, "#lunch time", 9);
insert into clpost_custom_tags(id, post_id, tag_name, user_id) values(30, 30, "#great food", 9);



insert into clpost_school values(1, 1, 3, 2, 5);
insert into clpost_school values(2, 6, 23, 38, 5);
insert into clpost_school values(3, 6, 24, 38, 10);
insert into clpost_school values(4, 6, 25, 38, 9);
insert into clpost_school values(5, 6, 26, 38, 8);
insert into clpost_school values(6, 6, 28, 38, 10);
insert into clpost_school values(7, 6, 29, 38, 12);
insert into clpost_school values(8, 6, 30, 38, 13);

insert into clpost_national values(1, 1, 3, 2, 50);
insert into clpost_national values(2, 6, 23, 38, 20);
insert into clpost_national values(3, 6, 24, 38, 10);
insert into clpost_national values(4, 6, 25, 38, 21);