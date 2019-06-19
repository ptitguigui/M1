# TP

Le but de se TP est de gérer la gestion de stockage des fichiers avec un disque virtuelle.

## Contenu

Dans ce tp vous trouverez les fichiers suivant :

- dossier lib
- dossier include
- dossier tpfs regroupant l'ensemble des fichiers générant les éxecutables

## Utilisation

- make realclean
- make
- ./mkvol --> suivre instruction (ex: 10 10 10 0)
- ./mknfs --> choisir 0
- export CURRENT_VOLUME=0
- ./if_nfile < file2.txt
- ./dfs
- ./if_cfile 1
- ./dfs
- ./if_pfile 1
- ./dfs
