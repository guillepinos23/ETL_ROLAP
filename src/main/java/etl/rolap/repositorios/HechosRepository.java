package etl.rolap.repositorios;

import etl.rolap.entidades.TablaHechos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HechosRepository extends JpaRepository<TablaHechos,Long> {
}
