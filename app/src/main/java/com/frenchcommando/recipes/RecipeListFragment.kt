package com.frenchcommando.recipes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RecipeListFragment(private val recipeList: List<Recipe>) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rv = inflater.inflate(
            R.layout.fragment_recipe_list,
            container,
            false
        ) as RecyclerView
        rv.layoutManager = LinearLayoutManager(rv.context)
        rv.adapter = SimpleStringRecyclerViewAdapter(recipeList)

        return rv
    }

    class SimpleStringRecyclerViewAdapter(
        private val values: List<Recipe>
    ) : RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder>() {

        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            var boundString: String? = null
            val image: ImageView = view.findViewById(R.id.avatar)
            val text: TextView = view.findViewById(android.R.id.text1)

            override fun toString(): String {
                return super.toString() + " '" + text.text
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val recipe : Recipe = values[position]

            holder.boundString = recipe.title
            holder.text.text = recipe.title

            holder.view.setOnClickListener { v ->
                val context = v.context
                val intent = Intent(context, RecipeDetailActivity::class.java)
                intent.putExtra(RecipeDetailActivity.EXTRA_NAME, recipe.title)
                context.startActivity(intent)
            }

            Glide.with(holder.image.context)
                .load(recipe.drawable)
                .apply(RequestOptions().fitCenter())
                .into(holder.image)
        }

        override fun getItemCount(): Int = values.size
    }
}
