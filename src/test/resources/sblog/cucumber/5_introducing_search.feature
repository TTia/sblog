# language: it
@cap5 @clear
Funzionalità: Ricerca fra i post
  Come Lettore
  Vorrei poter ricercare i post su RBlog
  Per poter navigare fra i contenuti più velocemente

  Contesto: 
    Dato apro RBlog
    Dato mi autentico come "mattia@rblog.io"

  @ignore
  Scenario: Autocompletamento della ricerca
    Dato nell'intestazione è presente la barra di ricerca
    Dato il post "Lorem Ipsum" esiste
    Quando inserisco il testo "Lorem" da ricercare
    Allora viene proposto il post "Lorem Ipsum"
    Quando inserisco il testo "lor" da ricercare
    Allora viene proposto il post "Lorem Ipsum"
    Quando inserisco il testo "xyz" da ricercare
    Allora non è proposto alcun post
    Quando inserisco il testo "L" da ricercare
    Allora non è proposto alcun post

  @ignore
  Scenario: Ricerca di un post esistente
    Dato nell'intestazione è presente la barra di ricerca
    Dato il post "Lorem Ipsum" esiste
    Quando ricerco "Lorem"
    Allora il post "Lorem Ipsum" è leggibile
    Quando ricerco "lo"
    Allora il post "Lorem Ipsum" è leggibile

  @ignore
  Scenario: Ricerca di un post non esistente
    Dato nell'intestazione è presente la barra di ricerca
    Dato il post "Lorem Ipsum" esiste
    Quando ricerco "XXX"
    Allora il post "Lorem Ipsum" non è leggibile
