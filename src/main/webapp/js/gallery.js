// gallery.js
document.addEventListener('DOMContentLoaded', function() {
    const images = document.querySelectorAll('.gallery-content img');
    const prevButton = document.querySelector('.gallery-prev');
    const nextButton = document.querySelector('.gallery-next');
    let currentIndex = 0;

    function showImage(index) {
        images.forEach((img, i) => {
            img.classList.toggle('active', i === index);
        });
        prevButton.disabled = (index === 0);
        nextButton.disabled = (index === images.length - 1);
    }

    prevButton.addEventListener('click', function() {
        if (currentIndex > 0) {
            currentIndex--;
            showImage(currentIndex);
        }
    });

    nextButton.addEventListener('click', function() {
        if (currentIndex < images.length - 1) {
            currentIndex++;
            showImage(currentIndex);
        }
    });

    // Initial display
    showImage(currentIndex);
});
