# Gestionale Negozio - Spring Boot

API REST per la gestione di un negozio di prodotti di prima necessità, sviluppata come progetto per approfondire lo sviluppo backend con Spring Boot. Copre l'intero ciclo di sviluppo: modellazione del dominio, persistenza, logica di business, API REST, validazione, gestione degli errori e testing automatico.

## Tecnologie

- **Java 21**
- **Spring Boot 4** (Web, Data JPA, Validation)
- **PostgreSQL**
- **Docker & Docker Compose**
- **Maven**
- **JUnit 5 & Mockito** (unit test e test di integrazione)
- **Springdoc OpenAPI / Swagger UI** (documentazione API interattiva)

## Funzionalità

- **Prodotti**: CRUD completo, ricerca per nome, filtro per categoria, prodotti sotto una soglia di scorta
- **Categorie**: CRUD, con controllo di unicità sul nome
- **Clienti**: CRUD, con controllo di unicità sull'email (opzionale)
- **Vendite**: registrazione di uno scontrino con più righe prodotto, con:
  - verifica e scalo automatico dello stock
  - calcolo automatico del totale
  - "fotografia" del prezzo del prodotto al momento della vendita (indipendente da futuri cambi di prezzo)
  - operazione interamente transazionale (rollback automatico in caso di errore, es. stock insufficiente)
- **Storico vendite**: ricerca per cliente e per intervallo di date

## Architettura

Il progetto segue un'architettura a livelli standard:

```
Controller  →  Service  →  Repository  →  Database
   (REST API)   (logica business)  (Spring Data JPA)   (PostgreSQL)
```

- **entity**: le classi JPA che rappresentano le tabelle del database, con le relative relazioni (es. una vendita può perdere il riferimento al cliente se questo viene eliminato, ma resta comunque nello storico)
- **repository**: interfacce Spring Data JPA, con query derivate dal nome del metodo
- **service**: logica di business, incluso il controllo di stock e il calcolo del totale in `VenditaService`
- **controller**: endpoint REST, con conversione tra entità e DTO per evitare di esporre direttamente il modello dati
- **dto**: oggetti di richiesta/risposta, disaccoppiati dalle entità
- **exception**: eccezioni personalizzate e gestore centralizzato degli errori (`@RestControllerAdvice`), con risposte HTTP coerenti (404, 409, 400) e messaggi di validazione dettagliati per campo

## Come avviare il progetto

### Prerequisiti
- JDK 21
- Docker Desktop

### Passaggi

1. Clona il repository
   ```bash
   git clone https://github.com/fesc424/gestionale-negozio-spring-boot.git
   cd gestionale-negozio-spring-boot
   ```

2. Avvia il database PostgreSQL
   ```bash
   docker compose up -d
   ```

3. Avvia l'applicazione
   ```bash
   ./mvnw spring-boot:run
   ```

4. L'applicazione è disponibile su `http://localhost:8080`

## Documentazione e test delle API

Una volta avviata l'applicazione, la documentazione interattiva delle API è disponibile su:

```
http://localhost:8080/swagger-ui.html
```

Da qui è possibile consultare tutti gli endpoint disponibili e provarli direttamente dal browser (creare categorie e prodotti, registrare vendite, ecc.), senza bisogno di strumenti esterni come Postman.

## Endpoint principali

| Metodo | Endpoint | Descrizione |
|---|---|---|
| GET | `/api/categorie` | Elenco categorie |
| POST | `/api/categorie` | Crea una categoria |
| GET | `/api/prodotti` | Elenco prodotti |
| GET | `/api/prodotti/cerca?nome=...` | Ricerca prodotti per nome |
| GET | `/api/prodotti/sotto-scorta?soglia=...` | Prodotti sotto una soglia di stock |
| POST | `/api/prodotti` | Crea un prodotto |
| PUT | `/api/prodotti/{id}` | Aggiorna un prodotto |
| GET | `/api/clienti` | Elenco clienti |
| POST | `/api/clienti` | Crea un cliente |
| GET | `/api/vendite` | Elenco vendite |
| GET | `/api/vendite/cliente/{id}` | Storico vendite di un cliente |
| POST | `/api/vendite` | Registra una nuova vendita (con più righe prodotto) |

L'elenco completo e interattivo è sempre disponibile su Swagger UI.

## Testing

Il progetto include sia unit test (con mock, senza dipendere dal database) sia test di integrazione (con database reale e simulazione di richieste HTTP tramite MockMvc).

```bash
# Esegue solo gli unit test
./mvnw test

# Esegue anche i test di integrazione (richiede Docker attivo)
./mvnw test "-Dtest=*Test,*IT"
```

## Note di progettazione

Alcune scelte di design volute, utili per capire il comportamento dell'applicazione:

- Eliminare una **categoria** elimina a cascata i prodotti collegati.
- Eliminare un **cliente** non elimina le sue vendite: il riferimento al cliente nella vendita viene semplicemente azzerato, mantenendo lo storico.
- Il **prezzo unitario** di ogni riga vendita è salvato indipendentemente dal prezzo corrente del prodotto, per mantenere corretti gli scontrini anche dopo futuri cambi di prezzo.

## Possibili sviluppi futuri

- Spring Security con ruoli (es. ADMIN / CASSIERE)
- Migrazioni dello schema versionate con Flyway
- Frontend dedicato (es. React) che consumi le API
- Deploy su un servizio cloud

## Stato del progetto

✅ Completo — modello dati, API REST, validazione e gestione errori, testing automatico
