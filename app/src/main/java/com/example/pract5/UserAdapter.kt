package com.example.pract5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (private var userList: ArrayList<User>)
    : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.userName.text = userList.get(position).name
        holder.userEmail.text = userList.get(position).email
        holder.userBirthDate.text = userList.get(position).birthDate
        holder.userGender.text = userList.get(position).gender
        holder.userAge.text = userList.get(position).age.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.idName)
        val userEmail: TextView = itemView.findViewById(R.id.idEmail)
        val userBirthDate: TextView = itemView.findViewById(R.id.idBirthDate)
        val userGender: TextView = itemView.findViewById(R.id.idBirthDate)
        val userAge: TextView = itemView.findViewById(R.id.idAge)
    }
}