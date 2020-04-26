google.charts.load('current', {'packages':['sankey','corechart', 'bar', 'calendar']});
google.charts.setOnLoadCallback(drawCharts);

/* función que carga cada uno de los gráficos */
function drawCharts() {
    drawChartP1();

    }

    	function drawChartP1() {

    		var data = new google.visualization.arrayToDataTable([
                        ['alcoholismo','edad'],

                        <?php
                            $query = "SELECT alcoholismo, edad FROM dim_paciente";
                            $exec = mysqli_query($con,$query);
                            while($row = mysqli_fetch_array($exec)){
                                echo "['".$row['alcoholismo']."',".$row['edad']."],";
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