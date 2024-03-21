create table recipes (
                         id bigint auto_increment primary key,
                         description varchar(255) null,
                         genre enum ('ITALIAN', 'KOREAN', 'ASIAN', 'AMERICAN') null,
                         name varchar(255) null,
                         total_calories int null,
                         uuid varchar(255) null,
                         year_of_publication int null
);
create table ingredients (
                             id bigint auto_increment primary key,
                             name varchar(32) null,
                             uuid varchar(255) null,
                             recipe_id bigint null,
                             constraint FK7p08vcn6wf7fd6qp79yy2jrwg foreign key (recipe_id) references recipes (id)
);
