# Origen dataset

* URL dataset Open Data euskadi: http://opendata.euskadi.eus/catalogo/-/calidad-aire-en-euskadi-2017
* Archivo DCAT original (Para saber que datos estamos manejando): `r01DCATDataset.rdf`

# Linked Data

* Archivo DCAT con URI de dataset modificada para cumplir el esquema de URIs acordado: `DCAT.rdf`
* URI dataset: http://opendata.euskadi.eus/datos/-/calidad-aire-en-euskadi-2017/
* Archivo DCAT con prototipo de grafo para Linked Data: `DCAT-LinkedData.ttl`
* Grafo datos en Triple Store: http://es.euskadi.eus/graph/calidad-aire-en-euskadi-2017
* Página Web simulando ficha de Open Data Euskadi (rama gh-pages): https://opendata-euskadi.github.io/LOD-datasets/calidad-aire-en-euskadi-2017 
* dataset en rdf: `rdf/`
* Consultas contra dataset: `sparql/`
* SPARQL y JSON para pipeline OntoRefine: `ontorefine/`

# URIs ejemplo 

Estas URIs están en los datos pero no cumplen la arquitectura de URIs consensuada:

* Observación de calidad del aire en el sensor de Av Gasteiz el 2017-01-26: `http://es.euskadi.eus/id/medio-ambiente/calidad-del-aire/observation/AV-GASTEIZ-2017-01-26`. `medio-ambiente` es un sector de la NTI, `calidad-del-aire` un dominio que hemos creado, y `observation` una clase del vocabulario [Data Cube](https://www.w3.org/TR/vocab-data-cube/).
* Estación de medición (no tiene dominio): `http://es.euskadi.eus/id/medio-ambiente/estacion/C040`.
* Propiedad interna para unir observacion con medicion concreta (sin idioma): `http://euskadi.eus/def/medio-ambiente/calidad-del-aire/medicion`. 
* Clase interna de todas las mediciones del monoxido del carbono: `http://euskadi.eus/def/medio-ambiente/medicion/CO`.
* Monoxido de carbono 2017-01-26: `http://es.euskadi.eus/id/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-26`.
* Propiedades de ontologías externas: `http://www.w3.org/2003/01/geo/wgs84_pos#location`, `http://purl.org/dc/terms/date`, `http://purl.org/linked-data/sdmx/2009/attribute#unitMeasure`, `http://purl.org/linked-data/sdmx/2009/measure#obsValue`,rdf:type, rdfs:comment
* Clases de ontologías externas: `http://purl.org/linked-data/cube#Observation`, `http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3`.
* Datatypes: `http://www.w3.org/2001/XMLSchema#long`, `http://www.w3.org/2001/XMLSchema#date`, `http://www.w3.org/2001/XMLSchema#double`.
