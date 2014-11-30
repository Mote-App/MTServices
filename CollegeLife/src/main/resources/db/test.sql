use cldb;

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


insert into clfriend_relation values(1, true, 1, 1);
insert into clfriend_relation values(2, true, 2, 1);
insert into clfriend_relation values(3, true, 3, 1);
insert into clfriend_relation values(4, true, 4, 1);

insert into clpost values(1, 1, "img/posts/post1.jpg", "2014-11-01", "My first year at college", 20);
insert into clpost values(2, 1, "img/posts/post2.jpg", "2014-11-01", "Amazing nature", 10);
insert into clpost values(3, 1, "img/posts/post3.jpg", "2014-11-01", "Great Achievement", 50);
insert into clpost values(4, 1, "img/posts/post4.jpg", "2014-11-01", "Winner", 5);
insert into clpost values(5, 1, "img/posts/post5.jpg", "2014-11-01", "playfull", 1);

insert into clpost values(6, 2, "img/posts/post6.jpg", "2014-11-01", "Life excitement", 20);
insert into clpost values(7, 2, "img/posts/post7.jpg", "2014-11-01", "Fun and Excitement", 10);
insert into clpost values(8, 2, "img/posts/post8.jpg", "2014-11-01", "Great Project", 50);
insert into clpost values(9, 2, "img/posts/post9.jpg", "2014-11-01", "Creativity", 5);
insert into clpost values(10, 2, "img/posts/post10.jpg", "2014-11-01", "Fired Up!!!", 1);

insert into clpost values(11, 3, "img/posts/post11.jpg", "2014-11-01", "Life excitement", 20);
insert into clpost values(12, 3, "img/posts/post12.jpg", "2014-11-01", "Fun and Excitement", 10);
insert into clpost values(13, 3, "img/posts/post13.jpg", "2014-11-01", "Great Project", 50);
insert into clpost values(14, 3, "img/posts/post14.jpg", "2014-11-01", "Creativity", 5);
insert into clpost values(15, 3, "img/posts/post15.jpg", "2014-11-01", "Fired Up!!!", 1);

insert into clpost values(16, 4, "img/posts/post16.jpg", "2014-11-01", "Life excitement", 20);
insert into clpost values(17, 4, "img/posts/post17.jpg", "2014-11-01", "Fun and Excitement", 10);
insert into clpost values(18, 4, "img/posts/post18.jpg", "2014-11-01", "Great Project", 50);
insert into clpost values(19, 4, "img/posts/post19.jpg", "2014-11-01", "Creativity", 5);
insert into clpost values(20, 4, "img/posts/post20.jpg", "2014-11-01", "Fired Up!!!", 1);

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


