# language: it
@cap2
Funzionalità: Introducendo il (S)CSS
  Per rendere l'esperienza di navigazione gradevole
  Come Lettore
  Vorrei che il sito esponesse una grafica omogenea

  Contesto: 
    Dato apro RBlog

  Scenario: intestazione e pié di pagina
    Dato è presente l'intestazione
    E è presente il pié di pagina
    Allora l'intestazione è posizionata all'inizio
    E il piè di pagina è posizionato alla fine

  Scenario: l'intestazione ed il piè di pagina
    hanno lo stesso schema di colori

    Dato è presente l'intestazione
    E l'intestazione ha un colore di sfondo
    E è presente il pié di pagina
    E il pié di pagina ha un colore di sfondo
    Allora intestazione e pié di pagina hanno lo stesso colore di sfondo

  Schema dello scenario: tramite l'intestazione è possibile navigare
     alle pagine dell'autore e dell'abstract

    Dato è presente l'intestazione
    E l'intestazione permette la navigazione
    Allora posso navigare verso "<nome della pagina>"

    Esempi: 
      | nome della pagina |
      | Abstract          |
      | Autore            |
      | RBlog             |

  Schema dello scenario: l'intestazione contiene il titolo della pagina
    Dato navigo verso "<nome della pagina>"
    E la pagina ha un titolo
    Allora il titolo della pagina è uguale a "<nome della pagina>"

    Esempi: 
      | nome della pagina |
      | Abstract          |
      | Autore            |
      | SBlog             |

  Scenario: l'intestazione espone dei semplici effetti cromatici
    Dato è presente l'intestazione
    E l'intestazione permette la navigazione
    E i collegamenti non hanno sfondo
    Quando il cursore si sposta sui collegamenti
    Allora lo sfondo del collegamento cambia

  Scenario: i collegamenti raffigurati tramite immagini devono
    anche avere una descrizione testuale

    Dato sono presenti dei collegamenti raffigurati tramite immagini
    Allora ogni collegamento ha una descrizione testuale
