package vn.hust.edu.assigtuan6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nameEditText = (EditText) findViewById(R.id.et_name);
        String fullName = nameEditText.getText().toString();
        RadioButton defaultGender = ( RadioButton ) findViewById ( R.id.male);
        Boolean RadioButtonGender = defaultGender.isChecked ();
        EditText mssvText = (EditText) findViewById(R.id.mssv);
        String mssv = mssvText.getText().toString();
        EditText birthday = (EditText) findViewById(R.id.birth) ;
        birthday.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    birthday.setText(current);
                    birthday.setSelection(sel < current.length() ? sel : current.length());

                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        EditText phoneNumberText = (EditText) findViewById(R.id.phoneNumber);
        String phoneNumber = phoneNumberText.getText().toString();
        EditText emailText = (EditText) findViewById(R.id.email);
        String email = emailText.getText().toString();
        EditText addressText = (EditText) findViewById(R.id.address);
        String address = addressText.getText().toString();
        Button btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBoxSports);
        Boolean bSports = checkBox1.isChecked();
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBoxTravel);
        Boolean bTravel = checkBox1.isChecked();
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBoxMusic);
        Boolean bMusic = checkBox1.isChecked();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameEditText.getText().length() != 0 && mssvText.getText().length() != 0 && birthday.getText().length() != 0 && phoneNumberText.getText().length() != 0 && emailText.getText().length() != 0){
                    Toast.makeText(MainActivity.this, "submitted successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "You are missing required field information!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}