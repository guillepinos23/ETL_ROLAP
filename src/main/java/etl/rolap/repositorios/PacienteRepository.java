package etl.rolap.repositorios;


import etl.rolap.entidades.DimHospital;
import etl.rolap.entidades.DimPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<DimPaciente,Integer> {


}
