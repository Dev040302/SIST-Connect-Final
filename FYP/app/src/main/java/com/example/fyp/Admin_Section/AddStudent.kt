package com.example.fyp.Admin_Section

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fyp.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_student.*
import java.io.IOException


class AddStudent : AppCompatActivity() {

    private var filePath: Uri? = null
    private val PICK_IMAGE_REQUEST = 22
    var storage: FirebaseStorage? = null
    var storageReference: StorageReference? = null
    private lateinit var auth: FirebaseAuth
    lateinit var ref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        auth = Firebase.auth
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        stdimg.setOnClickListener {

            SelectImage()

        }

        addstubtn.setOnClickListener(View.OnClickListener {
            if (stdname.text==null) {
                stdname.setError("Name is required")
                stdname.requestFocus()
                return@OnClickListener
            }
            if (stdemail.text==null) {
                stdemail.setError("Enter email")
                stdemail.requestFocus()
                return@OnClickListener
            }
            if (stdphnno.text==null) {
                stdphnno.setError("Enter the phone number")
                stdphnno.requestFocus()
                return@OnClickListener
            }
            if (stdregisterno.text==null) {
                stdregisterno.setError("Enter Register No")
                stdregisterno.requestFocus()
                return@OnClickListener
            }
            if (stddob.text==null) {
                stddob.setError("Enter Date Of Birth")
                stddob.requestFocus()
                return@OnClickListener
            }

            if (stdDept.text==null) {
                stdDept.setError("Enter Date Of Birth")
                stdDept.requestFocus()
                return@OnClickListener
            }

            if (stdyear.text==null) {
                stdyear.setError("Enter Date Of Birth")
                stdyear.requestFocus()
                return@OnClickListener
            }

            if (stdcrse.text==null) {
                stdcrse.setError("Enter Date Of Birth")
                stdcrse.requestFocus()
                return@OnClickListener
            }

            if (stdgndr.text==null) {
                stdgndr.setError("Enter Date Of Birth")
                stdgndr.requestFocus()
                return@OnClickListener
            }

            if (stdimg.getDrawable()==null){
                Toast.makeText(this, "Please Choose A Photo", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            ref = FirebaseDatabase.getInstance().reference.child("Students").child(stdregisterno.text.toString())

            ref.child("name").setValue(stdname.text.toString())
            ref.child("email").setValue(stdemail.text.toString())
            ref.child("phoneno").setValue(stdphnno.text.toString())
            ref.child("registerno").setValue(stdregisterno.text.toString())
            ref.child("dob").setValue(stddob.text.toString())
            ref.child("department").setValue(stdDept.text.toString())
            ref.child("year").setValue(stdyear.text.toString())
            ref.child("Course").setValue(stdcrse.text.toString())
            ref.child("Gender").setValue(stdgndr.text.toString())
            UploadImage(stdregisterno.text.toString())

            auth.createUserWithEmailAndPassword(stdemail.text.toString(),stddob.text.toString()).addOnSuccessListener {

                FirebaseDatabase.getInstance().reference.child("Users").child(auth.currentUser!!.uid).setValue(stdregisterno.text.toString().toInt())

            }





        })

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
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {

                // Setting image on image view using Bitmap
                val bitmap = MediaStore.Images.Media
                    .getBitmap(
                        contentResolver,
                        filePath
                    )
                stdimg.setImageBitmap(bitmap)
                //UploadImage()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun UploadImage(toString: String){
        var link=""
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            // Defining the child of storageReference
            val ref1 = storageReference
                ?.child(
                    "images/"
                            + toString
                )

            // adding listeners on upload
            // or failure of image
            ref1?.putFile(filePath!!)
                ?.addOnSuccessListener { // Image uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss()
                    Toast.makeText(this, "Student Added",Toast.LENGTH_SHORT).show()

                    ref1.downloadUrl.addOnSuccessListener(
                        OnSuccessListener<Uri> { uri -> // Got the download URL for 'users/me/profile.png' in uri
                            Picasso.get().load(uri).into(stdimg)
                            ref.child("image").setValue(uri.toString())

                        }).addOnFailureListener(OnFailureListener {
                        // Handle any errors
                    })

                    Picasso.get().load(ref1.downloadUrl.toString()).into(stdimg)
                }
                ?.addOnFailureListener { e -> // Error, Image not uploaded
                    progressDialog.dismiss()
                    Toast
                        .makeText(
                            this,
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
}

