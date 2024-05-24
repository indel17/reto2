package com.example.reto2javafx;

public class Jugador {
    private int rankingI;
    private int rankingF;
    private String titulo;
    private String nombre;
    private String federacion;
    private int fide;
    private String fideID;
    private boolean hotel;
    private boolean cv;
    private int importeP;
    private categoria tipoTorneo;
    private boolean descalificado;

    public enum categoria {
        A, B
    }

    public Jugador(int rankingI, int rankingF, categoria tipoTorneo) {
        this.rankingI = rankingI;
        this.rankingF = rankingF;
        this.tipoTorneo = tipoTorneo;
    }


    public categoria getTipoTorneo() {
        return tipoTorneo;
    }

    public void setTipoTorneo(categoria tipoTorneo) {
        this.tipoTorneo = tipoTorneo;
    }
    public Jugador(int rankingI, int rankingF, String titulo, String nombre, String federacion, int fide, String fideID, boolean hotel, boolean cv, int importeP, categoria tipoTorneo, boolean descalificado) {
        this.rankingI = rankingI;
        this.rankingF = rankingF;
        this.titulo = titulo;
        this.nombre = nombre;
        this.federacion = federacion;
        this.fide = fide;
        this.fideID = fideID;
        this.hotel = hotel;
        this.cv = cv;
        this.importeP = importeP;
        this.tipoTorneo = tipoTorneo;
        this.descalificado = descalificado;
    }

    public Jugador() {
        this.rankingI = 0;
        this.rankingF = 0;
        this.titulo = "";
        this.nombre = "";
        this.federacion = "";
        this.fide = 0;
        this.fideID = null;
        this.hotel = false;
        this.cv = false;
        this.importeP = 0;
        this.tipoTorneo= null;
        this.descalificado = false;
    }

    public boolean isDescalificado() {
        return descalificado;
    }

    public void setDescalificado(boolean descalificado) {
        this.descalificado = descalificado;
    }

    public int getRankingI() {
        return rankingI;
    }

    public void setRankingI(int rankingI) {
        this.rankingI = rankingI;
    }

    public int getRankingF() {
        return rankingF;
    }

    public void setRankingF(int rankingF) {
        this.rankingF = rankingF;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getFederacion() {
        return federacion;
    }

    public void setFederacion(String federacion) {
        this.federacion = federacion;
    }

    public int getFide() {
        return fide;
    }

    public void setFide(int fide) {
        this.fide = fide;
    }

    public String getFideID() {
        return fideID;
    }

    public void setFideID(String fideID) {
        this.fideID = fideID;
    }

    public boolean isHotel() {
        return hotel;
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

    public boolean isCv() {
        return cv;
    }

    public void setCv(boolean cv) {
        this.cv = cv;
    }

    public int getImporteP() {
        return importeP;
    }

    public void setImporteP(int importeP) {
        this.importeP = importeP;
    }



    @Override
    public String toString() {
        return "Jugador: " +
                "\n\trankingI=" + rankingI +
                "\n\trankingF=" + rankingF +
                "\n\ttitulo='" + titulo + '\'' +
                "\n\tnombre='" + nombre + '\'' +
                "\n\tfederacion='" + federacion + '\'' +
                "\n\tfide=" + fide +
                "\n\tfideID='" + fideID + '\'' +
                "\n\thotel=" + hotel +
                "\n\tcv=" + cv +
                "\n\timporteP=" + importeP +
                "\n\tcategoria=" + tipoTorneo +
                "\n\tdescalificado=" + descalificado;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jugador jugador = (Jugador) o;
        return fideID.equals(jugador.fideID);
    }

    @Override
    public int hashCode() {
        return fideID.hashCode();
    }
}

