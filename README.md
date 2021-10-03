# Test4Job
spring boot/spring data/spring mvc бэкенд-приложение (электронный каталог книг)<br>
localhost:8080/api/book/ - GET - получить все книги каталога
localhost:8080/api/book/{id} - GET - получить книгу с id = {id}
localhost:8080/api/book/ - POST - внести книгу в БД со строковыми параметрами title(обязательный), year, code, annotation, bookPic, bookPath, authorName(обязательный)
localhost:8080/api/book/{id} - PUT - изменить книгу с id = {id} со строковыми параметрами title, year, code, annotation, bookPic, bookPath, authorName
localhost:8080/api/book/{id} - DELETE - удалить книгу с id = {id}
localhost:8080/api/book/searchbytitle - GET - получить постраничный список книг с title содержащим текстовый параметр query, дополнительно можно задать сдвиг относительно начала списка (целочисленный параметр offset) и ограничить количество книг на странице (целочисленный параметр limit). Сортировка по названию (title).
localhost:8080/api/book/searchbyauthor - GET - получить постраничный список книг с автором, заданным параметром authorName, дополнительно можно задать сдвиг относительно начала списка (целочисленный параметр offset) и ограничить количество книг на странице (целочисленный параметр limit). Сортировка по году печати (year).
