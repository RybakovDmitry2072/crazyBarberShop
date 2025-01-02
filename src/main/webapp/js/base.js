document.addEventListener('DOMContentLoaded', function () {
    var swiper = new Swiper('.swiper-container', {
        slidesPerView: 'auto', // Автоматическое определение количества слайдов
        spaceBetween: 30,
        loop: true, // Включаем бесконечную прокрутку
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });
});
