 $(document).ready(function(){

	var employees = [
	{ "name":"java开发工程师" , "salary":"7000-8000/月" },
	{ "name":"Android开发工程师" , "salary":"8000-1000/月" },
	{ "name":"软件应用开发" , "salary":"8000-1000/月" },
	{ "name":"Android驱动开发" , "salary":"8000-1000/月" },
	{ "name":"Thomas" , "salary": "Carter" }
	];

	// 初始化轮播
		 
	$("#myCarousel").carousel('cycle');
		 
    
    $.each(employees,function(i,item){
    	$(".jobmore").after(
                   "<div class='joblist'>"+
					    "<div class='jobl'>"+
					    	"<span>"+item.name+"</span>"+
					    "</div>"+
						"<div class='jobr'>"+
							item.salary+
						"</div>"+
					"</div>"
    		);
    });

    var errstr=[
    { "name":"面试不通过是这个原因" },
	{ "name":"好好准备面试"},
	{ "name":"如何进行心理压力测试"},
	];

	$.each(errstr,function(i,item){
		$(".error").append(
			   "<span>"+item.name+
			   "</span>"
			);
	});












 });