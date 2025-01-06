<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <h2 class="index-content-title">Портфолио</h2>
                <div class="gallery portfolio">
                    <figure class="gallery-content">
                        <img src="${portfolioImgUrls[0].url}" width="286" height="164" alt="фото">
                    </figure>
                    <button class="btn gallery-prev portfolio" disabled>Назад</button>
                    <button class="btn gallery-next portfolio">Вперед</button>
                </div>
            </div>
            <div class="index-content-right">
                <h2 class="index-content-title">Фотогалерея</h2>
                <div class="gallery premises">
                    <figure class="gallery-content">
                        <img src="${premisesImgUrls[0].url}" width="286" height="164" alt="фото">
                    </figure>
                    <button class="btn gallery-prev premises" disabled>Назад</button>
                    <button class="btn gallery-next premises">Вперед</button>
                </div>
            </div>
        </div>
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
            <h2 class="index-content-title">Наши услуги</h2><br>
            <p>Мы предлагаем широкий спектр услуг для мужчин, включая стрижки, бритье и уход за волосами.</p>
            <c:forEach var="category" items="${categoryDtoList}" varStatus="status">
                <c:if test="${status.count <= 3}">
                    <div class="service">
                        <h3>${category.name}</h3>
                        <h4>${category.price} рублей</h4><br>
                    </div>
                </c:if>
            </c:forEach>
            <a class="btn" href="${pageContext.request.contextPath}/service">Посмотреть все услуги</a>
        </div>
    </div> <!---index-content--->
</main>
</t:mainLayuot></t>

<%@include file="/WEB-INF/view/parts/_footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        var portfolioImages = [
            <c:forEach var="image" items="${imageUrls}">
            "${image.url}",
            </c:forEach>
        ];
        var premisesImages = [
            <c:forEach var="image" items="${premisesImgUrls}">
            "${image.url}",
            </c:forEach>
        ];

        var currentPortfolioIndex = 0;
        var currentPremisesIndex = 0;

        function updateImage(images, index, galleryClass) {
            $(galleryClass + " img").attr("src", images[index]);
            $(galleryClass + " .gallery-prev").prop("disabled", index === 0);
            $(galleryClass + " .gallery-next").prop("disabled", index === images.length - 1);
        }

        $(".gallery-next.portfolio").click(function() {
            if (currentPortfolioIndex < portfolioImages.length - 1) {
                currentPortfolioIndex++;
                updateImage(portfolioImages, currentPortfolioIndex, ".gallery.portfolio");
            }
        });

        $(".gallery-prev.portfolio").click(function() {
            if (currentPortfolioIndex > 0) {
                currentPortfolioIndex--;
                updateImage(portfolioImages, currentPortfolioIndex, ".gallery.portfolio");
            }
        });

        $(".gallery-next.premises").click(function() {
            if (currentPremisesIndex < premisesImages.length - 1) {
                currentPremisesIndex++;
                updateImage(premisesImages, currentPremisesIndex, ".gallery.premises");
            }
        });

        $(".gallery-prev.premises").click(function() {
            if (currentPremisesIndex > 0) {
                currentPremisesIndex--;
                updateImage(premisesImages, currentPremisesIndex, ".gallery.premises");
            }
        });

        // Инициализация первых картинок
        updateImage(portfolioImages, currentPortfolioIndex, ".gallery.portfolio");
        updateImage(premisesImages, currentPremisesIndex, ".gallery.premises");
    });
</script>
</html>