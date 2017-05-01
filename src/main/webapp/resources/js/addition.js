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
//    $("#aCity").text(cityId);
}

function welcomeCity(){
    var i;
    var xhrCity = new XMLHttpRequest();
    xhrCity.open('GET', '/cities', false);
    xhrCity.send();
    console.log(xhrCity.responseText);
    var dataCity = JSON.parse(xhrCity.responseText);
    $("#cityList").empty();
    for(i = 0; i < dataCity.length; i++){
        $("#cityList").append("<li><button class='btn btn-link btn-block' id='city" + dataCity[i].cityId + "' onclick='setCity(" + dataCity[i].cityId + ")'>" + dataCity[i].cityName + "</button></li>");
    }
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/authenticatedUser', false);
    xhr.send();
    var data = JSON.parse(xhr.responseText);
    $("#orderId").val(data.information);
    $("#userId").val(data.userId);
    $("#aCity").text("Moscow");
}

function buildDrink(){
    var i;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/assortment' + '?restaurantId=' + $("#city").val() + "&category=" + $("#category1").val(), false);

    xhr.send();
    var data = JSON.parse(xhr.responseText);
    $("#div1").empty();
    for(i = 0; i < data.length; i++){
        $("#div1").append("<div class='col-sm-6 col-md-4'><div class='thumbnail'><img src='"+ data[i].imgPath +"'><div class='caption'><h4>" + data[i].foodName + "</h4><p>" + data[i].description + "</p><div class='input-group'><div class='input-group-btn'><button type='button' id='plus' class='btn btn-default' onclick='add(" + i + ")'><span class='glyphicon glyphicon-plus'></span></button><button type='button' id='minus' class='btn btn-default' onclick='remove(" + i + ")'><span class='glyphicon glyphicon-minus'></span></button><button type='button' id='toBascket' class='btn btn-default' onclick='addToBasket(" + data[i].foodId +"," + i + ")'><span class='glyphicon glyphicon-shopping-cart'></span></button></div><div class='col-xs-4'><input type='text' value='0' id='" + "res" + i + "' " + "class='form-control' disabled></div></div></div></div></div>");
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
        $("#div1").append("<div class='col-sm-6 col-md-4'><div class='thumbnail'><img src='"+ data[i].imgPath +"'><div class='caption'><h4>" + data[i].foodName + "</h4><p>" + data[i].description + "</p><div class='input-group'><div class='input-group-btn'><button type='button' id='plus' class='btn btn-default' onclick='add(" + i + ")'><span class='glyphicon glyphicon-plus'></span></button><button type='button' id='minus' class='btn btn-default' onclick='remove(" + i + ")'><span class='glyphicon glyphicon-minus'></span></button><button type='button' id='toBascket' class='btn btn-default' onclick='addToBasket(" + data[i].foodId +"," + i + ")'><span class='glyphicon glyphicon-shopping-cart'></span></button></div><div class='col-xs-4'><input type='text' value='0' id='" + "res" + i + "' " + "class='form-control' disabled></div></div></div></div></div>");
    }
}

function buildPizza(){
    var i;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/assortment' + '?restaurantId=' + $("#city").val() + "&category=" + $("#category3").val(), false);
    xhr.send();
    var data = JSON.parse(xhr.responseText);
    $("#div1").empty();
    for(i = 0; i < data.length; i++){
        $("#div1").append("<div class='col-sm-6 col-md-4'><div class='thumbnail'><img src='" + data[i].imgPath +"'><div class='caption'><h4>" + data[i].foodName + "</h4><p>" + data[i].description + "</p><div class='input-group'><div class='input-group-btn'><button type='button' id='plus' class='btn btn-default' onclick='add(" + i + ")'><span class='glyphicon glyphicon-plus'></span></button><button type='button' id='minus' class='btn btn-default' onclick='remove(" + i + ")'><span class='glyphicon glyphicon-minus'></span></button><button type='button' id='toBascket' class='btn btn-default' onclick='addToBasket(" + data[i].foodId +"," + i + ")'><span class='glyphicon glyphicon-shopping-cart'></span></button></div><div class='col-xs-4'><input type='text' value='0' id='" + "res" + i + "' " + "class='form-control' disabled></div></div></div></div></div>");
    }
}

