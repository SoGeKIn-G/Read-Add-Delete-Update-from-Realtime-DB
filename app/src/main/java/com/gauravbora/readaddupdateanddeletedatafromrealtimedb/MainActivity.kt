package com.gauravbora.readaddupdateanddeletedatafromrealtimedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRef = Firebase.database.reference
        val userId = "userid"

        val name = findViewById<TextView>(R.id.nameEditText)
        val email = findViewById<TextView>(R.id.emailEditText)

        val addButton = findViewById<Button>(R.id.addButton)
        val updateButton = findViewById<Button>(R.id.updateButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val readButton = findViewById<Button>(R.id.buttonRead)
        val readname=findViewById<TextView>(R.id.name)

        readname.visibility= View.INVISIBLE




        //region read-write-update-delete
        addButton.setOnClickListener{
            val userData = UserData(name.text.toString(), email.text.toString())

            myRef.child("users").child(userId).setValue(userData).addOnSuccessListener {
                Toast.makeText(this, "User data saved !", Toast.LENGTH_SHORT).show();
            }.addOnFailureListener{

            }

        }

        updateButton.setOnClickListener{
            val userData = UserData(name.text.toString(), email.text.toString())

            myRef.child("users").child(userId).updateChildren(userData.getMap()).addOnSuccessListener {
                Toast.makeText(this, "User data updated !", Toast.LENGTH_SHORT).show();
            }.addOnFailureListener{

            }

        }

        deleteButton.setOnClickListener{
            myRef.child("users").child(userId).removeValue().addOnSuccessListener {
                Toast.makeText(this, "User data deleted !", Toast.LENGTH_SHORT).show();
            }.addOnFailureListener{

            }
        }

        readButton.setOnClickListener{
            readname.visibility= View.VISIBLE

            myRef.child("users").child(userId).get().addOnSuccessListener {
                readname.text= "User data ${it.value}".toString()
                Toast.makeText(this, "User data ${it.value}", Toast.LENGTH_SHORT).show();
            }.addOnFailureListener{
                it.printStackTrace()
            }
        }



    }
}