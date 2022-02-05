# Enemigos5
Recyclerview-VievModel-LiveData on Android with Kotlin.

Es muy simple:
- Pulsando el botón 'suma' se añade un enemigo a la lista (nombre y porcentaje de energía, aleatorios)
- Pulsando el botón 'recargar' se limpia la lista
A partir de ahí, cada segundo se quita 1% de energía a cada enemigo de la lista
- Pulsando sobre un enemigo se repone su energía
- Un 'swipe'sobre un  enemigo lo borra de la lista

Está hecho para probar viewmodel+livedata en combinación con recyclerview. De ahí lo de la modificación continua de los porcentajes: para ver como se actualizan constantemente los valores en el IU.
