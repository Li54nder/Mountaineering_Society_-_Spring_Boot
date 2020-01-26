<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="model.Rezervise"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy.");
%>
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

    <sec:authorize access="hasAnyRole('Gost', 'Planinar')">
		<%@include file="./tmp/header.jsp" %>
    </sec:authorize>
    <sec:authorize access="hasRole('Sekretar')">
        <center>
            <h1 class="txt-color-basic">Dobrodošli ${korisnik.ime} ${korisnik.prezime}</h1>
        	<br><br>
        	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			    <input type="submit" value="Odjavi se" style="font-family: 'Times New Roman'; border: 0; background-color: transparent; font-size: 16px; cursor: pointer; outline: none;" class="txt-color-basic"/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form:form>
        </center>
        <hr>
    </sec:authorize>
    
    <div class="logo">
        <img src="/PD/resources/imgs/logo.png" alt="Planinarsko | Društvo">
    </div>
    
    <hr>
    
    <sec:authorize access="hasRole('Gost')">
    	<div class="sekcija planinar">
	        <h1 class="nijeclan txt-color-basic">Još uvek niste član društva ili je istekla članarina</h1>
	    </div>
    </sec:authorize>
    
    <sec:authorize access="hasRole('Planinar')">
	    <div class="sekcija planinar">
	        <div class="clan">
	            <center>
	                <h1 class="txt-color-basic">Dobrodošli ${korisnik.ime} ${korisnik.prezime}</h1>
	            </center>
	            <div class="table">
	                <table class="table-basic">
	                    <tr>
	                        <th></th>
	                        <th>Rezervisani domovi</th>
	                        <th></th>
	                    </tr>
	                    <c:forEach var="i" items="${korisnik.rezervises}">
	                    <tr>
	                        <td style="width: 33%">${i.dom.naziv} (${i.dom.planina.naziv})</td>
	                        <c:set var="pocetak" value="${i.pocetak}" />
	                        <td style="width: 34%">OD: <%= formater.format(pageContext.getAttribute("pocetak")) %></td>
	                        <c:set var="kraj" value="${i.kraj}" />
	                        <td style="width: 33%">OD: <%= formater.format(pageContext.getAttribute("kraj")) %></td>
	                    </tr>
	                    </c:forEach>
	                </table>
	            </div>
	            <div class="table">
	                <table class="table-basic">
	                    <tr>
	                        <th></th>
	                        <th colspan="3">Znamenitosti za koje ste zakazali posetu</th>
	                        <th></th>
	                    </tr>
	                    <c:forEach var="i" items="${korisnik.rezervises}">
	                    <c:forEach var="j" items="${i.zakazujes}">
		                    <tr>
		                    	<td></td>
		                        <td>${j.termin.znamenitost.tip}</td>
		                    	<td>${j.termin.znamenitost.opis} (${j.termin.pocetak} - ${j.termin.kraj})</td>
		                    	<td><a href="/PD/user/posecuje?idZ=${j.termin.znamenitost.idZnamenitost}&username=${korisnik.korisnickoIme}">Označi kao posećeno</a></td>
		                    	<td></td>
		                    </tr>
	                    </c:forEach>
	                    </c:forEach>
	                </table>
	            </div>
	            <div class="table">
	                <table class="table-basic">
	                    <tr>
	                        <th></th>
	                        <th>Znamenitosti koje ste posećivali</th>
	                        <th></th>
	                    </tr>
	                    <c:forEach var="i" items="${korisnik.rezervises}">
	                    <c:forEach var="j" items="${i.obilazis}">
		                    <tr>
		                        <td colspan="3">${j.znamenitost.tip} (${j.znamenitost.opis})</td>
		                    </tr>
	                    </c:forEach>
	                    </c:forEach>
	                </table>
	            </div>
	            <div class="table">
	                <table class="table-basic">
	                    <tr>
	                        <th></th>
	                        <th>Izveštaji koje ste pisali</th>
	                        <th></th>
	                    </tr>
	                    <c:forEach var="i" items="${korisnik.rezervises}">
	                    <c:forEach var="j" items="${i.izvestajs}">
	                    <tr>
	                    	<core:if test="${j.sadrzaj != null}">
	                        	<td colspan="3">${j.sadrzaj}</td>
	                    	</core:if>
	                    </tr>
	                    </c:forEach>
	                    </c:forEach>
	                </table>
	            </div>
	        </div>
	    </div>
    </sec:authorize>

    <sec:authorize access="hasRole('Sekretar')">
	    <div class="sekretar">
	        <div class="sekcija zahtevi">
	            <h1 class="txt-color-basic">Zahtevi za učlanjenje u društvo</h1>
	            <div class="table">
	                <table class="table-basic">
	                    <tr>
	                        <th>Ime</th>
	                        <th>Prezime</th>
	                        <th></th>
	                    </tr>
	                    <c:forEach var="i" items="${zahtevi}">
	                    <tr>
	                        <td>${i.ime}</td>
	                        <td>${i.prezime}</td>
	                        <td><a href="/PD/admin/uclani?username=${i.korisnickoIme}">Učlani</a></td> 
	                    </tr>
	                    </c:forEach>
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
	                    <c:forEach var="i" items="${clanovi}">
	                    <tr>
	                        <td>${i.ime}</td>
	                        <td>${i.prezime}</td>
	                        <td>${i.clanarina.pocetak}</td>
	                        <td>${i.clanarina.kraj}</td>
	                        <td><a href="/PD/admin/produziClanarinu?username=${i.korisnickoIme}">Produži</a></td>
	                    </tr>
	                    </c:forEach>
	                </table>
	            </div>
	        </div>
	        <div class="sekcija statistika">
	            <h1 class="txt-color-basic">Statistika rezervisanih noćenja</h1>
	            <div class="table">
	                <table class="table-basic">
	                    <tr>
	                        <th>Planina</th>
	                        <th>Broj poseta</th>
	                        <th>Broj noćenja</th>
	                    </tr>
	                    <%! int br; long brNocenja;%>
	                    <c:forEach var="p" items="${planine}">
	                    <% br = 0; brNocenja = 0;%>
	                    <c:forEach var="d" items="${p.doms}">
	                    <c:forEach var="r" items="${d.rezervises}">
	                    	<% 
	                    	Rezervise rez = (Rezervise) pageContext.getAttribute("r");
	                    	long diff = rez.getKraj().getTime() - rez.getPocetak().getTime();
	                    	brNocenja += TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	                    	%>
	                    	<% br++; %>
	                    </c:forEach>
	                    </c:forEach>
		                    <tr>
		                        <td>${p.naziv}</td>
		                        <td><% out.println(br); %></td>
		                        <td><% out.println(brNocenja); %></td>
		                    </tr>
	                    </c:forEach>
	                </table>
	            </div>
	        </div>
	        <div class="sekcija">
	            <h1 class="txt-color-basic">Preuzimanje spiskova rezervisanih termina za znamenitosti</h1>
	            <div class="table">
	                <table class="table-basic">
	                    <tr>
	                        <th>Znamenitost za koje je neophodna rezervacija</th>
	                        <th></th>
	                        <th>Generiši izveštaj</th>
	                    </tr>
	                    <c:forEach var="z" items="${sveZnamenitosti}">
		                    <tr>
		                    	<td>${z.tip} (${z.staza.planina.naziv})</td>
		                    	<td>:</td>
		                    	<td><a href="/PD/admin/generisiIzvestaj?idZ=${z.idZnamenitost}">Generiši</a></td>
		                    </tr>
	                    </c:forEach>
	               	</table>
	           	</div>
	        </div>
	    </div>
	</sec:authorize>
	
    <script src="/PD/resources/script/LexaN.js"></script>
</body>
</html>