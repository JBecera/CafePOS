# CafePOS

A full stack Point of Sale (POS) and inventory management system built for coffee shops and cafes. Handles product and category management, checkout/sales processing, transaction history, and low-stock tracking.

Built as a portfolio project to demonstrate full stack development skills across web, backend, and (optionally) mobile.

## Project Structure

This repository is a monorepo containing all parts of the system:

```
POS/
  web/        React (JavaScript) frontend
  backend/    Spring Boot REST API
  mobile/     Kotlin Android app (planned/optional)
```

Each subfolder has its own README with setup-specific instructions.

## Tech Stack

- **Frontend:** React (JavaScript), Vite
- **Backend:** Spring Boot (Java)
- **Database:** Supabase (PostgreSQL)
- **Mobile (planned):** Kotlin, Jetpack Compose

## Features (v1 Scope)

- Manage products (name, price, stock quantity, category)
- Process a sale (add items to cart, calculate total, checkout)
- View transaction history
- Stock view (track low inventory)

## Database Schema

- **users** — staff/owner accounts (role: owner or staff)
- **categories** — product categories
- **products** — name, price, stock quantity, category reference
- **transactions** — one row per checkout (total, tax, status, timestamp, processed by)
- **transaction_items** — line items per transaction (product, quantity, price at time of sale)

## Getting Started

Setup instructions for each part of the system live in their respective folders:

- [`web/README.md`](./web/README.md) — frontend setup
- [`backend/README.md`](./backend/README.md) — backend/API setup
- [`mobile/README.md`](./mobile/README.md) — mobile app setup (if applicable)

## Live Demo

_Coming soon._

## Roadmap / Future Work

- Multi-staff accounts with role-based permissions
- Mobile app (Kotlin/Android)

## Author

Built by Jian Brenz Becera as a full stack development portfolio project.
