function add(i){
     var val = $("#res" + i).val();
     val++;
     $("#res" + i).val(val);
}

function remove(i){
    var val = $("#res" + i).val();
    val--;
    if (val < 0){
    $("#res" + i).val(0);
    }else{$("#res" + i).val(val);
    }
}

function setCity(cityId){
    $("#city").val(cityId);
}

function buildDrink(){
    var i;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/assortment' + '?restaurantId=' + $("#city").val() + "&category=" + $("#category1").val(), false);
    xhr.send();
    var data = JSON.parse(xhr.responseText);
    $("#div1").empty();
    for(i = 0; i < data.length; i++){
        $("#div1").append("<div class='col-sm-6 col-md-4'><div class='thumbnail'><img src='http://s020.radikal.ru/i709/1704/1a/79606a4bc27b.jpg'><div class='caption'><h4>" + data[i].foodName + "</h4><p>" + data[i].description + "</p><div class='input-group'><div class='input-group-btn'><button type='button' id='plus' class='btn btn-default' onclick='add(" + i + ")'><span class='glyphicon glyphicon-plus'></span></button><button type='button' id='minus' class='btn btn-default' onclick='remove(" + i + ")'><span class='glyphicon glyphicon-minus'></span></button></div><div class='col-xs-3'><input type='text' value='0' id='" + "res" + i + "' " + "class='form-control' disabled></div></div></div></div></div>");
    }
}

function buildSnack(){
    var i;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/assortment' + '?restaurantId=' + $("#city").val() + "&category=" + $("#category2").val(), false);
    xhr.send();
    var data = JSON.parse(xhr.responseText);
    $("#div1").empty();
    for(i = 0; i < data.length; i++){
        $("#div1").append("<div class='col-sm-6 col-md-4'><div class='thumbnail'><img src='http://s020.radikal.ru/i709/1704/1a/79606a4bc27b.jpg'><div class='caption'><h4>" + data[i].foodName + "</h4><p>" + data[i].description + "</p><div class='input-group'><div class='input-group-btn'><button type='button' id='plus' class='btn btn-default' onclick='add(" + i + ")'><span class='glyphicon glyphicon-plus'></span></button><button type='button' id='minus' class='btn btn-default' onclick='remove(" + i + ")'><span class='glyphicon glyphicon-minus'></span></button></div><div class='col-xs-3'><input type='text' value='0' id='" + "res" + i + "' " + "class='form-control' disabled></div></div></div></div></div>");
    }
}
