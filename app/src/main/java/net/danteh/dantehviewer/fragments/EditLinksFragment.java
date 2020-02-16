package net.danteh.dantehviewer.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.livequery.SubscriptionHandling;

import net.danteh.dantehviewer.DantehApplication;
import net.danteh.dantehviewer.Links;
import net.danteh.dantehviewer.MainActivity;
import net.danteh.dantehviewer.R;
import net.danteh.dantehviewer.adapters.LinkRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class EditLinksFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    MaterialButton sync;
    RecyclerView recyclerView;
    LinkRVAdapter adapter;
    List<ParseObject> emptyList = new ArrayList<>();

    public EditLinksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_links, container, false);

        recyclerView = v.findViewById(R.id.links_rv);
        sync = v.findViewById(R.id.sync_links);
//        testlink.add(new Links(1,1,"صفحه اصلی","some url"));
//        testlink.add(new Links(2,1,"صفحه محصولات","some url"));
//        testlink.add(new Links(3,1,"لباسشویی اسنوا","some url"));
//        testlink.add(new Links(3,1,"لباسشویی ال جی ","some url"));
//        testlink.add(new Links(3,1,"یخچال ساید","some url"));
//        testlink.add(new Links(3,1,"گوشی سامسونگ","some url"));
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Links");
        query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
//        SubscriptionHandling<ParseObject> subscriptionHandling = MainActivity.parseLiveQueryClient.subscribe(query);
//        subscriptionHandling.handleEvents(new SubscriptionHandling.HandleEventsCallback<ParseObject>() {
//            @Override
//            public void onEvents(ParseQuery<ParseObject> query, SubscriptionHandling.Event event, ParseObject object) {
//                if(event.equals(SubscriptionHandling.Event.UPDATE) || event == SubscriptionHandling.Event.CREATE){
//                    Log.e("log event: ", "onEvents: "+object.getString("urlName") );
//                    emptyList.add(object);
//                    adapter.notifyDataSetChanged();
//                }
//                else
//                    Log.e("SUBSCRIB OUT if ", "error: " );
//            }
//        });

        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        emptyList.clear();
                        emptyList.addAll(objects);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
//


        adapter = new LinkRVAdapter(requireActivity(), emptyList);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
