# TP4  

Dans ce TP vous trouverez la résolution d'un problème avec des methodes d'heuristique

## Contenue du TP:

Dans ce TP vous trouverez :

- Le code source du TP dans le dossier `/src`
- La synthese de ce TP à travers le fichier `README.md` 
- Le dossier de jeux de `/donnees`

## Utilisation du TP

Lancer la classe `Heuristique`, suivez les instructions afin d'iniatiliser le problème.  
Cette classe vous permettra selon un fichier donnée, la résolution du problème selon plusieurs méthodes : 
- Version globale de façon itérative
- Version exhaustive 
- Version methode locale

## Synthese des experimentations

###  Version globale de façon itérative

Cette version correspond à la partie 1.1 (l'algorithme de l'heuristique globale en construction itérative).  
Vous pouvez voir le code dans la méthode `heuristiqueGlobaleIterative()`. Cette version reprends l'algorithme expliqué dans l'énoncé. Mais, cet algorithme test tous les sommet de départ possible et garde uniquement la permutation donnant la distance la plus courte. 

En expérimentant avec les jeux de données, nous remarquons que cet algorithme donne une valeur très approximative puisqu'il donne la meilleur solution uniquement pour `exe5.atsp`. Les autres sont des réponses "rapproché" comme : 
- exe7.atsp -->  104 avec 3 5 4 6 0 1 2 
- exe7b.atsp -->  82 avec 3 5 4 6 0 2 1 
- br17.atsp -->  87 avec 0 1 2 9 11 10 13 12 7 5 3 6 4 14 8 15 16
-  bayg29.tsp --> 1935 avec 20 1 19 9 3 14 18 24 6 22 26 23 7 27 0 5 11 8 4 25 28 2 12 15 10 21 13 17 16
- bays29.tsp --> 2134  avec 22 26 23 7 0 27 5 11 8 4 25 28 2 1 20 19 9 3 14 17 13 21 16 10 18 15 12 24 6 
- ...etc

### Version exhaustive 

Cette version reprend le TP3 qui calcule l'ensemble des permutations possible et récupère la meilleur solution. Par conséquent cet algorithme donne forcément la meilleur solution tant que les jeux de données ne sont pas très grand.
Par conséquent cette version trouve uniquement les jeux de données suivants : 
- exe5.atsp --> 22 avec 1 3 2 0 4
- exe7.atsp -->  36 avec  3 4 5 1 2 0 6 
- exe7b.atsp -->  36 avec 1 2 0 5 3 4 6 
- br17.atsp  et autres -->  données trop grande
- euclidA100.tsp -->  27463 25 31 76 58 98 57 29 59 35 52 36 95 91 34 92 69 78 65 83 11 33 22 82 46 30 79 96 24 47 87 64 85 84 60 13 42 94 10 2 71 3 39 48 56 62 14 89 73 70 5 15 61 38 43 20 68 44 90 50 66 37 40 17 49 32 19 8 28 51 74 12 81 86 16 88 55 63 0 75 21 18 9 67 80 93 41 4 6 54 99 7 27 53 72 23 77 26 1 45 97 
- euclidB500.tsp --> 62422 avec 373 335 392 482 32 347 115 348 225 40 125 33 26 147 151 405 217 420 493 331 474 329 170 227 484 14 76 143 245 22 452 100 349 410 440 61 497 208 86 444 50 58 0 205 310 290 363 129 106 133 430 388 449 149 195 346 321 185 198 324 231 66 341 102 80 314 18 167 73 228 138 432 6 441 479 12 495 265 232 473 189 437 362 262 259 90 11 81 374 337 210 267 144 64 369 211 458 46 426 4 237 111 451 253 152 9 5 258 199 193 7 190 446 191 286 350 161 260 216 277 139 229 263 204 93 281 252 272 162 219 207 98 411 97 419 361 421 99 316 340 424 312 74 282 399 477 416 368 269 75 317 172 107 38 447 45 386 480 491 353 379 89 383 469 140 457 174 352 19 376 295 328 465 488 116 15 53 472 159 131 243 274 69 406 20 463 320 59 387 82 471 171 127 239 122 2 478 415 255 41 468 39 221 92 177 47 146 48 414 77 150 412 248 105 212 334 187 396 404 236 157 164 154 268 91 83 176 241 79 148 123 460 498 333 292 120 49 296 42 104 65 270 24 200 179 448 70 408 168 63 327 394 319 220 467 113 481 206 119 234 338 476 306 490 360 23 294 375 95 215 462 257 264 496 289 244 202 443 284 222 398 391 246 323 356 397 214 445 166 326 275 109 285 188 108 318 494 325 466 345 359 135 358 435 301 413 156 114 194 163 230 71 400 297 145 1 224 423 235 308 136 31 342 454 271 186 499 37 380 88 429 492 439 178 464 390 389 142 101 438 455 226 330 279 291 84 367 287 126 201 453 3 250 483 56 85 134 78 450 370 427 180 266 402 385 442 52 184 371 249 35 43 315 283 433 357 117 431 57 309 365 13 34 218 165 121 238 372 103 251 196 343 25 393 470 304 418 67 110 311 173 87 425 382 8 428 298 305 112 377 384 475 489 378 137 233 62 203 55 395 21 51 293 96 332 351 322 132 486 155 302 44 30 456 336 307 29 141 461 288 422 213 366 276 487 407 16 256 344 68 183 434 261 273 160 313 94 181 280 153 409 54 36 28 158 118 254 169 209 223 240 182 300 355 303 247 130 128 124 485 354 60 339 72 401 403 436 381 278 175 10 364 197 27 299 242 417 192 17 459 
- etc...  

