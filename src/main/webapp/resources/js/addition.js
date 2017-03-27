$.ajax({
type: 'POST',
url: 'https://www.google.com/recaptcha/api/siteverify',

})

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

$("#build").click(function build(){
    var i;
    $("#div1").empty();
    for(i = 0; i < $("#divNum").val(); i++){
        $("#div1").append("<div class='col-xs-6 col-lg-6'><img src='https://dodopizzaru-a.akamaihd.net/Img/Products/Pizza/c581dcc8-15e7-4a0b-aea2-45bae256c4ec.jpg'><p></p><div class='input-group'><div class='input-group-btn'><button type='button' id='plus' class='btn btn-default' onclick='add(" + i + ")'><span class='glyphicon glyphicon-plus'></span></button><button type='button' id='minus' class='btn btn-default' onclick='remove(" + i + ")'><span class='glyphicon glyphicon-minus'></span></button></div><div class='col-xs-3'><input type='text' value='0' id='" + "res" + i + "' " + "class='form-control' disabled></div></div></div><!--/.col-xs-6.col-lg-6-->");
    }
})
