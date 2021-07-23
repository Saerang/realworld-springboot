insert into users (user_id, email, password, bio, image, username)
values
(101, 'realworld101@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio101', 'image101', 'realworld101'),
(102, 'realworld102@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio102', 'image102', 'realworld102'),
(103, 'realworld103@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio103', 'image103', 'realworld103'),

(201, 'realworld201@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio201', 'image201', 'realworld201'),
(202, 'realworld202@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio202', 'image202', 'realworld202'),
(203, 'realworld203@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio203', 'image203', 'realworld203');

insert into follow_relation (followee_id, follower_id)
values
(101, 102),
(202, 201);

-- Article List
insert into tag (tag_id, tag)
values
(101, 'tag101'),
(102, 'tag102'),
(103, 'tag103');

insert into article (article_id, body, created_at, description, slug, title, updated_at, user_id)
values
(101, 'body101', NULL, 'description', 'slug101', 'title101', NULL, 101),
(102, 'body102', NULL, 'description', 'slug102', 'title102', NULL, 101),
(103, 'body103', NULL, 'description', 'slug103', 'title103', NULL, 101),
(201, 'body201', NULL, 'description', 'slug201', 'title201', NULL, 201),
(202, 'body202', NULL, 'description', 'slug202', 'title202', NULL, 201),
(203, 'body203', NULL, 'description', 'slug203', 'title203', NULL, 201);

insert into article_tag (article_tag_id, article_id, tag_id)
values
(101, 101, 101),
(102, 102, 101),
(103, 103, 101),

(201, 201, 101),
(202, 201, 102),
(203, 201, 103),
(204, 202, 101),
(205, 203, 101);

insert into favorite (user_id, favorited_id, favorite_type)
values
(101, 101, 'ARTICLE'),
(101, 102, 'ARTICLE'),
(101, 103, 'ARTICLE'),

(201, 201, 'ARTICLE'),
(201, 202, 'ARTICLE'),
(201, 203, 'ARTICLE');
