$(function(){
	var $name = $("#name");
	$name.on("blur",function(){
		$.ajax({
			type:"post",
			url:"register/unique",
			data:{"name":$name},
			success:function(data){
				alert("data:"+data);
			}
		});
	});
})