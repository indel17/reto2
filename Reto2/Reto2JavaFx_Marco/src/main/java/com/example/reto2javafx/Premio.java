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

    public Premio(String tipo) {
        this.tipo = tipo;
        // Definimos valores predeterminados para los demás campos
        //this.tipoTorneo este lo puedo quitar creo
        this.puesto = 0;
        this.importe = 0;
        this.rankingI = 0;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

