﻿<h1 align="center">Gioco Dell'Oca</h1>
<p align="center">
  <i>Progetto di Ingegneria del Software, autori Calogero Falcone e Francesco Catania</i>
  <br/><br/>
  <img width="50" alt="Thumbnail" src="https://github.com/gerof7/GiocoDellOca/blob/develop/src/images/Icon.png"/>
  <br/><br/>
  <b><a href="#indiceContenuti">Indice Contenuti</a></b> | <b><a href="#autori">About us</a></b>
  <br/><br/>
  <a href="https://github.com/gerof7/GiocoDellOca/blob/develop/CHANGELOG.md"><img src="https://img.shields.io/badge/version-0.1-blue" alt="Current Version"/></a>
  <a target="_blank" href="https://github.com/gerof7/GiocoDellOca"><img src="https://img.shields.io/github/last-commit/gerof7/GiocoDellOca?logo=github&color=609966&logoColor=fff" alt="Last commit"/></a>
</p>
<div align="center">
  <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/PartitaClassicaSP.jpg" alt="Thumbnail" width="100%"/>
</div>
<br/><br/>

<details id="indiceContenuti">
  <summary><h2>Indice Contenuti</h2></summary>
  
* [Descrizione](#descrizione)
* [Funzionalità principali](#funzionalità-principali)
* [Architettura](#architettura)
* [Casi d’uso implementati](#casi-duso-implementati)
* [Requisiti](#requisiti)
* [Avvio](#avvio)
* [Testing](#testing)
* [Autori](#autori)

</details>

<details id="descrizione">
  <summary>
    <h3 >Descrizione</h3>
  </summary>
    <div align="center">
      <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/MenuIniziale.jpg" alt="Thumbnail" width="100%"/>
    </div>
  
Il progetto implementa il **Gioco dell’Oca** in versione digitale, evoluto attraverso cinque iterazioni. L’applicazione consente di giocare in modalità **singleplayer** e **multiplayer**, con possibilità di **personalizzazione**, **salvataggio impostazioni** e **gestione avanzata tramite pannello amministratore**.

[Indice Contenuti](#indiceContenuti)

</details>

<details id="funzionalità-principali">
  <summary>
    <h3 >Funzionalità principali</h3>
  </summary>
    <div align="center">
      <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/MenuIniziale.jpg" alt="Thumbnail" width="100%"/>
    </div>
  
### ✅ Modalità di gioco
- **Singleplayer**:
  - Configurazione partita (regole, scenario, personalizzazioni).
  - Gioco contro il computer con gestione turni e caselle speciali.
- **Multiplayer**:
  - Creazione partita e lobby.
  - Configurazione personalizzazioni per host e guest.
  - Gestione turni tra più giocatori.
 
  <details>
    <summary>
      <h4 >Vedi di più</h4>
    </summary>
      <div align="center">
        <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/SPSelezionaTipoRegole.jpg" alt="Thumbnail" width="100%"/>
      </div>
      
    All'inizio di ogni tipo di partita ti verrà chiesto di scegliere le regole: puoi optare per delle regole predefinite da set oppure scegliere da regole singole un mix personalizzato.

    <div align="center">
        <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/DropdownNonSelezionata.jpg" alt="Thumbnail" width="80%"/>
        <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/DropdownSelezionataCorrettamente.jpg" alt="Thumbnail" width="80%"/>
    </div>
    
    ` Nota bene: se scegli Regole Singole, assicurati di selezionare correttamente il dato (Swing ha un problema di selezione da dropdown): la prima immagine mostra una valorizzazione nulla, mentre quella di successiva una corretta `
     <div align="center">
        <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/PartitaClassicaSP.jpg" alt="Thumbnail" width="100%"/>
    </div>
    Se tutto è stato configurato correttamente (e sono stati impostati correttamente Pedine e Scenario) la schermata di gioco che apparirà sarà simile a questa.

    - ` Nota bene: quando si selezionano valori da tabelle, usare sempre il doppio click sul record dalla tabella di sinistra (Record selezionabili) e assicurarsi che il valore selezionato compaia sulla tabella di destra (Record impostati). `
    - ` Nota bene: Se una Pedina o un Dado impostato (quindi situato sulla tabella di destra) non va bene, usare il doppio click per riportarlo alla tabella di sinistra (Selezionabili) `
    - ` Nota bene: Se Pedina o Dado non sono stati impostati per il Giocatore, il pulsante Avvia Partita non si avvierà! `
    
    [Indice Contenuti](#indiceContenuti)
  
  </details>

### ✅ Personalizzazione
- Scelta di **dadi** e **pedine**.
- Configurazione **scenari** e **regole**.
- Salvataggio impostazioni utente per partite future.
  <details>
      <summary>
        <h4 >Vedi di più</h4>
      </summary>
        <div align="center">
          <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/Impostazioni.jpg" alt="Thumbnail" width="100%"/>
        </div>
      Scegli le impostazioni per i singoli Giocatori e le Personalizzazioni (Pedina e Dado) associati. Le modifiche salvate appariranno nelle successive partire SinglePlayer che MultiPlayer
    
  [Indice Contenuti](#indiceContenuti)
  
  </details>

### ✅ Pannello Admin
- Gestione **set di regole predefinite**.
- Gestione **scenari di gioco**.
- Gestione **elementi personalizzabili** (dadi e pedine).
- Autenticazione admin (username: `admin`, password: `admin`).

  <details>
      <summary>
        <h4 >Vedi di più</h4>
      </summary>
        <div align="center">
          <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/PannelloAdmin.jpg" alt="Thumbnail" width="100%"/>
        </div>
      Per modifiche ancora più impegnative invece, si ricorre al pannello di Admin. Si verrà indirizzati a questa schermata.
    
        - ` Nota bene: All'autenticazione -> nome: admin, password: admin `
  
  
  <div align="center">
          <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/AdminAggiuntaNuovoScenario.jpg" alt="Thumbnail" width="100%"/>
        </div>
      Nell'Esempio sopra riportato, è in atto l'aggiunta di un nuovo Scenario oltre ai due di default (anche loro modificabili, adesso che si hanno i permessi di admin)
    <div align="center">
          <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/PostAggiuntaScenarioAdmin.jpg" alt="Thumbnail" width="100%"/>
        </div>
    Il risultato di questa operazione sarà visibile in fase di selezione dello Scenario alla configurazione della nuova Partita (SinglePlayer o MultiPlayer)
  <div align="center">
          <img src="https://github.com/gerof7/GiocoDellOca/blob/develop/CopertinaReadme/AdminGestionePersonalizzazioni.jpg" alt="Thumbnail" width="100%"/>
        </div>
    
  - ` Nota bene: Se si vuole invece cambiare una immagine di una pedina, assicurarsi che la risorsa sia presente in /src/Images/ del progetto e incollare il percorso relativo come mostrato `
  </details>

[Indice Contenuti](#indiceContenuti)

</details>


<details id="architettura">
  <summary>
    <h3 >Architettura</h3>
  </summary>
  
- **Linguaggio:** Java.
- **GUI:** Java Swing.
- **Persistenza:** Dati in memoria (nessun database).
- **Pattern utilizzati:**  
  - **Singleton** e **Strategy** per la gestione degli effetti delle caselle speciali.
- **Principali entità:**
  - `Giocatore`, `Partita`, `Impostazioni`, `Scenario`, `Regola`, `Personalizzazione`, `Tabellone`, `Casella`.


[Indice Contenuti](#indiceContenuti)

</details>


<details id="casi-duso-implementati">
  <summary>
    <h3 >Casi d'uso implementati</h3>
  </summary>
  
- **UC1:** Configurazione e avvio partita singleplayer.
- **UC2:** Partita singleplayer.
- **UC3:** Creazione partita multiplayer.
- **UC4:** Unione a lobby multiplayer.
- **UC5:** Partita multiplayer.
- **UC6:** Gestione impostazioni utente.
- **UC7:** Gestione set di regole predefinite (admin).
- **UC8:** Gestione scenari di gioco (admin).
- **UC9:** Gestione elementi di gioco personalizzabili (admin).

[Indice Contenuti](#indiceContenuti)

</details>


<details id="requisiti">
  <summary>
    <h3 >Requisiti</h3>
  </summary>

  - Java JDK 17+
  - Librerie:  Java Swing (inclusa nel JDK)
  
[Indice Contenuti](#indiceContenuti)

</details>

<details id="avvio">
  <summary>
    <h3 >Avvio</h3>
  </summary>
    
  1. Clonare il repository.
  2. Verificare che la versione di Java sia 17+:
   ```bash
   java -version
   ```
  3. Eseguire jar dopo essersi spostato sulla cartella del progetto:
   ```bash
   java -jar GiocoDellOca.jar
   ```
  
[Indice Contenuti](#indiceContenuti)

</details>

<details id="testing">
  <summary>
    <h3 >Testing</h3>
  </summary>
  
   - Test unitari su:
  - Configurazione partita.
  - Gestione turni e caselle speciali.
  - Salvataggio impostazioni.
  - Autenticazione admin.
  - CRUD su regole, scenari e personalizzazioni.
  
[Indice Contenuti](#indiceContenuti)

</details>

<details id="autori">
  <summary>
    <h3 >Autori</h3>
  </summary>
  
   - Calogero Falcone
   - Francesco Catania
  
[Indice Contenuti](#indiceContenuti)

</details>
