package com.example.egl.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.egl.R
import com.example.egl.databinding.ProductListRowBinding
import com.example.egl.models.Product
import com.example.egl.ui.activity.ProductDetailActivity
import com.example.egl.utils.Constants
import com.squareup.picasso.Picasso

class ProductListAdapter(
    private val mList: List<Product>
) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.textViewTitle.text = mList[position].title
        holder.binding.textViewPrice.text = "$ ${mList[position].price[0].value.toString()}"
        holder.binding.textAddToCard.text = mList[position].addToCartButtonText
        Picasso.get()
            .load(mList[position].imageURL)
            .into(holder.binding.imageView)

        holder.binding.mainList.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.binding.root.context, ProductDetailActivity::class.java)
            intent.putExtra(Constants.PRoDUCT_DETAILS, mList[position])
            holder.binding.root.context.startActivity(intent)
        })


        holder.binding.imageFavorite.setOnClickListener {
            var isAdded = true
            for (favoriteList in Constants.favoriteList) {
                if (favoriteList.id.equals(mList[position].id)) {
                    isAdded = false
                }
            }
            if (isAdded) {
                holder.binding.imageFavorite.setImageResource(R.drawable.ic_favorite_)
                Constants.favoriteList.add(mList[position])
            }
        }

        var isFav =false
        for (favoriteItem in Constants.favoriteList) {
            if (favoriteItem.id.equals(mList[position].id)) {
            isFav =true
                break
            }
        }

        if (isFav){
            holder.binding.imageFavorite.setImageResource(R.drawable.ic_favorite_)
        }else {
            holder.binding.imageFavorite.setImageResource(R.drawable.ic_favorite)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class ProductViewHolder(val binding: ProductListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}