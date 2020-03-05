package com.frederickjavalera.recyclerviewproject;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * An adapter. A RecyclerView must posses an adapter. This class represents that Adapter.
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>
{
  // ExampleAdapter fields.
  private ArrayList<ExampleItem> mExampleList;
  private OnItemClickListener mListener;

  public interface OnItemClickListener
  {
    void onItemClick(int position);
    void onDeleteClick(int position);
  }

  public void setOnItemClickListener(OnItemClickListener listener)
  {
    mListener = listener;
  }

  /*
  Constructor.
   */
  public ExampleAdapter(ArrayList<ExampleItem> exampleList)
  {
    mExampleList = exampleList;
  }

  /*
  This method is called when a ViewHolder is created.
   */
  @NonNull
  @Override
  public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
  {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
    ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
    return evh;
  }

  /*
  This method is called when a ViewHolder is bound.
   */
  @Override
  public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position)
  {
    ExampleItem currentItem = mExampleList.get(position);
    holder.mImageView.setImageResource(currentItem.getImageResource());
    holder.mTextView1.setText(currentItem.getText1());
    holder.mTextView2.setText(currentItem.getText2());
  }

  /*
  Returns Adapter item count.
   */
  @Override
  public int getItemCount()
  {
    return mExampleList.size();
  }

  /**
   * An adapter must posses a ViewHolder. This class represents that ViewHolder.
   */
  public static class ExampleViewHolder extends RecyclerView.ViewHolder
  {
    public ImageView mImageView;
    public TextView mTextView1;
    public TextView mTextView2;
    public ImageView mDeleteImage;

    public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener)
    {
      super(itemView);
      mImageView = itemView.findViewById(R.id.imageView);
      mTextView1 = itemView.findViewById(R.id.textView);
      mTextView2 = itemView.findViewById(R.id.textView2);
      mDeleteImage = itemView.findViewById(R.id.image_delete);

      itemView.setOnClickListener(new View.OnClickListener()
      {
        @Override
        public void onClick(View v)
        {
          if (listener != null)
          {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION)
            {
              listener.onItemClick(position);
            }
          }
        }
      });

      mDeleteImage.setOnClickListener(new View.OnClickListener()
      {

        @Override
        public void onClick(View v)
        {
          if (listener != null)
          {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION)
            {
              listener.onDeleteClick(position);
            }
          }
        }
      });
    }
  }
}
