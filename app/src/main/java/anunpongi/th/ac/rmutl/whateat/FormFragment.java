package anunpongi.th.ac.rmutl.whateat;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment {

    private String nameString, surnameString, genderString,
            ageString, statureString,weightString;
    private boolean ageABoolean = true;
//    true Not Choose Age
    private boolean statureABoolean = true;


    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Gender Controller
        genderController();

//        Save Controller
        saveController();

//        Age Controller
        ageController();

//        Stature Controller
        statureController();

//        Weight Controller
        weightController();

    }   //Main Methon

    private void weightController() {
        TextView textView = getView().findViewById(R.id.txtweight);
        final String[] strings = new String[]{"กรุณาเลือก","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48",
                "49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67", "68","69","70","71",
                "72","73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"};
        Spinner spinner = getView().findViewById(R.id.txtweight);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                statureString = strings[position];
                if (!(position == 0)) {
                    statureABoolean = false;
                } else {
                    statureABoolean = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void statureController() {
        final String[] strings = new String[]{"กรุณาเลือก","130","131","132","133","134","135","136","137","138","139","140","141","142","143","144","145","146","147","148",
                "149","150","151","152","153","154","155","156","157","158","159","160","161","162","163","164","165","166","167", "168","169","170","171",
                "172","173","174","175","176","177","178","179","180","181","182","183","184","185","186","187","188","189","190"};
        Spinner spinner = getView().findViewById(R.id.EdtStature);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                statureString = strings[position];
                if (!(position == 0)) {
                    statureABoolean = false;
                } else {
                    statureABoolean = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void ageController() {
        final String[] strings = new String[]{"กรุณาเลือก","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40",
                "41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60",
            };
        Spinner spinner = getView().findViewById(R.id.EdtAge);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ageString = strings[position];
                if (!(position == 0)) {
                    ageABoolean = false;
                } else {
                    ageABoolean = true;
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void genderController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragGender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId) {
                    case R.id.radMale:
                        genderString = "0";
                        break;
                    case R.id.radFamale:
                        genderString = "1";
                        break;

                }

            }
        });
    }

    private void saveController() {
        Button button = getView().findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText surnameEditText = getView().findViewById(R.id.edtSurname);

                nameString = nameEditText.getText().toString().trim();
                surnameString = surnameEditText.getText().toString().trim();

                MyAlert myAlert = new MyAlert(getActivity());

                RadioButton maleRadioButton = getView().findViewById(R.id.radMale);
                RadioButton famaleRedioButton = getView().findViewById(R.id.radFamale);

//                Check Space
                if (nameString.isEmpty() || surnameString.isEmpty() )
                         {
//                    Have Space
                    myAlert.normalDialog("Have Space", "Please Fill Every Blank");

                } else if (!(maleRadioButton.isChecked() || famaleRedioButton.isChecked())) {
//                    Non Choose Gerder
                    myAlert.normalDialog("Non Gender","Please Choose Male or Famele");
                } else if (ageABoolean) {
                    myAlert.normalDialog("No Choose Age ", "Plese choose Age");
                } else {
                    saveData();
                    
                }
            } //onClick

        });

    }

    private void saveData() {

        MyManage myManage = new MyManage(getActivity());
        myManage.addValueToSQLite(nameString,surnameString,genderString,ageString,statureString);


        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

}
