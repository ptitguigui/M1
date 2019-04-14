<%@page import="car.tp4.entity.Book" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="car.tp4.entity.Command" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mes commandes</title>
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
            <li class="nav-item">
                <a class="nav-link" href="books">Gestion</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="command">Commander</a>
            </li>
            <li class="nav-item active">
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
        <h2>Mes commandes : </h2>
        <br/>
        <table class="table" border="1">
            <thead class="thead-dark">
            <tr>
                <th>N° commande</th>
                <th>Livre</th>
            </tr>
            </thead>
            <tbody>
            <%
                Collection<Command> commands = (Collection<Command>) request.getAttribute("commands");
                int i = 1;
                for (Command command : commands) {
                    Collection<Book> books = command.getBooks();
                    out.print("<tr><td> " + i + "</td>");
                    for (Book book : books) {
                        out.print("<td> " + book.getAuthor() + "</td>");
                        out.print("<td> " + book.getTitle() + "</td>");
                        out.print("<td> " + book.getDate() + "</td>");
                        out.print("<td> " + book.getQuantity() + " </td>");
                    }
                    out.print("<tr>");
                }
            %>
            </tbody>
        </table>

    </form>
</div>
</body>

<style>
    .table {
        text-align: center;
    }
</style>
</html>
