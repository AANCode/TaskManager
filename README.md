# TaskManager

REST API for taskadministrasjon bygget med Spring Boot og PostgreSQL.

## Innhold

- [Beskrivelse](#beskrivelse)
- [Krav](#krav)
- [Installasjon](#installasjon)
- [Kjøring](#kjøring)
- [API-endepunkter](#api-endepunkter)
- [Datamodell](#datamodell)
- [Docker](#docker)

## Beskrivelse

TaskManager er en RESTful API for oppgaveadministrasjon. Du kan opprette, hente, oppdatere og slette oppgaver. Hver oppgave har tittel, beskrivelse og status for fullføring.

Teknologi:
- Java 21
- Spring Boot 4.0.4
- Spring Data JPA
- PostgreSQL
- Maven

## Krav

- Java 21 eller høyere
- Maven 3.6.0 eller høyere
- PostgreSQL 12 eller høyere
- Docker (valgfritt)

## Installasjon

Konfigurer PostgreSQL:

```sql
CREATE DATABASE taskmanager;
CREATE USER taskmanager_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE taskmanager TO taskmanager_user;
```

Rediger `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=taskmanager_user
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Last ned avhengigheter:

```bash
mvn clean install
```

## Kjøring

Med Maven:

```bash
mvn spring-boot:run
```

Applikasjonen starter på `http://localhost:8080`

Med Docker Compose:

```bash
docker-compose up
```

## API-endepunkter

Base URL: `http://localhost:8080/tasks`

### Hent alle oppgaver

```http
GET /tasks
```

Respons: `200 OK`

### Hent oppgave etter ID

```http
GET /tasks/{id}
```

Respons: `200 OK` eller `404 Not Found`

### Opprett ny oppgave

```http
POST /tasks
Content-Type: application/json

{
  "title": "Min nye oppgave",
  "description": "Beskrivelse av oppgaven",
  "completed": false
}
```

Respons: `201 Created`

### Oppdater oppgave

```http
PUT /tasks/{id}
Content-Type: application/json

{
  "title": "Oppdatert tittel",
  "description": "Oppdatert beskrivelse",
  "completed": true
}
```

Respons: `200 OK` eller `404 Not Found`

### Slett oppgave

```http
DELETE /tasks/{id}
```

Respons: `200 OK` eller `404 Not Found`

## Datamodell

Task-objektet har følgende felt:

| Felt | Type | Krav | Beskrivelse |
|------|------|------|-------------|
| `id` | Long | Automatisk generert | Unik identifikator |
| `title` | String | 3-100 tegn | Tittel på oppgaven |
| `description` | String | 5-500 tegn | Detaljert beskrivelse |
| `completed` | Boolean | - | Status på oppgaven |

## Docker

Bygge Docker-image:

```bash
docker build -t taskmanager:latest .
```

Kjøre med Docker Compose:

```bash
docker-compose up -d
```

Stoppe:

```bash
docker-compose down
```

## Eksempler

Opprett oppgave:

```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Kjøpe melk",
    "description": "Kjøpe melk på supermarkedet",
    "completed": false
  }'
```

Hent alle oppgaver:

```bash
curl http://localhost:8080/tasks
```

Oppdater oppgave:

```bash
curl -X PUT http://localhost:8080/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Kjøpe melk",
    "description": "Kjøpe melk og yogurt",
    "completed": true
  }'
```

Slett oppgave:

```bash
curl -X DELETE http://localhost:8080/tasks/1
```

## Feilsøking

Tilkoblingsfeil til PostgreSQL:
- Sjekk at PostgreSQL kjører
- Verifiser brukernavn og passord i `application.properties`
- Sikre at databasen `taskmanager` finnes

Port allerede i bruk:
- Endre `server.port` i `application.properties`

Validering feiler:
- Sjekk at `title` er 3-100 tegn
- Sjekk at `description` er 5-500 tegn

## Lisens

Laget av Sheinks Code.

Versjon: 0.0.1-SNAPSHOT



