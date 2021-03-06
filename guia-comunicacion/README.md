Datos de la guia de la comunicacion irekia en RDF
=================================================


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

Los dos CSVs son idénticos, teniendo algunas columnas que se desglosan en columnas por idiomas (`Entidad`/`Erakundea`;`Cargo`/`Lanpostua`), de modo que en principio se creará una sola URI por cada recurso y se usarán datatypes de idioma para los literales. Sin embargo, en vez de usar literales sería más interesante relacionar cargos con entidades pero ahora no se puede hacer, ya que los datos de cargos y de entidades no comparten nada en común, con lo que no se pueden crear URIs de entidades para enlazarlos: [URIs de referencia](#uris-de-referencia). Por ejemplo el cargo "Delegado de Álava, Kristau Eskola" no tiene una entidad enlazable en entidades, tan sólo "Kristau eskola". Esto realmente afecta a la calidad de consultas SPARQL que se pueden hacer. 

[URIs de referencia](#uris-de-referencia)

## URIs de referencia

URIs de referencia a generar (el programa debería obtener esta URIs de una lista, en vez de generarlas):
* Cargos.
* Entidades.
* Direcciones y poblaciones.

## DCAT

Al DCAT original le he quitado la distribucion SPARQL y la TTL, y he añadido una distribución Linked Data y otra nquads:

```
<?xml version="1.0" encoding="utf-8" ?>
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
         xmlns:dcat="http://www.w3.org/ns/dcat#"
         xmlns:sd="http://www.w3.org/ns/sparql-service-description#"
         xmlns:void="http://rdfs.org/ns/void#"
         xmlns:dc="http://purl.org/dc/terms/"
         xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#">

  <dcat:Distribution rdf:about="http://data.euskadi.eus/distribution/guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades/lod">
    <rdf:type rdf:resource="http://rdfs.org/ns/void#Dataset"/>
    <sd:namedGraph rdf:resource="http://data.euskadi.eus/graph/guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades"/>
    <void:sparqlEndpoint rdf:resource="http://data.euskadi.eus/sparql"/>
  </dcat:Distribution>

  <dcat:Distribution rdf:about="http://data.euskadi.eus/distribution/guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades/nquads">
    <dc:format>
      <dc:IMT>
        <rdfs:label>RDF-nquads</rdfs:label>
        <rdf:value>application/n-quads</rdf:value>
      </dc:IMT>
    </dc:format>

    <dcat:byteSize rdf:datatype="http://www.w3.org/2001/XMLSchema#decimal">0.0</dcat:byteSize>
    <dcat:accessURL rdf:datatype="http://www.w3.org/2001/XMLSchema#anyURI">http://gida.irekia.euskadi.eus/open_data/gc_cargos_datos_completos.nquads</dcat:accessURL>
    <dc:title xml:lang="es">Datos de contacto</dc:title>
  </dcat:Distribution>
</rdf:RDF>
```

## Mecánica de transformación

Yo he usado la librería [RDF4J](http://rdf4j.org/) para generar el RDF, pero también se podría usar [JENA](http://jena.apache.org/). Jorge me comentó que JENA tiene una función para importar ontologías y que las clases y propiedades de esas ontologías estén disponibles como enumeraciones Java, en vez de crearlas a mano, como hago yo. 



