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
    <title>Izveštaji</title>
</head>
<body>
    <%@include file="./tmp/header.jsp" %>

    <div class="sekcija">
        <h1 class="txt-color-basic">Izveštaji/Slike za planinu...</h1>
        <form class="center" action="/PD/user/izvestajiZaPlaninu" method="get">
            <label class="lbl-basic">Odaberi planinu</label>
            <div class="select-basic" style="width: 250px;">
                <select name="idP">
                    <option value="0">Odaberi...</option>
                    <c:forEach var="p" items="${planine}">
                    	<option value="${p.idPlanina}">${p.naziv}</option>
                    </c:forEach>
                </select>
            </div>
            <br><br>
            <button type="submit" class="btn-basic-out">Pretraži</button>
        </form>
    </div>
    
    <c:if test="${izvestaji != null}">
	    <hr>
	    <div class="sekcija">
	        <h1 class="txt-color-basic">Postavi svoj sadržaj za planinu ${trazenaPlanina.naziv}</h1>
	        
	        <form action="">
	            <label class="r-btn-basic">Ostavi izveštaj
	                <input type="radio" checked="checked" name="radio" onclick="showIzvestaj()">
	                <span class="checkmark"></span>
	            </label><br><br>
	            <label class="r-btn-basic">Okači slike
	                <input type="radio" name="radio" onclick="showSlike()">
	                <span class="checkmark"></span>
	            </label>
	        </form><br><br>
	        
	        <form class="ostaviKomentar center" action="/PD/user/postaviIzvestaj" method="post">
	            <label class="lbl-basic">Izveštaj</label>
	            <input type="hidden" name="username" value="${korisnik.korisnickoIme}">
	            <input type="hidden" name="idP" value="${trazenaPlanina.idPlanina}">
	            <textarea name="izvestaj" id="" cols="25" rows="10" class="input-f-basic" placeholder="Sadržaj..."></textarea> <br><br>
	            <input class="btn-basic-out" type="submit" value="Postavi">
	        </form>
	        
	        <form class="ostaviSliku center" action="/PD/user/postaviSliku" method="post" enctype="multipart/form-data">
	            <input type="hidden" name="username" value="${korisnik.korisnickoIme}">
	            <input type="hidden" name="idP" value="${trazenaPlanina.idPlanina}">
	            <span>
	                <label class="lbl-basic-invert">Izaberi sliku: </label>
	                <input type="file" name="file" id="file" class="inputfile" accept=".png, .jpg, .jpeg" onchange="izborSike()"/>
	                <label for="file">IZABERI</label>
	            </span> 
	            <label class="lbl-basic selektovanaSlika">Slika: ${naziv}</label>
	            <br>
	            <input class="btn-basic-out" type="submit" value="Postavi" id="postaviSliku">
	        </form>
	
			<br><br>
	        <h1 class="txt-color-basic">Galerija izveštaja i slika za planinu ${trazenaPlanina.naziv}</h1>
	        <div class="sekcija">
	            <c:if test="${izvestaji == null || fn:length(izvestaji) == 0}">
	        		<h2 class="txt-color-basic">Trenutno nema izveštaja za odabranu planinu</h2>
	        	</c:if>
	            <div class="galerija">
	            	<c:forEach var="i" items="${izvestaji}">
	            		<c:if test="${i.sadrzaj == null}">
	            		<c:forEach var="s" items="${i.slikas}">
			                <div class="img">
			                    <img src="/PD/myImage/imageDisplayIMG?idSlika=${s.idSlika}" alt="${trazenaPlanina.naziv} | izvestaj">
			                    <span><i>Autor: ${i.rezervise.korisnik.ime} ${i.rezervise.korisnik.prezime}</i></span>
			                </div>
	            		</c:forEach>
	            		</c:if>
	            		<c:if test="${i.sadrzaj != null}">
			                <div class="izv">
			                    <div class="sadrzaj">
			                        <p>${i.sadrzaj}</p>
			                    </div>
			                    <span>${i.rezervise.korisnik.ime} ${i.rezervise.korisnik.prezime}</span>
			                </div>
	            		</c:if>
	            	</c:forEach>
	            </div>
	        </div>
	    </div>
    </c:if>
    
    <script src="/PD/resources/script/LexaN.js"></script>
    <script>
        function showIzvestaj() {
            const komentar = document.querySelector(".ostaviKomentar");
            const slike = document.querySelector(".ostaviSliku");
            slike.style.display = "none";
            komentar.style.display = "flex";
        }
        function showSlike() {
            const komentar = document.querySelector(".ostaviKomentar");
            const slike = document.querySelector(".ostaviSliku");
            komentar.style.display = "none";
            slike.style.display = "flex";
        }

        function izborSike() {
            const file = document.querySelector('.inputfile').files.item(0); 
            const btn = document.querySelector("#postaviSliku");
                const lbl = document.querySelector(".selektovanaSlika");
            const type = file.type;
            if(type === "image/jpeg" || type === "image/pjpeg" || type === "image/png") {
                btn.disabled = false;
                const name = file.name;
                lbl.innerHTML = name;
                lbl.style.opacity = "1";
            } else {
                btn.disabled = true;
                lbl.innerHTML = "Dozvoljene ekstenzije za fajlove su JPG/JPEG i PNG";
                lbl.style.opacity = "1";
            }
        }
    </script>
</body>
</html>