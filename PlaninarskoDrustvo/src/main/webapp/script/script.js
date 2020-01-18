
//Add ACTION for form for LOG OUT
function getHeader() {
	const html =
	'<header class="header-basic-out">\
        <a href="index.jsp">Početna</a>\
        <a href="reservation.jsp">Rezervacije</a>\
        <img src="./imgs/logo.png" alt="Planinarsko | Društvo">\
        <a href="report.jsp">Izveštaji</a>\
        <form action="" method="POST">\
            <input type="submit" value="Odjavi se" style="border: 0; background-color: transparent; font-size: 15px; cursor: pointer; outline: none;" class="txt-color-basic">\
        </form>\
    </header>"';
	document.write(html);
}