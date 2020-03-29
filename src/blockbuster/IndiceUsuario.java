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
public class IndiceUsuario {
    String cedulaUsuario;
    int índice;

    public IndiceUsuario(String cedulaUsuario, int índice) {
        this.cedulaUsuario = cedulaUsuario;
        this.índice = índice;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public int getÍndice() {
        return índice;
    }

    public void setÍndice(int índice) {
        this.índice = índice;
    }
    
}
