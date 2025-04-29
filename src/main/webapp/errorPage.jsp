<%@ page isErrorPage="true" %>
<html>
<head>
  <title>Greška u aplikaciji</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8d7da;
      color: #721c24;
      text-align: center;
      padding: 50px;
    }
    h1 {
      color: #721c24;
    }
    .error-box {
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
      display: inline-block;
    }
    a {
      color: #007bff;
      text-decoration: none;
      font-weight: bold;
    }
    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="error-box">
  <h1>🚨 Došlo je do greške!</h1>
  <p>Nažalost, desila se neočekivana greška prilikom obrade vašeg zahtjeva.</p>
  <p>Detalji: <%= exception.getMessage() %></p>
  <p>Možete se vratiti na <a href="products">početnu stranicu</a>.</p>
</div>
</body>
</html>