import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * Interfaz gráfica de la calculadora. Solo valida y muestra texto;
 * el cálculo lo resuelve Prolog a través de Connection.
 */
public class Window extends JFrame {

    private static final Pattern NUMERO = Pattern.compile("-?\\d+(\\.\\d+)?");

    private final JTextField campoA = new JTextField(8);
    private final JTextField campoB = new JTextField(8);
    private final JLabel resultado = new JLabel(" ");
    private final Connection conexion = new Connection();

    public Window() {
        super("Calculadora (Java + Prolog)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 8, 8));
        panelCampos.add(new JLabel("Número A:"));
        panelCampos.add(campoA);
        panelCampos.add(new JLabel("Número B:"));
        panelCampos.add(campoB);

        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 8, 0));
        panelBotones.add(crearBoton("+", "suma"));
        panelBotones.add(crearBoton("−", "resta"));
        panelBotones.add(crearBoton("×", "multiplicacion"));
        panelBotones.add(crearBoton("÷", "division"));

        resultado.setHorizontalAlignment(SwingConstants.CENTER);
        resultado.setFont(resultado.getFont().deriveFont(Font.BOLD, 16f));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panelPrincipal.add(panelCampos);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(resultado);

        setContentPane(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }

    private JButton crearBoton(String etiqueta, String operacion) {
        JButton boton = new JButton(etiqueta);
        boton.addActionListener(e -> calcular(operacion));
        return boton;
    }

    private void calcular(String operacion) {
        String textoA = campoA.getText().trim();
        String textoB = campoB.getText().trim();

        if (textoA.isEmpty() || textoB.isEmpty()) {
            resultado.setText("Escribí los dos números");
            return;
        }
        if (!NUMERO.matcher(textoA).matches() || !NUMERO.matcher(textoB).matches()) {
            resultado.setText("Los números deben ser enteros o decimales, ej: 8, 2.5, -3");
            return;
        }

        String valor = conexion.calcular(operacion, textoA, textoB);
        if (valor == null) {
            resultado.setText(operacion.equals("division")
                    ? "No se puede dividir entre cero."
                    : "Sin solución.");
        } else {
            resultado.setText(textoA + " " + simbolo(operacion) + " " + textoB + " = " + valor);
        }
    }

    private String simbolo(String operacion) {
        switch (operacion) {
            case "suma": return "+";
            case "resta": return "−";
            case "multiplicacion": return "×";
            case "division": return "÷";
            default: return operacion;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Window().setVisible(true));
    }
}
