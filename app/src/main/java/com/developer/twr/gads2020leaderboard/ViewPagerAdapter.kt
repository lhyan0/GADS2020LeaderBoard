package com.developer.twr.gads2020leaderboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.lang.IllegalArgumentException


class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        val fragment = when(position){
            0 -> LearningLeadersFragment()
            1 -> SkillIQLeadersFragment()
            else -> throw IllegalArgumentException()
        }

        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position){
            0 -> "Learning Leaders"
            1 -> "Skill IQ Leaders"
            else -> throw IllegalArgumentException()
        }

        return title
    }
}