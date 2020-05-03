package etl.rolap.entidades;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TablaHechos {
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
    private int uci;//0 o 1 0 es true y 1 false
    private int fallecido;//0 o 1 0 es true y 1 false
    private int tatramiento;
    public TablaHechos(){

    }
    public TablaHechos( long id,DimPaciente cliente,DimHospital hospital, DimTiempo fechaIngreso, int duracion, int uci, int fallecido, int tatramiento) {
        this.id = id;
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

    public int isUci() {
        return uci;
    }

    public void setUci(int uci) {
        this.uci = uci;
    }

    public int isFallecido() {
        return fallecido;
    }

    public void setFallecido(int fallecido) {
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
