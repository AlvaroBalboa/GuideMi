/**
 * Created by souporman on 4/13/17.
 */

(function(window,google){

    var options = {
        center:{
            lat:'38.6582',
            lng:'77.2497'
        },
        zoom:10
    }
    frameElement = document.getElementById('googleMap'),

    map = new google.map.Map(element,options);

}(window,google));