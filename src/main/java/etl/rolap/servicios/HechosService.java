package etl.rolap.servicios;

import etl.rolap.entidades.TablaHechos;
import etl.rolap.repositorios.HechosRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class HechosService {
    @Autowired
    HechosRepository repo;
    public void guardarAcceso(TablaHechos t){
        repo.save(t);
    }
}
