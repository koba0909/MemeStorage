package com.koba.memestorage.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.koba.memestorage.const.Const
import com.koba.memestorage.databinding.GalleryFragmentBinding
import com.koba.memestorage.util.PermissionUtils

class GalleryFragment : Fragment() {
    private val TAG = this::class.simpleName

    private val viewModel: GalleryViewModel by viewModels()

    private var _binding: GalleryFragmentBinding? = null
    private val binding get() = _binding!!

    private val galleryAdapter by lazy { GalleryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GalleryFragmentBinding.inflate(inflater, container, false)
        initView()
        initLiveDataObserve()
        checkPermissionLoadImages()
        return binding.root
    }

    private fun initLiveDataObserve() {
        with(viewModel) {
            imagesLiveData.observe(this@GalleryFragment.viewLifecycleOwner, Observer {
                galleryAdapter.submitList(it)
            })
        }
    }

    private fun initView() {
        with(binding) {
            rvGallery.apply {
                layoutManager = GridLayoutManager(
                    this@GalleryFragment.requireContext(),
                    2
                )
                adapter = galleryAdapter
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            Const.RequestCode.LOAD_IMAGE -> {
                viewModel.loadImages()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun checkPermissionLoadImages() {
        if (PermissionUtils.haveStoragePermission(requireContext())) {
            viewModel.loadImages()
        } else {
            PermissionUtils.requestMediaPermission(
                this@GalleryFragment,
                Const.RequestCode.LOAD_IMAGE
            )
        }
    }

    companion object {
        fun newInstance() = GalleryFragment()
    }
}