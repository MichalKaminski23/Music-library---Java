/**
 * Sends an HTTP GET request to fetch and display songs in the given table.
 * 
 * @param {string} tableID The ID of the table element where songs will be displayed.
 */
function getAllSongsTable(tableID) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById(tableID).innerHTML = this.responseText;
        }
    };

    xhttp.open("GET", "songs", true);
    xhttp.send();
}

/**
 * Sends an HTTP GET request to add a new song with the data from the input fields.
 * If the request is successful, the table of songs is updated. Otherwise, an error message is shown.
 * 
 * @param {string} songTitle The ID of the input field for the song title.
 * @param {string} authorName The ID of the input field for the author's first name.
 * @param {string} authorSurname The ID of the input field for the author's surname.
 * @param {string} songAlbum The ID of the input field for the song's album.
 * @param {string} songRelease The ID of the input field for the song's release date.
 * @param {string} songTime The ID of the input field for the song's duration.
 * @param {string} tableId The ID of the table element to update after adding the song.
 * @param {string} errorInfo The ID of the element where error messages will be displayed.
 */
function addNewSong(songTitle, authorName, authorSurname, songAlbum, songRelease, songTime, tableId, errorInfo) {
    document.getElementById(errorInfo).innerHTML = "&nbsp;";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log(this);
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById(tableId).innerHTML = this.responseText;
        }
        if (this.readyState === 4 && this.status === 400) {
            document.getElementById(errorInfo).innerHTML = this.responseText;
        }
    };

    var ar1 = document.getElementById(songTitle).value;
    var ar2 = document.getElementById(authorName).value;
    var ar3 = document.getElementById(authorSurname).value;
    var ar4 = document.getElementById(songAlbum).value;
    var ar5 = document.getElementById(songRelease).value;
    var ar6 = document.getElementById(songTime).value;

    xhttp.open("GET", "songInsert?songTitle=" + ar1 + "&authorName=" + ar2 + "&authorSurname=" + ar3 + "&songAlbum=" + ar4 + "&songRelease=" + ar5 + "&songTime=" + ar6, true);
    xhttp.send();
}

/**
 * Sends an HTTP GET request to update an existing song's information based on its ID.
 * If the request is successful, the table of songs is updated. Otherwise, an error message is shown.
 * 
 * @param {string} songID The ID of the song to update.
 * @param {string} songTitle The ID of the input field for the song title.
 * @param {string} authorName The ID of the input field for the author's first name.
 * @param {string} authorSurname The ID of the input field for the author's surname.
 * @param {string} songAlbum The ID of the input field for the song's album.
 * @param {string} songRelease The ID of the input field for the song's release date.
 * @param {string} songTime The ID of the input field for the song's duration.
 * @param {string} tableID The ID of the table element to update after updating the song.
 * @param {string} errorInfo The ID of the element where error messages will be displayed.
 */
function updateSong(songID, songTitle, authorName, authorSurname, songAlbum, songRelease, songTime, tableID, errorInfo) {
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

    var ar1 = document.getElementById(songTitle).value;
    var ar2 = document.getElementById(authorName).value;
    var ar3 = document.getElementById(authorSurname).value;
    var ar4 = document.getElementById(songAlbum).value;
    var ar5 = document.getElementById(songRelease).value;
    var ar6 = document.getElementById(songTime).value;

    xhttp.open("GET", "songUpdate?songID=" + songID + "&songTitle=" + ar1 + "&authorName=" + ar2 + "&authorSurname=" + ar3 + "&songAlbum=" + ar4 + "&songRelease=" + ar5 + "&songTime=" + ar6, true);
    xhttp.send();
}

/**
 * Sends an HTTP GET request to delete a song based on its ID.
 * If the request is successful, the table of songs is updated. Otherwise, an error message is shown.
 * 
 * @param {string} songID The ID of the song to delete.
 * @param {string} tableID The ID of the table element to update after deleting the song.
 * @param {string} errorInfo The ID of the element where error messages will be displayed.
 */
function deleteSong(songID, tableID, errorInfo) {
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

    xhttp.open("GET", "songDelete?id=" + songID, true);
    xhttp.send();
}

///**
// * Retrieves the value of a specified cookie.
// * 
// * @param {string} name The name of the cookie to retrieve.
// * @returns {string|null} The value of the cookie, or null if not found.
// */
//function getCookie(name) {
//    let nameEQ = name + "=";
//    let ca = document.cookie.split(';');
//    for (let i = 0; i < ca.length; i++) {
//        let c = ca[i];
//        while (c.charAt(0) === ' ')
//            c = c.substring(1, c.length);
//        if (c.indexOf(nameEQ) === 0)
//            return c.substring(nameEQ.length, c.length);
//    }
//    return null;
//}
//
///**
// * Sets a cookie with a specified name, value, and expiration time.
// * 
// * @param {string} name The name of the cookie to set.
// * @param {string} value The value to store in the cookie.
// * @param {number} days The number of days the cookie should be valid.
// */
//function setCookie(name, value, days) {
//    let date = new Date();
//    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
//    let expires = "expires=" + date.toUTCString();
//    document.cookie = name + "=" + value + ";" + expires + ";path=/";
//}
//
///**
// * Loads the theme from the cookie and displays a message if it was loaded from the cookie.
// */
//function loadThemeFromCookie() {
//    const theme = getCookie("theme") || "green";
//    if (theme) {
//        document.body.className = theme;
//        document.getElementById("themeInfo").style.display = "block";
//    }
//}
//
///**
// * Sets the theme for the page and stores the selected theme in a cookie.
// * 
// * @param {string} theme The theme to apply to the page.
// */
//function setTheme(theme) {
//    document.body.className = theme;
//    setCookie("theme", theme, 7);
//    document.getElementById("themeInfo").style.display = "none";
//}

/**
 * This function is executed when the window is loaded. 
 * It retrieves the theme from cookies (or defaults to "green" if not found), 
 * sets the theme on the page, and then loads the songs table by calling the getAllSongsTable function.
 */
window.onload = function () {
    getAllSongsTable('tableSong');
    //loadThemeFromCookie();
};
