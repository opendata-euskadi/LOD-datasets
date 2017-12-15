Intro
=====

## Datos

http://opendata.euskadi.eus/catalogo/-/guia-comunicacion-gobierno-vasco-datos-contacto-entidades-euskadi/

http://opendata.euskadi.eus/catalogo/-/guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades/

http://opendata.euskadi.eus/katalogoa/-/eusko-jaurlaritzaren-komunikazio-gida-euskadiko-erakundeen-kontaktu-datuak/

http://opendata.euskadi.eus/katalogoa/-/eusko-jaurlaritzaren-komunikazio-gida-erakundeetako-kargu-eta-ordezkarien-kontaktu-datuak/

(DCATs obtenidos inspeccionado código fuente de la página web)

## Problemas en los CSV

Mirar codigo
Mirar LOG

Los datos de cargos y de entidades no comparten nada en comun, con lo que no se pueden crear URIs de entidades para enlazarlos:

cargos: "Delegado de Álava, Kristau Eskola"
entidad: "Kristau eskola"

## Problemas/decisiones generales Linked Data

Asumiendo que las URIs generadas para cargos son constantes.

* Un unico grafo con dos idiomas lanpostua/cargo en vcard:role. Ventaja: un solo conversor Java. Desventaja: el dataset EU no se convierte.
* Dos grafos identicos con solo diferencia en Lanpostua/Cargo. Ventaja: dos conversores Java muy parecidos. Desventaja: muchos triples se duplican: en el caso de recursos da igual, en el caso de literales no (si se hace una consulta al default graph, los rdfs:comment duplicados saldrán dos veces).
* El grafo ES contiene todo menos Lanpostu, y el grafo EU solo contiene Lanpostu. Ventaja: menos triples. Desventaja: dos conversores Java, uno solo con las lo correspondiente a Lanpostu.

## DCAT

He quitado la distribucion SPARQL y la TTL original, he añadido la de Linked Data y la de nquads