package com.mc10inc.codetest.studylist

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mc10inc.codetest.databinding.ListItemStudyBinding
import com.mc10inc.codetest.model.Study

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
class StudyAdapter : RecyclerView.Adapter<StudyViewHolder>() {
    private val studyList = emptyList<Study>().toMutableList()

    override fun getItemCount(): Int = studyList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder =
            StudyViewHolder(ListItemStudyBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
        val study = studyList[position]

        holder.binding.title.text = study.title
        holder.binding.studyDisplayName.text = study.displayName
        holder.binding.dateTime.text = DateUtils.formatDateTime(holder.binding.root.context,
                study.createdTs.toEpochMilli(),
                DateUtils.FORMAT_SHOW_TIME
                        or DateUtils.FORMAT_SHOW_DATE
                        or DateUtils.FORMAT_SHOW_WEEKDAY
                        or DateUtils.FORMAT_SHOW_YEAR
                        or DateUtils.FORMAT_ABBREV_ALL)

    }

    fun swap(studyList: List<Study>) {
        this.studyList.clear()
        this.studyList += studyList

        notifyDataSetChanged()
    }
}