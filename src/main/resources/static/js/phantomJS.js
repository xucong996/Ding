//<script src = "G:/jquery-3.3.1.js"></script>
var page = require('webpage').create(), system = require('system'), address, output;
if (system.args.length < 3 || system.args.length > 5) {
    phantom.exit(1);
} else {
    address = system.args[1];
    output = system.args[2];
    
	//虚拟浏览器的窗口大小
	page.viewportSize = {
		width : 2000,
		height : 400
	};
	
	//js坑：
		//1.for是个疯子，循环每次执行不等带内部代码的返回
		//2.定时器是异步的
		//3.var a  定义的为undifined类型
    page.open(address, function(status) {
		
		
		
		var aa="" ;
		
		
		
		var t = setInterval(function(){
	
			aa= page.evaluate(function() {
				return document.getElementById('ajaxFlag').innerHTML;
			});
			
			console.log("正在获取Flag中,已经执行");
			
			if(aa!=null && aa!=""){
				clearInterval(t)
				dd()
			}
			
			
			
		},1000)
		
		
      
        
		
		console.log(bb.width);
		console.log(bb.height);	
		
		
		function dd(){
		
			var w = page.evaluate(function() {
				return document.body.scrollWidth;
			});
			var h = page.evaluate(function() {
				return document.body.scrollHeight;
			});
			
			console.log("w:" + w + "h:" + h);

			
			
			
			var bb = page.evaluate(function() {
				return document.getElementsByTagName('html')[0].getBoundingClientRect();
			});
			
			page.clipRect = {
				top : bb.top,
				left : bb.left,
				width : w,
				height : h+100
				
			};
				
			window.setTimeout(function() {
				page.render(output);
				page.close();
				phantom.exit();
			}, 1500);
		}
		
		
        
    });
}
