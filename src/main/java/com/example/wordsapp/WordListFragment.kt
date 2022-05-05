package com.example.wordsapp


import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {

    private lateinit var letterId: String

    companion object{
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }



    //https://developer.android.com/codelabs/basic-android-kotlin-training-fragments-navigation-component?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-2%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-fragments-navigation-component#4
    /**
     * Paso 1
     * En LetterListFragment.kt, comienza por hacer referencia a la FragmentLetterListBinding
     * y asigna el nombre _binding a la referencia.
     */
    private var _binding : FragmentWordListBinding? = null

    /**
     * NOTA: Cuando uses !! a fin de hacer que una variable admita un valor nulo,
    te recomendamos que limites su uso a solo uno o pocos lugares en los que sepas
    que el valor no será nulo, así como sabes que _binding tendrá un valor después
    de que se asigne en onCreateView(). Acceder a un valor anulable de esta forma es
    peligroso y puede generar fallas, por lo que, si lo usas, debes hacerlo con moderación.

    Paso 2
    Crea una nueva propiedad, llamada binding (sin el guion bajo), y establécela en _binding!!.
     */
    private val binding get() = _binding!!

    /**
     * Paso 5.
     * Debajo de la propiedad binding, crea una propiedad para la vista de reciclador.
     */

    private lateinit var recyclerView: RecyclerView

    /**
     * Paso 10.
     * Por último, copia sobre la propiedad isLinearLayoutManager de MainActivity.
     * Escribe esto debajo de la declaración de la propiedad recyclerView.
     */
    private var isLinearLayoutManager = true

    /**
     * Paso 3
     * Para implementar onCreate(), simplemente, llama a setHasOptionsMenu().
     */
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        //setHasOptionsMenu(true)
        arguments?.let{letterId=it.getString(LETTER).toString()}
    }

    /**
     * Paso 4.
     * Recuerda que, cuando se trata de fragmentos, el diseño aumenta en onCreateView().
     * Implementa onCreateView() aumentando la vista, configurando el valor de
     * _binding y mostrando la vista raíz.     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    /**
     * Paso 6.
     * Luego, establece el valor de la propiedad recyclerView en onViewCreated() y llama a chooseLayout()
     * como lo hiciste en MainActivity. Pronto moverás el método chooseLayout() a LetterListFragment.
     * No te preocupes si se produce un error.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.adapter = WordAdapter(activity?.intent?.extras?.getString(LETTER).toString(), requireContext())
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    /**
     * Paso 7.
     * Por último, en onDestroyView(), restablece la propiedad _binding a null, dado que la vista ya no existe.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    /**
     * Paso 8.
     * Lo único que también deberás tener en cuenta es que existen algunas
     * diferencias sutiles con el método onCreateOptionsMenu() cuando se
     * trabaja con fragmentos. Si bien la clase Activity tiene una propiedad global
     * llamada menuInflater, los fragmentos no tienen esta propiedad. En su lugar,
     * el amplificador de menú se pasa a onCreateOptionsMenu(). Además, ten en cuenta
     * que el método onCreateOptionsMenu() que se usa
     * con los fragmentos no requiere una sentencia de retorno.
     * Implementa el método como se muestra a continuación:
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    /**
     * paso 9.
     * Mueve el código restante de chooseLayout(), setIcon() y onOptionsItemSelected() desde MainActivity tal como está.
     * La única diferencia adicional que debes tener en cuenta es que, a diferencia de una actividad, un fragmento no es un Context.
     * No puedes pasar this (si te refieres al objeto de fragmento) como el contexto del administrador de diseño.
     * Sin embargo, los fragmentos proporcionan una propiedad context que puedes usar en su lugar.
     * El resto del código es idéntico a MainActivity.
     */

    private fun chooseLayout() {
        when (isLinearLayoutManager) {
            true -> {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = LetterAdapter()
            }
            false -> {
                recyclerView.layoutManager = GridLayoutManager(context, 4)
                recyclerView.adapter = LetterAdapter()
            }
        }
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}