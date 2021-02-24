# ProgettoIDS
Progetto Centro Commerciale in Centro (C3)


##Introduzione

Il progetto si rivolge ai centri abitati medi della provincia italiana dove le attività commerciali del centro soffrono la concorrenza di grossi centri commerciali situati nelle periferie. Si vuole risolvere tramite questo progetto la scomodità relativa al trasporto della merce una volta acquistata in centro e la difficoltà della ricerca dei punti vendita in relazione a specifiche categorie merceologiche. Il progetto si pone dunque come obiettivo quello di fornire un supporto tecnologico per rendere l’esperienza degli acquisti in centro più facile e interessante. Si identificano in particolare i seguenti chiari attori:

⦁	Commerciante: vende la merce fisicamente presso il suo punto vendita e definisce il punto di ritiro della stessa comunicando contestualmente al cliente la merce acquistata e il codice per il ritiro. Si registra sulla piattaforma, ma prima di poter accedere, la sua iscrizione deve venire accettata dall'amministratore. Può lanciare vendite promozionali sui prodotti. In sostanza può usare la piattaforma per promuovere la propria attività al fine di poter vendere la sua merce più facilmente.

⦁	Cliente: ritira la merce dai punti di prelievo (se questa non viene spedita direttamente presso il suo domicilio). Può ricercare i punti vendita dei commercianti. Può ricercare e magari filtrare le promozioni. Viene costantemente informato sullo stato degli ordini che sono legati appunto al cliente. Anche il cliente si registra sulla piattaforma e può subito accedere, senza dover aspettare che la sua iscrizione venga accettata.

⦁	Corriere: Si registra sulla piattaforma, ma prima di poter accedere, la sua iscrizione deve venire accettata dall'amministratore. Si rende disponibile a effettuare il trasporto della merce: - Prende in carico un ordine libero. - Preleva la merce dai punti vendita. - Rilascia la merce presso i punti di prelievo (che nel caso dei residenti in centro può essere la loro abitazione).

⦁	Amministratore: Esiste un solo amministratore della piattaforma, questo si occupa di accettare le iscrizioni di commercianti e corrieri, ma anche di aggiungere e rimuovere i punti vendita relativi ai commercianti.


Funzionalità implementate

