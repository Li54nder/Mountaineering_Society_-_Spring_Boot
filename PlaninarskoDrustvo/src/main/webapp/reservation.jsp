<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="sr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/PD/resources/style/LexaN.css">
    <link rel="stylesheet" href="/PD/resources/style/style.css">
    <script src="/PD/resources/script/script.js"></script>
    <title>Rezervacija</title>
</head>
<body>
    <%@include file="./tmp/header.jsp" %>
    
    <form action="/PD/user/pretragaPlanana" method="GET" class="sekcija">
        <h1 class="txt-color-basic">Odaberite planinu na kojoj želite da rezervišete smeštaj</h1>
        <div class="center">
            <label class="lbl-basic">Odabrati planinu</label>
            <div class="select-basic" style="width: 250px;">
                <select name="idPlanina">
                    <option value="0">Planina...</option>
                    <c:forEach var="i" items="${planine}">
                    	<option value="${i.idPlanina}">${i.naziv}</option>
                    </c:forEach>
                </select>
            </div>
            <br>
            <label class="lbl-basic">Odabrati datum dolaska na planinu</label>
            <input name="date" type="date" class="input-d-basic" required>
            <br>
            <label class="lbl-basic">Uneti broj koliko dana ostajete na planini</label>
            <input name="brDana" type="number" class="input-f-basic" title="Unesite broj" required>
            <br><br>
            <button class="btn-basic-out" type="submit">Pretraži</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

	<core:if test="${planina != null}">
	    <hr>
	
	    <div class="sekcija">
	        <h1 class="txt-color-basic">Planina ${planina.naziv}</h1>
	        <p class="txt-color-basic">Prikazani su svi domovi koji su dostupni od izabranog <b>datuma</b> u kojima možete rezervisati smeštaj klikom na link 'Rezerviši'.</p class="txt-color-basic">
	        <p class="txt-color-basic">Ispod raspoloživih domova se nalazi lista staza koje su dostupne na izabranoj planini. Na karticama o stazama možete videti mapu i opis staze, a pored toga i spisak znamenitosti koje se nalaze na toj stazi.</p class="txt-color-basic">
	
	        <h2 class="txt-color-basic">Domovi:</h2>
	        <div class="grid">
	        	<c:forEach var="i" items="${planina.doms}">
	        	<div class="card-basic-out">
	                <center>
	                    <h2>${i.naziv}</h2>
	                    <div>
	                        <span>Kapacitet doma: ${i.kapacitet}</span><br><br>
	                        <a href="/PD/user/rezervisiDom?idD=${i.idDom}&username=${korisnik.korisnickoIme}" class="txt-color-basic">Rezerviši</a>
	                    </div>
	                </center>
	            </div>
	        	</c:forEach>
	        </div>
	        <h2 class="txt-color-basic">Staze:</h2>
	        <div class="grid">
	        	<c:forEach var="i" items="${planina.stazas}">
	            <div class="card-basic-out">
	                <center>
	                	<img src="/PD/myImage/imageDisplay?idStaza=${i.idStaza}" />
	                    <h2>Težina: ${i.tezina}</h2>
	                    <div>
	                        <p>${i.opis}</p>
	                        <h3>Znamenitosti:</h3>
	                        <c:forEach var="j" items="${i.znamenitosts}">
	                        	<a href="/PD/user/getSights?idZ=${j.idZnamenitost}&username=${korisnik.korisnickoIme}" class="txt-color-basic">${j.tip}</a><br>
	        				</c:forEach>
	                    </div>
	                </center>
	            </div>
	        	</c:forEach>
	        </div>
	    </div>
	</core:if>
	
    <script src="/PD/resources/script/LexaN.js"></script>
</body>
</html>