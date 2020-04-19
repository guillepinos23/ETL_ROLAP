package etl.rolap.entidades;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TablaHechos {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    @Id
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name="id_cliente")
    private DimPaciente cliente;
    @ManyToOne(optional = false)
    @JoinColumn(name="id_hospital")
    private DimHospital hospital;
    @ManyToOne(optional =false)
    @JoinColumn(name="id_tiempo")
    private DimTiempo fechaIngreso;

    private int duracion;
    private boolean uci;
    private boolean fallecido;
    private int tatramiento;
    public TablaHechos(){

    }
    public TablaHechos( DimPaciente cliente,DimHospital hospital, DimTiempo fechaIngreso, int duracion, boolean uci, boolean fallecido, int tatramiento) {
        this.cliente = cliente;
        this.hospital = hospital;
        this.fechaIngreso = fechaIngreso;
        this.duracion = duracion;
        this.uci = uci;
        this.fallecido = fallecido;
        this.tatramiento = tatramiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DimPaciente getCliente() {
        return cliente;
    }

    public void setCliente(DimPaciente cliente) {
        this.cliente = cliente;
    }

    public DimHospital getHospital() {
        return hospital;
    }

    public void setHospital(DimHospital hospital) {
        this.hospital = hospital;
    }

    public DimTiempo getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(DimTiempo fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isUci() {
        return uci;
    }

    public void setUci(boolean uci) {
        this.uci = uci;
    }

    public boolean isFallecido() {
        return fallecido;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }

    public int isTatramiento() {
        return tatramiento;
    }

    public void setTatramiento(int tatramiento) {
        this.tatramiento = tatramiento;
    }

    @Override
    public String toString() {
        return "TablaHechos{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", hospital=" + hospital +
                ", fechaIngreso=" + fechaIngreso +
                ", duracion=" + duracion +
                ", uci=" + uci +
                ", fallecido=" + fallecido +
                ", tatramiento=" + tatramiento +
                '}';
    }
}