La piattaforma consiste nelle seguenti funzionalità principali:
⦁	Inizializzazione ordine: Il commerciante seleziona il cliente e il punto di ritiro relativo all'ordine, tra una lista di oggetti già presenti nel database. se il punto di ritiro non viene selezionato, questo corrisponde al domicilio del cliente. L'ordine viene salvato e caricato nel database.
⦁	Lancio vendita promozionale: Il commerciante può definire e lanciare vendite promozionali mirate, con descrizione data inizio e data fine. La promozione viene salvata e caricata nel database. Questa promozione è legata al commerciante e può dunque essere filtrata successivamente attraverso la categoria merceologica di questo attore. La promozione è considerata scaduta e non più valida quando la data di fine scade.
⦁	Presa in carico ordine: Il corriere accetta e prende in carico un ordine libero a scelta, selezionabile tra una lista di ordini salvati nel database. Implica prelievo e consegna ordine dello stesso corriere. L'ordine che viene preso in carico viene vincolato al corriere. Lo stato dell'ordine viene modificato e viene definita una data prevista per il prelievo della merce. Gli stati dell'ordine sono contenuti in un enumeratore, chiamato appunto statiOrdine (IN_ACCETTAZIONE, IN_RITIRO, IN_TRANSITO, PRONTO_PER_IL_RITIRO, CONSEGNATO). Lo stato iniziale è 'IN_ACCETTAZIONE', dopo questa funzione, diventa 'IN_RITIRO'.
⦁	Prelievo ordine: Il corriere prende possesso della merce acquistata dal cliente, lo stato dell'ordine viene modificato in 'IN_TRANSITO' e viene stabilita una data prevista per la consegna. Corriere pronto alla consegna.
⦁	Consegna ordine: Il corriere avvisa il commerciante e il cliente: la merce acquistata da quest'ultimo è arrivata al punto di ritiro o al domicilio del cliente. Viene modificato lo stato dell'ordine a seconda di queste due eventualita: se l'ordine viene consegnato presso un punto di ritiro lo stato diventerà 'PRONTO_PER_IL_RITIRO', altrimenti 'CONSEGNATO'. Nel secondo caso il processo intero dell'ordine termina.
⦁	Iscrizione con conferma: Iscrizione alla piattaforma che richiede l'accettazione da parte dell'amministratore. Viene effettuata da un utente che poi diverrà da corriere o commerciante.
⦁	Iscrizione senza conferma (libera): Iscrizione dedicata ai clienti, non necessita di nessuna accettazione da parte dell'amministratore.
⦁	Ricerca punti vendita: Il cliente può ricercare punti vendita relativi al commerciante. Tramite un metodo che appunto estrapola tutti i punti vendita da ogni commerciante presente nel database.
⦁	Ritiro merce: Il cliente ritira la merce al punto di ritiro prestabilito, utilizzando il codice di ritiro fornito. L'intero processo di fornitura e di ritiro è stato completato con successo.
⦁	Ricerca / filtra promozioni: Il cliente può cercare le promozioni con la possibilità di filtrare i risultati in base alla categoria merceologica del relativo commerciante. La categoria merceologica corrisponde ad un enumeratore che può assumere i valori:      -ALIMENTARI, -ELETTRONICA, -ABBIGLIAMENTO.
⦁	Accettazione iscrizioni: L'amministratore può accettare le iscrizioni di commercianti e corrieri cambiando una variabile relativa alle loro istanze, la variabile statoIscrizione. Questa è false quando l'utente non è accettato ed è true quando invece viene abilitato all'accesso.
⦁	Aggiunta punto di ritiro:  L'amministratore può aggiungere un nuovo punto di ritiro relativo a un commerciante.
⦁	Rimozione punto di ritiro: L'amministratore può rimuovere un punto di ritiro esistente, relativo a un commerciante.
⦁	Ogni metodo viene inizializzato dalle classi Controller di ogni attore o degli elementi come punti vendita, ordini e promozioni. Queste classi reindirizzano il lavoro alle classi Service, che appunto eseguono le operazioni e gestiscono il database.
⦁	L'aggiunta di nuovi oggetti avviene tramite degli oggetti intermedi di tipo simile all'oggetto da aggiungere, chiamati oggetti 'dto' o 'NuovoOggetto'. Questi sono oggetti che si ricevono dal frontend e poi vengono convertiti i oggetti concreti.
⦁	Per garantire la sicurezza nell'autenticazione alla piattaforma, è stato implementato un package apposito che contiene il meccanismo dei JWT o Java Web Token: ad ogni richiesta di autenticazione relativa a diversi utenti viene generato e firmato il token dal server. Questo token verrà utilizzato e incapsulato nell' header delle richieste di autenticazione successive da parte degli stessi utenti.


Tecnologie utilizzate

⦁	Lato backend: spring boot con relative librerie, Java Web Token, JUnit, mock.
⦁	Lato frontend: angular.
Per quanto riguarda la gestione del database è stato utilizzato MySql, e il database utilizzato è un db remoto fornito da DigitalOcean. Per testare le chiamate REST è stato utilizzato Postman.


Conclusione

Questo progetto è in una fase ancora matura di vita, può venire ancora esteso e possono venire aggiunte nuove funzionalità. Si dovrebbe effettuare un refactoring sia del codice backend che di quello frontend, si potrebbero aggiungere e migliorare alcuni test e si potrebbe creare una versione mobile.


Link utili:

Codice frontend: https://github.com/VLVTeam/FrontEndIDS
VPP: https://github.com/VLVTeam/UML

