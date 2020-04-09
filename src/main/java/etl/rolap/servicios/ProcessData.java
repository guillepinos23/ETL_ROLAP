package etl.rolap.servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import etl.rolap.entidades.*;

@Component
public class ProcessData {
	
	@PostConstruct
	//Abrimos el archivo
	public void process(){
		int contAccT = 0;
		int contador = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("H1.csv"))) {
	            String line;	            
	            while ((line = br.readLine()) != null) {  //Vamos linea a linea separando la informacion
	            	if(contador != 0) {
						contAccT++;
						String resultado = line;
						Long id = resultado.split(";")[0];

						String fecha = resultado.substring(resultado.indexOf("[") + 1, resultado.indexOf("]"));
						Tiempo tiempo = guardarfecha(fecha);
						resultado = resultado.substring(resultado.indexOf("]") + 3);

						//Guarda TODOS los accesos del .log
						Recursos recurso = new Recursos(resultado.substring(0, resultado.indexOf("\"")));
						if (recurso.getUrl().contains("/nitflex/")) {
							String tipoRecurso = recurso.getUrl();
							if (recurso.getUrl().contains("HTTP/"))
								tipoRecurso = tipoRecurso.substring(0, tipoRecurso.indexOf("HTTP/"));
							tipoRecurso = tipoRecurso.substring(tipoRecurso.lastIndexOf("/"));
							if (tipoRecurso.contains(".")) {
								tipoRecurso = tipoRecurso.substring(tipoRecurso.lastIndexOf("."));
								if (tipoRecurso.contains("?"))
									tipoRecurso = tipoRecurso.substring(0, tipoRecurso.indexOf("?"));
							} else {
								tipoRecurso = "Otros";
							}
							recurso.setUrl(tipoRecurso);

							resultado = resultado.substring(resultado.indexOf("\"") + 1);

							String numeros = resultado.substring(1, resultado.indexOf("\"") - 1);
							resultado = resultado.substring(resultado.indexOf("\"") + 1);

							String desde = resultado.substring(0, resultado.indexOf("\""));
							resultado = resultado.substring(resultado.indexOf("\"") + 3);
							if (desde.length() > 254)
								desde = "-";

							String buscador = resultado.substring(0, resultado.indexOf("\""));
							Datos datos = new Datos(ip, numeros, desde, buscador);


							//Comprueba si existe el dato de tiempo y obtiene su id
							long l = servicioTiempo.comprobarTiempo(tiempo);
							tiempo.setIdt(l);

							//Comprueba si existe el dato de recursos y obtiene su id
							long r = servicioRecurso.comprobarRecurso(recurso);
							recurso.setIdr(r);

							servicioDatos.guardarDatos(datos);

							Accesos acceso = new Accesos(recurso, tiempo);
							servicioAcceso.guardarAcceso(acceso);
						}//if
					}
	            }//while
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			//Imprime el informe por consola
		  	int a = servicioAcceso.getAccesos().size();
		  	int r = servicioRecurso.getRecursos().size();
	      	List<Tiempo> list = servicioTiempo.getTiempo();
		  	System.out.println("El numero total de accesos procesados es:"+ contAccT);
	      	System.out.println("El numero total de accesos guardados es:"+ a);
	      	System.out.println("El numero total de tipos de recursos es:"+ r);
	      	System.out.println("El rango de tiempo es:");
	      	System.out.println("\t fecha de inicio:"+ list.get(0).getDia()+"/"+list.get(0).getMes()+"/"+ list.get(0).getAnio()+" - "+list.get(0).getHora());
	      	System.out.println("\t fecha de fin:"+ list.get(list.size()-1).getDia()+"/"+list.get(list.size()-1).getMes()+"/"+ list.get(list.size()-1).getAnio()+" - "+list.get(list.size()-1).getHora());
		}//process
	
	public static Tiempo guardarfecha(String fecha) {
		Tiempo time = new Tiempo();
		time.setDia(Integer.parseInt(fecha.substring(0,2)));
		time.setMes(fecha.substring(3,6));
		time.setAnio(Integer.parseInt(fecha.substring(7,11)));
		time.setHora(Integer.parseInt(fecha.substring(12,14)));
		return time;		
	}
}
