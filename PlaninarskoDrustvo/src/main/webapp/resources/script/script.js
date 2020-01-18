
//Add ACTION for form for LOG OUT
function getHeader() {
	const html =
	'<header class="header-basic-out">\
        <a href="/PD/Uloge/index/getIndex">Početna</a>\
        <a href="/PD/Uloge/planinar/getRezervacije">Rezervacije</a>\
        <img src="/PD/resources/imgs/logo.png" alt="Planinarsko | Društvo">\
        <a href="/PD/Uloge/planinar/getReport">Izveštaji</a>\
        <form action="${pageContext.request.contextPath}/logout" method="POST">\
            <input type="submit" value="Odjavi se" style="border: 0; background-color: transparent; font-size: 15px; cursor: pointer; outline: none;" class="txt-color-basic">\
        </form>\
    </header>"';
	document.write(html); //action="/LoginLogout/logout"
}