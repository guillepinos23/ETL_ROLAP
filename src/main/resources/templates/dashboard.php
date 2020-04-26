<?php
    $con = mysqli_connect('localhost:3310','root','','test');
?>

<html>
<head>
 <meta charset="utf-8">
 <title>Dashboard</title>
 <script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <script type="text/javascript">
	google.charts.load('current', {'packages':['sankey','corechart', 'bar', 'calendar']});
	google.charts.setOnLoadCallback(drawCharts);

	/* función que carga cada uno de los gráficos */
	function drawCharts() {
		drawChartP1();

	}
	function drawChartP1() {
		var data = new google.visualization.arrayToDataTable([
		['edad','alcoholismo'],
		<?php
			$query = "SELECT alcoholismo, edad FROM dim_paciente";
			$exec = mysqli_query($con,$query);
			while($row = mysqli_fetch_array($exec)){
				echo "['".$row['edad']."',".$row['alcoholismo']."],";
			}
		?>
    	]);
    	var options = {
    	title: 'Number of Payments according to their method',
    	pieHole: 0.5,
    	pieSliceTextStyle: {
    			color: 'black',
			},
    	legend: 'top',
    	is3D: false
    	};

    	var chart = new google.visualization.PieChart(document.getElementById("p1Chart"));
			chart.draw(data,options);
    }
	google.charts.load('current', {'packages':['gauge']});
	google.charts.setOnLoadCallback(drawChartP2);
	function drawChartP2() {

		var data = google.visualization.arrayToDataTable([
		['Hospital', 'Value'],
		['Hospital', <?php
                		$query = "SELECT count(uci) FROM tabla_hechos where id_hospital=1";
                        $exec = mysqli_query($con,$query);
						while($row = mysqli_fetch_array($exec)){
                            echo "['".$row['Hospital']."',".$row['Value']."],";
						}
                		?>]);
		var options = {
            width: 400, height: 120,
			redFrom: 90, redTo: 100,
			yellowFrom:75, yellowTo: 90,
			minorTicks: 5
			};
			var chart = new google.visualization.Gauge(document.getElementById('p2Chart'));
			chart.draw(data, options);
			setInterval(function() {
			data.setValue(0, 1,40);
			chart.draw(data, options);
			}, 13000);
			}
</script>
 <script src="charts.js"></script>
 <link rel="stylesheet" href="style.css">

 </head>

    <body>
        <!-- Título superior -->
        <h1>Dashboard</h1>

        <!-- Grid con todos los paneles -->
        <div class="dashboard-wrapper">
            <div id='p1' class='panel'><h2>Panel principal</h2> <div id='p1Chart'></div> </div>
            <div id='p2' class='panel'><h2>Sub panel 1</h2> <div id='p2Chart'></div> </div>
            <div id='p3' class='panel'><h2>Sub panel 2</h2> <div id='p3Chart'></div> </div>
            <div id='p4' class='panel'><h2>Sub panel 3</h2> <div id='p4Chart'></div> </div>
            <div id='p5' class='panel'><h2>Bottom panel</h2> <div id='p5Chart'></div> </div>
        </div>

    </body>
</html>