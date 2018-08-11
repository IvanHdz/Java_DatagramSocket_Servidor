
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.SwingUtilities;

/**
 *
 * @author Viruz
 */
public class VentanaServidor extends javax.swing.JFrame {

    private DatagramSocket socket; //Clase a utilizar DatagramSocket

    /**
     * Creates new form VentanaServidor
     */
    public VentanaServidor() {
        initComponents();

        try {
            socket = new DatagramSocket(5000); //Puerto para conexión del cliente
        } catch (SocketException excepcionSocket) {
            System.exit(1);
        }
    }

    public void esperarPaquetesCliente() {
        while (true) {
            try {
                byte datos[] = new byte[100];
                DatagramPacket recibirPaquete = new DatagramPacket(datos, datos.length);

                socket.receive(recibirPaquete);

                mostrarMensaje("\nCliente dice: " + "\n" + new String(recibirPaquete.getData(), 0, recibirPaquete.getLength()) + "\n");
            } catch (IOException excepcionES) {
                mostrarMensaje(excepcionES.toString() + "\n");
            }
        }
    }

    private void mostrarMensaje(final String mensajeAMostrar) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jTextAreaServidor.append(mensajeAMostrar);
                jTextAreaServidor.setCaretPosition(
                        jTextAreaServidor.getText().length());
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaServidor = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Viruz Blog Mensajeria (BETA 1.0)");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(300, 303));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);

        jTextAreaServidor.setColumns(20);
        jTextAreaServidor.setEditable(false);
        jTextAreaServidor.setRows(5);
        jTextAreaServidor.setBorder(null);
        jScrollPane1.setViewportView(jTextAreaServidor);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(298, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel1.setText("Cliente (Disponible)");
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(298, 80));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Último mensaje recibido a las");
        jPanel3.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Image");
        jLabel4.setPreferredSize(new java.awt.Dimension(34, 80));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Image");
        jLabel3.setPreferredSize(new java.awt.Dimension(30, 80));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel8, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        try {
            jTextAreaServidor.append("\nServidor dice: " + "\n"
                    + evt.getActionCommand() + "\n");

            String mensaje = evt.getActionCommand();
            byte datos[] = mensaje.getBytes();

            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 63160); //Puerto del cliente

            socket.send(enviarPaquete);

            jTextAreaServidor.setCaretPosition(
                    jTextAreaServidor.getText().length());
            jTextField1.setText("");
        } catch (IOException excepcionES) {
            mostrarMensaje(excepcionES.toString() + "\n");
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaServidor;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
