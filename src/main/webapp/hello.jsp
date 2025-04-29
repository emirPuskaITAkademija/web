<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Vozdra raja s IT Akademije</h1>
<h2>Trentni datum je <%=LocalDate.now()%></h2>
<h2>Trenutni date and time <%=LocalDateTime.now()%></h2>
<h3>Session <%=session.getId()%></h3>
</body>
</html>
