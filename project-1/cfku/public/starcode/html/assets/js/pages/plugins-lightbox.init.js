/*
Template Name: StarCode & Dashboard Template
Author: StarCode Kh
Website: https://StarCode Kh.in/
Contact: StarCode Kh@gmail.com
File: plugins lightbox init js
*/

//basic example
const lightbox = GLightbox({
    touchNavigation: true,
    loop: true,
    autoplayVideos: true,
});

//description
const lightboxDescription = GLightbox({
    touchNavigation: true,
    loop: true,
    autoplayVideos: true,
    selector: '.description'
});

//video
var lightboxVideo = GLightbox({
    selector: '.video'
});