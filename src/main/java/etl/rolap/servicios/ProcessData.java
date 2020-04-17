package etl.rolap.servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
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
		int contador = -1;
		try (BufferedReader br = new BufferedReader(new FileReader("P1.csv"))) {
	            String line;	            
	            while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
                    contador++;
	                if(contador != 0) {

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

	        } catch (IOException e) {
	            e.printStackTrace();
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
					String nombre = resultado[1];
					int codigoPostal = Integer.parseInt(resultado[2]);
					String autopista = resultado[3];
					String gestor = resultado[4];
					DimHospital h = new DimHospital(nombre,codigoPostal,autopista,gestor);
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
					int dia = Integer.parseInt(resultado[2]);
					int mes = Integer.parseInt(resultado[3]);
					int anno = Integer.parseInt(resultado[4]);
					Date t = guardarfecha(dia,mes,anno);
					String cuatrim = resultado[5];
					String diaSemana = resultado[6];
					boolean esFinde = Boolean.parseBoolean(resultado[7]);
					DimTiempo d = new DimTiempo(id,t,dia,mes,anno,cuatrim,diaSemana,esFinde);
					tiempoService.guardarAcceso(d);

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
		try (BufferedReader br = new BufferedReader(new FileReader("H1.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
                contador++;
			    if(contador != 0) {

					String[] resultado = line.split(";");
					Long id = Long.parseLong(resultado[0]);
					Long paciente = Long.parseLong(resultado[1]);
					Optional<DimPaciente> d = access.findById(id);
					Date di = new Date();
					//DimTiempo t =this.tiempoService.recogerFecha(di);
					int duracion = Integer.parseInt(resultado[3]);
					String uci = resultado[4];
					String fallecido = resultado[5];
					int tratamiento = Integer.parseInt(resultado[6]);
					TablaHechos tabla = new TablaHechos(id,d,di,duracion,uci,fallecido,tratamiento);
                    this.hechos.guardarAcceso();
				}
			}//while

		} catch (IOException e) {
			e.printStackTrace();
		}
	}//process



	public static Date guardarfecha(int dia,int mes, int anno) {
		Date time = new Date(dia,mes,anno);

		return time;		
	}
}
