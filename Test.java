import java.util.Iterator;

public class Test {

    public static void main(String[] args) {

        System.out.println("CARTELLONE\n");
        
        Cartellone cartellone = new Cartellone("Archipelago", 30);
        
        cartellone.aggiungiProiezione("My Frozen Chestnuts", new ProgrammazionePeriodica(3, 4));
        cartellone.aggiungiProiezione("They Call Me Trinity", new ProgrammazioneReplica(4, 3));
        cartellone.aggiungiProiezione("The Single Standard", new ProgrammazioneUnica(8));
        cartellone.aggiungiProiezione("Three Amigos", new ProgrammazioneReplica(10, 3));
        
        System.out.println(cartellone + "\n");

        Iterator<String> date = cartellone.date();
        
        while (date.hasNext()) {
            System.out.println(date.next());
        }

        System.out.println("\nPROIEZIONI\n");

        Iterator<Cartellone.Proiezione> it = cartellone.proiezioni();
        
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}
