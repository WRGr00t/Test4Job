# Test4Job
<h3>spring boot/spring data/spring mvc бэкенд-приложение (электронный каталог книг)</h3><br>
localhost:8080/api/book/ - <b>GET</b> - получить все книги каталога<br>
localhost:8080/api/book/<i>{id}</i> - <b>GET</b> - получить книгу с id = {id}<br>
localhost:8080/api/book/ - <b>POST</b> - внести книгу в БД со строковыми параметрами title(обязательный), year, code, annotation, bookPic, bookPath, authorName(обязательный)<br>
localhost:8080/api/book/<i>{id}</i> - <b>PUT</b> - изменить книгу с id = {id} со строковыми параметрами title, year, code, annotation, bookPic, bookPath, authorName<br>
localhost:8080/api/book/<i>{id}</i> - <b>DELETE</b> - удалить книгу с id = {id}<br>
localhost:8080/api/book/searchbytitle - <b>GET</b> - получить постраничный список книг с title содержащим текстовый параметр query, дополнительно можно задать сдвиг относительно начала списка (целочисленный параметр offset) и ограничить количество книг на странице (целочисленный параметр limit). Сортировка по названию (title).<br>
localhost:8080/api/book/searchbyauthor - <b>GET</b> - получить постраничный список книг с автором, заданным параметром authorName, дополнительно можно задать сдвиг относительно начала списка (целочисленный параметр offset) и ограничить количество книг на странице (целочисленный параметр limit). Сортировка по году печати (year).<br>
