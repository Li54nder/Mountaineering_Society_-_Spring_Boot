<header class="header-basic-out">
    <a href="/PD/guest">Početna</a>
    <a href="/PD/user/getRezervacije">Rezervacije</a>
    <img src="/PD/resources/imgs/logo.png" alt="Planinarsko | Društvo">
    <a href="/PD/user/getReport">Izveštaji</a>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	    <input type="submit" value="Odjavi se" style="font-family: 'Times New Roman'; border: 0; background-color: transparent; font-size: 16px; cursor: pointer; outline: none;" class="txt-color-basic"/>
	</form:form>
</header>