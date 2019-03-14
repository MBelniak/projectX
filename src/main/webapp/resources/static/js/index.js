window.onload = function () {
    document.cookie = pushURL(document.cookie, window.location.href);
};