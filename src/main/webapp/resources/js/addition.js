$("#plus").click(function add(){
     var val = $("#res1").val();
     val++;
     $("#res1").val(val);
})
$("#minus").click(function remove(){
    var val = $("#res1").val();
    val--;
    if (val < 0){
    $("#res1").val(0);
    }else{$("#res1").val(val);
    }
})
