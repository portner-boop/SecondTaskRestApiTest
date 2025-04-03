##### **Реализовано 2 тестовое задание тестирование REST API**

**Проект разбит на папки в каждой папке, классы, выполняющие свою отдельную функцию, кратко про них:**

(base - package) -> (BaseRequest,GetAllEntitiesHelper,GetEntityHelper,CreateEntityHelper,GetEntityHelper) - BaseRequest хранит методы запросов, которые повторяются и часто используются,
преимущественно в before/after, а также настройку RequestSpecification. GetAllEntitiesHelper,GetEntityHelper,CreateEntityHelper,GetEntityHelper хранят основную логику тестов.

(config - package) -> (URLConfig - class)  - берет конфигурацию из application.yml и подставляет в BASE_URL.

(models- pachage) -> хранит в себе ещё два пакета **request** и **response** в них содержаться классы, которые участвуют в сериализация и десериализация.

(tests-package) ->  хранит тесты, а также один класс родитель для удобства управления логикой и уменьшения boilerplate code.

**Реализованы 5 эндпоинтов, логика /api/delete/{id} вынесена в класс BaseRequests так как он используются в AfterClass**

**Также сделал параллельный запуск тестов через suite**

**Также присутствует allure отчет**