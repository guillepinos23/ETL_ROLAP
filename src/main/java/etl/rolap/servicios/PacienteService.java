package etl.rolap.servicios;

import java.util.ArrayList;
import java.util.List;

import etl.rolap.entidades.DimPaciente;
import etl.rolap.repositorios.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repositorio;
	
	public List<DimPaciente> getAccesos() {
		return (List<DimPaciente>) repositorio.findAll();
	}
	public DimPaciente findById(Long id) {

		return repositorio.findById(id);
	}
	

	public void guardarAcceso(DimPaciente t){
		repositorio.save(t);
	}
	
	/*
	public List<List<String>> numAccesosHora(List<Accesos> listaAccesos){
		List<String> numAccess=new ArrayList<>();
		List<String> tiempos=new ArrayList<>();
		List<List<String>> resultado=new ArrayList<>();	
		
		for(Accesos aux: listaAccesos){
			Integer aux2=(int) aux.getTiempo().getIdt();
			if(numAccess.isEmpty()){
				numAccess.add(aux2-1, "1");
				tiempos.add(aux2-1, aux.getTiempo().toString());
			}else{
				if(numAccess.size()<aux2){
					numAccess.add(aux2-1, "1");
				}else{
					Integer suma=Integer.parseInt(numAccess.get(aux2-1))+1;
					numAccess.set(aux2-1, suma.toString());
				}
				if(tiempos.size()<aux2){
					tiempos.add(aux2-1, aux.getTiempo().toString());
				}
			}				
		}
		resultado.add(numAccess);
		resultado.add(tiempos);
		return resultado;
	}
	
	
	public List<List<String>> numAccesosRecurso(List<Accesos> listaAccesos){
		List<String> numRecurso=new ArrayList<>();
		List<String> recursos=new ArrayList<>();
		List<List<String>> resultado=new ArrayList<>();		
		
		for(Accesos aux: listaAccesos){
			Integer aux2=(int) aux.gettiporecurso().getIdr();
			if(numRecurso.isEmpty()){
				numRecurso.add(aux2-1, "1");
				recursos.add(aux2-1, aux.gettiporecurso().getUrl());
			}else{
				if(numRecurso.size()<aux2){
					numRecurso.add(aux2-1, "1");
				}else{
					Integer suma=Integer.parseInt(numRecurso.get(aux2-1))+1;
					numRecurso.set(aux2-1, suma.toString());
				}
				if(recursos.size()<aux2){
					recursos.add(aux2-1, aux.gettiporecurso().getUrl());
				}
			}				
		}
		resultado.add(numRecurso);
		resultado.add(recursos);
		return resultado;
	}*/
	
}
