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
public class Usuario {
    private String cédula;
    private String nombre;
    private String teléfono;

    public Usuario(String cédula, String nombre, String teléfono) {
        this.cédula = cédula;
        this.nombre = nombre;
        this.teléfono = teléfono;
    }

    public String getCédula() {
        return cédula;
    }

    public void setCédula(String cédula) {
        this.cédula = cédula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(String teléfono) {
        this.teléfono = teléfono;
    }
}
