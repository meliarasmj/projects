/*
Template Name: StarCode & Dashboard Template
Author: StarCode Kh
Website: https://StarCode Kh.in/
Contact: StarCode Kh@gmail.com
File: Form editor inline Js File
*/

//ckeditor Inline
var ckInlineEditor = document.querySelectorAll(".ckeditor-inline")
if (ckInlineEditor) {
    Array.from(ckInlineEditor).forEach(function () {
        InlineEditor
            .create(document.querySelector('.ckeditor-inline'))
            .catch(function (error) {
                console.error(error);
            });
       
    });
}