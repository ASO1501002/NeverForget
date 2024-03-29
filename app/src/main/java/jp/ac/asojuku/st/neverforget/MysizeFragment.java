package jp.ac.asojuku.st.neverforget;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import jp.ac.asojuku.st.neverforget.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MysizeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MysizeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MysizeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MysizeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MysizeFragment newInstance(String param1, String param2) {
        MysizeFragment fragment = new MysizeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mysize, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = this.getActivity().getSharedPreferences("mysize", Context.MODE_PRIVATE);

        //プリファレンスから neck というキーワードで値を呼び出す。初期値０。
        int neck = prefs.getInt("neck",0);
        int sleeve = prefs.getInt("sleeve",0);
        int waist = prefs.getInt("waist",0);
        int insideLeg = prefs.getInt("insideLeg",0);

        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        if(neck != 0){
            edText1.setText(Integer.toString(neck));
        }

        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);
        if(sleeve != 0){
            edText2.setText(Integer.toString(sleeve));
        }

        EditText edText3 = (EditText) getView().findViewById(R.id.editText3);
        if(waist != 0){
            edText3.setText(Integer.toString(waist));
        }

        EditText edText4 = (EditText) getView().findViewById(R.id.editText4);
        if(insideLeg != 0){
            edText4.setText(Integer.toString(insideLeg));
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);
        EditText edText3 = (EditText) getView().findViewById(R.id.editText3);
        EditText edText4 = (EditText) getView().findViewById(R.id.editText4);

        int neck;
        //ここで例外をキャッチして抜ける
        try{
            neck = Integer.parseInt(edText1.getText().toString());
        }
        catch (NumberFormatException e){
            neck = 0;
        }
        int sleeve;
        try{
            sleeve = Integer.parseInt(edText2.getText().toString());
        }
        catch (NumberFormatException e){
            sleeve = 0;
        }
        int waist;
        try{
            waist = Integer.parseInt(edText3.getText().toString());
        }
        catch (NumberFormatException e){
            waist = 0;
        }
        int insideLeg;
        try{
            insideLeg = Integer.parseInt(edText4.getText().toString());
        }
        catch (NumberFormatException e){
            insideLeg = 0;
        }

        //SharedPrefereceに保存
        SharedPreferences prefs = this.getActivity().getSharedPreferences("mysize",Context.MODE_PRIVATE);

        //編集状態にする
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("neck",neck);
        editor.putInt("sleeve",sleeve);
        editor.putInt("waist",waist);
        editor.putInt("insideLeg",insideLeg);

        //編集状態を終えて確定させる。
        //editor.commit();
        editor.apply(); //commitの非同期版
    }
}

