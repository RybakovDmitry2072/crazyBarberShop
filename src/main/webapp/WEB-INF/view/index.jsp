<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayuot title="Барбершоп \"Бородинский\"" cssPath="styleIndex">


<%@include file="/WEB-INF/view/parts/_header.jsp"%>

<main class="container">
    <div class="index-logo">
        <img src="https://i.imgur.com/RsD2o6O.png" height="204px" width="370px"; alt="Барбершоп Бородинский">
    </div>
    <section class="features clearfix">
        <div class="features-item">
            <b class="features-name">Быстро</b>
            <p>Мы делаем свою работу быстро! Два часа пролетят незаметно и Вы - счастливый обладатель стильной стрижки-минутки! </p>
        </div>
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
</t:mainLayuot></t>

<%@include file="/WEB-INF/view/parts/_footer.jsp"%>
</html>