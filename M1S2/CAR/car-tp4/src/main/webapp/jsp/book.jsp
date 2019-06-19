<%@page import="car.tp4.entity.Book" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Liste des livres</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<body>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">CAR-TP4</a>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Accueil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="createBook">Creer</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="books">Gestion</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="command">Commander</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="commands">Mes commandes</a>
            </li>
        </ul>
        <span class="navbar-text">
            <a class="nav-link" href="basket">Panier</a>
        </span>
    </div>
</nav>
<div>
    <form id="form" name="form" method="post" action="books">
        <br/>
        <h2>Liste des livres : </h2>
        <br/>
        <table class="table" border="1">
            <thead class="thead-dark">
            <tr>
                <th>
                    <select id="author" name="author" class="browser-default custom-select"
                            onChange="document.getElementById('form').submit()">
                        <option>Auteur</option>
                        <option></option>
                        <%
                            List<String> authors = (List<String>) request.getAttribute("authors");
                            for (String author : authors) {
                                out.print("<option  value= \"" + author + "\"> " + author + "</option>");
                            }
                        %>
                    </select>
                </th>
                <th>Titre <input type="text" name="title">
                    <button type="submit"><i class="fa fa-search"></i></button>
                </th>
                <th>Date de parution</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                Collection<Book> books = (Collection<Book>) request.getAttribute("books");
                for (Book book : books) {
                    if ((request.getParameter("author") == null || request.getParameter("author").equals("Auteur") || request.getParameter("author").equals(""))
                            || (request.getParameter("author").equals(book.getAuthor()))) {
                        out.print("<tr><td> " + book.getAuthor() + "</td>");
                        out.print("<td> " + book.getTitle() + "</td>");
                        out.print("<td> " + book.getDate() + "</td>");
                        out.print("<td> <a href=\"updateBook?id=" + book.getId() + "\">Détails</a> </td></tr>");
                    }
                }
            %>
            </tbody>
        </table>

    </form>
</div>
</body>

<style>
    h2{
        padding-left: 10px;
        margin: auto;
    }
    .table {
        text-align: center;
    }
</style>
</html>
