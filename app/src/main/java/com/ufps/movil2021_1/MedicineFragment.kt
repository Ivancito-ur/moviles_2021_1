package com.ufps.movil2021_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MedicineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicineFragment : Fragment() {
    lateinit var containerMedicine: RecyclerView
    lateinit var adapterMedicine: AdapterMedicine
    lateinit var addMedicine: FloatingActionButton

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_medicine, container, false)
        containerMedicine = view.findViewById(R.id.container_medicine)
        addMedicine = view.findViewById(R.id.add_medicine)
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        containerMedicine.layoutManager = linearLayout
        adapterMedicine = AdapterMedicine(context, cargarDatosFirebase(), R.id.card)
        containerMedicine.adapter = adapterMedicine
        addMedicine.setOnClickListener {
            irRegistrarMedicina()
        }
        return view
    }

    private fun irRegistrarMedicina() {
        val intent = Intent(this.context, RegisterMedicine::class.java)
        startActivity(intent)
    }

    private fun cargaDatos(): ArrayList<MedicineEntity> {
        var medicines: ArrayList<MedicineEntity> = java.util.ArrayList<MedicineEntity>()


        medicines.add(
                MedicineEntity(
                        "1",
                        "Metformina",
                        "Control del azucar",
                        "Mareso, desequilibrio",
                        "https://www.cruzverde.com.co/dw/image/v2/BDPM_PRD/on/demandware.static/-/Sites-masterCatalog_Colombia/default/dwb109ccb0/images/large/17443-1-METFORMINA-850MG-TAB-CAJ-X-30-WINTHROP.jpg?sw=1000&sh=1000"
                )
        )
        medicines.add(
                MedicineEntity(
                        "2",
                        "Losartan",
                        "Control de la tension",
                        "Deacaidas",
                        "https://drogueriascolombiafarma.viveshops.co/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBKzRMTVE9PSIsImV4cCI6bnVsbCwicHVyIjoiYmxvYl9pZCJ9fQ==--1505741c99185bbbe9da99bf327ff88c2c753a19/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdDRG9TY21WemFYcGxYM1J2WDJacGRGc0hhUUlnQTJrQ0lBTTZER052Ym5abGNuUkpJZ2hxY0djR09nWkZWRG9LYzJGMlpYSjdDRG9NY1hWaGJHbDBlV2xmT2dwemRISnBjRlE2RDJKaFkydG5jbTkxYm1SYkNHa0IvMmtCLzJrQi93PT0iLCJleHAiOm51bGwsInB1ciI6InZhcmlhdGlvbiJ9fQ==--f9b6205701877dad933126b17f2a1a57fbbba520/7702605103232-3.jpg?locale=es"
                )
        )
        return medicines
    }

    fun cargarDatosFirebase(): ArrayList<MedicineEntity> {
        val medicines = ArrayList<MedicineEntity>()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference()
        myRef.child("medicine").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    medicines.clear()
                    for (data in snapshot.children) {
                        val medicine = data.getValue(MedicineEntity::class.java)
                        medicines.add(medicine as MedicineEntity)
                        adapterMedicine.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Medicine Fragament", "Carga no elaborada", error.toException())
            }
        })
        return medicines
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MedicineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MedicineFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}