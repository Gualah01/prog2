# Cambiavalute

## Descrizione

Scopo della prova è progettare e implementare una gerarchia di oggetti utili a
rappresentare il comportamento di un *cambiavalute*.

Si richiede di decidere quali interfacce e classi (concrete o astratte)
implementare. Per ciascuna di esse, è necessario fornire una **descrizione**
attraverso commenti nel codice (preferibilmente in formato Javadoc); questa
documentazione dovrà includere le scelte relative alla **rappresentazione**
dello stato (con particolare riferimento all'*invariante di rappresentazione* e
alla *funzione di astrazione*) e ai **metodi** (con particolare riferimento a
*pre/post-condizioni*, *eccezioni* ed eventuali *effetti collaterali*). Si
sottolinea che *il risultato della prova sarà valutato sia in base a questa
documentazione che al codice sorgente*.

### Dettagli pratici

La soluzione deve essere realizzata in una serie di file `.java` che dovranno
essere salvati nella *stessa cartella* in cui si trova il testo d'esame. È
importante utilizzare solo il *default package*, evitando di creare
sottocartelle o di introdurre dichiarazioni `package` nei file sorgenti. **Se
queste indicazioni non vengono seguite correttamente, il materiale non sarà
raccolto dal sistema di consegna e il lavoro non potrà essere valutato**.

Si prega di prestare attenzione agli *errori di compilazione*: l'intero
contenuto dei file che il compilatore rifiuta di compilare *non verrà
esaminato*. Nel caso in cui si verifichino errori di compilazione che non si
riescono a correggere, è possibile considerare la possibilità di commentare le
porzioni di codice che li causano tenendo tuttavia presente che il codice così
commentato non sarà valutato (ma verrà almeno esaminato il resto del codice del
file).

## Le entità 

### Valute e importi

Una **valuta** è caratterizzata da un *nome* (non vuoto) e da un *simbolo*
(carattere), per semplicità considereremo solo le seguenti valute:

    Dollaro ($)
    Euro (€)
    Franco (₣)
    Lira (₺)
    Rupia (₹)
    Sterlina (£)
    Yen (¥)

Le valute sono definite nell'ordine in cui sono elencate qui; tale *ordine* sarà
rilevante quando si tratterà di enumerare importi di valute differenti.

Un **importo** è caratterizzato da un *valore* (espresso in unità e centesimi) e
da una valuta; sono ad esempio importi €-3, $123.32, ₹1000000. Due importi della
stessa valuta possono essere *sommati*, *sottratti* o *confrontati* (per sapere
chi è il maggiore, o minore, o se sono uguali) l'uno con l'altro; può essere
comodo poter produrre (data una valuta) l'importo *zero* in tale valuta, così
come determinare se un importo è pari a *zero*.

Si presti particolare attenzione alla rappresentazione del valore di un importo,
i tipi in virgola mobile (*double* e *float*) *non sono adatti* a causa della
loro incapacità a rappresentare in modo esatto le frazioni decimali. Ad esempio
`0.10 + 0.20` in Java è uguale a `0.30000000000000004` il che è "sostanzialmente
corretto" dal punto di vista dei numeri reali, ma non è quello che ci si aspetta
da degli importi! Non sarà ritenuto accettabile risolvere questo problema
effettuando dei troncamenti (neppure nella conversione a stringa).

### Cassa (multi-valuta)

Una **cassa** è un "contenitore" di importi, in essa è possibile *versare*
importi (in qualunque valuta) e *prelevare* importi (purché la cassa contenga un
importo sufficiente nella valuta richiesta). La cassa deve consentire di
*iterare* sui propri importi diversi da zero nell'ordine in cui sono state
definite le valute; tale capacità è particolarmente utile nel fornire una
rappresentazione testuale di una cassa che deve contenere solo gli importi
diversi da zero in ordine di valuta; ad esempio:

    Cassa:
    $55.30
    €87.79
    ₣89.50
    ₹11000.00
    £200.00
    ¥24.95

che non elenca la Lira turca dal momento che non è presente in cassa alcun
importo (diverso da zero) in tale valuta.

### Tassi di cambio

Un **tasso** di cambio è specificato da due importi (con valute diverse) da
intendersi "equivalenti" nel senso che è possibile convertire qualunque multiplo
del primo importo nello stesso multiplo del secondo.

Ad esempio, il cambio 

    $2 = €2.40 
    
significa che 2 Dollari sono equivalenti a 2.40 Euro.

Usando le proporzioni imparate alla scuola elementare, un importo in una valuta
è quindi in grado di determinare, dato un tasso di cambio tra la sua valuta e
un'altra valuta, il suo *equivalente* nell'altra valuta (se diversa dalla
prima).

Nel caso precedente, il tasso comporterà ad esempio che 3 Dollari siano
equivalenti 3.60 Euro, o 1 Dollaro a 1.20 Euro.


### Cambiavalute

Un **cambiavalute** è un servizio dotato di una *cassa* che, presa conoscenza di
una serie di tassi di cambio, può *cambiare* a richiesta un importo (in una data
valuta) in una valuta differente.

In maggior dettaglio:

