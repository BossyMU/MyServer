<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<h2>Edit</h2>
<p>${error}</p>
<form action="/edit" method="post">
    Username: ${username}<br/>
    <%--<input type="text" name="usernameEdit"/>--%>
    <br/>
    Password:<br/>
    <input type="text" name="passwordEdit">
    <br><br>
    <input type="hidden" name="usernameEdit" value="${username}">
    <input type="submit" value="Submit">
</form>
<form action="/" method="get">
    <input type="submit" value="Cancel">
</form>
</body>
</html>
