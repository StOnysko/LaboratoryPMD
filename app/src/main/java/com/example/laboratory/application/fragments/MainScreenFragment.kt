package com.example.laboratory.application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laboratory.R
import com.example.laboratory.application.data.Post
import com.example.laboratory.application.data.User
import com.example.laboratory.application.fragments.adapters.PostListAdapter
import com.example.laboratory.application.localdatabase.mvvm.PostViewModel
import com.example.laboratory.application.fragments.adapters.UsersListAdapter
import com.example.laboratory.application.interfaces.OnClickListener
import com.example.laboratory.application.model.PostModel
import com.example.laboratory.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var postVM: PostViewModel
    private lateinit var postShared: PostModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        postVM = ViewModelProvider(this)[PostViewModel::class.java]
        postShared = ViewModelProvider(requireActivity())[(PostModel::class.java)]

        setUserListAdapter(users = usersList())
        setPostListAdapter()

        return binding.root
    }

    private fun setUserListAdapter(users: MutableList<User>) {
        val adapter = UsersListAdapter()
        binding.apply {
            userRecyclerView.adapter = adapter
            userRecyclerView.setHasFixedSize(false)
            userRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        adapter.submitList(users)

    }

    private fun setPostListAdapter() {
        val adapter = PostListAdapter(object : OnClickListener {
            override fun onItemClick(post: Post) {
                postShared.selectPost(post = post)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, PostFragment()).addToBackStack(null).commit()
            }
        })
        with(binding) {
            postRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            postRecyclerView.setHasFixedSize(false)
            postRecyclerView.adapter = adapter
        }
        postVM.readData.observe(viewLifecycleOwner, Observer { post ->
            adapter.submitList(post)
        })
    }

    private fun usersList(): MutableList<User> {
        return mutableListOf(
            User(R.drawable.image_1),
            User(R.drawable.image_2),
            User(R.drawable.image_3),
            User(R.drawable.image_4),
            User(R.drawable.image_5),
            User(R.drawable.image_6),
            User(R.drawable.image_7),
            User(R.drawable.image_1),
            User(R.drawable.image_2),
            User(R.drawable.image_3),
            User(R.drawable.image_4),
            User(R.drawable.image_5),
            User(R.drawable.image_6),
            User(R.drawable.image_7),
            User(R.drawable.image_1),
            User(R.drawable.image_2),
            User(R.drawable.image_3),
            User(R.drawable.image_4),
            User(R.drawable.image_5),
            User(R.drawable.image_6),
            User(R.drawable.image_7),
        )
    }
}