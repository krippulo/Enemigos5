package iestr.gag.enemigos

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iestr.gag.enemigos.databinding.FragmentListaBinding


class ListaFragment : Fragment() {
    lateinit var enlace:FragmentListaBinding
    val vm:ListaViewModel by lazy{
        ViewModelProvider(this).get(ListaViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        enlace= FragmentListaBinding.inflate(inflater,container,false)
        //Le digo que este fragmento tiene un menú
        setHasOptionsMenu(true)
        return enlace.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adaptador=OrcosAdapter(vm)
        enlace.listado.adapter=adaptador
        enlace.listado.layoutManager= LinearLayoutManager(context)
        vm.cambio.observe(viewLifecycleOwner){
            adaptador.lista=vm.lista
        }

        //-------- Gestos sobre items del recycler ---------//
        val manejadorGestos=ItemTouchHelper(
            object:ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    adaptador.lista.removeAt(viewHolder.absoluteAdapterPosition)
                    adaptador.lista=vm.lista
                }
            })
        manejadorGestos.attachToRecyclerView(enlace.listado)
    }

    //Le digo qué menú es el que tiene que inflar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_superior,menu)
    }
    //Y cómo tiene que responder a la selección de cada item de menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.reiniciar -> vm.reiniciaLista()
            R.id.insertar -> vm.insertaOrco()
        }
        return true
    }
}