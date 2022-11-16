package com.example.egl.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.egl.interfaces.ItemClickListener
import com.example.egl.R
import com.example.egl.databinding.ProductListRowBinding
import com.example.egl.models.Product
import com.example.egl.ui.activity.ProductDetailActivity
import com.example.egl.utils.Constants
import com.squareup.picasso.Picasso

class FavoriteListAdapter(private val mList: List<Product>, val itemClick: ItemClickListener) :
    RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProductListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imageFavorite.setImageResource(R.drawable.ic_favorite_)
        holder.binding.textViewTitle.text = mList[position].title
        holder.binding.textViewPrice.text = "$ ${mList[position].price[0].value.toString()}"
        holder.binding.textAddToCard.text = mList[position].addToCartButtonText
        Picasso.get()
            .load(mList[position].imageURL)
            .into(holder.binding.imageView)

        holder.binding.mainList.setOnClickListener(View.OnClickListener {
            mList[position].apply {
                val intent = Intent(holder.binding.root.context, ProductDetailActivity::class.java)
                intent.putExtra(Constants.PRoDUCT_DETAILS, mList[position])
                holder.binding.root.context.startActivity(intent)
            }

        })
        holder.binding.imageFavorite.setOnClickListener(View.OnClickListener {
            itemClick.itemClick(it, position)
        })
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(val binding: ProductListRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}
