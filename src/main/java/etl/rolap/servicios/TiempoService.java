package etl.rolap.servicios;

import etl.rolap.entidades.DimPaciente;
import etl.rolap.entidades.DimTiempo;
import etl.rolap.repositorios.PacienteRepository;
import etl.rolap.repositorios.TiempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TiempoService {

    @Autowired
    private TiempoRepository repositorio;




    public void guardarAcceso(DimTiempo t){
        repositorio.save(t);
    }
    public DimTiempo recogerFecha(Date t){
        return repositorio.findByFecha(t);
    };
}
