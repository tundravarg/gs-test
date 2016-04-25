GEOSPAN TEST
============

Sergei Tumanov



СБОРКА
------

Для сборки необходимы:
1. JDK-8;
2. Maven.

Сборка осуществляется в каталоге src командой:

    mvn install



УСТАНОВКА
---------

Установка осущствляет копированием файла `src/gs_test_ep/target/tuman.gs_test_ep-1.0.0-SNAPSHOT.jar` в каталог `${ECLIPSE}/dropins`, где ${ECLIPSE} - каталог, в который установлен Eclipse (Mars).

Программа использует для отрисовки графики JOGL-2.3.1.
Перед запуском необходимо скачать `http://jogamp.org/deployment/v2.3.1/archive/jogamp-all-platforms.7z` и установить библиотеки из каталога `jogamp-all-platforms/lib`. Для Debian (64-битной сборки), необходимо скопировать все файлы \*.so из каталога архива `jogamp-all-platforms/lib/linux-amd64` в каталог системы `/usr/lib` (**не** в /usr/local/lib) и выполнить команду `ldconfig`.

