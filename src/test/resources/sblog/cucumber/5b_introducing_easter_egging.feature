# language: it
@cap5
Funzionalità: Easter Egging
  Come Sviluppatore
  Vorrei che nel blog fosse presente un mio logo
  Per firmare il mio lavoro

  Contesto:
    Dato apro SBlog

  Scenario: EasterEgg
    Dato non è presente il logo nell'intestazione
    Quando clicco sull'area del pié di pagina
    Allora è presente il logo