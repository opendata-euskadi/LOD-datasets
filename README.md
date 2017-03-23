# Introducción
Prototipos datasets Linked Open Data RDF a partir de algunos datasets Open Data Euskadi. Cada dataset tiene un `README.md` con información propia.

# Datasets de origen

* [retribuciones-altos-cargos-y-personal-eventual-gobierno-vasco-y-organismos-autonomos-y-entes-publicos](http://opendata.euskadi.eus/catalogo/-/retribuciones-altos-cargos-y-personal-eventual-gobierno-vasco-y-organismos-autonomos-y-entes-publicos/). 
* [relaciones-de-puestos-de-trabajo-de-los-departamentos-y-organismos-autonomos-de-la-administracion-de-la-comunidad-autonoma](http://opendata.euskadi.eus/catalogo/-/relaciones-de-puestos-de-trabajo-de-los-departamentos-y-organismos-autonomos-de-la-administracion-de-la-comunidad-autonoma/).
* [registro-de-contratos-del-sector-publico-de-euskadi](http://opendata.euskadi.eus/catalogo/-/registro-de-contratos-del-sector-publico-de-euskadi/).
* [estaciones-meteorologicas-lecturas-recogidas-en-2017](http://opendata.euskadi.eus/datos/-/estaciones-meteorologicas-lecturas-recogidas-en-2017).
* [calidad-aire-en-euskadi-2017](http://opendata.euskadi.eus/catalogo/-/calidad-aire-en-euskadi-2017).

# Creacion de datasets

Los datasets han sido creados a partir de los CSVs mediante la herramienta [OntoRefine de GraphDB](http://ontotext.com/knowledgehub/videos/ontorefine/). El archivo JSON describiendo la limpieza de datos y el archivo SPARQL describiendo la conversión a de CSV a RDF se encuentran en la carpeta `/ontorefine` de cada dataset.

# Consultas

Hay consultas SPARQL que hacen referencia a todos los datasets en `registro-de-contratos-del-sector-publico-de-euskadi/sparql` y `calidad-aire-en-euskadi-2017/sparql`.

# Enlaces

Hay algunos enlaces a recursos externos en `retribuciones-altos-cargos-y-personal-eventual-gobierno-vasco-y-organismos-autonomos-y-entes-publicos/enlaces`.

# Metadatos

Los metadatos propios de los dataset Linked Data son una distribución más que parte de la URI original del dataset:

``` 
<http://opendata.euskadi.eus/catalogo/id/calidad-aire-en-euskadi-2017> dcat:distribution  <http://opendata.euskadi.eus/dataset/id/calidad-aire-en-euskadi-2017> .
```

De modo que subiendo el DCAT original junto a los nuevos predicados, obtenemos el provenance del dataset. La URI de la distribución Linked Data tiene más metadatos específicos de Linked Data y esa misma URI es la que se usa para identificar el named graph de esos datos en la Triple Store. 


