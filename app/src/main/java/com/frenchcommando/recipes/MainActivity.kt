package com.frenchcommando.recipes

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.let { ab ->
            ab.setHomeAsUpIndicator(R.drawable.ic_menu)
            ab.setDisplayHomeAsUpEnabled(true)
        }

        drawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        setupDrawerContent(navigationView)

        val viewPager: ViewPager = findViewById(R.id.viewpager)
        setupViewPager(viewPager)

        val tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return true
    }

    private fun setupViewPager(viewPager: ViewPager) {
        viewPager.adapter = Adapter(supportFragmentManager).apply {
            addFragment(RecipeListFragment(Recipes.bakingList), "Baking")
            addFragment(RecipeListFragment(Recipes.cookingList), "Cooking")
            addFragment(RecipeListFragment(Recipes.bakingList), "Misc")
        }
    }

    private fun setupViewPagerEmpty(viewPager: ViewPager) {
        val intent = Intent(this, ContactActivity::class.java)
        startActivity(intent)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem)
    {
        menuItem.isChecked = true
        when (menuItem.itemId) {
            R.id.nav_home -> {
                val viewPager: ViewPager = findViewById(R.id.viewpager)
                setupViewPager(viewPager)
            }
            R.id.nav_discussion -> {
                val viewPager: ViewPager = findViewById(R.id.viewpager)
                setupViewPagerEmpty(viewPager)
            }
        }
        drawerLayout.closeDrawers()
    }

    internal class Adapter(
        fragmentManager: FragmentManager
    ) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val fragments: MutableList<Fragment> = ArrayList()
        private val titles: MutableList<String> = ArrayList()

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence? = titles[position]
    }
}