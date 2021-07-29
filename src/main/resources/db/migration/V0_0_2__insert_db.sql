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

insert into article (article_id, user_id, body, description, slug, title, created_at, updated_at)
values

(101, 101, 'body101', 'description', 'slug101', 'title101', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838'),
(102, 101, 'body102', 'description', 'slug102', 'title102', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838'),
(103, 101, 'body103', 'description', 'slug103', 'title103', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838'),
(104, 102, 'body104', 'description', 'slug104', 'title104', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838'),
(105, 102, 'body105', 'description', 'slug105', 'title105', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838'),
(106, 102, 'body106', 'description', 'slug106', 'title106', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838'),
(201, 201, 'body201', 'description', 'slug201', 'title201', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838'),
(202, 201, 'body202', 'description', 'slug202', 'title202', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838'),
(203, 201, 'body203', 'description', 'slug203', 'title203', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838');

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
(101, 104, 'ARTICLE'),
(102, 104, 'ARTICLE'),

(201, 201, 'ARTICLE'),
(201, 202, 'ARTICLE'),
(201, 203, 'ARTICLE');


insert into comments (comment_id, article_id, user_id, body, created_at, updated_at)
values
(101, 101, 101, 'body101', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838'),
(102, 101, 102, 'body102', '2021-07-30T00:01:00.838', '2021-07-30T00:01:00.838');
