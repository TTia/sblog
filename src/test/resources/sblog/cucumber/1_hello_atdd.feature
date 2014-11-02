# language: it
@cap1
Funzionalità: Hello RBlog!
  Per leggere i post e visitare il blog
  Come Lettore
  Vorrei che RBlog permettesse la navigazione

  Scenario: Visita alla pagina iniziale
    Dato apro RBlog
    Allora posso visitare la pagina dell'autore
    E posso visitare la pagina dell'abstract

  Schema dello scenario: Visita alla pagina dell'autore e alla pagina dell'abstract
    Dato apro RBlog
    Quando navigo verso "<nome della pagina>"
    Allora la pagina è intitolata "<nome della pagina>"
    E posso tornare alla pagina iniziale

    Esempi: 
      | nome della pagina |
      | Autore            |
      | Abstract          |
