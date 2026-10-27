import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Puente entre la interfaz y el motor tuProlog. El cálculo lo hace
 * siempre calculo.pl; esta clase solo arma la consulta y lee la respuesta.
 */
public class Connection {

    private final Prolog motor;

    public Connection() {
        motor = new Prolog();
        try (InputStream teoria = Connection.class.getResourceAsStream("/calculo.pl")) {
            if (teoria == null) {
                throw new IllegalStateException("No se encontró calculo.pl en el classpath");
            }
            motor.setTheory(new Theory(teoria));
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo cargar calculo.pl", e);
        }
    }

    /**
     * Ejecuta la operación en Prolog y devuelve el resultado como texto,
     * o null si la regla no tuvo solución (p. ej. división entre cero).
     */
    public String calcular(String operacion, String textoA, String textoB) {
        String consulta = operacion + "(" + textoA + ", " + textoB + ", R).";
        try {
            SolveInfo resultado = motor.solve(consulta);
            if (!resultado.isSuccess()) {
                return null;
            }
            return formatear((alice.tuprolog.Number) resultado.getVarValue("R"));
        } catch (MalformedGoalException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private String formatear(alice.tuprolog.Number r) {
        if (r.isInteger()) {
            return String.valueOf(r.longValue());
        }
        BigDecimal valor = BigDecimal.valueOf(r.doubleValue())
                .setScale(4, RoundingMode.HALF_UP)
                .stripTrailingZeros();
        return valor.toPlainString();
    }
}
