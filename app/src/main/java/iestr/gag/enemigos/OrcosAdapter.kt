package iestr.gag.enemigos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class OrcosAdapter(val vm:ListaViewModel): RecyclerView.Adapter<OrcosAdapter.Holder>() {
    var lista=mutableListOf<Orco>()
        set(value){
            field=value
            notifyDataSetChanged()
            //notifyItemRangeChanged(0,lista.size-1)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val vista=LayoutInflater.from(parent.context).inflate(R.layout.linea,parent,false)
        return Holder(vista)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val orco= lista[position]
        holder.rellena(orco)
    }

    override fun getItemCount(): Int =lista.size

    /////////////////////////////////   HOLDER  ////////////////////////////////////////////////////
    inner class Holder(itemView: View) :RecyclerView.ViewHolder(itemView){
        private val nombre=itemView.findViewById<TextView>(R.id.nombre)
        private val energia=itemView.findViewById<TextView>(R.id.energia)

        init{
            itemView.setOnClickListener {
                //lista[absoluteAdapterPosition].energia=100
                vm.reavivaOrco(absoluteAdapterPosition)
            }
        }

        fun rellena(orco:Orco){
            nombre.text=orco.nombre
            energia.text=orco.energia.toString()
        }
    }
}