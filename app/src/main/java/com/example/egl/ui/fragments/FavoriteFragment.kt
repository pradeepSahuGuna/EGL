package com.example.egl.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.egl.interfaces.ItemClickListener
import com.example.egl.R
import com.example.egl.adapter.FavoriteListAdapter
import com.example.egl.databinding.FragmentDashboardBinding
import com.example.egl.utils.Constants

class FavoriteFragment : Fragment(), ItemClickListener {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!
    lateinit var temClickListener: ItemClickListener


    lateinit var favoriteListAdapter: FavoriteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        temClickListener = this
        val recyclerProductList: RecyclerView = binding.favoriteList
        recyclerProductList.layoutManager = LinearLayoutManager(binding.root.context)
        favoriteListAdapter = FavoriteListAdapter(Constants.favoriteList, temClickListener)
        recyclerProductList.adapter =favoriteListAdapter
            return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClick(view: View, position: Int) {
        deleteAlert(position)
    }

    private fun deleteAlert(deleteItem: Int) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(R.string.dialog_title)
        builder.setMessage(R.string.dialog_message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton(R.string.txt_Yes) { dialogInterface, which ->
            Constants.favoriteList.removeAt(deleteItem)
            favoriteListAdapter.notifyDataSetChanged()
        }
        builder.setNeutralButton(R.string.txt_cancel) { dialogInterface, which ->
            dialogInterface.cancel()
        }

        builder.setNegativeButton(R.string.txt_no) { dialogInterface, which ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()

        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}