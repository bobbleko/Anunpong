package anunpongi.th.ac.rmutl.whateat;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        show View
        showView();


    }   // Main Methon

    private void showView() {
        TextView nameTextView = getView().findViewById(R.id.txtName);
        TextView surnameTextView = getView().findViewById(R.id.txtSurname);
        TextView ganderTextView = getView().findViewById(R.id.txtGender);
        TextView ageTextView = getView().findViewById(R.id.txtAge);
        TextView statureTextView = getView().findViewById(R.id.txtStature);
        TextView weight = getView().findViewById(R.id.txtweight);

        SQLiteDatabase sqLiteDatabase = getActivity().openOrCreateDatabase(MyOpenHelper.database_name, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABER",null);
        cursor.moveToFirst();

        nameTextView.setText("Name ==>" + cursor.getString(1 ));
        surnameTextView.setText(getString(R.string.Surname) + "" + cursor.getString(2));

        int indexGender = Integer.parseInt(cursor.getString(3));
        ganderTextView.setText(findGender(indexGender));


        ageTextView.setText(cursor.getString(4));

        statureTextView.setText(cursor.getString(5));

        weight.setText(cursor.getString(6));




    }

    private String findGender(int indexGender) {
        String result = "";
        String[] strings = new String[]{"ชาย", "หญิง"};

        result = "Gender ==> " + strings[indexGender];

        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

}
