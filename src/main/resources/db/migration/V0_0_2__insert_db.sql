insert into users (user_id, email, password, bio, image, username)
values
(101, 'realworld1@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio1', 'image1', 'realworld1'),
(102, 'realworld2@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio2', 'image2', 'realworld2'),
(103, 'realworld3@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio3', 'image3', 'realworld3'),

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
(101, 'tag1'),
(102, 'tag2'),
(103, 'tag3');

insert into article (article_id, body, created_at, description, slug, title, updated_at, user_id)
values
(101, 'body1', NULL, 'description', 'slug1', 'title1', NULL, 101),
(102, 'body2', NULL, 'description', 'slug2', 'title2', NULL, 101),
(103, 'body3', NULL, 'description', 'slug3', 'title3', NULL, 101),
(201, 'body21', NULL, 'description', 'slug21', 'title21', NULL, 201),
(202, 'body22', NULL, 'description', 'slug22', 'title22', NULL, 201),
(203, 'body23', NULL, 'description', 'slug23', 'title23', NULL, 201);

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