* nel momento in cui inizia a operare, il cambiavalute riceve una serie di
  importi (di varie valute) che deposita in cassa; successivamente la cassa non
  può essere più modificata direttamente (ma solo tramite le operazioni di
  cambio);

* quando sta operando può:

  * ricevere degli *aggiornamenti* sui tassi di cambio che memorizza; se riceve
    un tasso di cambio tra due valute di cui ne era già memorizzato uno, il
    nuovo tasso rimpiazza il precedente;

  * ricevere delle richieste di cambio di un dato importo in una nuova valuta;
    se ha memorizzato il tasso di cambio relativo e ha in cassa l'equivalente
    dell'importo nella nuova valuta procede a: (1) versare in cassa l'importo
    nella valuta originaria e (2) prelevare l'importo equivalente nella nuova
    valuta; viceversa segnala opportunamente gli errori relativi alla mancanza
    di conoscenza del tasso, o di disponibilità dei fondi.

In aggiunta, un cambiavalute deve consentire di *iterare* sui propri tassi di
cambio nell'ordine in cui sono stati aggiornati (ossia inseriti e, nel caso,
successivamente modificati); tale capacità è particolarmente utile nel fornire
una rappresentazione testuale di un cambiavalute.

## Cosa dovete implementare

Dovete implementare una gerarchia di classi atta a rappresentare il
cambiavalute, la cassa, i tassi, gli importi e le valute.

Prestate particolare attenzione a *mutabilità* e *immutabilità*, così come (se
necessaria) all'implementazione dei metodi `equals`, `hashCode` e `compareTo`
(dall'interfaccia `Comparable`) e degli *iteratori* delle classi che
realizzerete; osservate che, in alcuni casi, *record* ed *enum* possono essere
molto comodi in questo lavoro.

### La classe di test

La classe di test deve istanziare un cambiavalute dopo aver letto una sequenza
di importi (uno per linea) dal flusso di ingresso standard; quindi, proseguendo
nella lettura del flusso di ingresso, deve interpretare i seguenti comandi:

* `A` seguito da due importi, che comporta l'aggiornamento del tasso di cambio
  definito da tali importi;
* `C` seguito da un importo e una valuta, che comporta il cambio del primo
  importo nella seconda valuta e l'emissione del risultato nel flusso d'uscita;
* `P` che comporta l'emissione nel flusso d'uscita dello stato del cambiavalute
  (dato dall'elenco dei tassi e dal contenuto della cassa).

l'esecuzione termina al termine del flusso d'ingresso.

Se l'esecuzione del comando comporta un errore (ad esempio perché le due valute
nel comando `A` o `C` sono identiche, oppure perché il cambio richiesto dal
comando `C` non è possibile per mancanza di fondi nella cassa, o perché non è
noto il tasso) il programma deve emettere nel flusso d'uscita un opportuno
messaggio d'errore.

Ad esempio, eseguendo la classe di test e avendo nel flusso d'ingresso:

    $100
    €90.50
    £200
    ¥100
    ₣80.50
    ₹10000
    ₺95000
    A $1 = €1.07
    C $3 = €
    C €10 = ¥
    P
    A €0.10 = ¥15.01
    C €0.50 = ¥
    A ₣0.50 = ₺11.53
    A ₺200 = $9.54
    A ₹100 = ₣1.10
    C ₹1000 = ₣
    C €1 = €
    C ₣10 = ₺
    C €100 = ¥
    C ₺1000 = $
    P
    A ₣1.50 = ₺34.80
    P
    A ₣1.50 = ₣2
    C ₣10 = ₺
    A €1 = ¥149.46
    P

il programma emette

    €3.21
    ERRORE: Tasso non disponibile
    [
    Tassi:
    $1.00 = €1.07
    Cassa:
    $103.00
    €87.29
    ₣80.50
    ₺95000.00
    ₹10000.00
    £200.00
    ¥100.00
    ]
    ¥75.05
    ₣11.00
    ERRORE: Impossibile cambiare tra valute identiche
    ₺230.60
    ERRORE: Fondi non sufficienti
    $47.70
    [
    Tassi:
    $1.00 = €1.07
    €0.10 = ¥15.01
    ₣0.50 = ₺11.53
    ₺200.00 = $9.54
    ₹100.00 = ₣1.10
    Cassa:
    $55.30
    €87.79
    ₣79.50
    ₺95769.40
    ₹11000.00
    £200.00
    ¥24.95
    ]
    [
    Tassi:
    $1.00 = €1.07
    €0.10 = ¥15.01
    ₺200.00 = $9.54
    ₹100.00 = ₣1.10
    ₣1.50 = ₺34.80
    Cassa:
    $55.30
    €87.79
    ₣79.50
    ₺95769.40
    ₹11000.00
    £200.00
    ¥24.95
    ]
    ERRORE: Impossibile definire un tasso di cambio tra valute identiche
    ₺208.80
    [
    Tassi:
    $1.00 = €1.07
    ₺200.00 = $9.54
    ₹100.00 = ₣1.10
    ₣1.50 = ₺34.80
    €1.00 = ¥149.46
    Cassa:
    $55.30
    €87.79
    ₣89.50
    ₺95560.60
    ₹11000.00
    £200.00
    ¥24.95
    ]

nel flusso d'uscita.
