# VBankinfo
О проекте. 
VBankinfo - проект сайта на котором будет размещена и сгрупирована информация с сайта Национального Банка Украины
(инорфмация которая есть в окрытом доступе) о банках Украины. Сайт будет предоставлять пользователям максимально полную и 
структуризированую информацию о конкретном банке. Проблема официального сайта НБУ в том что там информация о банках раскидана по разным 
разделам и для получения полной картины необходимо лазить по разным разделам и екселевским файлам и сводить ее во едино.

Проект VBankinfo это по сути другой мой проект BankDB_JDBC но переделаный под Maven использованием Spring Framework 
(Spring MVC, Spring JDBC), MySQL, Jsoup, JSP, JSTL. 

По состоянию на 09.03.2016 по проекту на данный момент реализовано:
1. Фронт енд сторона.
2. Модуль отвечающиий за парсинг с сайта НБУ основной информайии по банкам.
3. Модуль работы с базой данных - сохранение в базу данных информации по банкам, поиск по коду ЕДРПОУ или по имени банка в базе данных.
4. Модуль отвечающий за вывод найденной информации.
