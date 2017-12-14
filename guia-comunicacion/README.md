# Intro

# Datos

http://opendata.euskadi.eus/catalogo/-/guia-comunicacion-gobierno-vasco-datos-contacto-entidades-euskadi/

http://opendata.euskadi.eus/catalogo/-/guia-de-la-comunicacion-del-gobierno-vasco-datos-de-contacto-de-los-representantes-y-cargos-de-entidades/

http://opendata.euskadi.eus/katalogoa/-/eusko-jaurlaritzaren-komunikazio-gida-euskadiko-erakundeen-kontaktu-datuak/

http://opendata.euskadi.eus/katalogoa/-/eusko-jaurlaritzaren-komunikazio-gida-erakundeetako-kargu-eta-ordezkarien-kontaktu-datuak/

(DCATs obtenidos inspeccionado código fuente de la página web)

# Problemas en los CSV

Mirar codigo
Mirar LOG

# Problemas/decisiones generales Linked Data

* Un unico grafo con dos propiedades lanpostua/cargo. Ventaja: un solo conversor Java. Desventaja: el dataset EU no se convierte.
* Dos grafos identicos con solo diferencia en Lanpostua/Cargo. Ventaja: dos conversores Java muy parecidos. Desventaja: muchos triples se duplican: en el caso de recursos da igual, en el caso de literales no (si se hace una consulta al default graph, los rdfs:comment duplicados saldrán dos veces).
* El grafo ES contiene todo menos Lanpostu, y el grafo EU solo contiene Lanpostu. Ventaja: menos triples. Desventaja: dos conversores Java.