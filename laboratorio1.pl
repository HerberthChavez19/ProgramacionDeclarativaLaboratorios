% Personajes
personaje(eric).
personaje(timmy).
personaje(kelvin).
personaje(virginia).

% Edades
edad(eric, 30).

% Roles
protagonista(eric).
aliado(kelvin).            
mutante(virginia).         

% Objetos que posee cada personaje
tiene(eric, hacha).
tiene(eric, encendedor).

% Habilidades
puede_cargar(kelvin, troncos).
puede_construir(kelvin).

% Zonas de la isla
zona(superficie).
zona(cuevas).
zona(bunkeres).

% Enemigos y dónde aparecen
enemigo(canibales).
enemigo(mutantes).
aparece(canibales, superficie).
aparece(mutantes, superficie).
aparece(mutantes, cuevas).

% Los búnkeres requieren llave para abrirse
requiere_llave(bunkeres).

% Nivel de peligro
peligro(cuevas, alto).
peligro_superficie(dia, medio).
peligro_superficie(noche, alto).

% Necesidades de Eric para sobrevivir
necesita(eric, refugio).
necesita(eric, comida).
necesita(eric, agua).

% Materiales y dónde se encuentran
material(troncos).
material(piedras).
se_encuentra(troncos, superficie).
se_encuentra(piedras, superficie).

% ---------------------- REGLAS ------------------------------

% Regla 1: un personaje es adulto si su edad es >= 18.
adulto(P) :-
    edad(P, E),
    E >= 18.

% Regla 2: un personaje puede construir un refugio si sabe construir. Y hay troncos y piedras en una misma zona.
puede_construir_refugio(P) :-
    puede_construir(P),
    se_encuentra(troncos, Z),
    se_encuentra(piedras, Z).

% Regla 3: una zona es segura si no aparece ningún enemigo en ella. Los búnkeres cumplen, aunque requieran llave
zona_segura(Z) :-
    zona(Z),
    \+ aparece(_, Z).