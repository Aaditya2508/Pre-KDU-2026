USE streamflix2; 


CREATE TABLE category(
	category_id INTEGER auto_increment primary key,  
    category_name VARCHAR(100) NOT NULL, 
    description TEXT 
); 


CREATE TABLE content(
 content_id SERIAL primary key, 
 title VARCHAR(200) NOT NULL, 
 rating decimal(3,1) CHECK (rating >=0 AND rating <= 10), 
 views_in_millions INTEGER, 
 category_id INTEGER, 
 release_year INTEGER, 
 FOREIGN KEY(category_id) REFERENCES category(category_id)
); 



-- FINAL QUERY 1: 
select content.title, category.category_name 
FROM content JOIN category 
ON content.category_id = category.category_id 
where category.category_name = "Documentaries" AND content.release_year = 2024 AND content.rating > 8.0 ; 


-- FINAL QUERY 2: 
SELECT * FROM (
SELECT title, ( rating + views_in_millions) as success_score
FROM content ) as r WHERE success_score > 100  


