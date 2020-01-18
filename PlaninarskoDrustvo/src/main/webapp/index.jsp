<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/PD/resources/style/LexaN.css">
    <link rel="stylesheet" href="/PD/resources/style/style.css">
    <script src="/PD/resources/script/script.js"></script>
    <title>Početna</title>
</head>
<body>
    <script>getHeader();</script>
    
    <div class="logo">
        <img src="/PD/resources/imgs/logo.png" alt="">
    </div>
    
    <hr>
    
    <div class="sekcija planinar">
        <h1 class="nijeclan txt-color-basic">Još uvek niste član društva ili je istekla članarina</h1>
        <div class="clan">
            <center>
                <h1 class="txt-color-basic">Dobrodošli $/planinar/</h1>
            </center>
            <div class="table">
                <table class="table-basic">
                    <tr>
                        <th></th>
                        <th>Rezervisani domovi</th>
                        <th></th>
                    </tr>
                    <tr>
                        <td colspan="3">$/dom/</td>
                    </tr>
                </table>
            </div>
            <div class="table">
                <table class="table-basic">
                    <tr>
                        <th></th>
                        <th>Znamenitosti koje ste posećivali</th>
                        <th></th>
                    </tr>
                    <tr>
                        <td colspan="3">${znamenitost}</td>
                    </tr>
                </table>
            </div>
            <div class="table">
                <table class="table-basic">
                    <tr>
                        <th></th>
                        <th>IzveÅ¡taji koje ste pisali</th>
                        <th></th>
                    </tr>
                    <tr>
                        <td colspan="3">$/izvestajKojiNijeSlika/</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div class="sekretar">
        <center>
            <h1 class="txt-color-basic">Dobrodošli $/sekretar/</h1>
        </center>
        <div class="sekcija zahtevi">
            <h1 class="txt-color-basic">Zahtevi za ućlanjenje u društvo</h1>
            <div class="table">
                <table class="table-basic">
                    <tr>
                        <th>Ime</th>
                        <th>Prezime</th>
                        <th></th>
                    </tr>
                    <tr>
                        <td>${ime}</td>
                        <td>${prezime}</td>
                        <td>Učlani</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="sekcija clanovi">
            <h1 class="txt-color-basic">Članovi društva</h1>
            <div class="table">
                <table class="table-basic">
                    <tr>
                        <th>Ime</th>
                        <th>Prezime</th>
                        <th>Učlanjen od</th>
                        <th>Učlanjen do</th>
                        <th>Produži članarinu</th>
                    </tr>
                    <tr>
                        <td>${ime}</td>
                        <td>${prezime}</td>
                        <td>${datumOd}</td>
                        <td>${datumDo}</td>
                        <td>Produži</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="sekcija statistika">
            <h1 class="txt-color-basic">Statistika rezervisanih noćenja</h1>
            <div class="table">
                <table class="table-basic">
                    <tr>
                        <th>Planina</th>
                        <th></th>
                        <th>Broj noćenja</th>
                    </tr>
                    <tr>
                        <td>${planina}</td>
                        <td>:</td>
                        <td>${broj}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <script src="/PD/resources/script/LexaN.js"></script>
</body>
</html>