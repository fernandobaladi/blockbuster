/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster;

import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author jinve
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vista
     */
    Película[] películas = new Película[10000];
    Usuario[] usuarios = new Usuario[10000];
    IndicePelicula[] indiPeli = new IndicePelicula[10000];
    IndiceUsuario[] indiUsuario = new IndiceUsuario[10000];
    File archivoPelícula = new File("archivoPelícula.txt");    
    File archivoUsuario = new File("archivoUsuario.txt"); 
    int índiceAuxilia = 0;
    public Vista() {
        initComponents();
          
        this.LeerInformacionPelícula();
        this.LeerInformacionUsuario();        
        
        PrincipaljPanel.setVisible(true);
        AgregarPeliculajPanel.setVisible(false);
        AgregarUsuariojPanel.setVisible(false);
        AlquilarPeliculajPanel.setVisible(false);
        ConsultarjPanel.setVisible(false);
        ModificarPeliculajPanel.setVisible(false);
        ModificarUsuariojPanel.setVisible(false);
        EntregarPeliculajPanel.setVisible(false);
        InfoConsultadajTextField.setEditable(false);
        
        //Restricciones de los inputs 
        NumeroPeliculajTextField.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent event)
           {
              char character = event.getKeyChar();

              // Verifica si la tecla pulsada por el usuario no es un digito
              if(((character < '0') ||
                 (character > '9')) &&
                 (character != '\b'))  // el backspace
              {
                 event.consume();  // ignorar el teclado
              }
           }
        });
        
        TelefonojTextField.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent event)
           {
              char character = event.getKeyChar();

              // Verifica si la tecla pulsada por el usuario no es un digito
              if(((character < '0') ||
                 (character > '9')) &&
                 (character != '\b'))  // el backspace
              {
                 event.consume();  // ignorar el teclado
              }
           }
        });
        
        FechaDevjTextField.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent event)
           {
              char character = event.getKeyChar();

              // Verifica si la tecla pulsada por el usuario no es un digito
              if(((character < '0') ||
                 (character > '9')) &&
                 (character != '\b'))  // el backspace
              {
                 event.consume();  // ignorar el teclado
              }
           }
        });
        
        ModificarNumeroPeliculajTextField.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent event)
           {
              char character = event.getKeyChar();

              // Verifica si la tecla pulsada por el usuario no es un digito
              if(((character < '0') ||
                 (character > '9')) &&
                 (character != '\b'))  // el backspace
              {
                 event.consume();  // ignorar el teclado
              }
           }
        });
        
        ModificarTelefonojTextField.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent event)
           {
              char character = event.getKeyChar();

              // Verifica si la tecla pulsada por el usuario no es un digito
              if(((character < '0') ||
                 (character > '9')) &&
                 (character != '\b'))  // el backspace
              {
                 event.consume();  // ignorar el teclado
              }
           }
        });
        
        EntregaNumeroPeliculajTextField.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent event)
           {
              char character = event.getKeyChar();

              // Verifica si la tecla pulsada por el usuario no es un digito
              if(((character < '0') ||
                 (character > '9')) &&
                 (character != '\b'))  // el backspace
              {
                 event.consume();  // ignorar el teclado
              }
           }
        });
    }
    
    public void LeerInformacionPelícula(){
    String aux;
    String[] arrayAux;
        try{
            BufferedReader bf = new BufferedReader(new FileReader(archivoPelícula));
            int counter = 0;
            while(bf.ready()){
                aux = bf.readLine();
                arrayAux = aux.split(",");
                if(arrayAux.length == 5){
                    películas[counter] = new Película(Integer.parseInt(arrayAux[0]), arrayAux[1], arrayAux[2], arrayAux[3], arrayAux[4]);
                }else{
                    películas[counter] = new Película(Integer.parseInt(arrayAux[0]), arrayAux[1], arrayAux[2]);
                }
                this.indiPeli[counter] = new IndicePelicula(películas[counter].getNúmeroDePelícula(), counter);
                counter++;
            }
            OrdenarIndicePelicula();
        }catch(Exception e){
            System.out.println("No se logró leer la base de datos de películas");
        }
    }
    public void LeerInformacionUsuario(){
        String aux;
        String[] arrayAux;
        try{
            BufferedReader bf = new BufferedReader(new FileReader(archivoUsuario));
            int counter = 0;
            while(bf.ready()){
                aux = bf.readLine();
                arrayAux = aux.split(",");
                usuarios[counter] = new Usuario(arrayAux[0], arrayAux[1], arrayAux[2]);
                this.indiUsuario[counter] = new IndiceUsuario(usuarios[counter].getCédula(), counter);
                counter++;
            }
            OrdenarIndiceUsuario();
        }catch(Exception e){
            System.out.println("No se logró leer la base de datos de usuarios");
        }
    }
    
    public int BusquedaBinariaPelícula(int numPelícula){
        int counter = 0;
        while(this.indiPeli[counter]!=null){
            counter++; 
        }
        int n = counter;
        int centro, inf=0, sup=n-1;
        while(inf <= sup){
            centro = (sup+inf)/2;
            if (this.indiPeli[centro].númeroPelícula == numPelícula) {
                return this.indiPeli[centro].getÍndice();
            }else if(numPelícula < this.indiPeli[centro].númeroPelícula){
                sup = centro -1;
            }else{
                inf = centro +1;
            }
        }
        return -1;
    }
    
    public int BusquedaBinariaUsuario(int CIUsuario){
        int counter = 0;
        while(this.indiUsuario[counter]!=null){
            counter++; 
        }
        int n = counter;
        int centro, inf=0, sup=n-1;
        while(inf <= sup){
            centro = (sup+inf)/2;
            if (Integer.parseInt(this.indiUsuario[centro].cedulaUsuario.substring(1, this.indiUsuario[centro].cedulaUsuario.length())) == CIUsuario) {
                return this.indiUsuario[centro].getÍndice();
            }else if(CIUsuario < Integer.parseInt(this.indiUsuario[centro].cedulaUsuario.substring(1, this.indiUsuario[centro].cedulaUsuario.length()))){
                sup = centro -1;
            }else{
                inf = centro +1;
            }
        }
        return -1;
    }
    
    public void GuardarInformacionPelícula(Película película){
        int counter = 0;
        while(películas[counter]!= null){
            counter++;
        }
        películas[counter] = película;
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.archivoPelícula));
            for (int i = 0; i < counter + 1; i++) {

                bw.write(String.valueOf(películas[i].getNúmeroDePelícula()));
                bw.write(",");
                bw.write(películas[i].getTítuloDePelícula());
                bw.write(",");
                bw.write(películas[i].getGéneroDePelícula());
                bw.write(",");
                if (películas[i].getCiAlquilada() != null) {
                    bw.write(películas[i].getCiAlquilada());                            
                }
                bw.write(",");
                if (películas[i].getFechaDeDevolución() != null) {
                    bw.write(películas[i].getFechaDeDevolución());                                                        
                }
                if (i<counter) {
                    bw.newLine();
                }
                this.indiPeli[i]= new IndicePelicula(películas[i].getNúmeroDePelícula(), i);
            }
            bw.close();
        }catch (Exception e) {
            System.out.println("No se pudo escribir el archivo de películas.");
        }    
    }
    public void ActualizarInformaciónUsuario (String nuevoNombre, String nuevoTelefono, int índice){
        int counter = 0;
        while(usuarios[counter]!= null){
            counter++;
        }
        usuarios[índice].setNombre(nuevoNombre);
        usuarios[índice].setTeléfono(nuevoTelefono);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.archivoUsuario));
            for (int i = 0; i < counter; i++) {

                bw.write(usuarios[i].getCédula());
                bw.write(",");
                bw.write(usuarios[i].getNombre());
                bw.write(",");
                bw.write(usuarios[i].getTeléfono());
                if (i<counter) {
                    bw.newLine();
                }
                this.indiUsuario[i]= new IndiceUsuario(usuarios[i].getCédula(), i);

            }
            bw.close();
        }catch (Exception e) {
            System.out.println("No se pudo escribir el archivo de películas.");
        }
    }
    public void ActualizarInformacionPelícula(String nuevoNombre, int índice, String nuevoGenero){
        int counter = 0;
        while(películas[counter]!= null){
            counter++;
        }
        películas[índice].setTítuloDePelícula(nuevoNombre);
        películas[índice].setGéneroDePelícula(nuevoGenero);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.archivoPelícula));
            for (int i = 0; i < counter; i++) {

                bw.write(String.valueOf(películas[i].getNúmeroDePelícula()));
                bw.write(",");
                bw.write(películas[i].getTítuloDePelícula());
                bw.write(",");
                bw.write(películas[i].getGéneroDePelícula());
                bw.write(",");
                if (películas[i].getCiAlquilada() != null) {
                    bw.write(películas[i].getCiAlquilada());                            
                }
                bw.write(",");
                if (películas[i].getFechaDeDevolución() != null) {
                    bw.write(películas[i].getFechaDeDevolución());                                                        
                }
                if (i<counter) {
                    bw.newLine();
                }
                this.indiPeli[i]= new IndicePelicula(películas[i].getNúmeroDePelícula(), i);
            }
            bw.close();
        }catch (Exception e) {
            System.out.println("No se pudo escribir el archivo de películas.");
        }    
    }
    
    public void DevolverInformacionPelícula(int índice){
        int counter = 0;
        while(películas[counter]!= null){
            counter++;
        }
        películas[índice].setCiAlquilada(null);
        películas[índice].setFechaDeDevolución(null);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.archivoPelícula));
            for (int i = 0; i < counter; i++) {

                bw.write(String.valueOf(películas[i].getNúmeroDePelícula()));
                bw.write(",");
                bw.write(películas[i].getTítuloDePelícula());
                bw.write(",");
                bw.write(películas[i].getGéneroDePelícula());
                bw.write(",");
                if (películas[i].getCiAlquilada() != null) {
                    bw.write(películas[i].getCiAlquilada());                            
                }
                bw.write(",");
                if (películas[i].getFechaDeDevolución() != null) {
                    bw.write(películas[i].getFechaDeDevolución());                                                        
                }
                if (i<counter) {
                    bw.newLine();
                }
                this.indiPeli[i]= new IndicePelicula(películas[i].getNúmeroDePelícula(), i);
            }
            bw.close();
        }catch (Exception e) {
            System.out.println("No se pudo escribir el archivo de películas.");
        }    
    }
    
    public void OrdenarIndicePelicula(){
        int counter = 0;
        while(this.películas[counter] != null){
            counter++;
        }
        int n = counter; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (this.indiPeli[j].getNúmeroPelícula() > this.indiPeli[j+1].getNúmeroPelícula()){ 
                    // swap arr[j+1] and arr[i] 
                    IndicePelicula temp = this.indiPeli[j]; 
                    this.indiPeli[j] =this.indiPeli[j+1]; 
                    this.indiPeli[j+1] = temp;
                } 
    }
    
    public void OrdenarIndiceUsuario(){
        int counter = 0;
        while(this.usuarios[counter] != null){
            counter++;
        }
        int n = counter;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                String aux = this.indiUsuario[j].getCedulaUsuario().substring(1, this.indiUsuario[j].getCedulaUsuario().length());
                String aux2 = this.indiUsuario[j+1].getCedulaUsuario().substring(1, this.indiUsuario[j+1].getCedulaUsuario().length());
                if (Integer.parseInt(aux)>Integer.parseInt(aux2)) {
                    IndiceUsuario tempo = this.indiUsuario[j];
                    this.indiUsuario[j] = this.indiUsuario[j+1];
                    this.indiUsuario[j+1] = tempo;
                }
            }
        }
    }
    public void GuardarInformaciónUsuario (Usuario usuario){
        int counter = 0;
        while(usuarios[counter]!= null){
            counter++;
        }
        usuarios[counter] = usuario;
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.archivoUsuario));
            for (int i = 0; i < counter + 1; i++) {

                bw.write(usuarios[i].getCédula());
                bw.write(",");
                bw.write(usuarios[i].getNombre());
                bw.write(",");
                bw.write(usuarios[i].getTeléfono());
                if (i<counter) {
                    bw.newLine();
                }
                this.indiUsuario[i]= new IndiceUsuario(usuarios[i].getCédula(), i);

            }
            bw.close();
        }catch (Exception e) {
            System.out.println("No se pudo escribir el archivo de películas.");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new javax.swing.JPanel();
        AgregarPeliculaButton = new javax.swing.JButton();
        AgregarUsuarioButton = new javax.swing.JButton();
        AlquilarPeliculaButton = new javax.swing.JButton();
        ConsultarButton = new javax.swing.JButton();
        SalirButton = new javax.swing.JButton();
        modificarUsuarioButton = new javax.swing.JButton();
        modificarPeliculaButton = new javax.swing.JButton();
        EntregarPeliculaButton = new javax.swing.JButton();
        PrincipaljPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        AgregarPeliculajPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        NumeroPeliculajTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TituloPeliculajTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ConfirmarPeliculaButton = new javax.swing.JButton();
        GeneroPeliculajComboBox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        AgregarUsuariojPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        CedulajTextField = new javax.swing.JTextField();
        NombrejTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TelefonojTextField = new javax.swing.JTextField();
        ConfirmarUsuarioButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        AlquilarPeliculajPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        FechaDevjTextField = new javax.swing.JTextField();
        CedulaAlquilerjTextField = new javax.swing.JTextField();
        AceptarAlquilerButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        SeleccionadorPeliculasjComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        ConsultarjPanel = new javax.swing.JPanel();
        ConsultarjTextField = new javax.swing.JTextField();
        AceptarConsultarButton = new javax.swing.JButton();
        ConsultarPeliculaButton = new javax.swing.JButton();
        MostrarInfoConsultadajScrollPane = new javax.swing.JScrollPane();
        InfoConsultadajTextField = new javax.swing.JTextArea();
        ModificarPeliculajPanel = new javax.swing.JPanel();
        GuardarModificarPeliButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        ModificarNumeroPeliculajTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ModificarTituloPeliculajTextField = new javax.swing.JTextField();
        BuscarModificarPeliButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        ModificarGeneroPeliculajComboBox = new javax.swing.JComboBox<>();
        ModificarUsuariojPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        ModificarTelefonojTextField = new javax.swing.JTextField();
        ModificarCedulajTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        ModificarNombrejTextField = new javax.swing.JTextField();
        GuardarModificarUsuarioButton = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        BuscarModificarUsuarioButton = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        EntregarPeliculajPanel = new javax.swing.JPanel();
        EntregaCedulajTextField = new javax.swing.JTextField();
        AceptarEntregarPeliculaButton = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        EntregaNumeroPeliculajTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setMaximumSize(new java.awt.Dimension(850, 750));
        setMinimumSize(new java.awt.Dimension(850, 750));
        setPreferredSize(new java.awt.Dimension(850, 750));
        setResizable(false);

        Menu.setBackground(new java.awt.Color(14, 63, 169));
        Menu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menú", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(255, 204, 0))); // NOI18N
        Menu.setMaximumSize(new java.awt.Dimension(190, 650));
        Menu.setMinimumSize(new java.awt.Dimension(0, 0));
        Menu.setName(""); // NOI18N
        Menu.setPreferredSize(new java.awt.Dimension(190, 650));

        AgregarPeliculaButton.setBackground(new java.awt.Color(255, 204, 0));
        AgregarPeliculaButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AgregarPeliculaButton.setText("Añadir película");
        AgregarPeliculaButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        AgregarPeliculaButton.setMaximumSize(new java.awt.Dimension(69, 21));
        AgregarPeliculaButton.setMinimumSize(new java.awt.Dimension(69, 21));
        AgregarPeliculaButton.setName(""); // NOI18N
        AgregarPeliculaButton.setPreferredSize(new java.awt.Dimension(69, 21));
        AgregarPeliculaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarPeliculaButtonActionPerformed(evt);
            }
        });

        AgregarUsuarioButton.setBackground(new java.awt.Color(255, 204, 0));
        AgregarUsuarioButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AgregarUsuarioButton.setText("Añadir usuario");
        AgregarUsuarioButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        AgregarUsuarioButton.setMaximumSize(new java.awt.Dimension(69, 21));
        AgregarUsuarioButton.setMinimumSize(new java.awt.Dimension(69, 21));
        AgregarUsuarioButton.setName(""); // NOI18N
        AgregarUsuarioButton.setPreferredSize(new java.awt.Dimension(69, 21));
        AgregarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarUsuarioButtonActionPerformed(evt);
            }
        });

        AlquilarPeliculaButton.setBackground(new java.awt.Color(255, 204, 0));
        AlquilarPeliculaButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AlquilarPeliculaButton.setText("Alquilar película");
        AlquilarPeliculaButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        AlquilarPeliculaButton.setMaximumSize(new java.awt.Dimension(69, 21));
        AlquilarPeliculaButton.setMinimumSize(new java.awt.Dimension(69, 21));
        AlquilarPeliculaButton.setName(""); // NOI18N
        AlquilarPeliculaButton.setPreferredSize(new java.awt.Dimension(69, 21));
        AlquilarPeliculaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlquilarPeliculaButtonActionPerformed(evt);
            }
        });

        ConsultarButton.setBackground(new java.awt.Color(255, 204, 0));
        ConsultarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ConsultarButton.setText("Consultar");
        ConsultarButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        ConsultarButton.setMaximumSize(new java.awt.Dimension(69, 21));
        ConsultarButton.setMinimumSize(new java.awt.Dimension(69, 21));
        ConsultarButton.setName(""); // NOI18N
        ConsultarButton.setPreferredSize(new java.awt.Dimension(69, 21));
        ConsultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarButtonActionPerformed(evt);
            }
        });

        SalirButton.setBackground(new java.awt.Color(255, 204, 0));
        SalirButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SalirButton.setText("Salir");
        SalirButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        SalirButton.setMaximumSize(new java.awt.Dimension(69, 21));
        SalirButton.setMinimumSize(new java.awt.Dimension(69, 21));
        SalirButton.setName(""); // NOI18N
        SalirButton.setPreferredSize(new java.awt.Dimension(69, 21));
        SalirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirButtonActionPerformed(evt);
            }
        });

        modificarUsuarioButton.setBackground(new java.awt.Color(255, 204, 0));
        modificarUsuarioButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        modificarUsuarioButton.setText("Modificar usuario");
        modificarUsuarioButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        modificarUsuarioButton.setMaximumSize(new java.awt.Dimension(69, 21));
        modificarUsuarioButton.setMinimumSize(new java.awt.Dimension(69, 21));
        modificarUsuarioButton.setName(""); // NOI18N
        modificarUsuarioButton.setPreferredSize(new java.awt.Dimension(69, 21));
        modificarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarUsuarioButtonActionPerformed(evt);
            }
        });

        modificarPeliculaButton.setBackground(new java.awt.Color(255, 204, 0));
        modificarPeliculaButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        modificarPeliculaButton.setText("Modificar película");
        modificarPeliculaButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        modificarPeliculaButton.setMaximumSize(new java.awt.Dimension(69, 21));
        modificarPeliculaButton.setMinimumSize(new java.awt.Dimension(69, 21));
        modificarPeliculaButton.setName(""); // NOI18N
        modificarPeliculaButton.setPreferredSize(new java.awt.Dimension(69, 21));
        modificarPeliculaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarPeliculaButtonActionPerformed(evt);
            }
        });

        EntregarPeliculaButton.setBackground(new java.awt.Color(255, 204, 0));
        EntregarPeliculaButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        EntregarPeliculaButton.setText("Entregar película");
        EntregarPeliculaButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        EntregarPeliculaButton.setMaximumSize(new java.awt.Dimension(69, 21));
        EntregarPeliculaButton.setMinimumSize(new java.awt.Dimension(69, 21));
        EntregarPeliculaButton.setName(""); // NOI18N
        EntregarPeliculaButton.setPreferredSize(new java.awt.Dimension(69, 21));
        EntregarPeliculaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntregarPeliculaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(EntregarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modificarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modificarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SalirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConsultarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlquilarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AgregarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AgregarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AgregarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AgregarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AlquilarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConsultarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modificarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modificarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EntregarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(SalirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        PrincipaljPanel.setBackground(new java.awt.Color(14, 63, 169));
        PrincipaljPanel.setMaximumSize(new java.awt.Dimension(600, 650));
        PrincipaljPanel.setPreferredSize(new java.awt.Dimension(600, 650));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blockbuster/Blockbuster_logo3.png"))); // NOI18N

        javax.swing.GroupLayout PrincipaljPanelLayout = new javax.swing.GroupLayout(PrincipaljPanel);
        PrincipaljPanel.setLayout(PrincipaljPanelLayout);
        PrincipaljPanelLayout.setHorizontalGroup(
            PrincipaljPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipaljPanelLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        PrincipaljPanelLayout.setVerticalGroup(
            PrincipaljPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipaljPanelLayout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AgregarPeliculajPanel.setBackground(new java.awt.Color(14, 63, 169));
        AgregarPeliculajPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Añadir película", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(255, 204, 0))); // NOI18N
        AgregarPeliculajPanel.setMaximumSize(new java.awt.Dimension(600, 650));
        AgregarPeliculajPanel.setPreferredSize(new java.awt.Dimension(600, 650));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Número de película");

        NumeroPeliculajTextField.setBackground(new java.awt.Color(255, 204, 0));
        NumeroPeliculajTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NumeroPeliculajTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NumeroPeliculajTextField.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Título de la película");

        TituloPeliculajTextField.setBackground(new java.awt.Color(255, 204, 0));
        TituloPeliculajTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TituloPeliculajTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TituloPeliculajTextField.setToolTipText("");
        TituloPeliculajTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TituloPeliculajTextFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Género de la película");

        ConfirmarPeliculaButton.setBackground(new java.awt.Color(255, 204, 0));
        ConfirmarPeliculaButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ConfirmarPeliculaButton.setText("Añadir");
        ConfirmarPeliculaButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        ConfirmarPeliculaButton.setMaximumSize(new java.awt.Dimension(69, 21));
        ConfirmarPeliculaButton.setMinimumSize(new java.awt.Dimension(69, 21));
        ConfirmarPeliculaButton.setName(""); // NOI18N
        ConfirmarPeliculaButton.setPreferredSize(new java.awt.Dimension(69, 21));
        ConfirmarPeliculaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmarPeliculaButtonActionPerformed(evt);
            }
        });

        GeneroPeliculajComboBox.setBackground(new java.awt.Color(255, 204, 0));
        GeneroPeliculajComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GeneroPeliculajComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Drama", "Comedia", "Deportivo", "Cultural" }));
        GeneroPeliculajComboBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 3, true));
        GeneroPeliculajComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GeneroPeliculajComboBoxActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ej: 999999 ");

        javax.swing.GroupLayout AgregarPeliculajPanelLayout = new javax.swing.GroupLayout(AgregarPeliculajPanel);
        AgregarPeliculajPanel.setLayout(AgregarPeliculajPanelLayout);
        AgregarPeliculajPanelLayout.setHorizontalGroup(
            AgregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgregarPeliculajPanelLayout.createSequentialGroup()
                .addGroup(AgregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgregarPeliculajPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(AgregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ConfirmarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AgregarPeliculajPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TituloPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AgregarPeliculajPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(GeneroPeliculajComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addGroup(AgregarPeliculajPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NumeroPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(AgregarPeliculajPanelLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel15)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        AgregarPeliculajPanelLayout.setVerticalGroup(
            AgregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgregarPeliculajPanelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(AgregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumeroPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(AgregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TituloPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addGroup(AgregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GeneroPeliculajComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(ConfirmarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        AgregarUsuariojPanel.setBackground(new java.awt.Color(14, 63, 169));
        AgregarUsuariojPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Añadir Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(255, 204, 0))); // NOI18N
        AgregarUsuariojPanel.setMaximumSize(new java.awt.Dimension(600, 650));
        AgregarUsuariojPanel.setPreferredSize(new java.awt.Dimension(600, 650));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cédula");

        CedulajTextField.setBackground(new java.awt.Color(255, 204, 0));
        CedulajTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CedulajTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CedulajTextField.setToolTipText("");

        NombrejTextField.setBackground(new java.awt.Color(255, 204, 0));
        NombrejTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NombrejTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NombrejTextField.setToolTipText("");
        NombrejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombrejTextFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombre");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Teléfono");

        TelefonojTextField.setBackground(new java.awt.Color(255, 204, 0));
        TelefonojTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TelefonojTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TelefonojTextField.setToolTipText("");
        TelefonojTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelefonojTextFieldActionPerformed(evt);
            }
        });

        ConfirmarUsuarioButton.setBackground(new java.awt.Color(255, 204, 0));
        ConfirmarUsuarioButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ConfirmarUsuarioButton.setText("Añadir");
        ConfirmarUsuarioButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        ConfirmarUsuarioButton.setMaximumSize(new java.awt.Dimension(69, 21));
        ConfirmarUsuarioButton.setMinimumSize(new java.awt.Dimension(69, 21));
        ConfirmarUsuarioButton.setName(""); // NOI18N
        ConfirmarUsuarioButton.setPreferredSize(new java.awt.Dimension(69, 21));
        ConfirmarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmarUsuarioButtonActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Ej: 04241643254 ");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Ej: V27814624 ");

        javax.swing.GroupLayout AgregarUsuariojPanelLayout = new javax.swing.GroupLayout(AgregarUsuariojPanel);
        AgregarUsuariojPanel.setLayout(AgregarUsuariojPanelLayout);
        AgregarUsuariojPanelLayout.setHorizontalGroup(
            AgregarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AgregarUsuariojPanelLayout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(AgregarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ConfirmarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AgregarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AgregarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(AgregarUsuariojPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(NombrejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AgregarUsuariojPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(CedulajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AgregarUsuariojPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TelefonojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(AgregarUsuariojPanelLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(AgregarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)))))
                .addGap(88, 88, 88))
        );
        AgregarUsuariojPanelLayout.setVerticalGroup(
            AgregarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgregarUsuariojPanelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(AgregarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CedulajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(AgregarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombrejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addGroup(AgregarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TelefonojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ConfirmarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        AlquilarPeliculajPanel.setBackground(new java.awt.Color(14, 63, 169));
        AlquilarPeliculajPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alquilar Película", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(255, 204, 0))); // NOI18N
        AlquilarPeliculajPanel.setMaximumSize(new java.awt.Dimension(600, 650));
        AlquilarPeliculajPanel.setPreferredSize(new java.awt.Dimension(600, 650));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fecha dev.");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cédula");

        FechaDevjTextField.setBackground(new java.awt.Color(255, 204, 0));
        FechaDevjTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        FechaDevjTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FechaDevjTextField.setToolTipText("");
        FechaDevjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaDevjTextFieldActionPerformed(evt);
            }
        });

        CedulaAlquilerjTextField.setBackground(new java.awt.Color(255, 204, 0));
        CedulaAlquilerjTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CedulaAlquilerjTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CedulaAlquilerjTextField.setToolTipText("");
        CedulaAlquilerjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaAlquilerjTextFieldActionPerformed(evt);
            }
        });

        AceptarAlquilerButton.setBackground(new java.awt.Color(255, 204, 0));
        AceptarAlquilerButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AceptarAlquilerButton.setText("Aceptar");
        AceptarAlquilerButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        AceptarAlquilerButton.setMaximumSize(new java.awt.Dimension(69, 21));
        AceptarAlquilerButton.setMinimumSize(new java.awt.Dimension(69, 21));
        AceptarAlquilerButton.setName(""); // NOI18N
        AceptarAlquilerButton.setPreferredSize(new java.awt.Dimension(69, 21));
        AceptarAlquilerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarAlquilerButtonActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Película");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ej: 221219 ");

        SeleccionadorPeliculasjComboBox1.setBackground(new java.awt.Color(255, 204, 0));
        SeleccionadorPeliculasjComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SeleccionadorPeliculasjComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        SeleccionadorPeliculasjComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 3, true));
        SeleccionadorPeliculasjComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionadorPeliculasjComboBox1ActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ej: V27814624 ");

        javax.swing.GroupLayout AlquilarPeliculajPanelLayout = new javax.swing.GroupLayout(AlquilarPeliculajPanel);
        AlquilarPeliculajPanel.setLayout(AlquilarPeliculajPanelLayout);
        AlquilarPeliculajPanelLayout.setHorizontalGroup(
            AlquilarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AlquilarPeliculajPanelLayout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AlquilarPeliculajPanelLayout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(AlquilarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AceptarAlquilerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AlquilarPeliculajPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))
                    .addGroup(AlquilarPeliculajPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FechaDevjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AlquilarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AlquilarPeliculajPanelLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel12))
                        .addGroup(AlquilarPeliculajPanelLayout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CedulaAlquilerjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(87, 87, 87))
            .addGroup(AlquilarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AlquilarPeliculajPanelLayout.createSequentialGroup()
                    .addContainerGap(294, Short.MAX_VALUE)
                    .addComponent(SeleccionadorPeliculasjComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(77, 77, 77)))
        );
        AlquilarPeliculajPanelLayout.setVerticalGroup(
            AlquilarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AlquilarPeliculajPanelLayout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(AlquilarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CedulaAlquilerjTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addGroup(AlquilarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FechaDevjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(AceptarAlquilerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(AlquilarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(AlquilarPeliculajPanelLayout.createSequentialGroup()
                    .addGap(132, 132, 132)
                    .addComponent(SeleccionadorPeliculasjComboBox1)
                    .addGap(440, 440, 440)))
        );

        ConsultarjPanel.setBackground(new java.awt.Color(14, 63, 169));
        ConsultarjPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(255, 204, 0))); // NOI18N
        ConsultarjPanel.setPreferredSize(new java.awt.Dimension(600, 650));

        ConsultarjTextField.setBackground(new java.awt.Color(255, 204, 0));
        ConsultarjTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ConsultarjTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ConsultarjTextField.setToolTipText("");
        ConsultarjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarjTextFieldActionPerformed(evt);
            }
        });

        AceptarConsultarButton.setBackground(new java.awt.Color(255, 204, 0));
        AceptarConsultarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AceptarConsultarButton.setText("Consultar");
        AceptarConsultarButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        AceptarConsultarButton.setMaximumSize(new java.awt.Dimension(69, 21));
        AceptarConsultarButton.setMinimumSize(new java.awt.Dimension(69, 21));
        AceptarConsultarButton.setName(""); // NOI18N
        AceptarConsultarButton.setPreferredSize(new java.awt.Dimension(69, 21));
        AceptarConsultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarConsultarButtonActionPerformed(evt);
            }
        });

        ConsultarPeliculaButton.setBackground(new java.awt.Color(255, 204, 0));
        ConsultarPeliculaButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ConsultarPeliculaButton.setText("Películas alquiladas");
        ConsultarPeliculaButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        ConsultarPeliculaButton.setMaximumSize(new java.awt.Dimension(69, 21));
        ConsultarPeliculaButton.setMinimumSize(new java.awt.Dimension(69, 21));
        ConsultarPeliculaButton.setName(""); // NOI18N
        ConsultarPeliculaButton.setPreferredSize(new java.awt.Dimension(69, 21));
        ConsultarPeliculaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarPeliculaButtonActionPerformed(evt);
            }
        });

        MostrarInfoConsultadajScrollPane.setAutoscrolls(true);
        MostrarInfoConsultadajScrollPane.setHorizontalScrollBar(null);

        InfoConsultadajTextField.setBackground(new java.awt.Color(255, 204, 0));
        InfoConsultadajTextField.setColumns(20);
        InfoConsultadajTextField.setRows(5);
        MostrarInfoConsultadajScrollPane.setViewportView(InfoConsultadajTextField);

        javax.swing.GroupLayout ConsultarjPanelLayout = new javax.swing.GroupLayout(ConsultarjPanel);
        ConsultarjPanel.setLayout(ConsultarjPanelLayout);
        ConsultarjPanelLayout.setHorizontalGroup(
            ConsultarjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarjPanelLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(ConsultarjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConsultarjPanelLayout.createSequentialGroup()
                        .addComponent(AceptarConsultarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addComponent(ConsultarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ConsultarjTextField)
                    .addComponent(MostrarInfoConsultadajScrollPane))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        ConsultarjPanelLayout.setVerticalGroup(
            ConsultarjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultarjPanelLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(ConsultarjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(ConsultarjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AceptarConsultarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConsultarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(MostrarInfoConsultadajScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        ModificarPeliculajPanel.setBackground(new java.awt.Color(14, 63, 169));
        ModificarPeliculajPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificar Película", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(255, 204, 0))); // NOI18N
        ModificarPeliculajPanel.setPreferredSize(new java.awt.Dimension(600, 650));

        GuardarModificarPeliButton.setBackground(new java.awt.Color(255, 204, 0));
        GuardarModificarPeliButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GuardarModificarPeliButton.setText("Guardar");
        GuardarModificarPeliButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        GuardarModificarPeliButton.setMaximumSize(new java.awt.Dimension(69, 21));
        GuardarModificarPeliButton.setMinimumSize(new java.awt.Dimension(69, 21));
        GuardarModificarPeliButton.setName(""); // NOI18N
        GuardarModificarPeliButton.setPreferredSize(new java.awt.Dimension(69, 21));
        GuardarModificarPeliButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarModificarPeliButtonActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Título de la película");

        ModificarNumeroPeliculajTextField.setBackground(new java.awt.Color(255, 204, 0));
        ModificarNumeroPeliculajTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ModificarNumeroPeliculajTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ModificarNumeroPeliculajTextField.setToolTipText("");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Número de película");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Género de la película");

        ModificarTituloPeliculajTextField.setBackground(new java.awt.Color(255, 204, 0));
        ModificarTituloPeliculajTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ModificarTituloPeliculajTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ModificarTituloPeliculajTextField.setToolTipText("");
        ModificarTituloPeliculajTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarTituloPeliculajTextFieldActionPerformed(evt);
            }
        });

        BuscarModificarPeliButton.setBackground(new java.awt.Color(255, 204, 0));
        BuscarModificarPeliButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BuscarModificarPeliButton.setText("Buscar");
        BuscarModificarPeliButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        BuscarModificarPeliButton.setMaximumSize(new java.awt.Dimension(69, 21));
        BuscarModificarPeliButton.setMinimumSize(new java.awt.Dimension(69, 21));
        BuscarModificarPeliButton.setName(""); // NOI18N
        BuscarModificarPeliButton.setPreferredSize(new java.awt.Dimension(69, 21));
        BuscarModificarPeliButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarModificarPeliButtonActionPerformed(evt);
            }
        });

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Ej: 999999 ");

        ModificarGeneroPeliculajComboBox.setBackground(new java.awt.Color(255, 204, 0));
        ModificarGeneroPeliculajComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ModificarGeneroPeliculajComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Drama", "Comedia", "Deportivo", "Cultural" }));
        ModificarGeneroPeliculajComboBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 3, true));
        ModificarGeneroPeliculajComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarGeneroPeliculajComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ModificarPeliculajPanelLayout = new javax.swing.GroupLayout(ModificarPeliculajPanel);
        ModificarPeliculajPanel.setLayout(ModificarPeliculajPanelLayout);
        ModificarPeliculajPanelLayout.setHorizontalGroup(
            ModificarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModificarPeliculajPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GuardarModificarPeliButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addGroup(ModificarPeliculajPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(ModificarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ModificarPeliculajPanelLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(ModificarGeneroPeliculajComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ModificarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(ModificarPeliculajPanelLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BuscarModificarPeliButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModificarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ModificarPeliculajPanelLayout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ModificarNumeroPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ModificarPeliculajPanelLayout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ModificarTituloPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        ModificarPeliculajPanelLayout.setVerticalGroup(
            ModificarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModificarPeliculajPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(ModificarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModificarNumeroPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ModificarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ModificarPeliculajPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BuscarModificarPeliButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ModificarPeliculajPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(ModificarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModificarTituloPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addGroup(ModificarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(ModificarGeneroPeliculajComboBox))
                .addGap(57, 57, 57)
                .addComponent(GuardarModificarPeliButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        ModificarUsuariojPanel.setBackground(new java.awt.Color(14, 63, 169));
        ModificarUsuariojPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificar Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(255, 204, 0))); // NOI18N
        ModificarUsuariojPanel.setMaximumSize(new java.awt.Dimension(600, 650));
        ModificarUsuariojPanel.setPreferredSize(new java.awt.Dimension(600, 650));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nombre");

        ModificarTelefonojTextField.setBackground(new java.awt.Color(255, 204, 0));
        ModificarTelefonojTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ModificarTelefonojTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ModificarTelefonojTextField.setToolTipText("");
        ModificarTelefonojTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarTelefonojTextFieldActionPerformed(evt);
            }
        });

        ModificarCedulajTextField.setBackground(new java.awt.Color(255, 204, 0));
        ModificarCedulajTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ModificarCedulajTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ModificarCedulajTextField.setToolTipText("");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Teléfono");

        ModificarNombrejTextField.setBackground(new java.awt.Color(255, 204, 0));
        ModificarNombrejTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ModificarNombrejTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ModificarNombrejTextField.setToolTipText("");
        ModificarNombrejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarNombrejTextFieldActionPerformed(evt);
            }
        });

        GuardarModificarUsuarioButton.setBackground(new java.awt.Color(255, 204, 0));
        GuardarModificarUsuarioButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GuardarModificarUsuarioButton.setText("Guardar");
        GuardarModificarUsuarioButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        GuardarModificarUsuarioButton.setMaximumSize(new java.awt.Dimension(69, 21));
        GuardarModificarUsuarioButton.setMinimumSize(new java.awt.Dimension(69, 21));
        GuardarModificarUsuarioButton.setName(""); // NOI18N
        GuardarModificarUsuarioButton.setPreferredSize(new java.awt.Dimension(69, 21));
        GuardarModificarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarModificarUsuarioButtonActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Cédula");

        BuscarModificarUsuarioButton.setBackground(new java.awt.Color(255, 204, 0));
        BuscarModificarUsuarioButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BuscarModificarUsuarioButton.setText("Buscar");
        BuscarModificarUsuarioButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        BuscarModificarUsuarioButton.setMaximumSize(new java.awt.Dimension(69, 21));
        BuscarModificarUsuarioButton.setMinimumSize(new java.awt.Dimension(69, 21));
        BuscarModificarUsuarioButton.setName(""); // NOI18N
        BuscarModificarUsuarioButton.setPreferredSize(new java.awt.Dimension(69, 21));
        BuscarModificarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarModificarUsuarioButtonActionPerformed(evt);
            }
        });

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Ej: V27814624 ");

        javax.swing.GroupLayout ModificarUsuariojPanelLayout = new javax.swing.GroupLayout(ModificarUsuariojPanel);
        ModificarUsuariojPanel.setLayout(ModificarUsuariojPanelLayout);
        ModificarUsuariojPanelLayout.setHorizontalGroup(
            ModificarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModificarUsuariojPanelLayout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addGroup(ModificarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(GuardarModificarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ModificarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(ModificarUsuariojPanelLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BuscarModificarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModificarUsuariojPanelLayout.createSequentialGroup()
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ModificarNombrejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(ModificarUsuariojPanelLayout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(ModificarCedulajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModificarUsuariojPanelLayout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ModificarTelefonojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(93, 93, 93))
        );
        ModificarUsuariojPanelLayout.setVerticalGroup(
            ModificarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModificarUsuariojPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(ModificarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModificarCedulajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ModificarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ModificarUsuariojPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BuscarModificarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ModificarUsuariojPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addGroup(ModificarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModificarNombrejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addGroup(ModificarUsuariojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModificarTelefonojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(GuardarModificarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        EntregarPeliculajPanel.setBackground(new java.awt.Color(14, 63, 169));
        EntregarPeliculajPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entregar Película", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(255, 204, 0))); // NOI18N
        EntregarPeliculajPanel.setMaximumSize(new java.awt.Dimension(600, 650));
        EntregarPeliculajPanel.setPreferredSize(new java.awt.Dimension(600, 650));

        EntregaCedulajTextField.setBackground(new java.awt.Color(255, 204, 0));
        EntregaCedulajTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        EntregaCedulajTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        EntregaCedulajTextField.setToolTipText("");

        AceptarEntregarPeliculaButton.setBackground(new java.awt.Color(255, 204, 0));
        AceptarEntregarPeliculaButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AceptarEntregarPeliculaButton.setText("Entregar");
        AceptarEntregarPeliculaButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 6, true));
        AceptarEntregarPeliculaButton.setMaximumSize(new java.awt.Dimension(69, 21));
        AceptarEntregarPeliculaButton.setMinimumSize(new java.awt.Dimension(69, 21));
        AceptarEntregarPeliculaButton.setName(""); // NOI18N
        AceptarEntregarPeliculaButton.setPreferredSize(new java.awt.Dimension(69, 21));
        AceptarEntregarPeliculaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarEntregarPeliculaButtonActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Cédula");

        EntregaNumeroPeliculajTextField.setBackground(new java.awt.Color(255, 204, 0));
        EntregaNumeroPeliculajTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        EntregaNumeroPeliculajTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        EntregaNumeroPeliculajTextField.setToolTipText("");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Número de película");

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Ej: V27814624 ");

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Ej: 999999 ");

        javax.swing.GroupLayout EntregarPeliculajPanelLayout = new javax.swing.GroupLayout(EntregarPeliculajPanel);
        EntregarPeliculajPanel.setLayout(EntregarPeliculajPanelLayout);
        EntregarPeliculajPanelLayout.setHorizontalGroup(
            EntregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EntregarPeliculajPanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(EntregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EntregarPeliculajPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(EntregarPeliculajPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
                        .addComponent(AceptarEntregarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))
                    .addGroup(EntregarPeliculajPanelLayout.createSequentialGroup()
                        .addGroup(EntregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(EntregarPeliculajPanelLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EntregaCedulajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EntregarPeliculajPanelLayout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EntregaNumeroPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(75, Short.MAX_VALUE))))
            .addGroup(EntregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(EntregarPeliculajPanelLayout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(jLabel27)
                    .addContainerGap(459, Short.MAX_VALUE)))
        );
        EntregarPeliculajPanelLayout.setVerticalGroup(
            EntregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EntregarPeliculajPanelLayout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addGroup(EntregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EntregaCedulajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(EntregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EntregaNumeroPeliculajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(175, 175, 175)
                .addComponent(AceptarEntregarPeliculaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(EntregarPeliculajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EntregarPeliculajPanelLayout.createSequentialGroup()
                    .addContainerGap(343, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(231, 231, 231)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PrincipaljPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AgregarPeliculajPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AgregarUsuariojPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AlquilarPeliculajPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsultarjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ModificarPeliculajPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ModificarUsuariojPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EntregarPeliculajPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ModificarUsuariojPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(ModificarPeliculajPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(ConsultarjPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(AlquilarPeliculajPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(AgregarUsuariojPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(PrincipaljPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(AgregarPeliculajPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(Menu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(EntregarPeliculajPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirButtonActionPerformed
          
        //verificaion y funcion para salir de la partida
        if (JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir? " , "Salir", YES_NO_OPTION) == 0){
        
            System.exit(0);
        }
    }//GEN-LAST:event_SalirButtonActionPerformed

    private void AgregarPeliculaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarPeliculaButtonActionPerformed
        // TODO add your handling code here:
        PrincipaljPanel.setVisible(false);
        AgregarPeliculajPanel.setVisible(true);
        AgregarUsuariojPanel.setVisible(false);
        AlquilarPeliculajPanel.setVisible(false);
        ConsultarjPanel.setVisible(false);
        
        //Inicializacion de los inputs 
        NumeroPeliculajTextField.setEditable(true);
        TituloPeliculajTextField.setEditable(true);
        GeneroPeliculajComboBox.setEnabled(true);
        NumeroPeliculajTextField.setText("");
        TituloPeliculajTextField.setText("");
        
        //inicializacion del boton de aceptar
        ConfirmarPeliculaButton.setEnabled(true);
        
        
    }//GEN-LAST:event_AgregarPeliculaButtonActionPerformed

    private void AgregarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarUsuarioButtonActionPerformed
        // TODO add your handling code here:
        PrincipaljPanel.setVisible(false);
        AgregarPeliculajPanel.setVisible(false);
        AgregarUsuariojPanel.setVisible(true);
        AlquilarPeliculajPanel.setVisible(false);
        ConsultarjPanel.setVisible(false);
        
        //inicializacion de los inputs
        CedulajTextField.setEditable(true);
        NombrejTextField.setEditable(true);
        TelefonojTextField.setEditable(true);
        CedulajTextField.setText("");
        NombrejTextField.setText("");
        TelefonojTextField.setText("");
        
        //inicializacion del boton de aceptar
        ConfirmarUsuarioButton.setEnabled(true);
        
    }//GEN-LAST:event_AgregarUsuarioButtonActionPerformed

    private void AlquilarPeliculaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlquilarPeliculaButtonActionPerformed
        // TODO add your handling code here:
        PrincipaljPanel.setVisible(false);
        AgregarPeliculajPanel.setVisible(false);
        AgregarUsuariojPanel.setVisible(false);
        AlquilarPeliculajPanel.setVisible(true);
        ConsultarjPanel.setVisible(false);
        
        //inicializacion de los inputs
        SeleccionadorPeliculasjComboBox1.setEnabled(true);
        CedulaAlquilerjTextField.setEditable(true);
        FechaDevjTextField.setEditable(true);
        CedulaAlquilerjTextField.setText("");
        FechaDevjTextField.setText("");
        
        //inicializacion del boton de aceptar
        AceptarAlquilerButton.setEnabled(true);
        SeleccionadorPeliculasjComboBox1.removeAllItems();
        int counter = 0;
        while(películas[counter]!= null){
            if (películas[counter].getCiAlquilada()==null) {
                SeleccionadorPeliculasjComboBox1.addItem(películas[counter].getTítuloDePelícula());                
            }
            counter++;
        }
    }//GEN-LAST:event_AlquilarPeliculaButtonActionPerformed

    private void ConsultarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarButtonActionPerformed
        // TODO add your handling code here:
        PrincipaljPanel.setVisible(false);
        AgregarPeliculajPanel.setVisible(false);
        AgregarUsuariojPanel.setVisible(false);
        AlquilarPeliculajPanel.setVisible(false);
        ConsultarjPanel.setVisible(true);
        ModificarUsuariojPanel.setVisible(false);
        ModificarPeliculajPanel.setVisible(false);
        EntregarPeliculajPanel.setVisible(false);
        ConsultarjTextField.setEditable(true);

        //inicializar boton
        AceptarConsultarButton.setEnabled(true);
        ConsultarPeliculaButton.setEnabled(false);
        
        //vaciar input
        ConsultarjTextField.setText("");
        InfoConsultadajTextField.setText("");
    }//GEN-LAST:event_ConsultarButtonActionPerformed

    private void TituloPeliculajTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TituloPeliculajTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TituloPeliculajTextFieldActionPerformed

    private void ConfirmarPeliculaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmarPeliculaButtonActionPerformed
       
        int numPeliAuxiliar = Integer.parseInt(NumeroPeliculajTextField.getText());
        int counter = 0;
        boolean películaRepetida = false;
        while(películas[counter]!= null && !películaRepetida){
            if (películas[counter].getNúmeroDePelícula() == numPeliAuxiliar) {
                películaRepetida = true;
            }
            counter++;
        }
        if ( !(TituloPeliculajTextField.getText().length()>30) && !películaRepetida && !NumeroPeliculajTextField.getText().equals("") && !TituloPeliculajTextField.getText().equals("") && (NumeroPeliculajTextField.getText().length() ==6)){

            //Aca iria lo de añadir la película al array y eso 

            NumeroPeliculajTextField.setEditable(false);
            TituloPeliculajTextField.setEditable(false);
            GeneroPeliculajComboBox.setEnabled(false);
            ConfirmarPeliculaButton.setEnabled(false);
            Película película = new Película(Integer.parseInt(NumeroPeliculajTextField.getText()),TituloPeliculajTextField.getText(), GeneroPeliculajComboBox.getSelectedItem().toString());
            GuardarInformacionPelícula(película);
            OrdenarIndicePelicula();
            JOptionPane.showMessageDialog(null, "Película cargada exitosamente. \nPara agregar otra película por favor vuelve a darle click a la opción del menú");
            
        }
        else {

            JOptionPane.showMessageDialog(null, "Debes llenar bien todos los campos para poder agregar una película");
        }
        
        for (int i = 0; i < counter+1; i++) {
            System.out.println("La película es: "+películas[this.indiPeli[i].índice].getTítuloDePelícula());
            System.out.println("El número es: "+películas[this.indiPeli[i].índice].getNúmeroDePelícula());
        
        }
    }//GEN-LAST:event_ConfirmarPeliculaButtonActionPerformed

    private void NombrejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombrejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombrejTextFieldActionPerformed

    private void TelefonojTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelefonojTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelefonojTextFieldActionPerformed

    private void ConfirmarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmarUsuarioButtonActionPerformed
        
        int counter = 0;
        boolean cedulaRepetida = false;
        while(usuarios[counter]!= null && !cedulaRepetida){
            if (usuarios[counter].getCédula().equals(CedulajTextField.getText())) {
                cedulaRepetida = true;
            }
            counter++;
        }
        char V = 'V';
        char E = 'E';
        boolean cedulaNoApta = false;
        if (CedulajTextField.getText().charAt(0) != V && CedulajTextField.getText().charAt(0) != E) {
            cedulaNoApta = true;
        }
        System.out.println(cedulaNoApta);
        for (int i = 1; i < CedulajTextField.getText().length(); i++) {
            char caracterCedula = CedulajTextField.getText().charAt(i);
            if ((caracterCedula != '0') && (caracterCedula != '1') && caracterCedula != '2' && caracterCedula != '3' && caracterCedula != '4' && caracterCedula != '5' && caracterCedula != '6' && caracterCedula != '7' && caracterCedula != '8' && caracterCedula != '9') {
                cedulaNoApta = true;
            }
        }
        System.out.println(cedulaNoApta);
        if (!cedulaNoApta && !cedulaRepetida && !CedulajTextField.getText().equals("") && !NombrejTextField.getText().equals("") && ( NombrejTextField.getText().length() < 30) && (TelefonojTextField.getText().length() == 11) && !TelefonojTextField.getText().equals("")){
                
            //Aca iria lo de añadir la película al array y eso 

            CedulajTextField.setEditable(false);
            NombrejTextField.setEditable(false);
            TelefonojTextField.setEditable(false);
            ConfirmarUsuarioButton.setEnabled(false);
            Usuario usuario = new Usuario(CedulajTextField.getText().toUpperCase(), NombrejTextField.getText(), TelefonojTextField.getText());
            GuardarInformaciónUsuario(usuario);
            OrdenarIndiceUsuario();
            counter=0;
            while(indiUsuario[counter]!=null){
                System.out.println(indiUsuario[counter].getCedulaUsuario());
                counter++;
            }
            JOptionPane.showMessageDialog(null, "Usuario cargado exitosamente. \nPara agregar otro usuario por favor vuelve a darle click a la opción del menú");
        }else if(!(NombrejTextField.getText().length() < 30)){
            JOptionPane.showMessageDialog(null, "El nombre de usuario no puede exceder de 30 caracteres.");
        }else if(!(TelefonojTextField.getText().length() == 11)){
            JOptionPane.showMessageDialog(null, "El número de teléfono debe tener exactamente 11 números");
        }else {

            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos para poder agregar un usuario");
        }
        
    }//GEN-LAST:event_ConfirmarUsuarioButtonActionPerformed

    private void CedulaAlquilerjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaAlquilerjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaAlquilerjTextFieldActionPerformed

    private void FechaDevjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaDevjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaDevjTextFieldActionPerformed

    private void AceptarAlquilerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarAlquilerButtonActionPerformed
        
        
        //int numCedulaAux = Integer.parseInt(CedulaAlquilerjTextField.getText());
        int counter = 0;
        boolean cedulaExiste = false;
        while(usuarios[counter]!= null && !cedulaExiste){
            if (usuarios[counter].getCédula().equals(CedulaAlquilerjTextField.getText())) {
                cedulaExiste = true;
            }
            counter++;
        }
        if (cedulaExiste && !CedulaAlquilerjTextField.getText().equals("") && !FechaDevjTextField.getText().equals("") && (FechaDevjTextField.getText().length() == 8)){
                
                //Aca iria lo de añadir la película al array y eso 
                
                SeleccionadorPeliculasjComboBox1.setEnabled(false);
                CedulaAlquilerjTextField.setEditable(false);
                FechaDevjTextField.setEditable(false);
                AceptarAlquilerButton.setEnabled(false);
                counter = 0;
                while(películas[counter]!= null){
                    if (películas[counter].getTítuloDePelícula().equals(SeleccionadorPeliculasjComboBox1.getSelectedItem()) ) {
                        películas[counter].setCiAlquilada(CedulaAlquilerjTextField.getText());
                        String aux = FechaDevjTextField.getText();
                        aux = aux.substring(0,2) + "/" +aux.substring(2,4) + "/"+aux.substring(4, aux.length());
                        películas[counter].setFechaDeDevolución(aux);
                    }
                    counter++;
                }
                try{
                    BufferedWriter bw = new BufferedWriter(new FileWriter(this.archivoPelícula));
                    for (int i = 0; i < counter; i++) {

                        bw.write(String.valueOf(películas[i].getNúmeroDePelícula()));
                        bw.write(",");
                        bw.write(películas[i].getTítuloDePelícula());
                        bw.write(",");
                        bw.write(películas[i].getGéneroDePelícula());
                        bw.write(",");
                        if (películas[i].getCiAlquilada() != null) {
                            bw.write(películas[i].getCiAlquilada());                            
                        }
                        bw.write(",");
                        if (películas[i].getFechaDeDevolución() != null) {
                            bw.write(películas[i].getFechaDeDevolución());                                                        
                        }
                        if (i<counter) {
                            bw.newLine();
                        }
                    }
                    bw.close();
                }catch (Exception e) {
                    System.out.println("No se pudo escribir el archivo de películas.");
                }
                JOptionPane.showMessageDialog(null, "Película alquilada exitosamente!. \nPara alquilar otra película por favor vuelve a darle click a la opción del menú");

        }else if(!cedulaExiste){
            JOptionPane.showMessageDialog(null, "La cédula ingresada no corresponde a la de ningún usuario registrado");

        }else if(!(FechaDevjTextField.getText().length() == 8)){
            JOptionPane.showMessageDialog(null, "Debes ingresar exactamente 8 dígitos para la fecha. "
                    + "\n Los primeros dos serán el día, luego los dos siguientes serán el mes, y los 4 últimos serán el año.");
        }else {

            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos para poder alquilar una película");
        }
    }//GEN-LAST:event_AceptarAlquilerButtonActionPerformed

    private void ConsultarjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConsultarjTextFieldActionPerformed

    private void AceptarConsultarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarConsultarButtonActionPerformed
        //llama a la funcion que chequea si el input esta formado solo por numeros 
        char V = 'V';
        char E = 'E';
        char slash = '/';
        boolean cedulaNoApta = false;
        boolean fecha = false;
        if (ConsultarjTextField.getText().charAt(0) != V && ConsultarjTextField.getText().charAt(0) != E) {
            cedulaNoApta = true;
        }
        System.out.println(cedulaNoApta);
        for (int i = 1; i < ConsultarjTextField.getText().length(); i++) {
            char caracterCedula = ConsultarjTextField.getText().charAt(i);
            if ((caracterCedula != '0') && (caracterCedula != '1') && caracterCedula != '2' && caracterCedula != '3' && caracterCedula != '4' && caracterCedula != '5' && caracterCedula != '6' && caracterCedula != '7' && caracterCedula != '8' && caracterCedula != '9') {
                cedulaNoApta = true;
            }
        }
        if ((ConsultarjTextField.getText().charAt(2) == slash) && (ConsultarjTextField.getText().charAt(5) == slash)) {
            fecha = true;
        }
        String auxiliarMayuscula = ConsultarjTextField.getText();
        auxiliarMayuscula = auxiliarMayuscula.toUpperCase();
        if(checkInput()){
            int índice = BusquedaBinariaPelícula(Integer.parseInt(ConsultarjTextField.getText()));
            System.out.println(índice);
            if (índice !=-1) {
                System.out.println("La película es: " + películas[índice].getTítuloDePelícula());
                if (películas[índice].getCiAlquilada()!=null) {
                    int índiceCedula = BusquedaBinariaUsuario(Integer.parseInt(películas[índice].getCiAlquilada().substring(1, películas[índice].getCiAlquilada().length())));
                    
                    InfoConsultadajTextField.setText("Título de la película: "+películas[índice].getTítuloDePelícula()+"\nNúmero de la película: "+películas[índice].getNúmeroDePelícula()+"\n Genéro de la película: " +películas[índice].getGéneroDePelícula()+"\n Película alquilada por: " + usuarios[índiceCedula].getNombre() +"\n Número de cédula del usuario: "+ películas[índice].getCiAlquilada()+"\n La película será devuelta en: " +películas[índice].getFechaDeDevolución() );
                }else{
                    InfoConsultadajTextField.setText("Título de la película: "+películas[índice].getTítuloDePelícula()+"\nNúmero de la película: "+películas[índice].getNúmeroDePelícula()+"\n Genéro de la película: " +películas[índice].getGéneroDePelícula()+"\n Esta película no está alquilida");
                }
                JOptionPane.showMessageDialog(null, "Consulta de película exitosa.");    
            }else{
                JOptionPane.showMessageDialog(null, "Debes ingresar una película que exista.");    
            }
        }else if(!cedulaNoApta){
            int índice = BusquedaBinariaUsuario(Integer.parseInt(ConsultarjTextField.getText().substring(1, ConsultarjTextField.getText().length())));
            if(índice !=-1){
                InfoConsultadajTextField.setText("Nombre del usuario: " + usuarios[índice].getNombre() + "\nNúmero de cédula: " + usuarios[índice].getCédula() + "\nNúmero de teléfono: "+ usuarios[índice].getTeléfono());
                JOptionPane.showMessageDialog(null, "Consulta de usuario exitosa.");
                ConsultarPeliculaButton.setEnabled(true);
                ConsultarjTextField.setEditable(false);
            }else{
                JOptionPane.showMessageDialog(null, "Debes ingresar un número de cédula de un usuario registrado");    
            }
        }else if(auxiliarMayuscula.equals("DRAMA")){
            String aux = "Las películas de drama son: ";
            int counter = 0;
            while(películas[counter]!=null){
                counter++;
            }
            for (int i = 0; i < counter; i++) {
                if(películas[i].getGéneroDePelícula().equals("Drama"))
                    aux = (aux + "\n" + películas[i].getTítuloDePelícula());
            }
            System.out.println(aux);
            InfoConsultadajTextField.setText(aux);
            JOptionPane.showMessageDialog(null, "Consulta de género exitosa.");        
        }else if(auxiliarMayuscula.equals("COMEDIA")){
            String aux = "Las películas de comedia son: ";
            int counter = 0;
            while(películas[counter]!=null){
                counter++;
            }
            for (int i = 0; i < counter; i++) {
                if(películas[i].getGéneroDePelícula().equals("Comedia"))
                    aux = (aux + "\n" + películas[i].getTítuloDePelícula());
            }
            System.out.println(aux);
            InfoConsultadajTextField.setText(aux);
            JOptionPane.showMessageDialog(null, "Consulta de género exitosa.");        
        }else if(auxiliarMayuscula.equals("DEPORTIVO")){
            String aux = "Las películas deportivas son: ";
            int counter = 0;
            while(películas[counter]!=null){
                counter++;
                System.out.println(counter);
            }
            for (int i = 0; i < counter; i++) {
                if(películas[i].getGéneroDePelícula().equals("Deportivo"))
                    aux = (aux + "\n" + películas[i].getTítuloDePelícula());
            }
            System.out.println(aux);
            InfoConsultadajTextField.setText(aux);
            JOptionPane.showMessageDialog(null, "Consulta de género exitosa.");        
        }else if(auxiliarMayuscula.equals("CULTURAL")){
            String aux = "Las películas de cultura son: ";
            int counter = 0;
            while(películas[counter]!=null){
                counter++;
                System.out.println(counter);
            }
            for (int i = 0; i < counter; i++) {
                if(películas[i].getGéneroDePelícula().equals("Cultural"))
                    aux = (aux + "\n" + películas[i].getTítuloDePelícula());
                System.out.println(aux);
            }
            System.out.println(aux);
            InfoConsultadajTextField.setText(aux);
            JOptionPane.showMessageDialog(null, "Consulta de género exitosa.");        
        }else if(fecha){
            String aux = "Las películas a devolver ese día son: ";
            int counter = 0;
            while(películas[counter]!=null){
                counter++;
            }
            boolean alguienDevuelve = false;
            for (int i = 0; i < counter; i++) {
                if (películas[i].getFechaDeDevolución()!=null) {
                    if(películas[i].getFechaDeDevolución().equals(ConsultarjTextField.getText())){
                        int índiceCedula = BusquedaBinariaUsuario(Integer.parseInt(películas[i].getCiAlquilada().substring(1, películas[i].getCiAlquilada().length())));
                        aux = (aux + "\n" + películas[i].getNúmeroDePelícula()+" "+películas[i].getTítuloDePelícula() + " será devuelta por: " + películas[i].getCiAlquilada() +  " " + usuarios[índiceCedula].getNombre());
                        alguienDevuelve = true;
                    }
                }
                
            }
            if (!alguienDevuelve) {
                aux = "Ese día no se entregará ninguna película.";
            }
            
            InfoConsultadajTextField.setText(aux);
        }else{
            JOptionPane.showMessageDialog(null, "Debe escribir una sentencia válida. "
                    + "\n Si deseas buscar un usuario ingresa las letras V o E seguido del número de cédula. Ejemplo: V27814624 "
                    + "\n Si deseas buscar una película, escribe los  6 dígitos del número de película "
                    + "\n Si deseas consultar por los géneros de película, ingresa 'Drama', 'Cultural', 'Deportivo' o 'Comedia' "
                    + "\n Si deseas buscar las películas que serán devueltas en un día ingresa la fecha en el siguiente formato. Ejemplo: 02/12/2019");    
        }
        
        //funcion para que rellenes de info la cosa scrolleable
        //InfoConsultadajTextField.setText(input);
    }//GEN-LAST:event_AceptarConsultarButtonActionPerformed

    private void ConsultarPeliculaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarPeliculaButtonActionPerformed
        // TODO add your handling code here:
        InfoConsultadajTextField.setText("");
        ConsultarPeliculaButton.setEnabled(false);
        char V = 'V';
        char E = 'E';
        boolean cedulaNoApta = false;
        if (ConsultarjTextField.getText().charAt(0) != V && ConsultarjTextField.getText().charAt(0) != E) {
            cedulaNoApta = true;
        }
        System.out.println(cedulaNoApta);
        for (int i = 1; i < ConsultarjTextField.getText().length(); i++) {
            char caracterCedula = ConsultarjTextField.getText().charAt(i);
            if ((caracterCedula != '0') && (caracterCedula != '1') && caracterCedula != '2' && caracterCedula != '3' && caracterCedula != '4' && caracterCedula != '5' && caracterCedula != '6' && caracterCedula != '7' && caracterCedula != '8' && caracterCedula != '9') {
                cedulaNoApta = true;
            }
        }
        if (!cedulaNoApta) {
            int índice = BusquedaBinariaUsuario(Integer.parseInt(ConsultarjTextField.getText().substring(1, ConsultarjTextField.getText().length())));
            if(índice !=-1){
                String aux = ("Las películas alquiladas por " +usuarios[índice].getNombre())+ " son: ";
                //InfoConsultadajTextField.setText("Nombre del usuario: " + usuarios[índice].getNombre() + "\nNúmero de cédula: " + usuarios[índice].getCédula() + "\nNúmero de teléfono: "+ usuarios[índice].getTeléfono());
                int counter = 0;
                while(películas[counter]!=null){
                    counter++;
                }
                for (int i = 0; i < counter; i++) {
                    if (películas[i].getCiAlquilada()!=null) {
                        if (películas[i].getCiAlquilada().equals(ConsultarjTextField.getText())) {
                            aux = (aux + "\n" + películas[i].getTítuloDePelícula());
                        }
                    }
                    System.out.println(i);
                }
                InfoConsultadajTextField.setText(aux);
            }else{
                JOptionPane.showMessageDialog(null, "Debes ingresar un número de cédula de un usuario registrado");    
            }
            
        }
        //funcion para que rellenes de info la cosa scrolleable
        //InfoConsultadajTextField.setText(input);
    }//GEN-LAST:event_ConsultarPeliculaButtonActionPerformed

    private void modificarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarUsuarioButtonActionPerformed
      
        PrincipaljPanel.setVisible(false);
        AgregarPeliculajPanel.setVisible(false);
        AgregarUsuariojPanel.setVisible(false);
        AlquilarPeliculajPanel.setVisible(false);
        ConsultarjPanel.setVisible(false);
        ModificarPeliculajPanel.setVisible(false);
        ModificarUsuariojPanel.setVisible(true);
        EntregarPeliculajPanel.setVisible(false);
        
        //inicializacion
        BuscarModificarUsuarioButton.setEnabled(true);
        ModificarCedulajTextField.setEditable(true);
        ModificarCedulajTextField.setText("");
        GuardarModificarUsuarioButton.setEnabled(false);
        ModificarNombrejTextField.setEditable(true);
        ModificarTelefonojTextField.setEditable(true);
        ModificarNombrejTextField.setVisible(false);
        ModificarTelefonojTextField.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        ModificarNombrejTextField.setText("");
        ModificarTelefonojTextField.setText("");
        
        
    }//GEN-LAST:event_modificarUsuarioButtonActionPerformed

    private void modificarPeliculaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarPeliculaButtonActionPerformed
        // TODO add your handling code here:
        PrincipaljPanel.setVisible(false);
        AgregarPeliculajPanel.setVisible(false);
        AgregarUsuariojPanel.setVisible(false);
        AlquilarPeliculajPanel.setVisible(false);
        ConsultarjPanel.setVisible(false);
        ModificarPeliculajPanel.setVisible(true);
        ModificarUsuariojPanel.setVisible(false);
        EntregarPeliculajPanel.setVisible(false);
        
        //inicializar
        BuscarModificarPeliButton.setEnabled(true);
        ModificarNumeroPeliculajTextField.setEditable(true);
        ModificarNumeroPeliculajTextField.setText("");
        GuardarModificarPeliButton.setEnabled(true);
        ModificarTituloPeliculajTextField.setEditable(true);
        ModificarGeneroPeliculajComboBox.setEditable(false);
        ModificarGeneroPeliculajComboBox.setEnabled(true);
        ModificarTituloPeliculajTextField.setText("");
        ModificarGeneroPeliculajComboBox.setVisible(false);
        ModificarTituloPeliculajTextField.setVisible(false);
        jLabel20.setVisible(false);
        jLabel21.setVisible(false);
        
    }//GEN-LAST:event_modificarPeliculaButtonActionPerformed

    private void EntregarPeliculaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntregarPeliculaButtonActionPerformed
        // TODO add your handling code here:
        PrincipaljPanel.setVisible(false);
        AgregarPeliculajPanel.setVisible(false);
        AgregarUsuariojPanel.setVisible(false);
        AlquilarPeliculajPanel.setVisible(false);
        ConsultarjPanel.setVisible(false);
        ModificarPeliculajPanel.setVisible(false);
        ModificarUsuariojPanel.setVisible(false);
        EntregarPeliculajPanel.setVisible(true);
        
        //inicializacion
        EntregaCedulajTextField.setEditable(true);
        EntregaNumeroPeliculajTextField.setEditable(true);
        AceptarEntregarPeliculaButton.setEnabled(true);
        EntregaCedulajTextField.setText("");
        EntregaNumeroPeliculajTextField.setText("");
    }//GEN-LAST:event_EntregarPeliculaButtonActionPerformed

    private void GuardarModificarPeliButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarModificarPeliButtonActionPerformed
        // TODO add your handling code here:
        
        if((ModificarTituloPeliculajTextField.getText().length()<30)&&!ModificarTituloPeliculajTextField.getText().equals("")){
            GuardarModificarPeliButton.setEnabled(false);
            ModificarGeneroPeliculajComboBox.setEnabled(false);
            ActualizarInformacionPelícula(ModificarTituloPeliculajTextField.getText(), this.índiceAuxilia, ModificarGeneroPeliculajComboBox.getSelectedItem().toString());
            OrdenarIndicePelicula();
            JOptionPane.showMessageDialog(null, "Película modificada exitosamente! Para modificar otra película debes darle click a la opción del menú");            
        }else if(!(ModificarTituloPeliculajTextField.getText().length()<30)){
             JOptionPane.showMessageDialog(null, "El título de la película no puede ser mayor a 30 caracteres.");               
        }else{
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos para poder guardar los cambios");
        }
        
    }//GEN-LAST:event_GuardarModificarPeliButtonActionPerformed

    private void ModificarNombrejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarNombrejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModificarNombrejTextFieldActionPerformed

    private void ModificarTelefonojTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarTelefonojTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModificarTelefonojTextFieldActionPerformed

    private void GuardarModificarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarModificarUsuarioButtonActionPerformed
        // TODO add your handling code here:
        if((ModificarNombrejTextField.getText().length()<30) && !ModificarNombrejTextField.getText().equals("") && !ModificarTelefonojTextField.getText().equals("")){
         JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente! Para modificar otro usuario debes darle click a la opción del menú");
            GuardarModificarUsuarioButton.setEnabled(false);
            ModificarNombrejTextField.setEditable(false);
            ModificarTelefonojTextField.setEditable(false);
            ActualizarInformaciónUsuario(ModificarNombrejTextField.getText(), ModificarTelefonojTextField.getText(), this.índiceAuxilia);
            OrdenarIndiceUsuario();
        }else if(!(ModificarNombrejTextField.getText().length()<30)){
            JOptionPane.showMessageDialog(null, "El nombre del uduario no puede ser mayor a 30 caracteres.");        
        }else{
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos para poder guardar los cambios");
        }
    }//GEN-LAST:event_GuardarModificarUsuarioButtonActionPerformed

    private void ModificarTituloPeliculajTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarTituloPeliculajTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModificarTituloPeliculajTextFieldActionPerformed

    private void BuscarModificarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarModificarUsuarioButtonActionPerformed
        // TODO add your handling code here:
        char V = 'V';
        char E = 'E';
        boolean cedulaNoApta = false;
        if (ModificarCedulajTextField.getText().charAt(0) != V && ModificarCedulajTextField.getText().charAt(0) != E) {
            cedulaNoApta = true;
        }
        System.out.println(cedulaNoApta);
        for (int i = 1; i < ModificarCedulajTextField.getText().length(); i++) {
            char caracterCedula = ModificarCedulajTextField.getText().charAt(i);
            if ((caracterCedula != '0') && (caracterCedula != '1') && caracterCedula != '2' && caracterCedula != '3' && caracterCedula != '4' && caracterCedula != '5' && caracterCedula != '6' && caracterCedula != '7' && caracterCedula != '8' && caracterCedula != '9') {
                cedulaNoApta = true;
            }
        }
        if(!cedulaNoApta && !ModificarCedulajTextField.getText().equals("")){
            int índice = BusquedaBinariaUsuario(Integer.parseInt(ModificarCedulajTextField.getText().substring(1, ModificarCedulajTextField.getText().length())));
            this.índiceAuxilia = índice;
            if (índice != -1) {
                JOptionPane.showMessageDialog(null, "Usuario encontrado exitosamente!");
                BuscarModificarUsuarioButton.setEnabled(false);
                ModificarCedulajTextField.setEditable(false);
                GuardarModificarUsuarioButton.setEnabled(true);
                ModificarNombrejTextField.setVisible(true);
                ModificarTelefonojTextField.setVisible(true);
                jLabel17.setVisible(true);
                jLabel18.setVisible(true);
                ModificarNombrejTextField.setText(usuarios[índice].getNombre());
                ModificarTelefonojTextField.setText(usuarios[índice].getTeléfono());
            }else{
                JOptionPane.showMessageDialog(null, "La cédula ingresada no fue encontrada");
            }
            
        }else if(cedulaNoApta){
                JOptionPane.showMessageDialog(null, "Ingresa correctamente la cédula");        
        }else{
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos para poder buscar un usuario");
        }
    }//GEN-LAST:event_BuscarModificarUsuarioButtonActionPerformed

    private void BuscarModificarPeliButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarModificarPeliButtonActionPerformed
        // TODO add your handling code here:
        if(!ModificarNumeroPeliculajTextField.getText().equals("") && !(ModificarNumeroPeliculajTextField.getText().length()!= 6)){
            int índice = BusquedaBinariaPelícula(Integer.parseInt(ModificarNumeroPeliculajTextField.getText()));
            this.índiceAuxilia = índice;
            if (índice !=-1) {
                
                
                JOptionPane.showMessageDialog(null, "Película encontrada exitosamente!");
                BuscarModificarPeliButton.setEnabled(false);
                ModificarNumeroPeliculajTextField.setEditable(false);
                GuardarModificarPeliButton.setEnabled(true);
                ModificarGeneroPeliculajComboBox.setVisible(true);
                ModificarTituloPeliculajTextField.setVisible(true);
                jLabel20.setVisible(true);
                jLabel21.setVisible(true);
                ModificarTituloPeliculajTextField.setText(películas[índice].getTítuloDePelícula());
                ModificarGeneroPeliculajComboBox.setSelectedItem(películas[índice].getGéneroDePelícula());
            }else{
                JOptionPane.showMessageDialog(null, "Película no encontrada");        
            }
            
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos para poder buscar una película");
        }
        
    }//GEN-LAST:event_BuscarModificarPeliButtonActionPerformed

    private void AceptarEntregarPeliculaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarEntregarPeliculaButtonActionPerformed
        // TODO add your handling code here:
        char V = 'V';
        char E = 'E';
        boolean cedulaNoApta = false;
        if (EntregaCedulajTextField.getText().charAt(0) != V && EntregaCedulajTextField.getText().charAt(0) != E) {
            cedulaNoApta = true;
        }
        System.out.println(cedulaNoApta);
        for (int i = 1; i < EntregaCedulajTextField.getText().length(); i++) {
            char caracterCedula = EntregaCedulajTextField.getText().charAt(i);
            if ((caracterCedula != '0') && (caracterCedula != '1') && caracterCedula != '2' && caracterCedula != '3' && caracterCedula != '4' && caracterCedula != '5' && caracterCedula != '6' && caracterCedula != '7' && caracterCedula != '8' && caracterCedula != '9') {
                cedulaNoApta = true;
            }
        }
        int índice = BusquedaBinariaPelícula(Integer.parseInt(EntregaNumeroPeliculajTextField.getText()));
        boolean cedulaValida = false;
        if ((índice!=-1)&&!cedulaNoApta) {
            if (películas[índice].getCiAlquilada().equals(EntregaCedulajTextField.getText())){
                cedulaValida = true;
            }
        }
        OrdenarIndicePelicula();
        if( cedulaValida && (índice!=-1) && !cedulaNoApta &&!EntregaCedulajTextField.getText().equals("") && !EntregaNumeroPeliculajTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Película entregada exitosamente! Para entregar otra película debes darle click a la opción del menú");
            EntregaCedulajTextField.setEditable(false);
            EntregaNumeroPeliculajTextField.setEditable(false);
            AceptarEntregarPeliculaButton.setEnabled(false);
            DevolverInformacionPelícula(índice);
            OrdenarIndicePelicula();
        }else if(índice==-1){
            JOptionPane.showMessageDialog(null, "El número de película no existe");
        }else if(!cedulaValida){
            JOptionPane.showMessageDialog(null, "La cédula no coincide con la película");
        }else{
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos para poder entregar una película");
        }
    }//GEN-LAST:event_AceptarEntregarPeliculaButtonActionPerformed

    private void GeneroPeliculajComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GeneroPeliculajComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GeneroPeliculajComboBoxActionPerformed

    private void SeleccionadorPeliculasjComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionadorPeliculasjComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SeleccionadorPeliculasjComboBox1ActionPerformed

    private void ModificarGeneroPeliculajComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarGeneroPeliculajComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModificarGeneroPeliculajComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    
    String input;
    
    //funcion para chequear si el input esta formado solo por numeros
    public boolean checkInput(){
        input = ConsultarjTextField.getText();
        try {
            double d = Double.parseDouble(input); 
            // or Integer.parseInt(text), etc.
            // OK, valid number.
            AceptarConsultarButton.setEnabled(false);
            return true;
        } catch (NumberFormatException nfe) {
            ConsultarPeliculaButton.setEnabled(true);
            AceptarConsultarButton.setEnabled(false);
            // Not a number.
        }
        return false;
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AceptarAlquilerButton;
    private javax.swing.JButton AceptarConsultarButton;
    private javax.swing.JButton AceptarEntregarPeliculaButton;
    private javax.swing.JButton AgregarPeliculaButton;
    private javax.swing.JPanel AgregarPeliculajPanel;
    private javax.swing.JButton AgregarUsuarioButton;
    private javax.swing.JPanel AgregarUsuariojPanel;
    private javax.swing.JButton AlquilarPeliculaButton;
    private javax.swing.JPanel AlquilarPeliculajPanel;
    private javax.swing.JButton BuscarModificarPeliButton;
    private javax.swing.JButton BuscarModificarUsuarioButton;
    private javax.swing.JTextField CedulaAlquilerjTextField;
    private javax.swing.JTextField CedulajTextField;
    private javax.swing.JButton ConfirmarPeliculaButton;
    private javax.swing.JButton ConfirmarUsuarioButton;
    private javax.swing.JButton ConsultarButton;
    private javax.swing.JButton ConsultarPeliculaButton;
    private javax.swing.JPanel ConsultarjPanel;
    private javax.swing.JTextField ConsultarjTextField;
    private javax.swing.JTextField EntregaCedulajTextField;
    private javax.swing.JTextField EntregaNumeroPeliculajTextField;
    private javax.swing.JButton EntregarPeliculaButton;
    private javax.swing.JPanel EntregarPeliculajPanel;
    private javax.swing.JTextField FechaDevjTextField;
    private javax.swing.JComboBox<String> GeneroPeliculajComboBox;
    private javax.swing.JButton GuardarModificarPeliButton;
    private javax.swing.JButton GuardarModificarUsuarioButton;
    private javax.swing.JTextArea InfoConsultadajTextField;
    private javax.swing.JPanel Menu;
    private javax.swing.JTextField ModificarCedulajTextField;
    private javax.swing.JComboBox<String> ModificarGeneroPeliculajComboBox;
    private javax.swing.JTextField ModificarNombrejTextField;
    private javax.swing.JTextField ModificarNumeroPeliculajTextField;
    private javax.swing.JPanel ModificarPeliculajPanel;
    private javax.swing.JTextField ModificarTelefonojTextField;
    private javax.swing.JTextField ModificarTituloPeliculajTextField;
    private javax.swing.JPanel ModificarUsuariojPanel;
    private javax.swing.JScrollPane MostrarInfoConsultadajScrollPane;
    private javax.swing.JTextField NombrejTextField;
    private javax.swing.JTextField NumeroPeliculajTextField;
    private javax.swing.JPanel PrincipaljPanel;
    private javax.swing.JButton SalirButton;
    private javax.swing.JComboBox<String> SeleccionadorPeliculasjComboBox1;
    private javax.swing.JTextField TelefonojTextField;
    private javax.swing.JTextField TituloPeliculajTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton modificarPeliculaButton;
    private javax.swing.JButton modificarUsuarioButton;
    // End of variables declaration//GEN-END:variables
}
