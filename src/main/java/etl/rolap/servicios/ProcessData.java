package etl.rolap.servicios;

import java.io.*;
import java.sql.Date;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.mahout.cf.taste.common.TasteException;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import etl.rolap.entidades.*;
import weka.associations.AssociationRule;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.StringToNominal;

@Component
public class ProcessData {
	@Autowired
	PacienteService access;
	@Autowired
	HospitalService hospitalService;
	@Autowired
	TiempoService tiempoService;
    @Autowired
    HechosService hechos ;
	@Autowired
	CompuestoService compuesto ;

	//Abrimos el archivo
	public void process(){
		for(int i=1;i<=4;i++) {
			int contador = -1;
			try (BufferedReader br = new BufferedReader(new FileReader("data/"+"P"+i+".csv"))) {
				String line;
				while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
					contador++;
					if (contador != 0) {
						String[] resultado = line.split(";");
						Long id = Long.parseLong(resultado[0]);
						int edad = Integer.parseInt(resultado[1]);
						int sexo = 0;//0 es mujer y uno es varon
						if(resultado[2].equals("M")) {
							 sexo = 0;
						}else if(resultado[2].equals("V")){
							 sexo=1;
						}else{
							sexo = Integer.parseInt(resultado[2]);
						}

						int imc =imc =  Integer.parseInt(resultado[3]);

						int formafisica=0;
						if(resultado[4].equals("No")) {
							imc=1;
						}else if(!resultado[4].equals("Si")){
							formafisica = Integer.parseInt(resultado[4]);
						}

						int tabaquismo=0;
						if(resultado[5].equals("No")) {
							tabaquismo=1;
						}else if(!resultado[5].equals("Si")){
							tabaquismo = Integer.parseInt(resultado[5]);
						}
						int alcoholismo=0;
						if(resultado[6].equals("No")) {
							alcoholismo=1;
						}else if(!resultado[6].equals("Si")){
							alcoholismo = Integer.parseInt(resultado[6]);
						}
						int colesterol=0;
						if(resultado[7].equals("No")) {
							colesterol=1;
						}else if(!resultado[7].equals("Si")){
							colesterol = Integer.parseInt(resultado[7]);
						}

						int hipertension=0;
						if(resultado[8].equals("No")) {
							hipertension=1;
						}else if(!resultado[8].equals("Si")){
							hipertension = Integer.parseInt(resultado[8]);
						}

						int cardiopatica=0;
						if(resultado[9].equals("No")) {
							cardiopatica=1;
						}else if(!resultado[9].equals("Si")){
							cardiopatica = Integer.parseInt(resultado[9]);
						}
						int rehuma=0;
						if(resultado[10].equals("No")) {
							rehuma=1;
						}else if(!resultado[10].equals("Si")){
							rehuma = Integer.parseInt(resultado[10]);
						}
						int epoc=0;
						if(resultado[11].equals("No")) {
							epoc=1;
						}else if(!resultado[11].equals("Si")){
							epoc = Integer.parseInt(resultado[11]);
						}

						int hepatitis=0;
						if(resultado[12].equals("No")) {
							hepatitis=1;
						}else if(!resultado[12].equals("Si")){
							hepatitis = Integer.parseInt(resultado[12]);
						}

						int cancer=0;
						if(resultado[13].equals("No")) {
							cancer=1;
						}else if(!resultado[13].equals("Si")){
							cancer = Integer.parseInt(resultado[13]);
						}

						DimPaciente d = new DimPaciente( edad, sexo, imc, formafisica, tabaquismo, alcoholismo, colesterol, hipertension, cardiopatica, rehuma, epoc, hepatitis, cancer);
						access.guardarAcceso(d);
					}
				}//while

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}//process

	public void cargarHospital(){
		try (BufferedReader br = new BufferedReader(new FileReader("data/"+"dimLUGAR.csv"))) {
			int contador = -1;
			String line;
			while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
                contador++;
				if(contador != 0) {
					Long l = Long.valueOf(contador);
					String[] resultado = line.split(";");
					String id = resultado[0];
					String nombre = resultado[1];
					int codigoPostal = Integer.parseInt(resultado[2]);
					String autopista = resultado[3];
					String gestor = resultado[4];
					DimHospital h = new DimHospital(l,id,nombre,codigoPostal,autopista,gestor);
					hospitalService.guardarAcceso(h);

				}
			}//while

		} catch (IOException e) {
			e.printStackTrace();
		}
	}//process

