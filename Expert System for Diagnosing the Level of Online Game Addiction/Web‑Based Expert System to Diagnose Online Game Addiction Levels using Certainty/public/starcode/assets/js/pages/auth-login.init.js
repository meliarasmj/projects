/*
Template Name: StarCode & Dashboard Template
Author: StarCode Kh
Version: 1.1.0
Website: https://StarCode Kh.in/
Contact: StarCode Kh@gmail.com
File: auth login init Js File
*/

document
    .getElementById("signInForm")
    .addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent the form from submitting

        // Get input values
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        // Define regular expressions for validation
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        const strongPasswordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

        // Validate email/email and password
        const emailError = document.getElementById("email-error");
        const passwordError = document.getElementById("password-error");
        const successAlert = document.getElementById("successAlert");
        const rememberMeCheckbox = document.getElementById("checkboxDefault1");
        const rememberError = document.getElementById("remember-error");

        emailError.classList.add("hidden"); // Hide any previous error message
        passwordError.classList.add("hidden");
        successAlert.classList.add("hidden"); // Hide the success message

        if (!emailRegex.test(email)) {
            emailError.classList.remove("hidden"); // Show error message
        } else if (!strongPasswordRegex.test(password)) {
            passwordError.classList.remove("hidden"); // Show error message
        } else {
            // Form is valid, show the success message
            successAlert.classList.remove("hidden");
        }

        if (!rememberMeCheckbox.checked) {
            // Prevent the form from submitting if the checkbox is not checked
            event.preventDefault();
            rememberError.classList.remove("hidden");
        } else {
            rememberError.classList.add("hidden");
        }
    });
