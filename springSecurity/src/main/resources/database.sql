insert into t_user values (10, 'admin', '12345');
insert into t_user values (11, 'user', '123');
insert into t_user values (12, 'pavel', 'abc');


# insert into t_user values (10, 'admin', '$2y$12$JaSEXdKc8cidXB3sBNRS0.9J.kZS.UqA3mQPpEw2Bt1Jky9pDn8M.');
insert into t_role values (1, 'USER');
insert into t_role values(2, 'ADMIN');
insert into user_roles values (10, 2);
insert into user_roles values (11, 1);
insert into user_roles values (12, 1);