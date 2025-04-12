# Makefile для управления Docker-окружением

.PHONY: start build down logs clean test

# Запуск всех сервисов в detached mode
start:
	docker-compose up -d

# Сборка всех сервисов без кэша
build:
	docker-compose build --no-cache

# Остановка и удаление всех контейнеров, сетей и volumes
down:
	docker-compose down -v

# Просмотр логов всех сервисов в реальном времени
logs:
	docker-compose logs -f

# Очистка: останавливает сервисы и удаляет все Docker-артефакты
clean: down
	docker system prune -a -f
	rm -rf api-gateway/target core-service/target

# Запуск тестов Core Service (требует запущенной БД)
test-core:
	cd core-service && mvn test -Dmaven.test.skip=false


# Список всех команд
help:
	@echo "Доступные команды:"
	@echo "  make start      - Запуск всех сервисов"
	@echo "  make build      - Пересборка всех сервисов"
	@echo "  make down       - Остановка и очистка"
	@echo "  make logs       - Просмотр логов"
	@echo "  make clean      - Полная очистка Docker"
	@echo "  make test-core  - Запуск тестов Core Service"