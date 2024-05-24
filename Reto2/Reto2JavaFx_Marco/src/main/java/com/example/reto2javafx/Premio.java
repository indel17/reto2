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

//haz que para los jugadores que solo pueden ganar un premio la prioridad sea que si optan entre varios premios, entre los premios que opten siempre ganaran el de mayor cantidad, si el importe de los premios coincide la prioridad sera de mayor a menor prioridad en este orden: general, sub2400, sub2200, cv, h para el caso de los jugadores del torneo A; y para los jugadores del torneo B será de mayor a menor prioridad en este orden: general, sub1800, sub1600, sub1400, cv, h.