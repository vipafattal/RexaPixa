package com.abedfattal.rexapixakt.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.databinding.FragmentHomeBinding
import com.abedfattal.rexapixakt.ui.home.adapters.ImagesListAdapter
import com.abedfattal.rexapixakt.ui.home.adapters.ImagesLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private val imagesListAdapter = ImagesListAdapter()
    private val vm: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        initMenu(menuHost)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.imagesRecycler.adapter = imagesListAdapter.withLoadStateFooter(
            footer = ImagesLoadStateAdapter(retry = { fetchImages() })
        )

        fetchImages()

        return binding.root
    }

    private fun fetchImages() {
        viewLifecycleOwner.lifecycleScope.launch {
            vm.getImages().collectLatest { pageData ->
                imagesListAdapter.submitData(pageData)
            }
        }
    }

    private fun initMenu(menuHost: MenuHost) {
        (activity as AppCompatActivity).setSupportActionBar(binding.topAppBar)
        binding.topAppBar.bringToFront()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                findNavController().navigate(R.id.action_homeFragment_to_myAccountDialog)
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


}