function buildRoll(){
    var i;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/assortment' + '?restaurantId=' + $("#city").val() + "&category=" + $("#category4").val(), false);
    xhr.send();
    var data = JSON.parse(xhr.responseText);
    $("#div1").empty();
    for(i = 0; i < data.length; i++){
        $("#div1").append("<div class='col-sm-6 col-md-4'><div class='thumbnail'><img src='" + data[i].imgPath + "'><div class='caption'><h4>" + data[i].foodName + "</h4><p>" + data[i].description + "</p><div class='input-group'><div class='input-group-btn'><button type='button' id='plus' class='btn btn-default' onclick='add(" + i + ")'><span class='glyphicon glyphicon-plus'></span></button><button type='button' id='minus' class='btn btn-default' onclick='remove(" + i + ")'><span class='glyphicon glyphicon-minus'></span></button><button type='button' id='toBascket' class='btn btn-default' onclick='addToBasket(" + data[i].foodId +"," + i + ")'><span class='glyphicon glyphicon-shopping-cart'></span></button></div><div class='col-xs-4'><input type='text' value='0' id='" + "res" + i + "' " + "class='form-control' disabled></div></div></div></div></div>");
    }
}

function buildDessert(){
    var i;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/assortment' + '?restaurantId=' + $("#city").val() + "&category=" + $("#category5").val(), false);
    xhr.send();
    var data = JSON.parse(xhr.responseText);
    $("#div1").empty();
    for(i = 0; i < data.length; i++){
        $("#div1").append("<div class='col-sm-6 col-md-4'><div class='thumbnail'><img src='" + data[i].imgPath + "'><div class='caption'><h4>" + data[i].foodName + "</h4><p>" + data[i].description + "</p><div class='input-group'><div class='input-group-btn'><button type='button' id='plus' class='btn btn-default' onclick='add(" + i + ")'><span class='glyphicon glyphicon-plus'></span></button><button type='button' id='minus' class='btn btn-default' onclick='remove(" + i + ")'><span class='glyphicon glyphicon-minus'></span></button><button type='button' id='toBascket' class='btn btn-default' onclick='addToBasket(" + data[i].foodId +"," + i + ")'><span class='glyphicon glyphicon-shopping-cart'></span></button></div><div class='col-xs-4'><input type='text' value='0' id='" + "res" + i + "' " + "class='form-control' disabled></div></div></div></div></div>");
    }
}

$("#grecaptcha").click(function(){
    var g_recaptcha_response = $("#g-recaptcha-response").val();

    $.ajax({
        type: 'POST',
        url: 'https://www.google.com/recaptcha/api/siteverify',
        dataType: 'JSON',
        data: {"secret" : "6LesJhgUAAAAAB_v7rmJdKHHOIa--MoNe60eTgV3", "response" : g_recaptcha_response },
        success: function(data){
            console.log(data);
        }
    });
});

function addToBasket(foodId, num){
    var i;
    var postIdentifier = true;
    var userId = $("#userId").val();
    var quantityButtonId = "#" + "res" + num;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/orderElements' + '?userId=' + userId, false);
    xhr.send();
    var data = JSON.parse(xhr.responseText);
    for(i = 0; i < data.length; i++){
        if(foodId == data[i]['food']['foodId']){
            var jsonData = {"orderElementId" : data[i].orderElementId, "quantity" : $(quantityButtonId).val(), "orderId" : $("#orderId").val(), "foodId" : foodId};
            postIdentifier = false;
            $.ajax({
                type: 'PUT',
                url: '/orderElements' + '?orderElementId=' + data[i].orderElementId,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(jsonData)
            })
        }
    }
    if(postIdentifier){
        var jsonData = {"orderElementId" : data.length + 1, "quantity" : $(quantityButtonId).val(), "orderId" : $("#orderId").val(), "foodId" : foodId};
            $.ajax({
            type: 'POST',
            url: '/orderElements',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(jsonData)
        })
    }
}

//function getOrder(){
//    $.ajax({
//        type: 'GET',
//        url:
//    })
//}

