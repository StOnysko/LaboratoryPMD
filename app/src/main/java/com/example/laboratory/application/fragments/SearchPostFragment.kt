package com.example.laboratory.application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.laboratory.databinding.FragmentPostScreenBinding

class SearchPostFragment : Fragment() {
    private var _binding: FragmentPostScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
}