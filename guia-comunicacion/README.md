Datos de la guia de la comunicacion irekia en RDF
=================================================

[create an anchor](#anchors-in-markdown)

## Introducción

En este repositorio se encuentran los datos de la [guía de la comunicación abierta](http://gida.irekia.euskadi.eus/) transformados a RDF. La transformación se ha hecho mediante un plugin desarrollado para la plataforma [ALDAPA](https://github.com/mikel-egana-aranguren/ALDAPA/tree/feature-rml.io/plugins/src/main/java/es/eurohelp/lod/aldapa/impl/transformation/guiacomunicacion). Durante el proceso de transformación me he encontrado con muchos problemas que sin duda nos encontraremos con otros datasets.

## Origen de datos

Los datos se dividen en entidades y cargos, y ambos tienen versión en Euskera y Castellano. Los DCAT se obtuvieron inspeccionando el código fuente de cada página web, ya que no hay ningún enlace visible.

### Entidades 

* [guia-comunicacion-gobierno-vasco-datos-contacto-entidades-euskadi](http://opendata.euskadi.eus/catalogo/-/guia-comunicacion-gobierno-vasco-datos-contacto-entidades-euskadi/).
* [eusko-jaurlaritzaren-komunikazio-gida-euskadiko-erakundeen-kontaktu-datuak](http://opendata.euskadi.eus/katalogoa/-/eusko-jaurlaritzaren-komunikazio-gida-euskadiko-erakundeen-kontaktu-datuak/).

Las dos versiones son el mismo CSV, habiendo una columna para el nombre de la entidad en Castellano (`Entidad`) y otra para Euskara (`Erakundea`).

### Cargos

* [guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades](http://opendata.euskadi.eus/catalogo/-/guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades/).
* [eusko-jaurlaritzaren-komunikazio-gida-erakundeetako-kargu-eta-ordezkarien-kontaktu-datuak](http://opendata.euskadi.eus/katalogoa/-/eusko-jaurlaritzaren-komunikazio-gida-erakundeetako-kargu-eta-ordezkarien-kontaktu-datuak/).

Las dos versiones son el mismo CSV, habiendo una columna para el cargo en Castellano (`Cargo`) y otra para Euskara (`Lanpostua`).

## Problemas en los CSV

La librería que he usado para parsear los CSV es [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/), y he asumido que los CSV tienen codificación UTF-8, que no sé si es el caso para todos los CSV que se conviertan a RDF.

Los CSV tienen bastantes problemas:

* Muchas líneas ni siquiera tienen el mismo número de celdas que se define en la cabecera, según el método [isConsistent](https://commons.apache.org/proper/commons-csv/apidocs/org/apache/commons/csv/CSVRecord.html#isConsistent--), tanto en cargos como en entidades. 
* Cargos: Hay celdas de `Nombre` que tienen nombre y apellidos, y la celda `Apellidos` esta vacía (Por ejemplo `Azucena Martínez`).
* Cargos: Hay líneas que no tienen nombre, y entonces cuando accedemos a nombre aparece el cargo (¿Deberían ser detectadas como inconsistentes?).
* Cargos: Euskera incorrecto: "Gipuzkoako Erizaintza Kolegio Oficiala".
* Cargos: direcciones postales vacías, o con falta de población o código postal.
* Cargos: muchas direcciones web son inválidas.
* En las entidades los errores son similares.

## Problemas/decisiones generales Linked Data

El dataset RDF publicado en Open Data Euskadi usaba una versión antigua de la ontologia [VCARD](https://www.w3.org/TR/vcard-rdf/), yo me estoy basando en la última versión, que es muy diferente. 

La idea original es que cada dataset en cada idioma (cada CSV) va a convertirse en un grafo con su DCAT correspondiente dentro de la Triple Store. Sin embargo, en este caso los dos CSVs son idénticos, teniendo algunas columnas que se desglosan en columnas por idiomas (`Entidad`/`Erakundea`;`Cargo`/`Lanpostua`). Tomando el CSV de cargos como ejemplo, hay tres opciones:

1. Un único grafo con dos valores diferentes en `vcard:role`, uno para cada idioma (`@es`, `@eu`). 
	* Ventaja: un solo conversor Java. 
	* Desventajas: el cargo tiene que ser un literal en vez de un recurso (Ver URIs de referencia); el dataset EU no se convierte.

2. Dos grafos idénticos con solo diferencia en Lanpostua/Cargo. 
	* Ventajas: el cargo puede ser un recurso en vez de un literal (Ver URIs de referencia); dos conversores Java muy parecidos. 
	* Desventaja: muchos triples se duplican: en el caso de recursos da igual, en el caso de literales no (si se hace una consulta al default graph, los rdfs:comment duplicados saldrán dos veces).
3. El grafo ES contiene todo menos `Lanpostu`, y el grafo EU solo contiene `Lanpostu`. 
	* Ventajas: el cargo puede ser un recurso en vez de un literal (Ver URIs de referencia); menos triples. 
	* Desventaja: dos conversores Java, uno solo con las lo correspondiente a Lanpostu.


Relacionar cargos con entidades sería muy interesante pero ahora no se puede hacer, ya que los datos de cargos y de entidades no comparten nada en común, con lo que no se pueden crear URIs de entidades para enlazarlos (Ver URIs de referencia). Por ejemplo el cargo "Delegado de Álava, Kristau Eskola" no tiene una entidad enlazable, tan sólo "Kristau eskola". Esto realmente afecta a la calidad de consultas SPARQL que se pueden hacer. 

URIs de referencia a generar (el programa debería obtener esta URIs de una lista, en vez de generarlas):
* Cargos.
* Entidades.
* Direcciones y poblaciones.

## DCAT

Al DCAT original le he quitado la distribucion SPARQL y la TTL, y he añadido una distribución Linked Data y otra nquads:

```
<http://es.euskadi.eus/distribution/guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades>
  a dcat:Distribution, void:Dataset ;
  sd:namedGraph <http://es.euskadi.eus/graph/guia-comunicacion-cargos> ;
  void:sparqlEndpoint <http://es.euskadi.eus/sparql/guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades> .

<http://es.euskadi.eus/distribution/open_data/gc_cargos_datos_completos.nquads>
  a dcat:Distribution ;
  dc:format [
    a dc:IMT ;
    rdfs:label "RDF-nquads" ;
    rdf:value "application/n-quads"
  ] ;
  dcat:byteSize 0.0 ;
  dcat:accessURL "http://gida.irekia.euskadi.eus/open_data/gc_cargos_datos_completos.nquads"^^xsd:anyURI ;
  dc:title "Datos de contacto"@es .
  ```

## Mecánica de transformación

Yo he usado la librería [RDF4J](http://rdf4j.org/) para generar el RDF, pero también se podría usar [JENA](http://jena.apache.org/). Jorge me comentó que JENA tiene una función para importar ontologías y que las clases y propiedades de esas ontologías estén disponibles como enumeraciones Java, en vez de crearlas a mano, como hago yo. 




## Anchors in markdown

blblalalalla
lalalalal


alla