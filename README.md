# SubnetCalc
Subnet calculator created during my studies at University of Technology and Science in Cracow

1.Skrypt przyjmuje jako argument adres IP (hosta lub sieci) wraz z maskąw formacie: a.b.c.d/maska
2.Jeśli argument nie został podany to skrypt pobieraadres komputera, na którym jest uruchomiony
3.Skrypt sprawdza, czy wprowadzony adres jest poprawnym adresem IP. Jeśli nie, wyświetla komunikat o błędzie.
4.Skrypt obliczanastępujące dane:
a).Adres sieci
b).Klasę siecii
c).Czy adres należy do puli adresów publicznych czy prywatnych
d).Maskasieciw formacie dziesiętnym (np. 255.255.255.0) i binarnym
e).Adres broadcast(dziesiętnie i binarnie)
f).Pierwszy adres hosta(dziesiętnie i binarnie)
g).Ostatni adres hosta(dziesiętnie i binarnie)
h).Maksymalna ilość hostów, która może być przypisana do danej podsieci
5.Obliczone wartości są wyświetlane na ekranie oraz zapisywane do pliku tekstowego
6.Adresyw systemie dwójkowym są prezentowane tak, aby każdy oktet był przedstawiony przy pomocy 8 znaków.
7.Jeżeli podany adres jest adresem hostato skrypt pyta, czy wykonać polecenie ping dla podanego adresu. 
Jeśli użytkownik wpisze Y to skrypt wykonuje polecenie ping oraz prezentuje jego wyniki.
