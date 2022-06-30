package com.example.fornitenews.ui.newsDetails

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.fornitenews.R
import com.example.fornitenews.databinding.FragmentDetailBinding


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        Glide.with(requireContext()).load(args.image).centerCrop().into(binding.imgBackground)
        Glide.with(requireContext()).load(args.image).centerCrop().into(binding.imgMovie)
        binding.txtTitle.text = args.title
        binding.txtDate.text = args.date
        binding.txtDescription.text = args.info
        binding.btnShare.setOnClickListener { shareOptions() }
    }

    private fun shareOptions(){
        val intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, args.title)
            putExtra(Intent.EXTRA_TEXT, args.info)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent,args.title)
        startActivity(shareIntent)
    }
}