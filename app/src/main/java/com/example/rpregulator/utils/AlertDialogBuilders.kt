package com.example.rpregulator.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AlertDialogBuilders: DialogFragment() {

    companion object{
        fun createDeleteAlert(context: Context, positiveClick:()-> Unit){

            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("Delete given entry?")
                    .setPositiveButton("Delete", DialogInterface.OnClickListener{
                        dialog, id ->
                        positiveClick()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener{
                        dialog, id ->
                        dialog.dismiss()
                    })
            val alert = dialogBuilder.create()
            alert.setTitle("Warning")
            alert.show()
        }

    }

}