package iestr.gag.enemigos

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

const val UN_SEGUNDO=1000L //Un segundo=1000 milisegundos

class ListaViewModel: ViewModel() {
    private val reloj: CountDownTimer
    private var _lista=mutableListOf<Orco>()
    val lista:MutableList<Orco>
        get()=_lista
    private var _cambio=MutableLiveData<Int>(0)
    val cambio:LiveData<Int>
        get()=_cambio

    init{
        reloj=object:CountDownTimer(UN_SEGUNDO,UN_SEGUNDO){
            override fun onTick(p0: Long) {//ver https://developer.android.com/reference/android/os/CountDownTimer#onTick(long)
                //Nada
            }
            override fun onFinish() {
                for(orco in _lista) orco.energia=Math.max(0,orco.energia-1)
                informaCambio()
                start()
            }
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
        reloj.cancel()
    }

    fun reiniciaLista(){
        _lista.clear()
        informaCambio()
    }

    fun insertaOrco(){
        _lista.add(Orco())
        informaCambio()
    }

    fun reavivaOrco(id:Int){
        _lista[id].energia=Orco.MAX_ENERGIA
        informaCambio()
    }

    fun informaCambio(){
        _cambio.value=(cambio.value!!+1)%2 //para evitar que crezca y, eventualmente, pudiera desbordar el rango
    }
}