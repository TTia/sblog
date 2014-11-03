# language: it
@cap3 @clear
Funzionalità: Gestione dei post
  Come Autore
  Vorrei poter inserire, modificare e rimuovere dei post su SBlog
  Per poter documentare la mia tesi

  Contesto: 
    Dato apro SBlog
    Dato mi autentico come "mattia@SBlog.io"

  Scenario: Scrittura di un nuovo post
    Dato il post "Lorem Ipsum" non è leggibile su SBlog
    E apro la pagina per la creazione di un nuovo post
    Quando inserisco "Lorem Ipsum" come titolo
    E inserisco del testo riempitivo come contenuto
    E salvo il post
    Allora il post "Lorem Ipsum" è stato creato con successo
    E il post "Lorem Ipsum" è leggibile su SBlog

  Scenario: Cancellazione di un post
    Dato il post "Lorem Ipsum" esiste
    Quando cancello il post "Lorem Ipsum"
    Allora il post "Lorem Ipsum" è stato cancellato con successo
    E il post "Lorem Ipsum" non è leggibile su SBlog

  Scenario: Modifica di un post
    Dato il post "Lorem Ipsum2" esiste
    Quando modifico il post "Lorem Ipsum2"
    E inserisco "Lorem Ipsum" come titolo
    E inserisco "Questo post è stato modificato" come contenuto
    E salvo il post
    Allora il post "Lorem Ipsum2" non è leggibile su SBlog
    E il post "Lorem Ipsum" è leggibile su SBlog

  Scenario: Tentativo di creazione di un post duplicato
    Dato il post "Lorem Ipsum" esiste
    Dato apro la pagina per la creazione di un nuovo post
    Quando inserisco "Lorem Ipsum" come titolo
    E inserisco del testo riempitivo come contenuto
    E salvo il post
    Allora compare l'errore "Il titolo è già presente."
    E il post "Lorem Ipsum" è leggibile su SBlog

  Scenario: Tentativo di creazione di un post con titolo invalido
    Dato apro la pagina per la creazione di un nuovo post
    Quando inserisco "LI" come titolo
    E inserisco del testo riempitivo come contenuto
    E salvo il post
    Allora compare l'errore "Il titolo deve essere compreso fra 5 e 100 caratteri."
    E il post "LI" non è leggibile su SBlog

  Scenario: Tentativo di creazione di un post con contenuto invalido
    Dato apro la pagina per la creazione di un nuovo post
    Quando inserisco "Lorem Ipsum" come titolo
    E inserisco "Body" come contenuto
    E salvo il post
    Allora compare l'errore "Il post deve essere almeno di 5 caratteri."
    E il post "Lorem Ipsum" non è leggibile su SBlog
