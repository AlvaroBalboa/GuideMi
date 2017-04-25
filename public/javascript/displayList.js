/**
 * Created by souporman on 4/24/17.
 */
function showArt(artArray) {
    for (var i in artArray) {
        var elem = $("<a>");
        // var elem = $("<img>");
        // elem.attr("src", photo.filename,"height=&#822042&#8221 width=&#822042&#8221");
        // $("#photosdisplay").append(elem);
        elem.attr("href", "photosdisplay/" + artArray[i].name);
        elem.text(artArray[i].name);
        $("#artList").append(elem);
        var elem2 = $("<br>");
        $("#artList").append(elem2);
    }
}

$.get("/rest/results/art_gallery", showArt);