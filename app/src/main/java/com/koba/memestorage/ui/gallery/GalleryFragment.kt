package com.koba.memestorage.ui.gallery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = GalleryFragmentBinding.inflate(inflater, container, false)
        initView()
        return binding.root
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
//            tvLoadImages.setOnClickListener {
//                if(PermissionUtils.haveStoragePermission(requireContext())){
//                    loadImages()
//                }else{
//                    PermissionUtils.requestMediaPermission(
//                        this@GalleryFragment,
//                        Const.RequestCode.LOAD_IMAGE
//                    )
//                }
//            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            Const.RequestCode.LOAD_IMAGE -> {
                loadImages()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun loadImages(){
        val images = viewModel.loadImages()
        Log.d(TAG, "이미지 갯수 : ${images.size}")
    }

    companion object {
        fun newInstance() = GalleryFragment()
    }
}