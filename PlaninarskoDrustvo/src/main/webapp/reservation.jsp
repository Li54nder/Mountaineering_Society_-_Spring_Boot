<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="sr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="./style/LexaN.css">
    <link rel="stylesheet" href="./style/style.css">
    <script src="./script/script.js"></script>
    <title>Rezervacija</title>
</head>
<body>
    <script>getHeader();</script>
    
    <div class="sekcija">
        <h1 class="txt-color-basic">Odaberite planinu na kojoj Å¾elite da rezerviÅ¡ete smeÅ¡taj</h1>
        <div class="center">
            <label class="lbl-basic">Odabrati planinu</label>
            <div class="select-basic" style="width: 250px;">
                <select>
                    <option value="0">Planina...</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                </select>
            </div>
            <br>
            <label class="lbl-basic">Odabrati datum dolaska na planinu</label>
            <input type="date" class="input-d-basic">
            <br><br>
            <button class="btn-basic-out">PretraÅ¾i</button>
        </div>
    </div>

    <hr>

    <div class="sekcija">
        <h1 class="txt-color-basic">Planina ${taita}</h1>
        <p class="txt-color-basic">Prikazani su svi domovi koji su dostupni od izabranog datuma u kojima moÅ¾ete rezervisati smeÅ¡taj klikom na link 'RezerviÅ¡i'.</p class="txt-color-basic">
        <p class="txt-color-basic">Ispod raspoloÅ¾ivih domova se nalazi lista staza koje su dostupne na izabranoj planini. Na karticama o stazama moÅ¾ete videti mapu i opis staze, a pored toga i spisak znamenitosti koje se nalaze na toj stazi.</p class="txt-color-basic">

        <h2 class="txt-color-basic">Domovi:</h2>
        <div class="grid">
            <div class="card-basic-out">
                <center>
                    <h2>${dom}</h2>
                    <div>
                        <span>Kapacitet doma: ${kapacitet}</span><br><br>
                        <a href="" class="txt-color-basic">RezerviÅ¡i</a>
                    </div>
                </center>
            </div>
            <div class="card-basic-out">
                <center>
                    <h2>${dom}</h2>
                    <div>
                        <span>Kapacitet doma: ${kapacitet}</span><br><br>
                        <a href="" class="txt-color-basic">RezerviÅ¡i</a>
                    </div>
                </center>
            </div>
        </div>
        <h2 class="txt-color-basic">Staze:</h2>
        <div class="grid">
            <div class="card-basic-out">
                <center>
                    <img src="./imgs/avatar.png" alt="avatar">
                    <h2>TeÅ¾ina: ${tezina}</h2>
                    <div>
                        <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.</p>
                        <h3>Znamenitosti:</h3>
                        <a href="sights.html" class="txt-color-basic">Znamenitost ta i ta...</a><br>
                        <a href="sights.html" class="txt-color-basic">Znamenitost ta i ta...</a><br>
                    </div>
                </center>
            </div>
            <div class="card-basic-out">
                <center>
                    <img src="./imgs/avatar.png" alt="avatar">
                    <h2>TeÅ¾ina: ${tezina}</h2>
                    <div>
                        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Molestiae adipisci provident rem repellat dignissimos perspiciatis porro quasi deserunt nostrum rerum expedita quos, voluptatem cumque itaque dolore qui officia corrupti unde?</p>
                        <h3>Znamenitosti:</h3>
                        <a href="sights.html" class="txt-color-basic">Znamenitost ta i ta...</a><br>
                        <a href="sights.html" class="txt-color-basic">Znamenitost ta i ta...</a><br>
                    </div>
                </center>
            </div>
        </div>
    </div>
    
    <script src="./script/LexaN.js"></script>
</body>
</html>