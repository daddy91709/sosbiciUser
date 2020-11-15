package com.example.sosbicicletta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Objects;


public class PercorsiFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.lite_list_demo,container,false);

        mGridLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        mLinearLayoutManager = new LinearLayoutManager(this.getActivity());

        // Set up the RecyclerView
        mRecyclerView = mView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(new MapAdapter(LIST_LOCATIONS));
        mRecyclerView.setRecyclerListener(mRecycleListener);

        return mView;
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.lite_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.layout_linear:
                mRecyclerView.setLayoutManager(mLinearLayoutManager);
                break;
            case R.id.layout_grid:
                mRecyclerView.setLayoutManager(mGridLayoutManager);
                break;
        }
        return true;
    }

    private class MapAdapter extends RecyclerView.Adapter<MapAdapter.ViewHolder> {

        private NamedLocation[] namedLocations;

        private MapAdapter(NamedLocation[] locations) {
            super();
            namedLocations = locations;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lite_list_demo_row, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder == null) {
                return;
            }
            holder.bindView(position);
        }

        @Override
        public int getItemCount() {
            return namedLocations.length;
        }


        class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

            MapView mapView;
            TextView title;
            GoogleMap map;
            View layout;



            private ViewHolder(View itemView) {
                super(itemView);
                layout = itemView;
                mapView = layout.findViewById(R.id.lite_listrow_map);
                title = layout.findViewById(R.id.lite_listrow_text);
                if (mapView != null) {

                    mapView.onCreate(null);

                    mapView.getMapAsync(this);
                }
            }

            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapsInitializer.initialize(Objects.requireNonNull(getContext()).getApplicationContext());
                map = googleMap;
                setMapLocation();
                NamedLocation data = (NamedLocation) mapView.getTag();
                map.getUiSettings().setScrollGesturesEnabled(false);
                map.getUiSettings().setZoomControlsEnabled(false);




            }
            private void retrieveFileFromResource() {
                try {
                    KmlLayer kmlLayer = new KmlLayer(map, R.raw.anzio4, getContext());
                    kmlLayer.addLayerToMap();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }




            private void setMapLocation() {
                if (map == null) return;


                NamedLocation data = (NamedLocation) mapView.getTag();

                if (data == null) return;

                try {
                    int Stream = 1;
                    KmlLayer kmlLayer = new KmlLayer(map, data.N1 , getContext());
                    kmlLayer.addLayerToMap();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }


                map.moveCamera(CameraUpdateFactory.newLatLngZoom(data.location, data.focus));
                //map.addMarker(new MarkerOptions().position(data.location));



                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }

            private void bindView(int pos) {
                NamedLocation item = namedLocations[pos];

                layout.setTag(this);
                mapView.setTag(item);
                setMapLocation();
                title.setText(item.name);
            }
        }
    }

    private RecyclerView.RecyclerListener mRecycleListener = new RecyclerView.RecyclerListener() {

        @Override
        public void onViewRecycled(RecyclerView.ViewHolder holder) {
            MapAdapter.ViewHolder mapHolder = (MapAdapter.ViewHolder) holder;
            if (mapHolder != null && mapHolder.map != null) {

                mapHolder.map.clear();
                mapHolder.map.setMapType(GoogleMap.MAP_TYPE_NONE);
            }
        }
    };

    private static class NamedLocation {

        public final String name;
        public final LatLng location;
        public final int N1;
        public final float focus;


        NamedLocation(String name,  LatLng location, int N1, float focus) {
            this.name = name;
            this.location = location;
            this.N1 = N1;
            this.focus = focus;

        }
    }


    private static final NamedLocation[] LIST_LOCATIONS = new NamedLocation[]{
            new NamedLocation("Tour di Anzio", new LatLng(41.4487961, 12.6246567),R.raw.anzio4,15.3f),
            new NamedLocation("Anzio", new LatLng(41.4496568, 12.6307489),R.raw.anzio3,14.5f),
            new NamedLocation("Aprilia", new LatLng(41.4487961, 12.6246567),R.raw.anzio4,15.3f),
            new NamedLocation("Nettuno", new LatLng(41.4496568, 12.6307489),R.raw.anzio3,14.5f),

    };

}

