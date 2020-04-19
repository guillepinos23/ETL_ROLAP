package etl.rolap.entidades;

import javax.persistence.*;

@Entity
public class DimHospital {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String idn;
    @Column
    private String nombre;
    @Column
    private int codigoPostal;
    @Column
    private String autopista;
    @Column
    private String gestor;

    public DimHospital(String idn,String nombre, int codigoPostal, String autopista, String gestor) {
        this.idn = idn;
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.autopista = autopista;
        this.gestor = gestor;
    }

    private DimHospital(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long idn) {
        this.id = id;
    }

    public String getIdn() {
        return idn;
    }

    public void setIdn(String id) {
        this.idn = idn;
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
