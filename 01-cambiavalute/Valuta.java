import java.util.Objects;

/* 
 * Valuta
 * Enum che rappresenta una valuta
 */
public enum Valuta {
    /* 
     * Valute disponibili associate al proprio simbolo
     */
    DOLLARO("Dollaro", '$'),
    EURO("Euro",'€'),
    FRANCO("Franco",'₣'),
    LIRA("Lira",'₺'),
    RUPIA("Rupia",'₹'),
    STERLINA("Sterlina",'£'),
    YEN("Yen",'¥');

    /* Nome della valuta */
    String nome;
    /* Simbolo della valuta */
    char simbolo;

    /**
     * @param nome nome della valuta
     * @param simbolo simbolo della valuta
     */
    Valuta(String nome, char simbolo){
        this.nome = Objects.requireNonNull(nome);
        this.simbolo = simbolo;
    }
}
