
import javax.swing.JFrame;

/**
 *
 * @author Viruz
 */
public class Main {

    public static void main(String args[]) {
        VentanaServidor servidor = new VentanaServidor();
        servidor.setVisible(true);
        servidor.setLocationRelativeTo(null);
        servidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        servidor.esperarPaquetesCliente();
    }
}
