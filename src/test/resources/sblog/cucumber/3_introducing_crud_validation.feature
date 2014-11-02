# language: it
  
@cap3
@clear_and_logout
Funzionalità: Gestione dei post
  Come Autore
  Vorrei poter inserire, modificare e rimuovere dei post su RBlog
  Per poter documentare la mia tesi

  Contesto:
    Dato apro RBlog
    Dato mi autentico come "mattia@rblog.io"

  Scenario: Scrittura di un nuovo post
    Dato il post "Lorem Ipsum" non è leggibile su RBlog
    E apro la pagina per la creazione di un nuovo post
    Quando inserisco "Lorem Ipsum" come titolo
    E inserisco del testo riempitivo come contenuto
    E salvo il post
    Allora il post "Lorem Ipsum" è stato creato con successo
    E il post "Lorem Ipsum" è leggibile su RBlog

  Scenario: Cancellazione di un post
    Dato il post "Lorem Ipsum" esiste
    Quando cancello il post "Lorem Ipsum"
    Allora il post "Lorem Ipsum" è stato cancellato con successo
    E il post "Lorem Ipsum" non è leggibile su RBlog

  Scenario: Modifica di un post
    Dato il post "Lorem Ipsum2" esiste
    Quando modifico il post "Lorem Ipsum2"
    E inserisco "Lorem Ipsum" come titolo
    E inserisco "Questo post è stato modificato" come contenuto
    E salvo il post
    Allora il post "Lorem Ipsum2" non è leggibile su RBlog
    E il post "Lorem Ipsum" è leggibile su RBlog

  Scenario: Tentativo di creazione di un post duplicato
    Dato il post "Lorem Ipsum" esiste
    Dato apro la pagina per la creazione di un nuovo post
    Quando inserisco "Lorem Ipsum" come titolo
    E inserisco del testo riempitivo come contenuto
    E salvo il post
    Allora compare l'errore "Esiste già un post con questo titolo"
    E il post "Lorem Ipsum" è leggibile su RBlog

  Scenario: Tentativo di creazione di un post con titolo invalido
    Dato apro la pagina per la creazione di un nuovo post
    Quando inserisco "LI" come titolo
    E inserisco del testo riempitivo come contenuto
    E salvo il post
    Allora compare l'errore "Il titolo deve essere almeno di 5 caratteri"
    E il post "LI" non è leggibile su RBlog

  Scenario: Tentativo di creazione di un post con contenuto invalido
    Dato apro la pagina per la creazione di un nuovo post
    Quando inserisco "Lorem Ipsum" come titolo
    E inserisco "Body" come contenuto
    E salvo il post
    Allora compare l'errore "L'articolo deve essere almeno di 5 caratteri"
    E il post "Lorem Ipsum" non è leggibile su RBlog