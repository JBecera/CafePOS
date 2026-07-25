# CafePOS Backend

Spring Boot REST API for CafePOS that handles products, categories, transactions, and stock management, backed by a Supabase (PostgreSQL) database.

## Tech Stack

- **Framework:** Spring Boot 4.1.0 (Java 25)
- **Build tool:** Maven
- **ORM:** Spring Data JPA / Hibernate
- **Database:** PostgreSQL (via Supabase)
- **Dependencies:** Spring Web, Spring Data JPA, Validation, PostgreSQL Driver

## Prerequisites

- Java 25 (JDK)
- Maven (or use the included `mvnw` / `mvnw.cmd` wrapper for no local Maven install required)
- A Supabase project with a PostgreSQL database

## Setup

1. Clone the repository and navigate into the backend folder:
   ```
   cd backend
   ```

2. Create a `.env` file in this folder (same level as `pom.xml`) based on `.env.example`:
   ```dotenv
   DB_URL=jdbc:postgresql://aws-0-<region>.pooler.supabase.com:6543/postgres
   DB_USERNAME=postgres.<your-project-ref>
   DB_PASSWORD=your_supabase_db_password
   ```
   Get these values from your Supabase project: **Settings → Database → Connection string → Transaction pooler**. Use the pooler connection (not the direct `db.<ref>.supabase.co` host) — the direct connection requires IPv6, which fails to resolve on many networks.

   `.env` is gitignored and should never be committed. `.env.example` documents the required keys with placeholder values.

3. Run the application:
   ```
   mvn spring-boot:run
   ```
   or, using the wrapper (no local Maven required):
   ```
   ./mvnw spring-boot:run       # macOS/Linux
   .\mvnw.cmd spring-boot:run   # Windows
   ```

4. The app starts on `http://localhost:8080`. A successful start logs `Started BackendApplication` with no connection errors.

## How Environment Variables Are Loaded

This project uses Spring Boot's built-in config import mechanism (no external dotenv library) to load `.env`:

```properties
spring.config.import=optional:file:.env[.properties]
```

This tells Spring Boot to parse `.env` using `.properties` syntax, since the `KEY=VALUE` format is identical. Values are then referenced in `application.properties` via `${DB_URL}`, `${DB_USERNAME}`, `${DB_PASSWORD}`.

## Database Schema

Managed via JPA entities with `spring.jpa.hibernate.ddl-auto=update` (Hibernate auto-creates/updates tables from entity classes). Core tables:

- **users** — staff/owner accounts
- **categories** — product categories
- **products** — name, price, stock quantity, category reference
- **transactions** — one row per checkout
- **transaction_items** — line items per transaction (product, quantity, price at time of sale)

## Deployment

Deployed on Render. Environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`) are set directly in Render's dashboard under the service's **Environment** tab — no `.env` file is used in production.

## Project Structure

```
backend/
  src/main/java/com/cafepos/backend/   Application code (entities, controllers, repositories, services)
  src/main/resources/                  application.properties
  src/test/                            Tests
  .env                                 Local environment variables (gitignored)
  .env.example                         Template for required environment variables
  pom.xml                              Maven dependencies and build config
```
