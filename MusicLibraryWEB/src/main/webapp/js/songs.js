//function getPeopleTable(firstName, tableId) {
//    var xhttp = new XMLHttpRequest();
//    xhttp.onreadystatechange = function () {
//        if (this.readyState === 4 && this.status === 200) {
//            document.getElementById(tableId).innerHTML = this.responseText;
//        }
//    };
//
//    xhttp.open("GET", "songServlet?firstName=" + document.getElementById(firstName).value, true);
//    xhttp.send();
//}
//ogarnąć update metodę@@!!
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
function updateSong(songID, songTitle, authorName, authorSurname, songAlbum, songRelease, songTime, tableID, errorInfo) {
    // Reset error message
    document.getElementById(errorInfo).innerHTML = "&nbsp;";
    
    // Pobieranie wartości z elementów HTML
    var ar1 = document.getElementById(songID).value;
    var ar2 = document.getElementById(songTitle).value;
    var ar3 = document.getElementById(authorName).value;
    var ar4 = document.getElementById(authorSurname).value;
    var ar5 = document.getElementById(songAlbum).value;
    var ar6 = document.getElementById(songRelease).value;
    var ar7 = document.getElementById(songTime).value;

    

    if (!ar6.match(/^(0[1-9]|[12][0-9]|3[01])\.(0[1-9]|1[0-2])\.(19|20)\d{2}$/)) {
        document.getElementById(errorInfo).innerHTML = "Invalid date format! Use dd.MM.yyyy.";
        return;
    }

    if (!ar7.match(/^\d+$/) || parseInt(ar6, 10) <= 0) {
        document.getElementById(errorInfo).innerHTML = "Song time must be a positive number.";
        return;
    }

    // Utworzenie zapytania AJAX
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                document.getElementById(tableID).innerHTML = this.responseText;
            } else if (this.status === 400) {
                document.getElementById(errorInfo).innerHTML = this.responseText;
            } else {
                document.getElementById(errorInfo).innerHTML = "An unexpected error occurred.";
            }
        }
    };

    // Enkodowanie parametrów URL dla bezpieczeństwa
    var params = new URLSearchParams({
        songID: ar1,
        songTitle: ar2,
        authorName: ar3,
        authorSurname: ar4,
        songAlbum: ar5,
        songRelease: ar6,
        songTime: ar7
    });

    xhttp.open("GET", "songUpdate?" + params, true);
    xhttp.send();
}

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

//function getAttendanceListTable(tableId) {
//    var xhttp = new XMLHttpRequest();
//    xhttp.onreadystatechange = function () {
//        if (this.readyState === 4 && this.status === 200) {
//            document.getElementById(tableId).innerHTML = this.responseText;
//        }
//    };
//
//    xhttp.open("GET", "attendanceList", true);
//    xhttp.send();
//}