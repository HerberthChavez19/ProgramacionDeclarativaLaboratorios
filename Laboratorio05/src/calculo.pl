% calculo.pl
% Reglas de la calculadora. Java solo envia A y B y lee R; todo el calculo
% ocurre aqui.

suma(A, B, R) :-
    number(A), number(B),
    R is A + B.

resta(A, B, R) :-
    number(A), number(B),
    R is A - B.

multiplicacion(A, B, R) :-
    number(A), number(B),
    R is A * B.

division(A, B, R) :-
    number(A), number(B),
    B =\= 0,
    R is (A * 1.0) / B.
