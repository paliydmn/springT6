<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <title>Title</title>

  <link rel="stylesheet" type="text/css" href="main.css"/>
</head>
<body>
  <h2>List of books</h2>


  <div>
    <form method="post" id="book"  action="/apply">
        <input type="text" name="title" value="${book.title}" />
        <input type="text" name="price" value="${book.price}" />
        <input type="text" name="author" value="${book.author}" />
        <input type="text" name="description" value="${book.description}" />
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" name="edit" value="${book.id}">Apply</button>
    </form>
  </div>

</body>
</html>