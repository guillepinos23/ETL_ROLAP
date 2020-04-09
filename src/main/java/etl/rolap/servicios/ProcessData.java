package etl.rolap.servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import etl.rolap.entidades.*;
import sun.jvm.hotspot.debugger.win32.coff.COFFLineNumber;

@Component
public class ProcessData {
	@Autowired
	PacienteService access;
	@Autowired
	HospitalService hospitalService;
	
	@PostConstruct
	//Abrimos el archivo
	public void process(){
		int contador = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("P1.csv"))) {
	            String line;	            
	            while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
	            	if(contador != 0) {
	            		contador++;
						String[] resultado = line.split(";");
						Long id = Long.parseLong(resultado[0]);
						int edad = Integer.parseInt(resultado[1]);
						int sexo = Integer.parseInt(resultado[2]);
						int imc = Integer.parseInt(resultado[3]);
						int formafisica = Integer.parseInt(resultado[4]);
						int tabaquismo = Integer.parseInt(resultado[5]);
						int alcoholismo = Integer.parseInt(resultado[6]);
						int colesterol = Integer.parseInt(resultado[7]);
						int hipertension = Integer.parseInt(resultado[8]);
						int cardiopatica = Integer.parseInt(resultado[9]);
						int rehuma = Integer.parseInt(resultado[10]);
						int epoc = Integer.parseInt(resultado[11]);
						int hepatitis = Integer.parseInt(resultado[12]);
						int cancer = Integer.parseInt(resultado[13]);
						DimPaciente d = new DimPaciente(id, edad, sexo, imc, formafisica, tabaquismo, alcoholismo, colesterol, hipertension, cardiopatica, rehuma, epoc, hepatitis, cancer);
						access.guardarAcceso(d);
	            	}
	            }//while
				cargarHospital();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}//process
	public void cargarHospital(){
		try (BufferedReader br = new BufferedReader(new FileReader("dimLUGAR.csv"))) {
			int contador = 0;
			String line;
			while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
				if(contador != 0) {
					contador++;
					String[] resultado = line.split(";");
					String nombre = resultado[1];
					int codigoPostal = Integer.parseInt(resultado[2]);
					String autopista = resultado[3];
					String gestor = resultado[4];
					DimHospital h = new DimHospital(nombre,codigoPostal,autopista,gestor);
					hospitalService.guardarAcceso(h);

				}
			}//while
			cargarHospital();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//process
	}
	public static Tiempo guardarfecha(String fecha) {
		Tiempo time = new Tiempo();
		time.setDia(Integer.parseInt(fecha.substring(0,2)));
		time.setMes(fecha.substring(3,6));
		time.setAnio(Integer.parseInt(fecha.substring(7,11)));
		time.setHora(Integer.parseInt(fecha.substring(12,14)));
		return time;		
	}
}
