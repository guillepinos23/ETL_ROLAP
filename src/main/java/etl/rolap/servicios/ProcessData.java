package etl.rolap.servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import etl.rolap.entidades.*;

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

	@PostConstruct
	//Abrimos el archivo
	public void process(){
		for(int i=1;i<5;i++) {
			int contador = -1;
			try (BufferedReader br = new BufferedReader(new FileReader("P"+i+".csv"))) {
				String line;
				while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
					contador++;
					if (contador != 0) {
						String[] resultado = line.split(";");
						Long id = Long.parseLong(resultado[0]);
						int edad = Integer.parseInt(resultado[1]);
						int sexo = 0;
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

	@PostConstruct
	public void cargarHospital(){
		try (BufferedReader br = new BufferedReader(new FileReader("dimLUGAR.csv"))) {
			int contador = -1;
			String line;
			while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
                contador++;
				if(contador != 0) {

					String[] resultado = line.split(";");
					String id = resultado[0];
					String nombre = resultado[1];
					int codigoPostal = Integer.parseInt(resultado[2]);
					String autopista = resultado[3];
					String gestor = resultado[4];
					DimHospital h = new DimHospital(id,nombre,codigoPostal,autopista,gestor);
					hospitalService.guardarAcceso(h);

				}
			}//while

		} catch (IOException e) {
			e.printStackTrace();
		}
	}//process
	@PostConstruct
	public void cargarTiempo(){
		int contador = -1;
		try (BufferedReader br = new BufferedReader(new FileReader("dimTIEMPO.csv"))) {
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

	@PostConstruct
	//Abrimos el archivo
	public void tablaHechos(){
		int contador = -1;
		for(int i =1;i<3;i++){
		try (BufferedReader br = new BufferedReader(new FileReader("H"+i+".csv"))) {
			String line;
			while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
                contador++;
			    if(contador != 0) {
			    	List<DimHospital> hospitales = new ArrayList<>();
					List<DimPaciente> paceintes = new ArrayList<>();
					List<DimTiempo> tiempo = new ArrayList<>();
					String[] resultado = line.split(";");
					Long paciente = Long.parseLong(resultado[1]);
					DimPaciente d = access.findById(contador);
					Long ia = Long.valueOf(i);
					DimHospital h = this.hospitalService.findById(ia);
					String fecha = resultado[2];
					String[] res = fecha.split("/");
					int a = res[2].length();
					if(res[2].length()==2){
						fecha = res[0]+"/"+res[1]+"/"+"20"+res[2];
					}
					java.util.Date fec = this.transformar(fecha);
					DimTiempo t =this.tiempoService.recogerFecha(fecha);
					int duracion = Integer.parseInt(resultado[3]);
					Boolean uci = Boolean.parseBoolean(resultado[4]);
					Boolean fallecido = Boolean.parseBoolean(resultado[5]);
					int tratamiento = Integer.parseInt(resultado[6]);
					paceintes.add(d);

					tiempo.add(t);
					TablaHechos tabla = new TablaHechos(d,h,t,duracion,uci,fallecido,tratamiento);
                    this.hechos.guardarAcceso(tabla);
				}
			}//while

		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}//process


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
}
