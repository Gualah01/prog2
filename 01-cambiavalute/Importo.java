import java.math.BigDecimal;

/**
 * Importo
 * Classe concreta che rappresenta un importo caratterizzato da un valore e una valuta
 */
public class Importo {
    /* Valore dell' importo */
    BigDecimal valore;
    /* Valuta relativa al valore dell'importo */
    Valuta valuta;

    //Constructor

    /**
     * @param valore valore dell' importo
     * @param valuta valuta dell' importo
     */
    public Importo(BigDecimal valore, Valuta valuta){
        this.valore = valore;
        this.valuta = valuta;
    }

    //Methods
   

    /**
     * Produce l'importo zero nella valuta corrente
     * @return importo di valore zero di valuta
     */
    public Importo zero(){
        return new Importo(BigDecimal.ZERO, valuta);
    }

    
    /**
     * Effettua la somma tra l'importo this e i
     * @param i importo da sommare
     * @return somma degli importi this e i
     * @throws Exception se this.valuta e valuta sono diverse
     */
    public Importo somma(Importo i) throws Exception{
        if(this.valuta != i.valuta) throw new Exception("Valute differenti");
        return new Importo(valore.add(i.valore), this.valuta);
    }

    /**
     * Effettua la sottrazione tra l'importo this e i
     * @param i importo da sottrarre
     * @return sottrazione degli importi this e i
     * @throws Exception se this.valuta e valuta sono diverse
     */
    public Importo sottrazione(Importo i) throws Exception{
        if(this.valuta != i.valuta) throw new Exception("Valute differenti");
        return new Importo(valore.subtract(i.valore), this.valuta);
    }
}