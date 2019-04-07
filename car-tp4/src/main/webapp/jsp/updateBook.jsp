<%@page import="car.tp4.entity.Book" %>
<%@page import="java.util.Collection" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Creer un livre</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
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
    <a class="navbar-brand" href="#">CAR-TP4</a>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Accueil</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="books">Lister</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="createBook">Creer</a>
            </li>
        </ul>
    </div>
</nav>
<div>
    <form action="updateBook">
        <div>
            <h1>Détails du livre :</h1>
        </div>
        <div>
            <div>
                <label>Titre : </label> <input type="text"
                                               name="titleBook"
                                               id="titleBook"
                                               value=<%=request.getParameter("titleBook") %>>
            </div>
            <div>
                <label>Auteur : </label> <input type="text"
                                                name="authorBook"
                                                id="authorBook"
                                                value=<%=request.getParameter("authorBook") %>>
            </div>
            <div>
                <label>Date de parution : </label> <input type="text"
                                                          name="dateBook"
                                                          id="dateBook"
                                                          value=<%=request.getParameter("dateBook") %>>
            </div>
            <div>
                <button type="button" class="btn btn-primary">Modifier</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
