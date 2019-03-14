function pushURL(cookie, URL) {
    if (cookie != undefined) {
        var cookies = document.cookie.split(/;/g);              //get distinct cookies
        var i;
        for (i = 0; i < cookies.length; i++) {
            var cookiePart = cookies[i].split(/=/g);
            if (cookiePart[0] == "previousURL") {               //check the name of cookie
                cookie = cookies[i] + "|" + URL;                              //cookies[i] == previousURL=URL|URL|URL...
                if (cookies[i].split("|").length === 4)                                       //delete the first url
                    cookie = cookies[i].substr(0, cookiePart[0].length + 1) + cookies[i].substr(cookiePart[0].length + 2 + cookiePart[1].split(/\|/g)[0].length, cookies[i].length);
                break;
            }
        }
        if (i === cookies.length)
            cookie = "previousURL=" + URL + "; path=/";
    }
    else
        cookie = "previousURL=" + URL + "; path=/";

    return cookie + "; path=/";
}

function getURL(cookie) {
    if (cookie != undefined) {
        var cookies = document.cookie.split(/;/g);
        var i;
        for (i = 0; i < cookies.length; i++) {
            var cookiePart = cookies[i].split(/=/g);
            if (cookiePart[0] == "previousURL") {
                return cookiePart[1].split(/\|/g).pop();
            }
        }
        if (i === cookies.length)
            return null;
    }
    else
        return null;
}

function eraseLastURL(cookie) {
    if (cookie != undefined) {
        var cookies = document.cookie.split(/;/g);
        var i;
        for (i = 0; i < cookies.length; i++) {
            var cookiePart = cookies[i].split(/=/g);
            if (cookiePart[0] == "previousURL") {
                cookie = cookies[i].substr(0, cookies[i].length - cookiePart[1].split(/\|/g).pop().length - 1);
                break;
            }
        }
        if (i === cookies.length)
            return null;
    }
    else
        return null;
    return cookie + "; path=/";
}
