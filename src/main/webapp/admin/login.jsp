<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="../css/custom_admin.css" rel = stylesheet type = "text/css">
    <title></title>
</head>
<body>

    <div id = form>
        <div id = errorMessage><%if (request.getAttribute("error") != null) out.print(request.getAttribute("error"));%></div>
        <form method="post" action = admin>
            <div class="input-block">
                <div class="input-prepend">
                    <span class="add-on">Login</span>
                    <input class="span2" id="prependedInput" name = login type="text" placeholder="Enter a login">
                </div>
                <br/>

                <div class="input-prepend">
                    <span class="add-on">Password</span>
                    <input class="span2" id="prependedInput2" name = password type="text" placeholder="Enter a password">
                </div>
                <br/>

                <div class="col-lg-10 col-lg-offset-2">
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary" id="findAll">Submit</button>
                </div>
            </div>
        </form>
    </div>

</body>
</html>