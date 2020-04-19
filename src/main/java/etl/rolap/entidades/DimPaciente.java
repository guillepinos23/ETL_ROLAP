package etl.rolap.entidades;

import javax.persistence.*;

@Entity
public class DimPaciente {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;
    @Column
    private int edad;
    @Column
    private int sexo;
    @Column
    private int imc;
    @Column
    private int formaFisica;
    @Column
    private int tabaquismo, alcoholismo, colesterol, hipertension, cardiopatia, rehuma, epoc, hepatitis, cancer;

    public DimPaciente(int edad, int sexo, int imc, int formaFisica, int tabaquismo, int alcoholismo, int colesterol, int hipertension, int cardiopatia, int rehuma, int epoc, int hepatitis, int cancer) {
        this.edad = edad;
        this.sexo = sexo;
        this.imc = imc;
        this.formaFisica = formaFisica;
        this.tabaquismo = tabaquismo;
        this.alcoholismo = alcoholismo;
        this.colesterol = colesterol;
        this.hipertension = hipertension;
        this.cardiopatia = cardiopatia;
        this.rehuma = rehuma;
        this.epoc = epoc;
        this.hepatitis = hepatitis;
        this.cancer = cancer;
    }

    public DimPaciente(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getImc() {
        return imc;
    }

    public void setImc(int imc) {
        this.imc = imc;
    }

    public int getFormaFisica() {
        return formaFisica;
    }

    public void setFormaFisica(int formaFisica) {
        this.formaFisica = formaFisica;
    }

    public int getTabaquismo() {
        return tabaquismo;
    }

    public void setTabaquismo(int tabaquismo) {
        this.tabaquismo = tabaquismo;
    }

    public int getAlcoholismo() {
        return alcoholismo;
    }

    public void setAlcoholismo(int alcoholismo) {
        this.alcoholismo = alcoholismo;
    }

    public int getColesterol() {
        return colesterol;
    }

    public void setColesterol(int colesterol) {
        this.colesterol = colesterol;
    }

    public int getHipertension() {
        return hipertension;
    }

    public void setHipertension(int hipertension) {
        this.hipertension = hipertension;
    }

    public int getCardiopatia() {
        return cardiopatia;
    }

    public void setCardiopatia(int cardiopatia) {
        this.cardiopatia = cardiopatia;
    }

    public int getRehuma() {
        return rehuma;
    }

    public void setRehuma(int rehuma) {
        this.rehuma = rehuma;
    }

    public int getEpoc() {
        return epoc;
    }

    public void setEpoc(int epoc) {
        this.epoc = epoc;
    }

    public int getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(int hepatitis) {
        this.hepatitis = hepatitis;
    }

    public int getCancer() {
        return cancer;
    }

    public void setCancer(int cancer) {
        this.cancer = cancer;
    }

    @Override
    public String toString() {
        return "dimPaciente{" +
                "id=" + id +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                ", imc=" + imc +
                ", formaFisica='" + formaFisica + '\'' +
                ", tabaquismo='" + tabaquismo + '\'' +
                ", alcoholismo='" + alcoholismo + '\'' +
                ", colesterol='" + colesterol + '\'' +
                ", hipertension='" + hipertension + '\'' +
                ", cardiopatia='" + cardiopatia + '\'' +
                ", rehuma='" + rehuma + '\'' +
                ", epoc='" + epoc + '\'' +
                ", hepatitis='" + hepatitis + '\'' +
                ", cancer='" + cancer + '\'' +
                '}';
    }
}
