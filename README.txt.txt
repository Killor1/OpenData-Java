/////////////////////////////////
/    ÁNALISIS DE LOS DATOS	    /
/////////////////////////////////

Nos encontramos ante unos ficheros xml sobre las bibliotecas de Barcelona entre 2013 y 2015. Lo que podemos observar es que los datos
proporcionados son casi idénticos en su estructura, cambiando únicamente contenidos específicos significativos como la cantidad de visitas a 
las bibliotecas, los préstamos concedidos, los accesos a internet, etc.

Los datos que hemos encontrado presentaban una complejidad ínfima, pues era una estructura, como hemos dicho anteriormente, muy sencilla y clara. Tal vez podríamos concluir que la mayor parte de los datos proporcionados llegaban a ser redundantes y que su presentación podría haber sido realizada en forma de un único fichero pero añadiéndole aquellos nodos que son únicos por las distintas categorías de los datos, pues estos son los únicos que difieren entre los archivos.

Entre todos los ficheros adjuntos, hemos encontrado que podemos hacer pocas operaciones para cruzar datos, pero los muestreos se realizan exhaustivamente
consultando sobre todos los archivos.


/////////////////////////////////
/      TRABAJO EN GRUPO   	    /
/////////////////////////////////

En el paquete principal encontraremos 4 clases diferentes. Por una parte tenemos nuestro Gestor que será el contenedor principal de la mayoría de métodos
para procesar los distintos ficheros utilizados y sus correspondientes datos.

Tenemos también un método Main que se encarga de juntar todas las piezas del puzzle y ejecutarlo todo en un sólo bloque. Por otra parte hemos decidido implementar los menús en una clase a parte así como también hacerlo con las Estadísticas Gráficas, pues consideramos que era una sección de código bastante diferente del análisis de datos que realizamos en el Gestor principal.

Finalmente, nuestro código principal realiza las siguientes tareas:

-Muestreo total de los datos ordenados por año de manera ascendente.
-Muestreo de datos por un año determinado vía selección del usuario
-Muestreo de los datos de un fichero determinado por la selección del usuario.
-Consulta de datos por un distrito determinado.
-3 Estadísticas distintas.
-Las mismas estadísticas mostradas en forma de gráfico de barras.
-Adición de un registro.
-Modificación de un registro.
-Borrado de un registro.



Realizado por: Manuel Lorenzo, Daniel Ruz.

