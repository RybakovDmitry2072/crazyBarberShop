<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayuot title="О Нас" cssPath="styleAbout">
    <%@include file="/WEB-INF/view/parts/_header.jsp"%>
    <main>
        <section class="main-content">
            <img src="https://i.imgur.com/RsD2o6O.png" alt="Барбершоп Бородинский" class="main-image">
            <h1>ГЛАВНОЕ</h1>
            <p>И если ты считаешь, что барбершоп - это не всем доступный и дорогой мужской клуб, где мужчины затрачивают около 2 часов своего времени на стрижку и оформление бороды, мы развеем твои сомнения. Барбершоп происходит от латинского слова "Barba" - означает борода. И главная отличительная черта барбершопа от салонов красоты и парикмахерских является сам барбер, мастер по стрижкам и бороде.</p>
        </section>
        <section class="stats">
            <div class="stat">
                <p class="number">120</p>
                <p class="label">Записей в день</p>
            </div>
            <div class="stat">
                <p class="number">26872</p>
                <p class="label">Доверяют нам</p>
            </div>
            <div class="stat">
                <p class="number">91</p>
                <p class="label">Общий опыт работы</p>
            </div>
        </section>

        <section class="features">
            <div class="feature">
                <div class="feature-number">1</div>
                <p class="feature-title">Мы ценим время своих клиентов</p>
            </div>
            <div class="feature">
                <div class="feature-number">2</div>
                <p class="feature-title">Предлагаем высокий сервис и обстановку</p>
            </div>
            <div class="feature">
                <div class="feature-number">3</div>
                <p class="feature-title">У нас работают только профессионалы</p>
            </div>
        </section>

        <section class="masters">
            <h2>НАШИ МАСТЕРА</h2>
            <p>Для наших барберов стиль — это образ жизни. Они внимательны к каждой детали, что позволяет им заслужить уважение каждого гостя.</p>
            <div class="swiper-container">
                <div class="swiper-wrapper" id="mastersList">
                    <c:forEach var="master" items="${masters}">
                        <div class="swiper-slide master">
                            <img src="${master.urlImage}" alt="${master.name}" class="master-image">
                            <h3>Барбер ${master.name}</h3>
                            <p class="experience">Стрижёт и бреет ${master.experience} лет</p>
                            <p class="description">${master.about}</p>
                        </div>
                    </c:forEach>
                </div>
                <!-- Add Navigation -->
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </section>
    </main>
    <section class="map">
        <h2>Наше местоположение</h2>
        <div id="map" style="height: 400px; width: 100%;">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1621.3149225867637!2d38.270423065513775!3d46.71139992062883!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40e42ed70c7c437d%3A0x2f2b641e94d6dd6d!2z0YPQuy4g0JrQsNGA0LvQsCDQnNCw0YDQutGB0LAsIDY2LCDQldC50YHQuiwg0JrRgNCw0YHQvdC-0LTQsNGA0YHQutC40Lkg0LrRgNCw0LksIDM1MzY4MA!5e0!3m2!1sru!2sru!4v1735854515074!5m2!1sru!2sru" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
    </section>
</t:mainLayuot>
<%@include file="/WEB-INF/view/parts/_footer.jsp"%>

<!-- Swiper CSS -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">

<!-- Swiper JS -->
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

<!-- Initialize Swiper -->
<script src="${pageContext.request.contextPath}/js/base.js"></script>
