/**
 * Created by souporman on 4/24/17.
 */

function displayList() {
    var x = document.getElementById("attraction_type").selectedIndex;
    var y = document.getElementById("attraction_type").options;
    var selectedItem=y[x].text;
    console.log(selectedItem);
    if(selectedItem=='Art Gallery'){
        setTimeout(artlist,5000);
        function artlist() {
            $.get("/rest/results/art_gallery", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    $("#artList").append('<li class="list-group-item"><i class="icon-ok text-info"></i> '+data[i] +'</li>');
                }
            });
        }
    }
    else if(selectedItem=='mosque'){
        setTimeout(mosquelist,5000);
        function mosquelist() {
            $.get("/rest/results/mosque", function (data) {
                for (var i in data) {
                    $("#mosqueList").append('<li class="list-group-item"><i class="icon-ok text-info"></i> '+data[i] +'</li>');
                }
            });
        }
    }
    else if(selectedItem=='museum'){
        setTimeout(museumlist,5000);
        function museumlist() {
            $.get("/rest/results/museum", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    var text = data[i];
                    $("#artList").append($("<li>").text(text));
                }
            });
        }
    }
    else if(selectedItem=='casino'){
        setTimeout(casinolist,5000);
        function casinolist() {
            $.get("/rest/results/casino", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    var text = data[i];
                    $("#artList").append($("<li>").text(text));
                }
            });
        }
    }
    else if(selectedItem=='park'){
        setTimeout(parklist,5000);
        function parklist() {
            $.get("/rest/results/park", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    var text = data[i];
                    $("#artList").append($("<li>").text(text));
                }
            });
        }
    }
    else if(selectedItem=='church'){
        setTimeout(churchlist,5000);
        function churchlist() {
            $.get("/rest/results/church", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    var text = data[i];
                    $("#artList").append($("<li>").text(text));
                }
            });
        }
    }
    else if(selectedItem=='stadium'){
        setTimeout(stadiumlist,5000);
        function stadiumlist() {
            $.get("/rest/results/stadium", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    var text = data[i];
                    $("#artList").append($("<li>").text(text));
                }
            });
        }
    }
    else if(selectedItem=='church'){
        setTimeout(churchlist,5000);
        function churchlist() {
            $.get("/rest/results/church", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    var text = data[i];
                    $("#artList").append($("<li>").text(text));
                }
            });
        }
    }
    else if(selectedItem=='synagogue'){
        setTimeout(synagoguelist,5000);
        function synagoguelist() {
            $.get("/rest/results/synagogue", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    var text = data[i];
                    $("#artList").append($("<li>").text(text));
                }
            });
        }
    }
    else if(selectedItem=='zoo'){
        setTimeout(zoolist,5000);
        function zoolist() {
            $.get("/rest/results/zoo", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    var text = data[i];
                    $("#artList").append($("<li>").text(text));
                }
            });
        }
    }
    else if(selectedItem=='hindu_temple'){
        setTimeout(hindu_templelist,5000);
        function hindu_templelist() {
            $.get("/rest/results/hindu_temple", function (data) {
                for (var i in data) {
                    console.log(data[i]);
                    var text = data[i];
                    $("#artList").append($("<li>").text(text));
                }
            });
        }
    }

}


