<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/PD/resources/style/LexaN.css">
    <link rel="stylesheet" href="/PD/resources/style/style.css">
    <script src="/PD/resources/script/script.js"></script>
    <title>Znamenitosti</title>
</head>
<body>
    <%@include file="./tmp/header.jsp" %>
    
    <div class="opis">
        <h1 class="txt-color-basic">Informacije o izabranoj znamenitost</h1>
        <h2 class="txt-color-basic"><i>Tip znamenitosti:</i> ${znamenitost.tip}</h2>
        <h2 class="txt-color-basic"><i>Opis:</i> ${znamenitost.opis}</h2>
        <h2 class="txt-color-basic"><i>Galerija:</i></h2>
        <div class="galerija">
        	<c:forEach var="i" items="${znamenitost.slikas}">
            	<img src="/PD/myImage/imageDisplayIMG?idSlika=${i.idSlika}" alt="Slika znamenitosti">
        	</c:forEach>          
        </div>
    </div>
    
    
    <c:if test="${znamenitost.zakazujeSe == true}">
    <hr>
    <div class="sekcija">
        <h1 class="txt-color-basic">Za ovu znamenitost neophodno je rezervisati termin posete</h1>
        <div class="center">
            <label class="lbl-basic">Odaberi termin posete</label>
            <div class="select-basic" style="width: 250px;">
                <select>
                    <option value="0">Odaberi...</option>
                    <c:forEach var="i" items="termins">
                    	<option value="${i.pocetak}-${i.kraj}">${i.pocetak} - ${i.kraj}</option>
                    </c:forEach>
                </select>
            </div>
            <br><br>
            <button class="btn-basic-out">Zakazi termin</button> 		<!-- KONTROLER ZA ZAKAZIVANJE TERMINA -->
        </div>
    </div>
    </c:if>
    <c:if test="${znamenitost.zakazujeSe != true}">
    <div class="sekcija">  												<!-- KONTROLER ZA POSETU ZNAMENITOSTI -->
    	<h2 class="txt-color-basic"><i>Označi kao posećeno:</i></h2> 
    	<a href="/PD/user/posecuje?idZ=${znamenitost.idZnamenitost}&idK=${korisnik.idKorisnik}"><button class="btn-basic-out">Označi</button></a>
    </div>
    </c:if>
    
    <hr>
    <div class="sekcija">
        <h1 class="txt-color-basic">Komentari</h1>
        <div class="center">
            <label class="lbl-basic">Ostavi svoj komentar</label>
            <form action="/PD/user/ostaviKomentar" method="post">
            	<input type="hidden" name="idZ" value="${znamenitost.idZnamenitost}">
            	<input type="hidden" name="username" value="${korisnik.korisnickoIme}">
	            <textarea name="komentar" id="" cols="25" rows="10" class="input-f-basic" placeholder="Sadržaj komentara..."></textarea> <br><br>
	            <button type="submit" class="btn-basic-out">Komentariši</button> 
            </form>
        </div>
        
        <div class="listakomentara">
        	<c:if test="${znamenitost.komentarises != null && fn:length(znamenitost.komentarises) gt 0}">
	        	<c:forEach var="i" items="${znamenitost.komentarises}">
	        	<div class="komentar">
	                <span class="autor">${i.rezervise.korisnik.ime} ${i.rezervise.korisnik.prezime}</span>
	                <p>${i.komentar}</p>
	            </div>
	        	</c:forEach>
        	</c:if>
        	<c:if test="${znamenitost.komentarises == null || fn:length(znamenitost.komentarises) == 0}">
        		<h2 class="txt-color-basic">Trenutno nema komentara za ovu znamenitost</h2>
        	</c:if>
        </div>
    </div>
    
    
    <script src="/PD/resources/script/LexaN.js"></script>
</body>
</html>