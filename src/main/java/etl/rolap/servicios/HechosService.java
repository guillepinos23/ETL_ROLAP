package etl.rolap.servicios;

import etl.rolap.entidades.TablaHechos;
import etl.rolap.repositorios.HechosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HechosService {
    @Autowired
    HechosRepository repo;
    public void guardarAcceso(TablaHechos t){
        repo.save(t);
    }
}
