<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="./style/LexaN.css">
    <link rel="stylesheet" href="./style/style.css">
    <script src="./script/script.js"></script>
    <title>IzveÅ¡taji</title>
</head>
<body>
    <header class="header-basic-out">
        <a href="index.html">PoÄetna</a>
        <a href="reservation.html">Rezervacije</a>
        <img src="./imgs/logo.png" alt="">
        <a href="#">IzveÅ¡taji</a>
        <form action="" method="POST">
            <input type="submit" value="Odjavi se" style="border: 0; background-color: transparent; font-size: 15px; cursor: pointer; outline: none;" class="txt-color-basic">
        </form>
    </header>

    <div class="sekcija">
        <h1 class="txt-color-basic">IzveÅ¡taji/Slike za planinu...</h1>
        <div class="center">
            <label class="lbl-basic">Odaberi planinu</label>
            <div class="select-basic" style="width: 250px;">
                <select>
                    <option value="0">Odaberi...</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                </select>
            </div>
            <br><br>
            <button class="btn-basic-out">PretraÅ¾i</button>
        </div>
    </div>
    
    <hr>

    <div class="sekcija">
        <h1 class="txt-color-basic">Postavi svoj sadrÅ¾aj za ${planinu}</h1>
        <form action="">
            <label class="r-btn-basic">Ostavi izveÅ¡taj
                <input type="radio" checked="checked" name="radio" onclick="showIzvestaj()">
                <span class="checkmark"></span>
            </label><br><br>
            <label class="r-btn-basic">OkaÄi slike
                <input type="radio" name="radio" onclick="showSlike()">
                <span class="checkmark"></span>
            </label>
        </form><br><br>
        <form class="ostaviKomentar center">
            <label class="lbl-basic">IzveÅ¡taj</label>
            <textarea name="izvestaj" id="" cols="25" rows="10" class="input-f-basic" placeholder="SadrÅ¾aj..."></textarea> <br><br>
            <input class="btn-basic-out" type="submit" value="Postavi">
        </form>
        <form class="ostaviSliku center">
            <span>
                <label class="lbl-basic-invert">Izaberi sliku: </label>
                <input type="file" name="slika" id="" class="inputfile" accept=".png, .jpg, .jpeg" onchange="izborSike()"/>
                <label for="file">IZABERI</label>
            </span> 
            <label class="lbl-basic selektovanaSlika">Slika: ${naziv}</label>
            <br>
            <input class="btn-basic-out" type="submit" value="Postavi" id="postaviSliku">
        </form>

        <div class="sekcija">
            <h1 class="txt-color-basic">Galerija izveÅ¡taja i slika za planinu: ${planina}</h1>
            <div class="galerija">
                <div class="img">
                    <img src="./imgs/avatar.png" alt="">
                    <span><i>Autor: Ime Prezime</i></span>
                </div>
                <div class="izv">
                    <div class="sadrzaj">
                        <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Incidunt porro, officia, fugiat ab autem quo cumque animi, aspernatur deserunt cupiditate ad facere aperiam at ipsa. Reprehenderit corrupti quae quo perferendis.</p>
                    </div>
                    <span>Ime Prezime</span>
                </div>
                <div class="img">
                    <img src="./imgs/avatar.png" alt="">
                    <span><i>Autor: Ime Prezime</i></span>
                </div>
                <div class="img">
                    <img src="./imgs/avatar.png" alt="">
                    <span><i>Autor: Ime Prezime</i></span>
                </div>
                <div class="img">
                    <img src="./imgs/avatar.png" alt="">
                    <span><i>Autor: Ime Prezime</i></span>
                </div>
                <div class="img">
                    <img src="./imgs/avatar.png" alt="">
                    <span><i>Autor: Ime Prezime</i></span>
                </div>
            </div>
        </div>
    </div>
    
    <script src="./script/LexaN.js"></script>
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