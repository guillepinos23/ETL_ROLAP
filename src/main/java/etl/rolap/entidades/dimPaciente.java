package etl.rolap.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class dimPaciente {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;
    private int edad;
    private String sexo;
    private int imc;
    private String formaFisica;
    private String tabaquismo, alcoholismo, colesterol, hipertension, cardiopatia, rehuma, epoc, hepatitis, cancer;

    public dimPaciente(Long id, int edad, String sexo, int imc, String formaFisica, String tabaquismo, String alcoholismo, String colesterol, String hipertension, String cardiopatia, String rehuma, String epoc, String hepatitis, String cancer) {
        this.id = id;
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

    public dimPaciente(){

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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getImc() {
        return imc;
    }

    public void setImc(int imc) {
        this.imc = imc;
    }

    public String getFormaFisica() {
        return formaFisica;
    }

    public void setFormaFisica(String formaFisica) {
        this.formaFisica = formaFisica;
    }

    public String getTabaquismo() {
        return tabaquismo;
    }

    public void setTabaquismo(String tabaquismo) {
        this.tabaquismo = tabaquismo;
    }

    public String getAlcoholismo() {
        return alcoholismo;
    }

    public void setAlcoholismo(String alcoholismo) {
        this.alcoholismo = alcoholismo;
    }

    public String getColesterol() {
        return colesterol;
    }

    public void setColesterol(String colesterol) {
        this.colesterol = colesterol;
    }

    public String getHipertension() {
        return hipertension;
    }

    public void setHipertension(String hipertension) {
        this.hipertension = hipertension;
    }

    public String getCardiopatia() {
        return cardiopatia;
    }

    public void setCardiopatia(String cardiopatia) {
        this.cardiopatia = cardiopatia;
    }

    public String getRehuma() {
        return rehuma;
    }

    public void setRehuma(String rehuma) {
        this.rehuma = rehuma;
    }

    public String getEpoc() {
        return epoc;
    }

    public void setEpoc(String epoc) {
        this.epoc = epoc;
    }

    public String getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(String hepatitis) {
        this.hepatitis = hepatitis;
    }

    public String getCancer() {
        return cancer;
    }

    public void setCancer(String cancer) {
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
