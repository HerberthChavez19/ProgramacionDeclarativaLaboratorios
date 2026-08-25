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

% Enemigos y donde aparecen
enemigo(canibales).
enemigo(mutantes).
aparece(canibales, superficie).
aparece(mutantes, superficie).
aparece(mutantes, cuevas).

% Los bunkeres requieren llave para abrirse
requiere_llave(bunkeres).

% Nivel de peligro
peligro(cuevas, alto).
peligro_superficie(dia, medio).
peligro_superficie(noche, alto).

% Necesidades de Eric para sobrevivir
necesita(eric, refugio).
necesita(eric, comida).
necesita(eric, agua).

% Materiales y donde se encuentran
material(troncos).
material(piedras).
se_encuentra(troncos, superficie).
se_encuentra(piedras, superficie).

% ---------------------- REGLAS ------------------------------
% Regla 1: una zona es peligrosa si aparece algun enemigo en ella o su nivel de peligro es alto.
zona_peligrosa(Z) :-
    zona(Z),
    ( aparece(_, Z)
    ; peligro(Z, alto)
    ).
 
% Regla 2: un personaje es adulto si su edad es mayor o igual a 18
adulto(P) :-
    edad(P, E),
    E >= 18.
 
% Regla 3: alguien es posible aliado si ya es aliado o es un mutante (puede volverse aliada).
posible_aliado(P) :-
    ( aliado(P)
    ; mutante(P)
    ).
 
% Regla 4: un personaje puede construir un refugio si sabe construir. Y hay troncos y piedras en una misma zona.    (usa ,)
puede_construir_refugio(P) :-
    puede_construir(P),
    se_encuentra(troncos, Z),
    se_encuentra(piedras, Z).
 
% Regla 5: una zona es segura si no aparece ningun enemigo en ella. Los bunkeres cumplen, aunque requieran llave
zona_segura(Z) :-
    zona(Z),
    \+ aparece(_, Z).
 