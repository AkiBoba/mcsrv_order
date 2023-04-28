1. Техническое задание - проект доставка еды "Голодный волк"
   В блоке микросервисы мы будем разрабатывать проект для доставки готовых блюд.

В этом уроке мы опишем технические требования проекта "Голодный волк".

Опишем желания заказчика:

- имеет каталог блюд

- может принимать заказы клиента на сайте. Либо через скачанное клиентами приложение

- предоставляет клиенту курьерскую доставку. Клиент может контролировать положение курьера

- предоставляет курьерам приложения, где они могут отчитываться о заказах, обновлять свое положение

- имеет админку в виде веб приложения, где можно оформлять поставки продуктов, а также видеть прибыль.

Приложение будет состоять из отдельных проектов. Каждый проект будет отдельное Spring boot приложение со своим репозиторием.

/admin/ - сервис админки
/dish/ - сервис блюд
/order/ - сервис заказов
/delivery/ - сервис доставки
/kitchen/ - сервис кухни
/payment/ - сервис платежей
/notification/ - сервис уведомлений
/domain/ - доменные модели

Этапы проектирования
В этом задании необходимо создать репозиторий job4j_order.

Синхронный обмен сообщений. Добавьте REST API метод /order/{1}.
Метод должен вернуть DTO детали заказа и полное содержание блюда OrderDTO (order, dish).

Сценарий. Пользователь делает заказ через Rest API в сервис order. Сервис order отправляет уведомление в очередь messengers о том, что поступил заказ.
Сервис notification должен прочитать это сообщение и записать данные себе в базу данных.


