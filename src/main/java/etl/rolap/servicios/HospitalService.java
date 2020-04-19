package etl.rolap.servicios;

import etl.rolap.entidades.DimHospital;
import etl.rolap.entidades.DimPaciente;
import etl.rolap.repositorios.HospitalRepository;
import etl.rolap.repositorios.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository repositorio;


    public void guardarAcceso(DimHospital t){
        repositorio.save(t);
    }

    public DimHospital findById(long id){
       return repositorio.findById(id);
    }

}
