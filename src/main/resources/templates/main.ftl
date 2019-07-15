<!DOCTYPE html>
<html lang="en">
    <head>
        <title>T6</title>


        <link rel="stylesheet" type="text/css" href="main.css"/>
<!--
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"/>
-->

    </head>
    <body>
        <div class="notification">
            <#if notif?has_content>
            Notification: ${notif}
        </#if>
         </div>
        <h2>Books</h2>
        <div class="addBookForm">
            <form  method="post" action="/main">
                <input type="text" name="title" placeholder="title" />
                <input type="text" name="price" placeholder="price" />
                <input type="text" name="author" placeholder="author" />
                <input type="text" name="description" placeholder="description" />
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit">Add</button>
            </form>
        </div>
        <div style="margin-top:15px; margin-bottom:10px">
            <span id="header">List of books</span>
        <form method="get" action="/undelete">
            <button class="buttons" type="submit">Undelete</button>
        </form>
         </div>
    <table id="books">
                    <tr>
                        <th>Id</th>
                        <th>title</th>
                        <th>price</th>
                        <th>author</th>
                        <th>description</th>
                        <th>action</th>
                    </tr>
                    <#list books as book>
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.price}</td>
                        <td>${book.author}</td>
                        <td>${book.description}</td>
                        <td>
                                <form method="post" action="/remove">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button class="editBtn" type="submit" name="remove" value="${book.id}">Remove</button>
                                </form>
                                <form method="post" action="/edit">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button class="editBtn" type="submit" name="edit" value="${book.id}">Edit</button>
                                </form>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </body>
</html>