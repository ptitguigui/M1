#include "context.h"

#define N 1                         /* nombre de places dans le tampon */
static int cpt = 0;
static struct sem_s mutex, vide, plein;


void mettre_objet(){
    printf("objet n%d mis\n", ++cpt);
}


void retirer_objet(){
    printf("objet n%d retiré\n", --cpt);
}

void producteur (void *args)
{
  for(int i=0; i<20; i++) {
    sem_down(&vide);                  /* dec. nb places libres */
    sem_down(&mutex);                 /* entree en section critique */
    mettre_objet();                   /* mettre l'objet dans le tampon */
    for (int i=0; i<1000000; i++);
    sem_up(&mutex);                   /* sortie de section critique */
    sem_up(&plein);                   /* inc. nb place occupees */
  }
}

void consommateur (void *args)
{
  for(int i=0; i<20; i++) {
    sem_down(&plein);                 /* dec. nb emplacements occupes */
    sem_down(&mutex);                 /* entree section critique */
    retirer_objet();                  /* retire un objet du tampon */
    for (int i=0; i<1000000; i++);
    sem_up(&mutex);                   /* sortie de la section critique */
    sem_up(&vide);                    /* inc. nb emplacements libres */
    }
}

int main(int argc, char *argv[])
{

    sem_init(&mutex, 1);                /* controle d'acces au tampon */
    sem_init(&vide, N);                 /* nb de places libres */
    sem_init(&plein, 0);                /* nb de places occupees */

    create_ctx(16384, producteur, NULL);
    create_ctx(16384, consommateur, NULL);
    start_schedule();
    exit(EXIT_SUCCESS);
}