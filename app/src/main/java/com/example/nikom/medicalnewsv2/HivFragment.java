package com.example.nikom.medicalnewsv2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HivFragment extends Fragment {

    View viewHiv;
    private List<newsItem> newsFeedHiv = new ArrayList<>();
    public HivFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewHiv =  inflater.inflate(R.layout.fragment_hiv, container, false);

       newsEngine();

        return viewHiv;
    }

    private void newsEngine() {
        RequestQueue queueHiv = Volley.newRequestQueue(getActivity().getApplicationContext());
        final ArrayAdapter<newsItem> adapterHiv = new customAdapter();

        JsonObjectRequest myRequestHiv = new JsonObjectRequest(Request.Method.GET,
                "https://content.guardianapis.com/search?q=hiv%20drug&show-fields=trailText,thumbnail&order-by=relevance&api-key=5f96460d-253c-4bfd-803b-cac439e7bce5",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject responseObj) {
                        JSONObject responseNodeObj = null;
                        try {
                            responseNodeObj = responseObj.getJSONObject("response");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONArray newsItems = null;
                        try {
                            newsItems = responseNodeObj.getJSONArray("results");

                        } catch (JSONException e) {
                            Log.i("myTag4", e.toString());
                        }
                        for (int i = 0; i < newsItems.length(); i++) { //Loop Se Oles Tis Eidiseis
                            try {
                                JSONObject temp = newsItems.getJSONObject(i);
                                String title = temp.getString("webTitle");
                                String desc = temp.getJSONObject("fields").getString("trailText");
                                String imageUrl = temp.getJSONObject("fields").getString("thumbnail");
                                String url = temp.getString("webUrl");
                                Log.i("Url", url);
                                newsFeedHiv.add(new newsItem(title, desc, "Jan 1 1999", "12:00", url, imageUrl));
                                adapterHiv.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", error.toString());
            }
        });

        queueHiv.add(myRequestHiv);
        ListView hivListView = (ListView) viewHiv.findViewById(R.id.hivListView);
        hivListView.setAdapter(adapterHiv);
    }


    private class customAdapter extends ArrayAdapter<newsItem> {
        public customAdapter() {
            super(getActivity(), R.layout.item, newsFeedHiv);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item, parent, false);
            }

            newsItem currentItem = newsFeedHiv.get(position);

            ImageView newsImage = (ImageView) convertView.findViewById(R.id.leftIco);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);
            TextView heading = (TextView) convertView.findViewById(R.id.heading);

            Picasso.with(getActivity().getApplicationContext()).load(currentItem.getImageID()).into(newsImage);
            desc.setText(currentItem.getDescSmall());
            heading.setText(currentItem.getNewsHeading());


            return convertView;
        }
    }
}
