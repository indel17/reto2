package com.example.reto2javafx;

public class Premio {
    private String tipo;
    private Categoria tipoTorneo;
    private int puesto;
    private int importe;
    private int rankingI;

    public Premio(String tipo, Categoria categoria, int puesto, int importe, int rankingI) {
        this.tipo = tipo;
        this.tipoTorneo = categoria;
        this.puesto = puesto;
        this.importe = importe;
        this.rankingI = rankingI;
    }

    // Getters y setters omitidos por brevedad

    private enum Categoria {
        A, B
    }

    public Premio(String tipo) {// creo este constructor para mostrar el premio q opta, se puede utilizar en el futuro para otras cosas
        this.tipo = tipo;

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}

