package com.frenchcommando.recipes2

import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout

class RecipeDetailActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val recipeName = intent.getStringExtra(EXTRA_NAME)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        collapsingToolbar.title = recipeName

        loadBackdrop()
        loadContent()
    }

    private fun loadBackdrop() {
        val imageView: ImageView = findViewById(R.id.backdrop)
        Glide.with(imageView)
            .load(Recipes.getRecipe(intent.getStringExtra(EXTRA_NAME))!!.drawable)
            .apply(RequestOptions.centerCropTransform())
            .into(imageView)
    }

    private fun loadContent() {
        val recipeValue: Recipe = Recipes.getRecipe(intent.getStringExtra(EXTRA_NAME))!!
        val infoView: TextView = findViewById(R.id.info_content)
        infoView.text = recipeValue.content.description
        val ingredientsView: TextView = findViewById(R.id.ingredients_content)
        ingredientsView.text = TextUtils.join("\n", recipeValue.content.ingredients)
        val ovenView: TextView = findViewById(R.id.oven_content)
        ovenView.text = recipeValue.content.oven
    }

    companion object {
        const val EXTRA_NAME = "recipe_name"
    }
}