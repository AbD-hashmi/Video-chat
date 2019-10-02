package org.appspot.apprtc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class StartingAvtivity extends AppCompatActivity {


    Button button;
    EditText editText ,editText1;
    TextView textView,textView1;
    Spinner spinner;
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_starting_avtivity);
        setTitle("Sign In");
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editText2);
        editText1=findViewById(R.id.editText3);
        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView2);
        spinner=findViewById(R.id.spinner);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();

        arrayList=new ArrayList();
        arrayList.add("Room 1");
        arrayList.add("Room 2");
        arrayList.add("Room 3");
        arrayList.add("Room 4");
        arrayList.add("Room 5");
        arrayList.add("Room 6");
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        spinner.setAdapter(arrayAdapter);

       /*  String str= sharedPreferences.getString("otp verified","false");
         if (str!="false"){
             textView.setVisibility(View.GONE);
             editText1.setVisibility(View.GONE);
             editText.setVisibility(View.GONE);
             spinner.setVisibility(View.VISIBLE);
             getRoom();
         }*/
        Random random=new Random();

        otp=random.nextInt(10000);

         button.setText("send otp");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().trim().length()!=10){
                    editText.setError("Please enter a valid mobile number");
                }else{
                    button.setText("Sign up");
                    Toast.makeText(StartingAvtivity.this, "Your Otp is "+ otp, Toast.LENGTH_SHORT).show();
                    editText1.setEnabled(true);
                    editText1.setText(""+otp);

                    if (editText1.getVisibility()==View.VISIBLE) {
                        if (editText1.getText().toString().trim() == String.format("%04d", otp) && !editText1.getText().toString().trim().equals("")) {

                            Toast.makeText(StartingAvtivity.this, "Otp incorrect", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(StartingAvtivity.this, "Otp Verified", Toast.LENGTH_SHORT).show();
                            editor.putString("otp verified", "true");
                            editor.commit();
                            textView.setVisibility(View.GONE);
                            editText1.setVisibility(View.GONE);
                            editText.setVisibility(View.GONE);
                            textView1.setVisibility(View.VISIBLE);
                            spinner.setVisibility(View.VISIBLE);
                            getRoom();
                        }
                    }else {
                        editText1.setVisibility(View.VISIBLE);
                        return;
                    }
                }
            }
        });
    }
    private void getRoom(){
        editText1.setVisibility(View.GONE);
        startActivity(new Intent(StartingAvtivity.this,ConnectActivity.class).putExtra("room",""+otp));

        /*button.setText("join");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StartingAvtivity.this, "Joining room", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
