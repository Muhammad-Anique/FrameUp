//package com.example.frameupclient.Activities;
//
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatDialogFragment;
//
//import com.example.frameupclient.R;
//
//public class PostingDialogue extends AppCompatDialogFragment {
//    @Override
//    public Dialog onCreate(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.posting_dialogue_box,null);
//        builder.setView(view)
//                .setTitle("Create Post")
//                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        })
//                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//
//                                    }
//                                });
//
//        super.onCreate(savedInstanceState);
//        return builder.create();
//    }
//}
