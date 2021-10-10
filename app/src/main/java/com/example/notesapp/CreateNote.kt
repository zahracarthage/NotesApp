package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.entities.Notes
import kotlinx.android.synthetic.main.fragment_create_note.*
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.*

class CreateNote : BaseFragment() {
    var currentDate:String ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CreateNote().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss ")
        val currentDate = sdf.format(Date())

        tvDateTime.text = currentDate

        imgCheck.setOnClickListener()
        {
            //savenote

            saveNote()
        }
        imgBack.setOnClickListener()
        {
            replaceFragment(HomeFragment.newInstance(),false)
        }
    }
    private fun saveNote() {
        if (noteTitle.text.isNullOrEmpty()) {
            Toast.makeText(context, "title required", Toast.LENGTH_SHORT).show()
        }
        if (notesSubTitle.text.isNullOrEmpty()) {
            Toast.makeText(context, "subtitle required", Toast.LENGTH_SHORT).show()

        }
        if (notesDesc.text.isNullOrEmpty()) {
            Toast.makeText(context, "description required", Toast.LENGTH_SHORT).show()

        }

        launch {
            var notes = Notes()
            notes.title = noteTitle.text.toString()
            notes.subTitle = notesSubTitle.text.toString()
            notes.noteText = notesDesc.text.toString()
            notes.dateTime = currentDate

            context?.let{
                NotesDatabase.getnotesdb(it).noteDato().insertNotes(notes)
                notesDesc.setText("")
                notesSubTitle.setText("")
                noteTitle.setText("")
            }



        }
    }
    fun replaceFragment(fragment: Fragment, istransition:Boolean)
    {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if(istransition)
        {
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName)
    }
}