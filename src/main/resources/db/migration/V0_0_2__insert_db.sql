insert into users (user_id, email, password, bio, image, username)
values
('1', 'realworld1@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio1', 'image1', 'realworld1'),
('2', 'realworld2@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio2', 'image2', 'realworld2'),
('3', 'realworld3@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio3', 'image3', 'realworld3');

insert into follow_relation (followee_id, follower_id)
values
(1, 2);

-- insert into tag (tag_id, tag)
-- values (1, 'tag1');
--
-- insert into article (article_id, body, created_at, description, favorited, favorites_count, slug, title, updated_at, user_id)
-- values
-- (1, 'body1', NULL, 'description', false, 1, 'slug1', 'title1', NULL, 1),
-- (2, 'body2', NULL, 'description', false, 1, 'slug2', 'title2', NULL, 1);
--
-- insert into article_tag (article_tag_id, article_id, tag_id)
-- values
-- (1, 1, 1),
-- (2, 2, 1);