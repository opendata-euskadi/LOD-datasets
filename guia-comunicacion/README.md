Datos de la guia de la comunicacion irekia en RDF
=================================================

## Introducción

En este repositorio se encuentran los datos de la [guía de la comunicación abierta](http://gida.irekia.euskadi.eus/) transformados a RDF. La transformación se ha hecho mediante un plugin desarrollado para la plataforma [ALDAPA](https://github.com/mikel-egana-aranguren/ALDAPA/tree/feature-rml.io/plugins/src/main/java/es/eurohelp/lod/aldapa/impl/transformation/guiacomunicacion). Durante el proceso de transformación me he encontrado con muchos problemas que sin duda nos encontraremos con otros datasets.

## Origen de datos

Los datos se dividen en entidades y cargos, y ambos tienen versión en Euskera y Castellano. Los DCAT se obtuvieron inspeccionando el código fuente de cada página web, ya que no hay ningún enlace visible.

### Entidades 

* [guia-comunicacion-gobierno-vasco-datos-contacto-entidades-euskadi](http://opendata.euskadi.eus/catalogo/-/guia-comunicacion-gobierno-vasco-datos-contacto-entidades-euskadi/).
* [eusko-jaurlaritzaren-komunikazio-gida-euskadiko-erakundeen-kontaktu-datuak/](http://opendata.euskadi.eus/katalogoa/-/eusko-jaurlaritzaren-komunikazio-gida-euskadiko-erakundeen-kontaktu-datuak/).

Las dos versiones son el mismo CSV, habiendo una columna para el nombre de la entidad en Castellano (`Entidad`) y otra para Euskara (`Erakundea`).

### Cargos

* [guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades](http://opendata.euskadi.eus/catalogo/-/guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades/).
* [eusko-jaurlaritzaren-komunikazio-gida-erakundeetako-kargu-eta-ordezkarien-kontaktu-datuak](http://opendata.euskadi.eus/katalogoa/-/eusko-jaurlaritzaren-komunikazio-gida-erakundeetako-kargu-eta-ordezkarien-kontaktu-datuak/).

Las dos versiones son el mismo CSV, habiendo una columna para el cargo en Castellano (`Cargo`) y otra para Euskara (`Lanpostua`).

## Problemas en los CSV (Baja calidad de los datos)

La libreria que he usado para parsear los CSV es [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/).

Los CSV tienen bastantes problemas. El primero y más importante es que muchas líneas ni siquiera tienen el mismo número de celdas que se define en la cabecera, según el método [isConsistent](https://commons.apache.org/proper/commons-csv/apidocs/org/apache/commons/csv/CSVRecord.html#isConsistent--). 

Mirar codigo
Mirar LOG

## Problemas/decisiones generales Linked Data

El dataset RDF publicado en Open Data Euskadi usaba una versión antigua de la ontologia [VCARD](https://www.w3.org/TR/vcard-rdf/), yo me estoy basando en una la última versión, que difieren. 

La idea original es que cada dataset en cada idioma (cada CSV) va a convertirse en un grafo con su DCAT correspondiente dentro de la Triple Store. Sin embargo, en este caso los dos CSVs son idénticos, teniendo algunas columnas que se desglosan en columnas por idiomas (`Entidad`/`Erakundea`;`Cargo`/`Lanpostua`). Tomando el CSV de cargos como ejemplo, hay tres opciones:

* Un único grafo con dos valores diferentes en vcard:role, uno para cada idioma (@es, @eu). 
	* Ventaja: un solo conversor Java. 
	* Desventajas: el cargo tiene que ser un literal en vez de un recurso (Ver URIs de referencia); el dataset EU no se convierte.

* Dos grafos idénticos con solo diferencia en Lanpostua/Cargo. 
	* Ventajas: el cargo puede ser un recurso en vez de un literal (Ver URIs de referencia); dos conversores Java muy parecidos. 
	* Desventaja: muchos triples se duplican: en el caso de recursos da igual, en el caso de literales no (si se hace una consulta al default graph, los rdfs:comment duplicados saldrán dos veces).
* El grafo ES contiene todo menos `Lanpostu`, y el grafo EU solo contiene `Lanpostu`. 
	* Ventajas: el cargo puede ser un recurso en vez de un literal (Ver URIs de referencia); menos triples. 
	* Desventaja: dos conversores Java, uno solo con las lo correspondiente a Lanpostu.

Grafos entidades

Relacionar cargos con entidades sería muy interesante pero ahora no se puede hacer
Los datos de cargos y de entidades no comparten nada en comun, con lo que no se pueden crear URIs de entidades para enlazarlos (Ver URIs de referencia):

cargos: "Delegado de Álava, Kristau Eskola"
entidad: "Kristau eskola"

URIs de referencia a generar:
* Cargos

## DCAT

He quitado la distribucion SPARQL y la TTL original, he añadido la de Linked Data y la de nquads:

prociones de DCAT

## Mecánica de transformación

Yo he usado la librería [RDF4J](http://rdf4j.org/) para generar el RDF, pero también se podría usar [JENA](http://jena.apache.org/). Jorge me comentó que JENA tiene una función para importar ontologías y que las clases y propiedades de esas ontologías estén disponibles como enumeraciones Java, en vez de crearlas a mano, como hago yo. 

