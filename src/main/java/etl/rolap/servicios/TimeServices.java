package etl.rolap.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etl.rolap.entidades.Tiempo;
import etl.rolap.repositorios.TiempoRepository;

@Service
public class TimeServices extends Tiempo{

	@Autowired
	private TiempoRepository repositorio;
	
	public List<Tiempo> getTiempo(){
		return (List<Tiempo>) repositorio.findAll();
	}
	
	public void guardarTiempo(Tiempo t){
		repositorio.save(t);
	}
	
	public long comprobarTiempo(Tiempo t){
		Tiempo time = repositorio.findByAnioAndDiaAndMesAndHora(t.getAnio(),t.getDia(),t.getMes(),t.getHora());
		if (time!= null){
			long id = time.getIdt();
			return id;
		}
		else{
			return (repositorio.save(t).getIdt());
		}
	}
}
