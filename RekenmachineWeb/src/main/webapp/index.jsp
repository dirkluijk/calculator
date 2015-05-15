<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="bower_components/bootswatch/cerulean/bootstrap.min.css"/>
    <link rel="stylesheet" href="bower_components/fontawesome/css/font-awesome.min.css"/>
    <style>
        body {
            padding: 20px;
            background: #ffffff url("images/spinning-bg.gif") no-repeat left top;
        }
    </style>
    <title>Awesome Calc!!</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="well col-md-6 col-md-offset-3">
            <h1><i class="fa fa-calculator"></i> The Awesome Calculator</h1>
            <p>Enter your math expression.</p>
            <form class="" action="calculator" method="POST">
                <div class="form-group">
                    <input type="text" name="input" class="form-control" placeholder="e.g. 2 + 5 * 3 / 1" />
                    <div class="help-block">Supported operators:
                        <span class="label label-default">+</span>
                        <span class="label label-default">-</span>
                        <span class="label label-default">*</span>
                        <span class="label label-default">/</span>
                        <span class="label label-default">(</span>
                        <span class="label label-default">)</span></div>
                </div>
                <button class="btn btn-primary">Calculate</button>
            </form>
            <p><%=request.getAttribute("message") %></p>
        </div>
    </div>
</div>

</body>
</html>