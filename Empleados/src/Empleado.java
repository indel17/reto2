import java.sql.Date;
import java.time.LocalDate;

public class Empleado {
    private int codemp;
    private String coddep;
    private String extelemp;
    private Date fecinemp;
    private Date fecnaemp;
    private String nifemp;
    private String nomemp;
    private int numhi;
    private double salemp;


    public Empleado(int codemp, String coddep, String extelemp, Date fecinemp, Date fecnaemp, String nifemp, String nomemp, int numhi, double salemp) {
        this.codemp = codemp;
        this.coddep = coddep;
        this.extelemp = extelemp;
        this.fecinemp = fecinemp;
        this.fecnaemp = fecnaemp;
        this.nifemp = nifemp;
        this.nomemp = nomemp;
        this.salemp = salemp;
        this.numhi = numhi;
    }


    public Empleado() {
        this.codemp = 0;
        this.coddep = null;
        this.extelemp = "";
        this.fecinemp = null;
        this.fecnaemp = null;
        this.nifemp = "";
        this.nomemp = "";
        this.numhi = 0;
        this.salemp = 0.0;
    }

    public int getCodemp() {
        return codemp;
    }

    public void setCodemp(int codemp) {
        this.codemp = codemp;
    }

    public String getCoddep() {
        return coddep;
    }

    public void setCoddep(String coddep) {
        this.coddep = coddep;
    }

    public String getExtelemp() {
        return extelemp;
    }

    public void setExtelemp(String extelemp) {
        this.extelemp = extelemp;
    }

    public Date getFecinemp() {
        return fecinemp;
    }

    public void setFecinemp(Date fecinemp) {
        this.fecinemp = fecinemp;
    }

    public Date getFecnaemp() {
        return fecnaemp;
    }

    public void setFecnaemp(Date fecnaemp) {
        this.fecnaemp = fecnaemp;
    }

    public String getNifemp() {
        return nifemp;
    }

    public void setNifemp(String nifemp) {
        this.nifemp = nifemp;
    }

    public String getNomemp() {
        return nomemp;
    }

    public void setNomemp(String nomemp) {
        this.nomemp = nomemp;
    }

    public double getSalemp() {
        return salemp;
    }

    public void setSalemp(double salemp) {
        this.salemp = salemp;
    }

    public int getNumhi() {
        return numhi;
    }

    public void setNumhi(int numhi) {
        this.numhi = numhi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleado empleado = (Empleado) o;
        return nifemp.equals(empleado.nifemp);
    }

    @Override
    public int hashCode() {
        return nifemp.hashCode();
    }

    @Override
    public String toString() {
        return "Empleado: " +
                "\n\tCódigo de empleado: " + codemp +
                "\n\tCódigo de departamento: " + coddep +
                "\n\tExtensión telefónica: " + extelemp +
                "\n\tFecha de ingreso: " + fecinemp +
                "\n\tFecha de nacimiento: " + fecnaemp +
                "\n\tDNI: " + nifemp +
                "\n\tNombre: " + nomemp +
                "\n\tNúmero de hijos: " + numhi +
                "\n\tSalario: " + salemp +
                "\n";
    }

}

