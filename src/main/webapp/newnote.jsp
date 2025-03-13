<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uk.ac.ucl.model.ModelFactory, uk.ac.ucl.model.Model" %>
<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Model model = ModelFactory.getModel();
        model.addNote(title, content);

        response.sendRedirect("index.html");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New Note</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h2>Create New Note</h2>
<form action="newnote.jsp" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br><br>
    <label for="content">Content:</label><br>
    <textarea id="content" name="content" rows="10" cols="30" required></textarea><br><br>
    <input type="submit" value="Create Note">
</form>
</body>
</html>