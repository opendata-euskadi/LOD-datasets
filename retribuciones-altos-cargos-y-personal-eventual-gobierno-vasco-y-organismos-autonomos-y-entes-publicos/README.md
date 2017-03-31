# Origen dataset

* URL dataset Open Data euskadi: http://opendata.euskadi.eus/catalogo/-/retribuciones-altos-cargos-y-personal-eventual-gobierno-vasco-y-organismos-autonomos-y-entes-publicos/
* Archivo DCAT original (Para saber que datos estamos manejando): `r01DCATDataset.rdf`

# Linked Data

* Archivo DCAT con URI de dataset modificada para cumplir el esquema de URIs acordado: `DCAT.rdf`
* URI dataset: http://opendata.euskadi.eus/catalogo/id/retribuciones-altos-cargos-y-personal-eventual-gobierno-vasco-y-organismos-autonomos-y-entes-publicos
* Archivo DCAT con prototipo de grafo para Linked Data: `DCAT-LinkedData.ttl`
* Grafo datos en Triple Store: http://opendata.euskadi.eus/dataset/id/retribuciones-altos-cargos-y-personal-eventual-gobierno-vasco-y-organismos-autonomos-y-entes-publicos
* Página Web simulando ficha de Open Data Euskadi (rama gh-pages): 
* dataset en rdf: `rdf/`
* Consultas contra dataset: `sparql/`
* SPARQL y JSON para pipeline OntoRefine: `ontorefine/`
* enlaces: `enlaces`

# URIs ejemplo definitivas

Estas URIs son adaptaciones de las URIs del dataset a la arquitectura de URIs consensuada (por ejemplo cambian idioma y `id`):

* URI dataset DCAT: `http://es.euskadi.eus/id/catalogo/calidad-aire-en-euskadi-2017`.
* URI grafo dataset: `http://es.euskadi.eus/id//dataset/retribuciones-altos-cargos-y-personal-eventual-gobierno-vasco-y-organismos-autonomos-y-entes-publicos`.
* Contrato: `http://es.euskadi.eus/id/sector-publico/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22`.
* Departamento: `http://es.euskadi.eus/id/sector-publico/departamento/lehendakaritza>`.
* Propiedades internas: `http://euskadi.eus/def/sector-publico/contrato/id-organo`, `http://euskadi.eus/def/sector-publico/contrato/organo`.
* Propiedades de ontologías externas: `http://contsem.unizar.es/def/sector-publico/pproc#managingDepartment`,`http://contsem.unizar.es/def/sector-publico/pproc#actualEndDate`,`http://contsem.unizar.es/def/sector-publico/pproc#formalizedDate`, `http://contsem.unizar.es/def/sector-publico/pproc#ContractEconomicConditions`, `http://dbpedia.org/ontology/occupation`, `http://schema.org/employee`, `http://purl.org/dc/terms/modified`.
* Clases de ontologías externas:`http://purl.org/cerif/frapo/EmploymentContract`.