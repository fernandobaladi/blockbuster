/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster;

/**
 *
 * @author Fernando Baladi
 */
public class Película {
     private int númeroDePelícula;
    private String títuloDePelícula;
    private String géneroDePelícula;
    private String ciAlquilada;
    private String fechaDeDevolución;

    public Película(int númeroDePelícula, String títuloDePelícula, String géneroDePelícula, String ciAlquilada, String fechaDeDevolución) {
        this.númeroDePelícula = númeroDePelícula;
        this.títuloDePelícula = títuloDePelícula;
        this.géneroDePelícula = géneroDePelícula;
        this.ciAlquilada = ciAlquilada;
        this.fechaDeDevolución = fechaDeDevolución;
    }

    public Película(int númeroDePelícula, String títuloDePelícula, String géneroDePelícula) {
        this.númeroDePelícula = númeroDePelícula;
        this.títuloDePelícula = títuloDePelícula;
        this.géneroDePelícula = géneroDePelícula;
    }

    public int getNúmeroDePelícula() {
        return númeroDePelícula;
    }

    public void setNúmeroDePelícula(int númeroDePelícula) {
        this.númeroDePelícula = númeroDePelícula;
    }

    public String getTítuloDePelícula() {
        return títuloDePelícula;
    }

    public void setTítuloDePelícula(String títuloDePelícula) {
        this.títuloDePelícula = títuloDePelícula;
    }

    public String getGéneroDePelícula() {
        return géneroDePelícula;
    }

    public void setGéneroDePelícula(String géneroDePelícula) {
        this.géneroDePelícula = géneroDePelícula;
    }

    public String getCiAlquilada() {
        return ciAlquilada;
    }

    public void setCiAlquilada(String ciAlquilada) {
        this.ciAlquilada = ciAlquilada;
    }

    public String getFechaDeDevolución() {
        return fechaDeDevolución;
    }

    public void setFechaDeDevolución(String fechaDeDevolución) {
        this.fechaDeDevolución = fechaDeDevolución;
    }
}
