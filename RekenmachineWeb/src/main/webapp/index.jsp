<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="bower_components/bootswatch/cerulean/bootstrap.min.css"/>
    <link rel="stylesheet" href="bower_components/fontawesome/css/font-awesome.min.css"/>
    <style>
        body {
            padding: 20px;
            background: #ffffff url("images/spinning-bg.gif") no-repeat left top;
        }
    </style>
    <title>Toffe Rekenmachine</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="well col-md-6 col-md-offset-3">
            <h1><i class="fa fa-calculator"></i> De toffe rekenmachine</h1>
            <p>Vul een rekenopdracht in.</p>
            <form class="" action="calculator" method="POST">
                <div class="form-group">
                    <input type="text" name="input" class="form-control" placeholder="bijv. 2 + 5 * 3 / 1"
                           value="${ input }" />
                    <div class="help-block">Ondersteunde operaties:
                        <span class="label label-default">+</span>
                        <span class="label label-default">-</span>
                        <span class="label label-default">*</span>
                        <span class="label label-default">/</span>
                        <span class="label label-default">(</span>
                        <span class="label label-default">)</span></div>
                </div>
                <button class="btn btn-primary">Berekenen</button>
            </form>
            <p>${ message }</p>
        </div>
    </div>
</div>

</body>
</html>