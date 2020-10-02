# Technaxis

## Table of Contents
- [Database Model](#database-model)
- [Dockerizing](#dockerizing)
- [API SpringRestDoc](#spring-restapidoc)

  
  
## Database Model
![model](book_vector.png)

To increase the search speed create index 
and reduce the creation of vectors by adding a new field (tsv) and separate because you don't need to back up
 
Create index 
```postgresql

CREATE INDEX idx_gin_document
    ON book_vector
        USING gin ("tsv");
```
---

Adding occurs via a trigger
```postgresql
CREATE FUNCTION trg_tsv_trigger() RETURNS trigger AS
$$
BEGIN
    insert into book_vector (id, tsv) values (new.id, to_tsvector('english', COALESCE(NEW.description, '')));
    RETURN new;
END
$$ LANGUAGE plpgsql;
```
---

Example of how to get data with a keyword
```postgresql
EXPLAIN (ANALYSE )
SELECT book.id
FROM book
         INNER JOIN book_vector bv on book.id = bv.id
WHERE tsv @@ plainto_tsquery('rus');
```

---

Example of how to get statistics of books read in the interval

```postgresql
SELECT title, count(*) AS Count
FROM book
where read_already = true
  and date BETWEEN SYMMETRIC '1982-00-00' AND '2049-00-00'
group by title, description, author;
```
## Dockerizing
run the below command to create 
of an image of our application.

`mvn clean package docker:build`


## Spring RestApiDoc
Documentation is generated automatically when running tests.

The REST service documentation is guaranteed to match the logic of operation. The documentation is synchronized with the application logic
```text
MockHttpServletRequest:
      HTTP Method = GET
      Request URI = /books/209
       Parameters = {}
          Headers = []
             Body = null
    Session Attrs = {}

Handler:
             Type = ru.ruslan.controller.BookRestController
           Method = ru.ruslan.controller.BookRestController#findOne(Long)

Async:
    Async started = false
     Async result = null

Resolved Exception:
             Type = null

ModelAndView:
        View name = null
             View = null
            Model = null

FlashMap:
       Attributes = null

MockHttpServletResponse:
           Status = 200
    Error message = null
          Headers = [Content-Type:"application/json"]
     Content type = application/json
             Body = {"id":209,"title":"Ruslan","description":"Description","author":"author","isbn":"","printYear":2020,"readAlready":false,"image":"http://example.com/"}
    Forwarded URL = null
   Redirected URL = null
          Cookies = []



```