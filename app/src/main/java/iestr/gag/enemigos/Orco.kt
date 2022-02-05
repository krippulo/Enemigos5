package iestr.gag.enemigos

import java.lang.StringBuilder
import kotlin.random.Random

class Orco(
    val nombre:String = nombreAleatorio(),
    var energia:Int = Random.nextInt(50,101)
){
    companion object{
        public const val MAX_ENERGIA=100
        private const val VOCALES = "aeiou"
        private const val CONSONANTES = "bcdfghjklmnpqrstvwxyz"
        private var contador=0
        private fun nombreAleatorio():String{//Entre 4 y 6 letras
            val nombre: StringBuilder = StringBuilder("")
            //Dos letras siempre
            if (Math.random() < 0.5) { //por añadir un poco de variedad a la estructura
                nombre.append(vocalAleatoria())
                nombre.append(consonanteAleatoria())
            } else {
                nombre.append(consonanteAleatoria())
                nombre.append(vocalAleatoria())
            }
            if(Random.nextInt(2)==1) nombre.append(vocalAleatoria())//quizás esta también
            if(Random.nextInt(2)==1)nombre.append(consonanteAleatoria())//quizás esta también
            //Otras dos letras siempre
            if (Math.random() < 0.5) { //por añadir un poco de variedad a la estructura
                nombre.append(vocalAleatoria())
                nombre.append(consonanteAleatoria())
            } else {
                nombre.append(consonanteAleatoria())
                nombre.append(vocalAleatoria())
            }
            return nombre.toString()
        }

        private fun vocalAleatoria(): String {
            val pos = (Math.random() * VOCALES.length).toInt()
            return VOCALES.substring(pos, pos + 1)
        }

        private fun consonanteAleatoria(): String {
            val pos = (Math.random() * CONSONANTES.length).toInt()
            return CONSONANTES.substring(pos, pos + 1)
        }
    }
}