<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css"/>
    <style>
        body {
            padding: 20px;
        }
    </style>
<title>The Awesome Calculator</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="well col-md-6 col-md-offset-3">
            <h1>The Inner Space Calculator</h1>
            <p>Space-separate your values!!</p>

            <form action="awesome-calculator" method="post">
                <div class="form-group">
                    <input type="text" name="calcinput" class="form-control" placeholder="Voer uw berekening in." />
                </div>
                <button class="btn btn-primary">Berekenen</button>
            </form>

    		<p><%=request.getAttribute("message") %></p>
            
            <p class="pull-right"><a href=".">go back</a></p>
        </div>
    </div>
</div>

</body>
</html>