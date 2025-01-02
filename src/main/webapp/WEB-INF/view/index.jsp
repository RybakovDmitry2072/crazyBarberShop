<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayuot title="Барбершоп \"Бородинский\"" cssPath="styleIndex">


<%@include file="/WEB-INF/view/parts/_header.jsp"%>

<%--    <div id="confirmationModal" class="modal">--%>
<%--        <div class="modal-content">--%>
<%--            <span class="close-button">&times;</span>--%>
<%--            <p>Ваш заказ подтвержден!</p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <script src="script.js"></script>--%>



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
            <h2 class="index-content-title">Портфолио</h2>
            <div class="gallery">
                <figure class="gallery-content">
                    <img src="https://i.imgur.com/QBY4tac.jpg" width="286" height="164" alt="фото">
                </figure>
                <button class="btn gallery-prev" disabled>Назад</button>
                <button class="btn gallery-next">Вперед</button>
            </div> <!---gallery--->
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
            <a class="btn" href="https://maps.app.goo.gl/EYWiGkEmZXHSvPwe8" target="_blank">Как проехать</a>
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