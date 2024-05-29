package com.example.reto2javafx;

public class Premio {
    private String tipo;
    private Categoria tipoTorneo;
    private int puesto;
    private int importe;
    private int rankingI;
    private int prioridad; //he añadido prioridad



    public Premio(String tipo, Categoria categoria, int puesto, int importe, int rankingI, int prioridad) {
        this.tipo = tipo;
        this.tipoTorneo = categoria;
        this.puesto = puesto;
        this.importe = importe;
        this.rankingI = rankingI;
        this.prioridad= prioridad;
    }

    public Categoria getTipoTorneo() {
        return tipoTorneo;
    }

    public void setTipoTorneo(Categoria tipoTorneo) {
        this.tipoTorneo = tipoTorneo;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getRankingI() {
        return rankingI;
    }

    public void setRankingI(int rankingI) {
        this.rankingI = rankingI;
    }


    public enum Categoria {
        A, B
    }

    public Premio(String tipo) {    //Creo este constructor para mostrar el premio q opta, se puede utilizar en el futuro para otras cosas
        this.tipo = tipo;

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrioridad(){return prioridad;}

    public void setPrioridad(int prioridad){this.prioridad=prioridad;}

}

