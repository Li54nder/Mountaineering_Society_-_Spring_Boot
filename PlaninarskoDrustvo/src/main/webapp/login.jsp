<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/PD/resources/style/LexaN.css">
    <link rel="stylesheet" href="/PD/resources/style/style.css">
    <title>Prijava</title>
</head>
<body>
    <img src="/PD/resources/imgs/logo.png" alt="" class="background">
    <div class="login-center">
        <div class="card-basic-out" style="width: 300px; background-color: rgba(255, 255, 255, 0.7);">
            <center>
                <h2>Prijavi se</h2>
                <form action="${pageContext.request.contextPath}/login" method="POST">
                    <label class="lbl-basic ">Korisnicko ime</label><br>
                    <input type="username" name="username" class="input-f-basic" title="Unesite korisnicko ime" pattern="[A-Za-z\d]+" required> <br><br>
                    <label class="lbl-basic ">Sifra</label><br>
                    <input type="password" name="password" class="input-f-basic" minlength="5"  title="Unesite Šifru" required> <br><br>
                    <button class="btn-basic-out" type="submit">Prijava</button><br><br>
                    <a href="#" class="txt-color-basic" onclick="registracija()"><small>Nemate nalog? Registruj se!</small></a>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </center>
         </div>
    </div>
    <script>
        function registracija() {
        	document.title = "Registracija";
            const form = document.getElementsByTagName('form')[0];
            form.removeAttribute("action");
            form.setAttribute("action", "/PD/registration");
            const h2 = document.getElementsByTagName('h2')[0];
            const btn = document.querySelector('.btn-basic-out');
            const a = document.getElementsByTagName('a')[0];
            const lbl = document.querySelectorAll('.lbl-basic')[0];

            h2.innerHTML = "Registurj se";
            btn.innerHTML = "Registracija";
            a.style.display = "none";

            lbl.outerHTML = '\
            <label class="lbl-basic">Ime i Prezime</label><br>\
            <input type="text" name="punoime" class="input-f-basic" title="Unesite ime pa prezime odvojeno razmakom" pattern="[A-Za-zŠšĐđČčĆćŽž]+[ ][A-Za-zŠšĐđČčĆćŽž]+" required> <br><br>' + lbl.outerHTML;
        }
    </script>
</body>
</html>