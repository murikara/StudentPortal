package com.example.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Deze adapter plaatst Portal objecten in de recycler view van de main
 */
public class PortalAdapter extends RecyclerView.Adapter<PortalAdapter.ViewHolder> {

    private List<Portal> mPortals;
    final private PortalClickListener mPortalClickListener;

    public PortalAdapter(List<Portal> mPortals, PortalClickListener mPortalClickListener) {
        this.mPortals = mPortals;
        this.mPortalClickListener = mPortalClickListener;
    }

    @NonNull
    @Override
    public PortalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_portal, null);

        // Return a new holder instance
        return new PortalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortalAdapter.ViewHolder holder, int position) {
        Portal portal = mPortals.get(position);
        holder.textView.setText(portal.getTitel());
    }

    @Override
    public int getItemCount() {
        return mPortals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.portalTextView);
            itemView.setOnClickListener(this);
        }

        /**
         * Deze methode geeft de geklikte view door aan de portalOnClick methode
         * @param v de geklikte view
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Log.e("PORTAL", String.valueOf(clickedPosition));
            mPortalClickListener.portalOnClick(clickedPosition);

        }
    }

    public interface PortalClickListener{
        void portalOnClick (int i);
    }
}
