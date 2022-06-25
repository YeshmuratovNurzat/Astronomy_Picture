package com.etoolkit.home.presentions.screen.homeScreen


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.etoolkit.home.R
import com.etoolkit.home.databinding.FragmentHomeBinding
import com.etoolkit.home.domian.model.AstronomyPicture
import com.etoolkit.home.presentions.adapter.AstronomyPictureAdapter
import com.etoolkit.home.presentions.screen.detailtScreen.DetailActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var astronomyPictureAdapter : AstronomyPictureAdapter
    private val viewModel : HomeViewModel by viewModels()

    private var currentSelectedStartDate: Long? = null
    private var currentSelectedEndDate: Long? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        init()

    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
//            val decoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
//            addItemDecoration(decoration)
            astronomyPictureAdapter = AstronomyPictureAdapter()
            adapter = astronomyPictureAdapter
            astronomyPictureAdapter.setOnClick(object : AstronomyPictureAdapter.SetClickListener{
                override fun onClick(astronomyPicture: AstronomyPicture){
                    val intent = Intent(context.applicationContext, DetailActivity::class.java)
                    intent.putExtra("astronomyPicture",astronomyPicture)
                    startActivity(intent)
                }
            })
        }
    }

    private fun init() {
        viewModel.getAllAstronomyPicture().observe(this, {
            binding.progressBar.visibility = View.GONE
            astronomyPictureAdapter.setListData(it.asReversed())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu_activity,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.date ->{
                getAstronomyPictureListFromDate()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAstronomyPictureListFromDate() {

        val builder = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Select date")
            .setSelection(
                Pair(
                    MaterialDatePicker.thisMonthInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()
                )
            )
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointBackward.now()).build()
            )

        val picker = builder.build()

        picker.show(activity?.supportFragmentManager!!, picker.toString())

        picker.addOnCancelListener {}

        picker.addOnNegativeButtonClickListener {}

        picker.addOnPositiveButtonClickListener {

            currentSelectedStartDate = it.first ?: System.currentTimeMillis()
            currentSelectedEndDate = it.second ?: System.currentTimeMillis()

            val startDateTime: LocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(currentSelectedStartDate!!), ZoneId.systemDefault()
            )
            val endDateTime: LocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(currentSelectedEndDate!!), ZoneId.systemDefault()
            )

            val datePattern = "yyyy-MM-dd"
            val startDateFormatted: String =
                startDateTime.format(DateTimeFormatter.ofPattern(datePattern))
            val endDateFormatted: String =
                endDateTime.format(DateTimeFormatter.ofPattern(datePattern))

            viewModel.getAstronomyPictureListFromDate(startDateFormatted,endDateFormatted).observe(this,{
                astronomyPictureAdapter.setListData(it.asReversed())
            })

            Toast.makeText(activity?.applicationContext,
                "$startDateFormatted -- $endDateFormatted",
                Toast.LENGTH_LONG).show()
        }
    }
}