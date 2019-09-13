package com.example.bottomnavigationissue.ui.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.bottomnavigationissue.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_show_keyboard.setOnClickListener {

             edit_text.requestFocusFromTouch()
            requireContext().showKeyboard(edit_text)
        }

        view.findViewById<View>(R.id.button_home).setOnClickListener {
            requireContext().hideKeyboard(edit_text)
            edit_text.clearFocus()
            val action = HomeFragmentDirections
                .actionHomeFragmentToHomeSecondFragment("From HomeFragment")
            NavHostFragment.findNavController(this@HomeFragment)
                .navigate(action)
        }
    }
}

fun Context.hideKeyboard(view: View) {
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showKeyboard(view: EditText) {
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

private val Context.inputMethodManager get() = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
