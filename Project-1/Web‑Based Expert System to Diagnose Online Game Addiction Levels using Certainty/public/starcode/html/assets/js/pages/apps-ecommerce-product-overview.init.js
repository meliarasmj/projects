/*
Template Name: StarCode & Dashboard Template
Author: StarCode Kh
Version: 1.1.0
Website: https://StarCode Kh.in/
Contact: StarCode Kh@gmail.com
File: apps ecommerce overview init Js File
*/

// Get all elements with the 'count-element' class
const countButtons = document.querySelectorAll('.count-button');

countButtons.forEach((element) => {
    element.addEventListener('click', () => {
        const numberDisplay = element.querySelector('.count-number');
        
        let numberOfProcesses = parseInt(numberDisplay.textContent);
        numberOfProcesses++;
        numberDisplay.textContent = numberOfProcesses;
    });
});
