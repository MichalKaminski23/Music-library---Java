/**
 * Retrieves all albums and updates the specified table with the response.
 *
 * @param {string} tableID - The ID of the table element to update.
 */
function getAllAlbumsTable(tableID) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById(tableID).innerHTML = this.responseText;
        }
    };

    xhttp.open("GET", "albums", true);
    xhttp.send();
}

/**
 * Deletes an album and updates the table or displays an error message based on the response.
 *
 * @param {number} albumID - The ID of the album to delete.
 * @param {string} tableID - The ID of the table element to update.
 * @param {string} errorInfo - The ID of the element to display error information.
 */
function deleteAlbum(albumID, tableID, errorInfo) {
    document.getElementById(errorInfo).innerHTML = "&nbsp;";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById(tableID).innerHTML = this.responseText;
        }
        if (this.readyState === 4 && this.status === 400) {
            document.getElementById(errorInfo).innerHTML = this.responseText;
        }
    };

    xhttp.open("GET", "albumDelete?id=" + albumID, true);
    xhttp.send();
}

/**
 * Retrieves the value of a specified cookie.
 * 
 * @param {string} name - The name of the cookie to retrieve.
 * @returns {string|null} The value of the cookie, or null if not found.
 */
function getCookie(name) {
    let nameEQ = name + "=";
    let ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ')
            c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0)
            return c.substring(nameEQ.length, c.length);
    }
    return null;
}

/**
 * Sets a cookie with a specified name, value, and expiration time.
 * 
 * @param {string} name - The name of the cookie to set.
 * @param {string} value - The value to store in the cookie.
 * @param {number} days - The number of days the cookie should be valid.
 */
function setCookie(name, value, days) {
    let date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    let expires = "expires=" + date.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
}

/**
 * Loads the theme from the cookie and displays a message if it was loaded from the cookie.
 */
function loadThemeFromCookie() {
    const theme = getCookie("theme") || "green";
    if (theme) {
        document.body.className = theme;
        document.getElementById("themeInfo").style.display = "block";
    }
}

/**
 * Sets the theme for the page and stores the selected theme in a cookie.
 * 
 * @param {string} theme - The theme to apply to the page.
 */
function setTheme(theme) {
    document.body.className = theme;
    setCookie("theme", theme, 7);
    document.getElementById("themeInfo").style.display = "none";
}

/**
 * This function is executed when the window is loaded. 
 * It retrieves the theme from cookies (or defaults to "green" if not found), 
 * sets the theme on the page, and then loads the songs table by calling the getAllSongsTable function.
 */
window.onload = function () {
    getAllAlbumsTable('tableAlbum');
    loadThemeFromCookie();
};