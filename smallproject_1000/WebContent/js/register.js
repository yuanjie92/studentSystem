$(function(){
	var $name = $("#name");
	$name.on("blur",function(){
		$.ajax({
			type:"post",
			url:"register/unique",
			data:{"name":$name.val()},
			success:function(data){
				if(data=="1"){
					$name.val("");
					$("#tipMsg").text("该用户名已存在，请重新输入！！！");
				}else{
					$("#tipMsg").text("可以使用！！！");
				}
			}
		});
	});
})
