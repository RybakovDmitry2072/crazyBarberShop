<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title> Барбершоп "Бородинский"</title>
<%--    <link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700&amp;subset=cyrillic" rel="stylesheet">--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styleIndexCSS.css">
</head>


<body>
<header class="main-header">
    <div class="container clearfix">
        <nav class="main-navigation">
            <ul>
                <li><a href="#">Главная</a></li>
                <li><a href="#">Информация</a></li>
                <li><a href="#">Новости</a></li>
                <li><a href="#">Прайс-лист</a></li>
                <li><a href="#">Магазин</a></li>
                <li><a href="#">Контакты</a></li>
            </ul>
        </nav>
        <div class="user-block">
            <a class="user-login" href="#">Вход</a>
        </div> <!---user-block--->
    </div> <!---container--->
</header>


<main class="container">
    <div class="index-logo">
        <img src="https://i.imgur.com/RsD2o6O.png" height="204px" width="370px"; alt="Барбершоп Бородинский">
    </div>   <!---index-logo--->
    <section class="features clearfix">
        <div class="features-item">
            <b class="features-name">Быстро</b>
            <p>Мы делаем свою работу быстро! Два часа пролетят незаметно и Вы - счастливый обладатель стильной стрижки-минутки! </p>
        </div> <!---features-item--->
        <div class="features-item">
            <b class="features-name">Круто</b>
            <p>Забудьте как вы стриглись раньше. Мы сделаем из вас звезду футбола или кино! Во всяком случае внешне. </p>
        </div> <!---features-item--->
        <div class="features-item">
            <b class="features-name">Дорого</b>
            <p>Наши мастера - профессионалы своего дела и не могут стоить дешево. К тому же, разве цена не даёт определенный статус? </p>
        </div> <!---features-item--->
    </section> <!---features--->

    <div class="index-content clearfix">
        <div class="index-content-left">
            <h2 class="index-content-title"> Новости </h2>
            <ul class="news-preview">
                <li>
                    <p>Нам наконец завезли ягермастер! Теперь вы можете пропустить стаканчик во время стрижки </p>
                    <time datetime="2016-01-11"> 11 января</time>
                </li>
                <li>
                    <p> В нашей команде пополнение, Борис "Бритва" Стригунец, обладатель множества титулов и наград пополнил наши стройные ряды</p>
                    <time datetime="2016-01-19"> 19 января</time>
                </li>
            </ul>
            <a class="btn" href="#"> Все новости </a>
        </div> <!---index-content-left--->

        <div class="index-content-right">
            <h2 class="index-content-title">Фотогалерея</h2>
            <div class="gallery">
                <figure class="gallery-content">
                    <img src="https://i.imgur.com/QBY4tac.jpg" width="286" height="164" alt="фото">
                </figure>
                <button class="btn gallery-prev" disabled>Назад</button>
                <button class="btn gallery-next">Вперед</button>
            </div> <!---gallery--->
        </div> <!---index-content-right--->
    </div> <!---index-content--->

    <div class="index-content clearfix">
        <div class="index-content-left">
            <h2 class="index-content-title"> Контактная информация </h2>
            <p>
                Барбершоп "Бородинский" <br>
                Адрес: г. Санкт-Петербург, Б. Конюшенная, д. 19/8<br>
                Телефон: +7 (495) 666-02-08 </p>
            <p>
                Время работы:<br>
                ПН-ПТ: с 10:00 до 22:00<br>
                СБ-ВС: с 10:00 до 19:00
            </p>
            <a class="btn" href="#">Как проехать</a>
            <a class="btn" href="#">Обратная связь</a>
        </div> <!---index-content-left--->

        <div class="index-content-right">
            <h2 class="index-content-title"> Записаться </h2>
            <p> Укажите желаемую дату и время и мы свяжемся с вами для подтверждения брони.</p>
            <form class="appointment-form" action="#" method="post">
                <input type="text" name="data" value="" placeholder="Дата">
                <input type="text" name="time" value="" placeholder="Время">
                <input type="text" name="name" value="" placeholder="Ваше имя">
                <input type="tel" name="phone-number" value="" placeholder="Телефон">
                <button class="btn" type="submit"> Отправить </button>
            </form>
        </div>  <!---index-content-right--->
    </div> <!---index-content--->
</main>

<footer class="main-footer">
    <div class="container clearfix">
        <section class="footer-contacts">
            <p>
                Барбершоп "Бородинский"<br>
                Адрес: г. Санкт-Петербург, Б. Конюшенная, д. 19/8<br>
                <a href="#"> Как нас найти?</a><br>
                Телефон: +7 (495) 666-02-08
            </p>
        </section>

        <section class="footer-social">
            <p>Давайте дружить!</p>
            <a class="social-btn social-btn-vk" href="#">vk</a>
            <a class="social-btn social-btn-fb" href="#">fb</a>
            <a class="social-btn social-btn-inst" href="#">inst</a>
        </section>

    </div>
</footer>
</body>
</html>