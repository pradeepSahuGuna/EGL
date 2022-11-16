package com.example.egl.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.egl.ProductApplication
import com.example.egl.adapter.ProductListAdapter
import com.example.egl.databinding.FragmentHomeBinding
import com.example.egl.viewmodels.MainViewModel
import com.example.egl.viewmodels.ProductListViewModelFactory
import javax.inject.Inject

class ProductListFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private var productAdapter: ProductListAdapter? = null

    @Inject
    lateinit var productListViewModelFactory: ProductListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        (requireActivity().application as ProductApplication).applicationComponent.inject(this)
        mainViewModel =
            ViewModelProvider(this, productListViewModelFactory).get(MainViewModel::class.java)
        val recyclerProductList: RecyclerView = binding.productList
        recyclerProductList.layoutManager = LinearLayoutManager(binding.root.context)
        mainViewModel.productsLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            productAdapter = ProductListAdapter(it.products)
            recyclerProductList.adapter = productAdapter

        }
        )

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        productAdapter?.let {
            it.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}