
## Query 1:  
SELECT c.content_id, c.title, cat.category_name
FROM content c JOIN category cat
ON c.category_id = cat.category_id;

![Query 1 Output](screenshots/query1.png)



## Query 2:
SELECT content_id, title, views_in_millions
FROM content
ORDER BY views_in_millions DESC;

![Query 2 Output](screenshots/q2.png)


-- Query 3:
SELECT cat.category_name, AVG(c.rating) AS average_rating
FROM content c JOIN category cat 
ON c.category_id = cat.category_id
GROUP BY cat.category_name;


![Query 3 Output](screenshots/q3.png)

-- Query 4 :
SELECT c.title,c.rating,c.views_in_millions,cat.category_name
FROM content c JOIN category cat 
ON c.category_id = cat.category_id
WHERE c.rating > 8.5 AND c.views_in_millions > 100;

![Query 4 Output](screenshots/q4.png)


-- Query 5: 
-- Step 1 :

EXPLAIN ANALYZE
SELECT c.content_id,c.title, cat.category_name
FROM content c JOIN category cat
ON c.category_id = cat.category_id;


![Query 5 Output](screenshots/q5_p1.png)

-- Step 2 :
CREATE INDEX idx_category_id ON content(category_id);

-- Step 3 : 

EXPLAIN ANALYZE
SELECT c.content_id,c.title, cat.category_name
FROM content c JOIN category cat
ON c.category_id = cat.category_id;


![Query 5 Output](screenshots/q5_p2.png)

