document.addEventListener('DOMContentLoaded', function() {
    const videoElement = document.createElement('video');
    videoElement.src = '../../background/soccerBackground.mp4';
    videoElement.loop = true;
    videoElement.muted = true;
    videoElement.autoplay = true;
    videoElement.style.objectFit = 'cover';

    const container = document.querySelector('.swiper-container');
    container.appendChild(videoElement);
  });