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



	function drawCharts() {
		drawChartP1();
		drawChartP2();

	}
	/*Personas que han fallecido despues de estar en la uni separadas por sexo */
	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(drawChartP1);
	function drawChartP1() {
		var data = google.visualization.arrayToDataTable([  
		['SEXO','UCI' ,'FALLECIDOS',], 
		  	<?php  				   
			$query = "SELECT (SELECT sexo from dim_paciente where id_cliente=id) as s, count(uci) ,count(fallecido) FROM tabla_hechos where fallecido='1' and uci='1' group by s";  
			$result = mysqli_query($con, $query);    
			while($row = mysqli_fetch_array($result)){  
				echo "[".$row["s"].", ".$row["count(uci)"].",".$row['count(fallecido)']."],";  
            	  
			}
		?>  
	]);  
		var options = {  
	        title: 'Personas que han fallecido y despues de estar en la uci separados por sexo',  
			is3D:true,  
			pieHole: 0.4  
        };  
		var chart1 = new google.visualization.ColumnChart(document.getElementById('p1Chart')); 
        chart1.draw(data, options);  
	} 
	
	
	/*Grafico que muestra los fallecidados en cada hospital */
	google.charts.setOnLoadCallback(drawChartP2);
	function drawChartP2() {
		var data = google.visualization.arrayToDataTable([  
		['Hospital', 'Fallecido'], 
		  	<?php  				   
			$query = "SELECT id_hospital, count(fallecido) FROM tabla_hechos WHERE fallecido = '1' GROUP BY id_hospital";  
			$result = mysqli_query($con, $query);    
			while($row = mysqli_fetch_array($result)){  
				echo "['Hospital ".$row["id_hospital"]."', ".$row["count(fallecido)"]."],";  
            	  
			}
		?>  
	]);  
		var options = {  
	        title: 'Porcentaje de fallecidos en cada hospital',  
			is3D:true,  
			pieHole: 0.4  
        };  
		var chart1 = new google.visualization.PieChart(document.getElementById("p2Chart"));  
        chart1.draw(data, options);  
	} 


	//Grafico de barras numero de personas en la uci en cada hospital y duracion media que han estado es ella.
	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(drawChartP3);
	function drawChartP3() {
		var data = google.visualization.arrayToDataTable([  
		['Hospiatal','UCI' ,'DURACION'], 
		  	<?php  				   
			$query = "SELECT id_hospital, avg(duracion) ,count(uci) FROM tabla_hechos where uci='1' GROUP by id_hospital";  
			$result = mysqli_query($con, $query);    
			while($row = mysqli_fetch_array($result)){  
				echo "['Hospital ".$row["id_hospital"]."', ".$row["count(uci)"].",".$row['avg(duracion)']."],";  
            	  
			}
		?>  
	]);  
		var options = {  
	        title: 'Numero de ingresados en UCI por cada hospital y la duracion media que han estado en UCI',  
			is3D:true,  
			pieHole: 0.4  
        };  
		var chart1 = new google.visualization.ColumnChart(document.getElementById('p3Chart')); 
        chart1.draw(data, options);  
	}

	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(drawChartP4);
	function drawChartP4() {
		var data = google.visualization.arrayToDataTable([  
		['Hospiatal','DURACION'], 
		  	<?php  				   
			$query = "SELECT id_hospital, avg(duracion) FROM tabla_hechos where fallecido = '0' GROUP by id_hospital";  
			$result = mysqli_query($con, $query);    
			while($row = mysqli_fetch_array($result)){  
				echo "['Hospital ".$row["id_hospital"]."',".$row['avg(duracion)']."],";  
            	  
			}
		?>  
	]);  
		var options = {  
	        title: 'Duracion media de los pacientes en el hospital y no han fallecido',  
			is3D:true,  
			pieHole: 0.4  
        };  
		var chart1 = new google.visualization.ColumnChart(document.getElementById('p4Chart')); 
        chart1.draw(data, options);  
	} 
	
</script>
 <link rel="stylesheet" href="style.css">

 </head>

    <body>
        <!-- Título superior -->
        <h1>Dashboard</h1>

        <div class="dashboard-wrapper">
        
			<div id='p1' class='panel'><h2>Fallecidos </h2><div id='p2Chart'></div> </div>
			<div id='p2' class='panel'><h2>UCI y fallecidos por sexo </h2><div id='p1Chart'></div> </div>
			<div id='p3' class='panel'><h2>Ingresados en UCI</h2> <div id='p3Chart'></div> </div>
            <div id='p4' class='panel'><h2>Duracion de no fallecidos</h2> <div id='p4Chart'></div> </div>
        </div>

    </body>
</html>
