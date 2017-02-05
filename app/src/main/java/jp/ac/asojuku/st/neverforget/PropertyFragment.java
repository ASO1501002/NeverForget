package jp.ac.asojuku.st.neverforget;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PropertyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PropertyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropertyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PropertyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PropertyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PropertyFragment newInstance(String param1, String param2) {
        PropertyFragment fragment = new PropertyFragment();
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
        return inflater.inflate(R.layout.fragment_property, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public static Fragment newInstance(Context context){
        MysizeFragment f = new MysizeFragment();
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = this.getActivity().getSharedPreferences("property",Context.MODE_PRIVATE);

        int car_number   = prefs.getInt("car_number",0);
        int phone_number = prefs.getInt("phone_number",0);

        EditText edText1 = (EditText)getView().findViewById(R.id.editCar);
        if(car_number != 0 ){
            edText1.setText(Integer.toString(car_number));
        }

        EditText edText2 = (EditText)getView().findViewById(R.id.editPhone);
        if(phone_number != 0 ){
            edText2.setText(Integer.toString(phone_number));
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        EditText edText1 = (EditText)getView().findViewById(R.id.editCar);
        EditText edText2 = (EditText)getView().findViewById(R.id.editPhone);

        int car_number;
        try{
            car_number = Integer.parseInt(edText1.getText().toString());
        }
        catch (NumberFormatException e){
            car_number = 0;
        }

        int phone_number;
        try{
            phone_number = Integer.parseInt(edText2.getText().toString());
        }
        catch (NumberFormatException e){
            phone_number = 0;
        }


        SharedPreferences prefs = this.getActivity().getSharedPreferences("property",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("car_number",car_number);
        editor.putInt("phone_number",phone_number);

        editor.apply();

    }
}
