almacenar(0, []).

almacenar(N, [D | Resto]) :-
    N > 0,
    D is N mod 10,
    R is N // 10,
    almacenar(R, Resto).