### Version locale : Hill Climbing 

Cette version utilise la méthode de `voisinage` grâce à la méthode `swap` comme il a été présenté en TP.  
Cette version fonctionne de la manière suivante, on prends un heuristique (ici c'est l'heuristique globale) et on cherche l'ensemble des voisins qui donne une distance inférieur à l'heuristique de base.  
Ainsi, grâce à cette méthode, on trouve une meilleur solution que la version globale jusqu'à trouver la meilleur solution pour certains jeux de données ou se rapprocher fortement même pour des jeux de données très grands.

On remarque que si l'heuristique de base est très éloigné de la meilleur solution, il est difficile de trouver la meilleur solution.  
Voici les résultats à nos jeux de données : 

- br17.atsp -->  39 avec 0 11 13 2 1 9 10 12 15 14 6 5 3 4 7 8 16 
- bayg29.tsp --> 1889 avec 20 2 28 25 4 8 11 5 27 0 23 7 26 22 15 12 1 19 9 3 14 18 24 6 10 21 13 17 16
- bays29.tsp --> 2035 avec 22 26 7 23 0 27 5 11 8 4 25 28 2 1 20 19 9 3 14 17 16 13 21 10 18 12 15 24 6
- euclidA100.tsp --> 26823 avec 25 31 76 58 98 57 29 59 35 52 36 95 91 34 92 69 78 65 45 1 26 77 23 72 53 24 47 87 64 85 84 60 13 42 94 10 2 71 3 39 48 56 62 14 89 73 70 5 15 61 38 43 20 68 44 90 50 66 28 8 19 32 49 37 40 17 21 0 75 63 55 88 16 86 81 12 51 74 18 9 67 80 93 41 4 6 54 99 7 27 96 79 30 46 82 22 33 83 11 97 
- euclidB500.tsp --> 58789 avec 373 335 392 482 115 347 32 348 225 40 125 33 26 147 151 331 493 420 217 405 227 170 329 474 484 14 76 143 22 245 452 100 349 410 440 497 61 208 130 247 303 355 204 93 281 252 272 162 98 207 219 411 97 419 361 366 213 422 288 461 141 29 307 336 456 243 274 320 463 20 69 406 59 387 82 122 44 30 302 2 478 415 255 41 468 155 92 39 221 47 177 146 48 414 77 150 412 248 105 212 334 187 396 404 55 499 37 380 88 429 492 439 178 464 390 389 142 101 386 480 491 353 379 89 383 469 140 457 174 352 19 376 295 328 465 488 116 15 53 472 131 159 239 471 171 127 486 132 322 351 332 96 293 51 21 395 157 236 164 154 268 91 83 176 241 79 148 123 460 271 454 342 31 308 136 235 224 423 220 467 113 481 206 119 234 338 476 306 490 360 23 294 375 95 215 462 244 289 496 264 257 202 443 284 222 398 391 246 323 356 397 297 400 71 230 163 194 156 114 413 358 435 301 135 359 345 325 466 494 318 108 188 285 109 275 326 214 445 166 145 1 319 394 327 63 168 408 70 186 270 498 333 292 120 49 296 42 104 65 24 200 179 448 203 62 233 137 378 475 489 384 238 372 393 470 304 418 67 110 382 311 173 425 87 8 305 428 298 112 377 343 25 196 251 103 121 165 365 34 218 13 309 57 431 117 357 433 283 315 43 35 249 371 184 52 442 385 402 266 94 313 160 273 261 434 183 68 344 256 16 276 487 407 300 182 240 223 209 169 254 232 189 473 437 362 262 259 5 152 9 253 451 111 237 4 426 46 458 211 369 64 267 144 210 337 374 81 11 90 258 199 190 7 193 139 277 216 260 161 229 446 191 286 350 263 421 99 424 340 316 312 74 282 399 477 416 368 269 75 317 172 107 45 447 38 438 455 226 330 279 291 84 367 201 126 287 453 3 250 483 56 85 134 78 450 370 427 180 181 479 12 495 265 118 167 73 228 138 432 441 6 280 153 409 54 36 158 28 18 314 80 102 341 66 231 324 198 185 321 346 195 149 449 388 430 133 106 129 363 290 310 205 0 58 50 86 444 128 124 485 354 60 339 72 401 403 436 381 278 175 10 364 197 27 299 242 417 192 17 459 
- etc...
