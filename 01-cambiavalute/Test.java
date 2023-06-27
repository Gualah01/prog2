import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        List<Importo> importi = new LinkedList<>();
        importi.add(new Importo(BigDecimal.valueOf(100), Valuta.DOLLARO));
        importi.add(new Importo(BigDecimal.valueOf(90.50), Valuta.EURO));
        importi.add(new Importo(BigDecimal.valueOf(200), Valuta.STERLINA));
        importi.add(new Importo(BigDecimal.valueOf(100), Valuta.YEN));
        importi.add(new Importo(BigDecimal.valueOf(80.50), Valuta.FRANCO));
        importi.add(new Importo(BigDecimal.valueOf(10000), Valuta.RUPIA));
        importi.add(new Importo(BigDecimal.valueOf(95000), Valuta.LIRA));

        Cambiavalute c = new Cambiavalute(importi);


        
    }
}