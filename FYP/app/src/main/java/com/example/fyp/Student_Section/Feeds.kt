package com.example.fyp.Student_Section

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.Adaptor.itemsAdaptor
import com.example.fyp.Adaptor.feedsAdaptor
import com.example.fyp.Data.feeds
import com.example.fyp.Data.items
import com.example.fyp.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_student.stdimg
import java.io.IOException

class Feeds : Fragment() {
    lateinit var Rc: RecyclerView
    lateinit var adapter: feedsAdaptor
    lateinit var mbase: DatabaseReference
    private var filePath: Uri? = null
    private val PICK_IMAGE_REQUEST = 22
    lateinit var img:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var btn = view.findViewById<FloatingActionButton>(R.id.sendbtn)

        btn.setOnClickListener {

            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Post")

            // set the custom layout
            val customLayout: View = layoutInflater.inflate(R.layout.addfeed, null)
            builder.setView(customLayout)

            val reg = customLayout.findViewById<EditText>(R.id.pstreg)
            img = customLayout.findViewById<ImageView>(R.id.pstimg)
            val caption = customLayout.findViewById<EditText>(R.id.pstcaption)

            img.setOnClickListener {

                SelectImage()

            }
            builder.setPositiveButton("Add Post") { dialog: DialogInterface?, which: Int ->

                UploadImage(reg.text.toString(),caption.text.toString())

            }
            // create and show the alert dialog
            val dialog = builder.create()
            dialog.show()

        }

        mbase = FirebaseDatabase.getInstance().getReference().child("Feeds")

        Rc = view.findViewById(R.id.recyclerview)

        Rc.layoutManager = LinearLayoutManager(context)

        val option: FirebaseRecyclerOptions<feeds> =
            FirebaseRecyclerOptions.Builder<feeds>().setQuery(
                mbase,
                feeds::class.java
            ).build()

        adapter = feedsAdaptor(option)

        Rc.adapter = adapter

    }

    private fun SelectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select Image from here..."
            ),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {

                // Setting image on image view using Bitmap
                val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,filePath)
                img.setImageBitmap(bitmap)
                //UploadImage()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun UploadImage(registerno: EditText, content: EditText){
        var link=""
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(activity)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            // Defining the child of storageReference
            val ref1 = FirebaseStorage.getInstance().reference?.child("Feedimages/" + getRandomString(5))

            // adding listeners on upload
            // or failure of image
            ref1?.putFile(filePath!!)
                ?.addOnSuccessListener { // Image uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss()
                    Toast.makeText(activity, "Post Added", Toast.LENGTH_SHORT).show()

                    ref1.downloadUrl.addOnSuccessListener(
                        OnSuccessListener<Uri> { uri -> // Got the download URL for 'users/me/profile.png' in uri
                            Picasso.get().load(uri).into(img)
                            var a: feeds = feeds(registerno,uri.toString(),content,0)
                            FirebaseDatabase.getInstance().reference.child("Feeds").push().setValue(a)

                        }).addOnFailureListener(OnFailureListener {
                        // Handle any errors
                    })

                    Picasso.get().load(ref1.downloadUrl.toString()).into(img)
                }
                ?.addOnFailureListener { e -> // Error, Image not uploaded
                    progressDialog.dismiss()
                    Toast
                        .makeText(
                            activity,
                            "Failed " + e.message,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                ?.addOnProgressListener { taskSnapshot ->

                    // Progress Listener for loading
                    // percentage on the dialog box
                    val progress = (100.0
                            * taskSnapshot.bytesTransferred
                            / taskSnapshot.totalByteCount)
                    progressDialog.setMessage(
                        "Uploaded "
                                + progress.toInt() + "%"
                    )
                }
        }

    }
    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }


}