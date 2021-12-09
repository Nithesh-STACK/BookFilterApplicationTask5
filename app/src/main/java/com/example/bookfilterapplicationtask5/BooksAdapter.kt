package com.example.bookfilterapplicationtask5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BooksAdapter: RecyclerView.Adapter<BooksAdapter.MyViewHolder>() {
    var bookList= emptyList<Book>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_data,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=bookList[position]
        holder.itemView.findViewById<TextView>(R.id.id).text=currentItem.book_id.toString()
        holder.itemView.findViewById<TextView>(R.id.bookTitle).text=currentItem.title
        holder.itemView.findViewById<TextView>(R.id.pages).text=currentItem.pages.toString()
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
    fun setData(books:List<Book>)
    {
        this.bookList=books
        notifyDataSetChanged()
    }
}