	public void cargarTiempo(){
		int contador = -1;
		try (BufferedReader br = new BufferedReader(new FileReader("data/"+"dimTIEMPO.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
                contador++;
				if(contador != 0) {
					String[] resultado = line.split(";");
					Long id = Long.parseLong(resultado[0]);
					String d = resultado[2];
					String m = resultado[3];
					String a = resultado[4];
					int dia = Integer.parseInt(d);
					int mes = Integer.parseInt(m);
					int anno = Integer.parseInt(a);
					String fec = d+"/"+m+"/"+a;
					java.util.Date t = this.transformar(resultado[1]);
					String cuatrim = resultado[5];
					String diaSemana = resultado[6];
					boolean esFinde = Boolean.parseBoolean(resultado[7]);
					DimTiempo dq = new DimTiempo(id,resultado[1],dia,mes,anno,cuatrim,diaSemana,esFinde);
					tiempoService.guardarAcceso(dq);

				}
			}//while

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Abrimos el archivo
	public void tablaHechos(){
		int contador = -1;
		long c = 0;
		for(int i =1;i<=4;i++){
		try (BufferedReader br = new BufferedReader(new FileReader("data/"+"H"+i+".csv"))) {
			String line;
			contador = -1;
			while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
                contador++;

			    if(contador != 0) {
					c++;
					List<DimPaciente> paceintes = new ArrayList<>();
					List<DimTiempo> tiempo = new ArrayList<>();
					String[] resultado = line.split(";");
					DimPaciente d = access.findById(contador);
					Long ia = Long.valueOf(i);
					DimHospital h = this.hospitalService.findById(ia);
					String fecha = resultado[2];
					String[] res = fecha.split("/");
					if(res[2].length()==2){
						fecha = res[0]+"/"+res[1]+"/"+"20"+res[2];
					}
					java.util.Date fec = this.transformar(fecha);
					DimTiempo t =this.tiempoService.recogerFecha(fecha);
					int duracion = Integer.parseInt(resultado[3]);
					//1 Es que esta en la UCI y 0 que no
					int uci=1;
					if(resultado[4].equals("No")) {
						uci=0;

					}
					//Uno esque ha fallecido y 0 es que no
					int fallecido=1;
					if(resultado[5].equals("No")) {
						fallecido=0;
					}

					int tratamiento = Integer.parseInt(resultado[6]);
					paceintes.add(d);

					tiempo.add(t);
					TablaHechos tabla = new TablaHechos(c,d,h,t,duracion,uci,fallecido,tratamiento);
                    this.hechos.guardarAcceso(tabla);
				}
			}//while

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}//process

	public void tablaCompuestos() throws IOException, TasteException {
	int contador = -1;

		for(int i =1;i<=4;i++){
		try (BufferedReader br = new BufferedReader(new FileReader("data/"+"datos_filtrado_colaborativo_"+i+".csv"))) {
			String line;
			contador = -1;
			while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
				contador++;
				if(contador != 0) {

					String[] resultado = line.split(",");

					int c1 = Integer.parseInt(resultado[1]);
					int c2 = Integer.parseInt(resultado[2]);
					int c3 = Integer.parseInt(resultado[3]);
					int c4 = Integer.parseInt(resultado[4]);
					int c5 = Integer.parseInt(resultado[5]);
					int c6 = Integer.parseInt(resultado[6]);
					int c7 = Integer.parseInt(resultado[7]);
					int c8 = Integer.parseInt(resultado[8]);
					int c9 = Integer.parseInt(resultado[9]);
					int c10 = Integer.parseInt(resultado[10]);
					int c11 = Integer.parseInt(resultado[11]);
					int c12 = Integer.parseInt(resultado[12]);
					int c13 = Integer.parseInt(resultado[13]);
					int c14 = Integer.parseInt(resultado[14]);
					int c15 = Integer.parseInt(resultado[15]);
					int c16 = Integer.parseInt(resultado[16]);
					int c17 = Integer.parseInt(resultado[17]);
					int c18 = Integer.parseInt(resultado[18]);
					int c19= Integer.parseInt(resultado[19]);
					int c20 = Integer.parseInt(resultado[20]);
					DimPaciente d = access.findById(Long.parseLong(resultado[0]));
					DimCompuesto dc = new DimCompuesto(d,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20);
				this.compuesto.guardarService(dc);
				}
			}//while

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public  Date guardarfecha(int dia,int mes, int anno) {
		Date time = new Date(dia,mes,anno);

		return time;		
	}
	public java.util.Date transformar(String t) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date =null;

		try {

			date = formatter.parse(t);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

	}

	public void ejercicio4() {
		ArrayList<DimCompuesto> array = this.compuesto.find();
		ArrayList<StringToTxt> lineas = new ArrayList<>();
		for (DimCompuesto d : array) {
			String c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20 = "";
			if (d.c1 >= 4) {
				c1 = "true,";
			} else {
				c1 = "false,";
			}
			if (d.c2 >= 4) {
				c2 = "true,";
			} else {
				c2 = "false,";
			}
			if (d.c3 >= 4) {
				c3 = "true,";
			} else {
				c3 = "false,";
			}
			if (d.c4 >= 4) {
				c4 = "true,";
			} else {
				c4 = "false,";
			}
			if (d.c5 >= 4) {
				c5 = "true,";
			} else {
				c5 = "false,";
			}
			if (d.c6 >= 4) {
				c6 = "true,";
			} else {
				c6 = "false,";
			}
			if (d.c7 >= 4) {
				c7 = "true,";
			} else {
				c7 = "false,";
			}
			if (d.c8 >= 4) {
				c8 = "true,";
			} else {
				c8 = "false,";
			}
			if (d.c9 >= 4) {
				c9 = "true,";
			} else {
				c9 = "false,";
			}
			if (d.c10 >= 4) {
				c10 = "true,";
			} else {
				c10 = "false,";
			}
			if (d.c11 >= 4) {
				c11 = "true,";
			} else {
				c11 = "false,";
			}
			if (d.c12 >= 4) {
				c12 = "true,";
			} else {
				c12 = "false,";
			}
			if (d.c13 >= 4) {
				c13 = "true,";
			} else {
				c13 = "false,";
			}
			if (d.c14 >= 4) {
				c14 = "true,";
			} else {
				c14 = "false,";
			}

			if (d.c15 >= 4) {
				c15 = "true,";
			} else {
				c15 = "false,";
			}
			if (d.c16 >= 4) {
				c16 = "true,";
			} else {
				c16 = "false,";
			}
			if (d.c17 >= 4) {
				c17 = "true,";
			} else {
				c17 = "false,";
			}
			if (d.c18 >= 4) {
				c18 = "true,";
			} else {
				c18 = "false,";
			}
			if (d.c19 >= 4) {
				c19 = "true,";
			} else {
				c19 = "false,";
			}
			if (d.c20 >= 4) {
				c20 = "true";
			} else {
				c20 = "false";
			}
			StringToTxt s = new StringToTxt(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20);
			lineas.add(s);
		}
		fichero(lineas,"reglasExito");
	}
	public void ejercicio4B() {
		ArrayList<DimCompuesto> array = this.compuesto.find();
		ArrayList<StringToTxt> lineas = new ArrayList<>();
		for (DimCompuesto d : array) {
			String c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20 = "";
			if (d.c1 >= 4) {
				c1 = "false,";
			} else {
				c1 = "true,";
			}
			if (d.c2 >= 4) {
				c2 = "false,";
			} else {
				c2 = "true,";
			}
			if (d.c3 >= 4) {
				c3 = "false,";
			} else {
				c3 = "true,";
			}
			if (d.c4 >= 4) {
				c4 = "false,";
			} else {
				c4 = "true,";
			}
			if (d.c5 >= 4) {
				c5 = "false,";
			} else {
				c5 = "true,";
			}
			if (d.c6 >= 4) {
				c6 = "false,";
			} else {
				c6 = "true,";
			}
			if (d.c7 >= 4) {
				c7 = "false,";
			} else {
				c7 = "true,";
			}
			if (d.c8 >= 4) {
				c8 = "false,";
			} else {
				c8 = "true,";
			}
			if (d.c9 >= 4) {
				c9 = "false,";
			} else {
				c9 = "true,";
			}
			if (d.c10 >= 4) {
				c10 = "false,";
			} else {
				c10 = "true,";
			}
			if (d.c11 >= 4) {
				c11 = "false,";
			} else {
				c11 = "true,";
			}
			if (d.c12 >= 4) {
				c12 = "false,";
			} else {
				c12 = "true,";
			}
			if (d.c13 >= 4) {
				c13 = "false,";
			} else {
				c13 = "true,";
			}
			if (d.c14 >= 4) {
				c14 = "false,";
			} else {
				c14 = "true,";
			}

			if (d.c15 >= 4) {
				c15 = "false,";
			} else {
				c15 = "true,";
			}
			if (d.c16 >= 4) {
				c16 = "false,";
			} else {
				c16 = "true,";
			}
			if (d.c17 >= 4) {
				c17 = "false,";
			} else {
				c17 = "true,";
			}
			if (d.c18 >= 4) {
				c18 = "false,";
			} else {
				c18 = "true,";
			}
			if (d.c19 >= 4) {
				c19 = "false,";
			} else {
				c19 = "true,";
			}
			if (d.c20 >= 4) {
				c20 = "false";
			} else {
				c20 = "true";
			}
			StringToTxt s = new StringToTxt(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20);
			lineas.add(s);
		}
		fichero(lineas,"reglasFallos");
	}
	public void fichero(ArrayList<StringToTxt> lineas,String nombre){
		FileWriter fichero = null;
		try {

			fichero = new FileWriter("ficheroResultado/"+nombre+".csv");

			// Escribimos linea a linea en el fichero
			fichero.write("c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20 \n");
			for (StringToTxt linea : lineas) {
				fichero.write(linea + "\n");
			}

			fichero.close();

		} catch (Exception ex) {
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}
	public void fichero1(ArrayList<String> lineas,String nombre){
		FileWriter fichero = null;
		try {

			fichero = new FileWriter("ficheroResultado/"+nombre+".txt");

			// Escribimos linea a linea en el fichero

			for (String linea : lineas) {
				fichero.write(linea + "\n");
			}

			fichero.close();

		} catch (Exception ex) {
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}

	/*Procesamos el fichero csv que contiene true y false segun las directrices puestas en el enunciado y lo procesamos para
	obtener las reglas de asociacion y guardarlas en el fichero reglasFallos.txt*/
	public void ejercicid4C() throws Exception {
		String inputDataFile = "data/reglasFallos.csv";

		//- Crear el cargador de CSV
		File inFile = new File(inputDataFile);
		CSVLoader loader = new CSVLoader();
		//- Especificar las caracteristicas del CSV
		loader.setFieldSeparator(",");
		loader.setNoHeaderRowPresent(false);
		// get instances object
		loader.setSource(inFile);
		Instances data = loader.getDataSet();
		//- String to Nominal
		StringToNominal filter1 = new StringToNominal();
		filter1.setAttributeRange("first-last");
		filter1.setInputFormat(data);
		data  = Filter.useFilter(data, filter1);

		//- Discretizar en 2 cubos
		Discretize  filter2 = new Discretize();
		filter2.setBins(2);
		filter2.setInputFormat(data);
		data = Filter.useFilter(data, filter2);

		//- Mostrar en pantalla los atributos de los datos
		System.out.println(loader.getStructure()+" ...\n\n");

		// the Apriori alg.
		Apriori model = new Apriori();
		System.out.println(model.getOutputItemSets());


		// build model
		model.buildAssociations(data);
		ArrayList<String> s = new ArrayList<>();
		for(AssociationRule r:model.getAssociationRules().getRules()){
			s.add(r.toString());
		}
		this.fichero1(s,"reglasFallos");
		System.out.println(model);
	}
	/*Procesamos el fichero csv que contiene true y false segun las directrices puestas en el enunciado y lo procesamos para
	obtener las reglas de asociacion y guardarlas en el fichero reglasExito.txt*/
	public void ejercicid4d() throws Exception {
		String inputDataFile = "data/reglasExito.csv";

		//- Crear el cargador de CSV
		File inFile = new File(inputDataFile);
		CSVLoader loader = new CSVLoader();
		//- Especificar las caracteristicas del CSV
		loader.setFieldSeparator(",");
		loader.setNoHeaderRowPresent(false);
		// get instances object
		loader.setSource(inFile);
		Instances data = loader.getDataSet();
		//- String to Nominal
		StringToNominal filter1 = new StringToNominal();
		filter1.setAttributeRange("first-last");
		filter1.setInputFormat(data);
		data  = Filter.useFilter(data, filter1);

		//- Discretizar en 2 cubos
		Discretize  filter2 = new Discretize();
		filter2.setBins(2);
		filter2.setInputFormat(data);
		data = Filter.useFilter(data, filter2);

		//- Mostrar en pantalla los atributos de los datos
		System.out.println(loader.getStructure()+" ...\n\n");

		// the Apriori alg.
		Apriori model = new Apriori();
		System.out.println(model.getOutputItemSets());


		// build model
		model.buildAssociations(data);
		ArrayList<String> s = new ArrayList<>();
		for(AssociationRule r:model.getAssociationRules().getRules()){
			s.add(r.toString());
		}
		this.fichero1(s,"reglasExito");
		System.out.println(model);
	}

	/*Con la informacion recogida, de la bbdd, almacenada en los ficheros csv fallecidos,uci y los que no han fallecido y no han estado
	en la uci, utilizando el agrupamiento selecionamos tres prototipos para cada uno de los casos que son almacenados
	en los fichero protoFallicido, protoResto y protoResto en la carpeta ficheroResultado*/
	public void ejercicio5() throws Exception {
		String[] l = {"fallecidosAgru","uciAgru","restoAgru"};
		String[] r = {"protoFallecido","protoUCI","protoResto"};
		for(int q = 0;q<l.length;q++){
			int K=3;
			int maxIteration = 100;
			String inputDataFile = "data/"+l[q]+".csv";

			File inFile = new File(inputDataFile);
			CSVLoader loader = new CSVLoader();

			loader.setFieldSeparator(";");
			loader.setNoHeaderRowPresent(false);

			loader.setSource(inFile);
			Instances data = loader.getDataSet();

			System.out.println(loader.getStructure()+" ...\n\n");

			SimpleKMeans kmeans = new SimpleKMeans();

			kmeans.setNumClusters(K);
			kmeans.setMaxIterations(maxIteration);
			kmeans.setPreserveInstancesOrder(true);

			try {
				kmeans.buildClusterer(data);
			} catch (Exception ex) {
				System.err.println("Unable to buld Clusterer: " + ex.getMessage());
				ex.printStackTrace();
			}
			Instances centroids = kmeans.getClusterCentroids();
			ArrayList<String> lista = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				String s = "Prototipo: " + centroids.instance(i)+"\n";
				lista.add(s);
			}
			this.fichero1(lista,r[q]);
		}

	}
	@PostConstruct
	public void init() throws Exception {
		this.cargarHospital();
		this.cargarTiempo();
		this.process();
		this.tablaHechos();
		this.tablaCompuestos();
		ejercicio5();
		ejercicid4C();
		ejercicid4d();
	}
}
