package etl.rolap.servicios;

import etl.rolap.entidades.DimCompuesto;
import etl.rolap.entidades.DimHospital;
import etl.rolap.repositorios.PacienteRepository;
import etl.rolap.repositorios.TablaCompuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompuestoService {
    @Autowired
    private TablaCompuesto r;

    public void guardarService(DimCompuesto dc){
        r.save(dc);
    }
    public ArrayList<DimCompuesto> find() {return  r.findAll();}
}
