package com.example.kotlin_rest_recycler_okhttp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CustomAdapter(val context : Context,val list : List<CardModel>): RecyclerView.Adapter<CustomAdapter.ViewHolder>()
{

    var listener : IonItemClickListener?=null;

    interface IonItemClickListener
    {
        fun ItemClick(position: Int)
    }

    fun setOnItemClickListener(itemClickListener : IonItemClickListener)
    {
        listener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val curretItem : CardModel  = list.get(position);

        holder.creatorNameTextView.text = " Creator Name: " + curretItem.creatorName
       // holder.imageView.setText(curretItem.imageUrl);
        holder.likesCountTextView.text = " Likes: "+curretItem.likesCount

        val requestOptions = RequestOptions()
//
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(curretItem.imageUrl).into(holder.imageView)


        }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

           val imageView :ImageView= itemView.findViewById<ImageView>(R.id.cardImageViewUI) as ImageView
            val creatorNameTextView : TextView = itemView.findViewById(R.id.cardCreatorNameUI) as TextView
            val  likesCountTextView : TextView = itemView.findViewById(R.id.cardLikesCountUI) as TextView

        init{
                itemView.setOnClickListener(View.OnClickListener {

                    if(listener!=null)
                    {
                        listener?.ItemClick(adapterPosition);
                    }
                })
        }

    }
}