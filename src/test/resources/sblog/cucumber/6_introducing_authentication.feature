# language: it
@cap6
Funzionalità: Autenticazione su RBlog
  Come Autore di RBlog
  Vorrei che alcune operazioni sensibili siano permesse previa autenticazione
  Per poter garantire l'autenticità dei contenuti

  Contesto:
    Dato apro RBlog

  Scenario: Possibilità di autenticarsi
    Dato l'utente non è autenticato
    Allora tramite l'intestazione posso autenticarmi

  @logout
  Scenario: Autenticazione su RBlog
    Dato l'utente non è autenticato
    Allora tramite l'intestazione posso autenticarmi
    Quando mi autentico come "mattia@rblog.io"
    Allora l'utente è autenticato
    E tramite l'intestazione non posso autenticarmi
    E tramite l'intestazione posso disconnettermi

  Scenario: Disconnessione da RBlog
    Dato l'utente non è autenticato
    Quando mi autentico come "mattia@rblog.io"
    Allora tramite l'intestazione posso disconnettermi
    Quando quando mi disconnetto
    E l'utente non è autenticato

  @logout
  Scenario: Possibilità di compiere operazioni sensibili avendo compiuto l'accesso
    Dato l'utente non è autenticato
    Quando mi autentico come "mattia@rblog.io"
    Allora posso navigare verso la pagina per la creazione di un nuovo post

  Scenario: Autenticazione fallita su RBlog
    Dato l'utente non è autenticato
    Allora tramite l'intestazione posso autenticarmi
    Quando mi autentico come "anonymous@rblog.io"
    Allora l'utente non è autenticato
    E compare l'errore di autenticazione "Credenziali invalide."