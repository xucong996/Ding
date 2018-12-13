<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/echarts.min.js"></script>
<title>Hello</title>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [20,56,23,66,12,54]
            }]
        };
		
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        
    </script>
    <form action="chart/cutout" method="get">
    	<input type="number" name="width" value="輸入寬度"/>
    	<input type="number" name="height" value="輸入長度"/>
    	<input type="submit" value="点击截图"/>
    </form>
</body>
</html>