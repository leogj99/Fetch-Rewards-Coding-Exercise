package com.example.fetchexercise.data_display

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.fetchexercise.R

class ExpandableListAdapter(
    var context: Context,
    var group: List<String>,
    var children: List<List<String>?>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return group.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return children[groupPosition]!!.size
    }

    override fun getGroup(groupPosition: Int): String {
        return group[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return children[groupPosition]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var myView = convertView
        if (myView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            myView = inflater.inflate(R.layout.list_group, null)
        }
        val groupTitle = myView?.findViewById<TextView>(R.id.group_title)
        groupTitle?.text = "List " + getGroup(groupPosition)
        return myView!!
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var myView = convertView
        if (myView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            myView = inflater.inflate(R.layout.list_item, null)
        }
        val childItem = myView?.findViewById<TextView>(R.id.child_item)
        childItem?.text = getChild(groupPosition, childPosition)
        return myView!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
