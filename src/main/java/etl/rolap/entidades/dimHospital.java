package etl.rolap.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

class dimHospital {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;

    private String nombre;
    private int codigoPostal;
    private String autopista;
    private String gestor;

    public dimHospital(Long id, String nombre, int codigoPostal, String autopista, String gestor) {
        this.id = id;
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.autopista = autopista;
        this.gestor = gestor;
    }

    private dimHospital(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoPostal() {
        return codigoPostal;

    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getAutopista() {
        return autopista;
    }

    public void setAutopista(String autopista) {
        this.autopista = autopista;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    @Override
    public String toString() {
        return "dimHospital{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigoPostal=" + codigoPostal +
                ", autopista='" + autopista + '\'' +
                ", gestor='" + gestor + '\'' +
                '}';
    }
}
