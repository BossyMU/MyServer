<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<h2>Register</h2>
<p>${error}</p>
<form action="/add" method="post">
    Username:<br/>
    <input type="text" name="username"/>
    <br/>
    Password:<br/>
    <input type="text" name="password">
    <br><br>
    <input type="submit" value="Submit">
</form>
<form action="/home" method="get">
    <input type="submit" value="Cancel">
</form>
</body>
</html>
