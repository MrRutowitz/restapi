1. Rozdziel model bazodanowy od modelu HTTP:
   - przygotuj modele request/response dla danych entity
   - nie korzystaj z konwersji do stringa przy użyciu objectMapper
2. Dodaj kilka customowych metod do JPA repository w ramach praktyki:
   - dodaj wyszukiwanie po zakresie salary <min, max>
   - dodaj wyszukiwanie po polu tekstowym z użyciem query LIKE (użyj native query)
3. Dodaj paginację do endpointa z pobieraniem listy
4. Dodać obsługę błędów + walidacje na API
5. Spróbuj wykorzystać adnotację @RequiredArgsConstructor zamiast tworzyć konstruktory z polami
6. Dodaj odczytywanie parametrów z application.properties (np. format czasu)
7. Dodaj aktuator springa dla monitoringu aplikacji
8. Dodaj integration unit-test dla aplikacji
9. Dodaj swaggera
10. Zdockeryzuj aplikację
