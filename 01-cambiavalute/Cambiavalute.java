import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* 
 * Cambiavalute
 * Classe concreta che offre un serivizio dotato di una cassa e dei tassi di cambio
 * che può cambiare a richiesta un importo (in una data valuta) in un importo differente.
 */
public class Cambiavalute{
    /* Struttura che memorizza i tassi di cambio */
    Map<Importo, Importo> tassi = new HashMap<>();
    /* Cassa che contiene gli importi del cambiavalute*/
    private Cassa cassa;
    
    //Constructor

    /**
     * Costruisce un Cambiavalute con una cassa e nessun tasso di cambio
     * @param importi lista degli importi della cassa
     */
    public Cambiavalute(List<Importo> importi){
        this.cassa = new Cassa(importi);
    }

    /**
     * Aggiunge o aggiorna il tasso di cambio di due importi aventi valuta differente
     * @param fi
     * @param si
     */
    public void AggiornaTasso(Importo fi, Importo si){
        
    }

    /**
     * Effettua il cambio di un importo in una valuta se è disponibile il tasso di cambio
     * e ha in cassa l'equivalente dell'importo della nuova valuta
     * @param i
     * @param v
     * @return
     */
    public String Cambio(Importo i, Valuta v){

        return "";
    }


    /**
     * Ritorna un iteratore sui tassi di cambio delle valute
     * @return iteratore sui tassi di cambio delle valute
     */
    public Iterator<Map.Entry<Importo, Importo>> tassi(){
        return tassi.entrySet().iterator();
    }

    /* 
     * Stato del cambiavalute
     */
    @Override
    public String toString() {
        return super.toString();
    }


    /* 
    * Cassa
    * Classe concreta che rappresenta un "contenitore" di importi, dal quale è possibile versare importi
    * e prelevare importi
    */
    public class Cassa{
        /* Struttura che contiene gli importi */
        private List<Importo> importi = new LinkedList<Importo>();

        //Constructor

        /* 
        * Costruisce una cassa con la lista di importi delle varie valute,
        * successivamente sarà modificabile esclusivamente utilizzando i metodi 
        * versa e preleva
        */
        public Cassa(List<Importo> importi){
            this.importi = importi;
        }

        //Methods

        /**
         * Versa l'importo in cassa sommandolo a quello già presente nella relativa valuta
         * @param i importo da versare
         */
        public void versa(Importo i){
            while(importi().hasNext()){

            }
        }

        /**
         * Iteratore sugli importi della cassa
         * @return iteratore sugli importi della cassa
         */
        public Iterator<Importo> importi(){
            return new Iterator<Importo>() {

                int i = 0;
    
                @Override
                public boolean hasNext() {
                    return i <= importi.size();
                }
    
                @Override
                public Importo next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    i++;
                    return importi.get(i);
                }
                
            };
        }
        
        /**
         * Funzione per prelevare l' importo i dalla cassa
         * @param i importo da prelevare
         */
        public void preleva(Importo i){
            
        }


    }
}
