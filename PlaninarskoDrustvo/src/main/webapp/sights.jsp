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
    <title>Znamenitosti</title>
</head>
<body>
    <header class="header-basic-out">
        <a href="index.html">PoÄetna</a>
        <a href="reservation.html">Rezervacije</a>
        <img src="./imgs/logo.png" alt="">
        <a href="report.html">IzveÅ¡taji</a>
        <form action="" method="POST">
            <input type="submit" value="Odjavi se" style="border: 0; background-color: transparent; font-size: 15px; cursor: pointer; outline: none;" class="txt-color-basic">
        </form>
    </header>
    <div class="opis">
        <h1 class="txt-color-basic">Znamenitost: ${taita}</h1>
        <h2 class="txt-color-basic"><i>Tip znamenitosti:</i> ${tip}</h2>
        <h2 class="txt-color-basic"><i>Opis:</i></h2>
        <p style="text-align: justify;">Lorem ipsum dolor sit amet consectetur adipisicing elit. Pariatur dicta vitae accusantium fugiat quia totam non illum rerum, eius a excepturi, inventore explicabo quam perspiciatis nostrum impedit porro nam nulla. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Quae in nostrum labore? Vel, voluptatum quam fugit, ratione expedita quibusdam sint eos quas nihil eaque mollitia, minima veniam corporis ducimus velit.</p>
        <h2 class="txt-color-basic"><i>Galerija:</i></h2>
        <div class="galerija">
            <img src="./imgs/avatar.png" alt="">
            <img src="./imgs/avatar.png" alt="">
            <img src="./imgs/avatar.png" alt="">  
            <img src="./imgs/avatar.png" alt="">             
        </div>
    </div>
    <hr>
    <div class="sekcija">
        <h1 class="txt-color-basic">Za ovu znamenitost neophodno je rezervisati termin posete</h1>
        <div class="center">
            <label class="lbl-basic">Odaberi termin posete</label>
            <div class="select-basic" style="width: 250px;">
                <select>
                    <option value="0">Odaberi...</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                </select>
            </div>
            <br><br>
            <button class="btn-basic-out">Zakazi termin</button>
        </div>
    </div>
    <hr>
    <div class="sekcija">
        <h1 class="txt-color-basic">Komentari</h1>
        <div class="center">
            <label class="lbl-basic">Ostavi svoj komentar</label>
            <textarea name="" id="" cols="25" rows="10" class="input-f-basic" placeholder="SadrÅ¾aj..."></textarea> <br><br>
            <button class="btn-basic-out">KomentariÅ¡i</button>
        </div>
        <div class="listakomentara">
            <div class="komentar">
                <span class="autor">Ime Prezime</span>
                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ducimus minima sunt explicabo suscipit aliquid maxime ea recusandae delectus qui harum blanditiis fugiat, ullam reprehenderit illo dolor in architecto, pariatur non.</p>
            </div>
            <div class="komentar">
                <span class="autor">Ime Prezime</span>
                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ducimus minima sunt explicabo suscipit aliquid maxime ea recusandae delectus qui harum blanditiis fugiat, ullam reprehenderit illo dolor in architecto, pariatur non.</p>
            </div>
        </div>
    </div>
    
    
    <script src="./script/LexaN.js"></script>
</body>
</html>