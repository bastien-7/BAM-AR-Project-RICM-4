# BAM-AR-Project-RICM-4

Projet de Bus Agent Mobile, école de Polytech Grenoble fillière RICM4.

Projet réalisé par :

Cédric LAFRASSE
Bastien TERRIER
Julien COURTIAL
Hugo GROS-DAILLON

## PARTIE RMI.

Pour exécuter la partie RMI, rendez-vous dans le package RMI :

- Exécutez le "Serveur.java" pour lancer le serveur.
- Exécutez le "LookForHotel.java" avec les arguments :
~~~
Paris
~~~

## PARTIE BAM - HELLO

Pour exécuter la partie BAM - HELLO, rendez-vous sur le package mobilagent.kernel :

- Exécutez le premier serveur en exécutant "Starter.java" avec les arguments : 
~~~
src/jus/aor/mobilagent/kernel/Configurations/hello.server1.xml serveur1
~~~
Ainsi que les VM arguments :
~~~
-Djava.security.policy='/home/hugogda/AppRep-Projet/BAM-AR-Project-RICM-4/src/jus/aor/mobilagent/kernel/Policy' -DLEVEL=ALL -Dsun.lang.ClassLoader.allowArraySyntax=true
~~~

- Exécutez le second serveur en exécutant "Starter.java" avec les arguments :
~~~
src/jus/aor/mobilagent/kernel/Configurations/hello.server2.xml serveur2
~~~
Ainsi que les VM arguments :
~~~
-Djava.security.policy='/home/hugogda/AppRep-Projet/BAM-AR-Project-RICM-4/src/jus/aor/mobilagent/kernel/Policy' -DLEVEL=ALL -Dsun.lang.ClassLoader.allowArraySyntax=true
~~~

- Exécutez enfin le client en exécutant "Starter.java" avec les arguments :
~~~
src/jus/aor/mobilagent/kernel/Configurations/hello.client1.xml client1
~~~
Ainsi que les VM arguments :
~~~
-Djava.security.policy='/home/hugogda/AppRep-Projet/BAM-AR-Project-RICM-4/src/jus/aor/mobilagent/kernel/Policy' -DLEVEL=ALL -Dsun.lang.ClassLoader.allowArraySyntax=true
~~~

## PARTIE BAM - LookForHotel

Pour exécuter la partie BAM - LookForHotel, rendez-vous sur le package mobilagent.kernel :

- Exécutez le premier serveur de CHAINE en exécutant "Starter.java" avec les arguments : 
~~~
src/jus/aor/mobilagent/kernel/Configurations/hostel.server1.xml serveur1
~~~
Ainsi que les VM arguments :
~~~
-Djava.security.policy='/home/hugogda/AppRep-Projet/BAM-AR-Project-RICM-4/src/jus/aor/mobilagent/kernel/Policy' -DLEVEL=ALL -Dsun.lang.ClassLoader.allowArraySyntax=true
~~~

- Exécutez le second serveur de CHAINE en exécutant "Starter.java" avec les arguments : 
~~~
src/jus/aor/mobilagent/kernel/Configurations/hostel.server2.xml serveur2
~~~
Ainsi que les VM arguments :
~~~
-Djava.security.policy='/home/hugogda/AppRep-Projet/BAM-AR-Project-RICM-4/src/jus/aor/mobilagent/kernel/Policy' -DLEVEL=ALL -Dsun.lang.ClassLoader.allowArraySyntax=true
~~~


- Exécutez le serveur d' ANNUAIRE en exécutant "Starter.java" avec les arguments : 
~~~
src/jus/aor/mobilagent/kernel/Configurations/hostel.server3.xml serveur_annuaire
~~~
Ainsi que les VM arguments :
~~~
-Djava.security.policy='/home/hugogda/AppRep-Projet/BAM-AR-Project-RICM-4/src/jus/aor/mobilagent/kernel/Policy' -DLEVEL=ALL -Dsun.lang.ClassLoader.allowArraySyntax=true
~~~

- Exécutez le client en exécutant "Starter.java" avec les arguments : 
~~~
src/jus/aor/mobilagent/kernel/Configurations/hostel.client1.xml client1
~~~
Ainsi que les VM arguments :
~~~
-Djava.security.policy='/home/hugogda/AppRep-Projet/BAM-AR-Project-RICM-4/src/jus/aor/mobilagent/kernel/Policy' -DLEVEL=ALL -Dsun.lang.ClassLoader.allowArraySyntax=true
~~~
