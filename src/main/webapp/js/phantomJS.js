var page = require('webpage').create(), system = require('system'), address, output, pageWidth, pageHight;

if (system.args.length < 3 || system.args.length > 7) {
    phantom.exit(1);
} else {
    address = system.args[1];
    output = system.args[2];
	pageWidth = system.args[3];
	pageHight = system.args[4];
    //定义宽高
    page.viewportSize = {
        width : pageWidth,
        height : pageHight
    };
    page.open(address, function(status) {
        var bb = page.evaluate(function() {
//        	var posts = document.getElementsByClassName("body");
//        	posts[0].style.backgroundColor = "#FFF";
            return document.getElementsByTagName('html')[0].getBoundingClientRect();
        });
       
        page.clipRect = {
            top : bb.top,
            left : bb.left,
            width : bb.width,
            height : bb.height
        };
        window.setTimeout(function() {
            page.render(output);
            page.close();
            console.log('渲染成功...');
        }, 1500);
    });
}
