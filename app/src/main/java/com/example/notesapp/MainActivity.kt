package com.example.notesapp

import android.net.IpSecTransform
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_create_note)

        replaceFragment(HomeFragment.newInstance(), true)
    }

    fun replaceFragment(fragment: Fragment, istransition:Boolean)
    {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        if(istransition)
        {
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName)
    }
}