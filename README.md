# ListWibuKu - Soap Service

ListWibuKu is a SOAP service that manage user subscription.

## Daftar Anggota

1. Akbar Maulana Ridho (13521093)
2. Eugene Yap Jin Quan (13521074)

## Requirement

1. Docker installed

## Installation Guide

1. Clone repo
2. Inside the repository, run `docker compose up`

## How to send request

Add this header before doing SOAP request.

`Authorization: your_api_key`

## Service information

- Mysql was run on port 3307
- Soap service was run on port 3001
- Mailhog SMTP server was run on port 1025
- Mailhog HTTP server was run on port 8025

## Bonus Mailer

User will be notified via email when their subscription is started or renewed through local SMTP server.

## Pembagian Tugas

| Tugas             | NIM      |
|-------------------|----------|
| User subscription | 13521074 |
| Logger            | 13521093 |
| Api Key           | 13521093 | 