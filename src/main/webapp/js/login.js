$(function(){
	$(".btn").click(function(){
		   is_hide();
    })
    $("#submit").live('click',function(){
    	var u = $("input[name=username]");
        var p = $("input[name=password]");
        if(u.val() == '' || p.val() =='')
        {
            $("#ts").html("用户名或密码不能为空~");
            is_show();
            return false;
        }
        else{
            var reg = /^[0-9A-Za-z]+$/;
            if(!reg.exec(u.val()))
            {
                $("#ts").html("用户名错误");
                is_show();
                return false;
            }
        }
    });
})
    
    /*window.onload = function()
    {
        var error = $("#error").val() ;
        if(error != ""){
            $("#ts").html("用户名或密码错误！");
            is_show();
        }
        $(".connect p").eq(0).animate({"left":"0%"}, 600);
        $(".connect p").eq(1).animate({"left":"0%"}, 400);
    }*/
function is_hide(){
	$(".alert").animate({"top":"-40%"}, 300) 
}
function is_show(){
	$(".alert").show().animate({"top":"45%"}, 300) 
}