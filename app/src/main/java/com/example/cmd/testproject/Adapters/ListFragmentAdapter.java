package com.example.cmd.testproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cmd.testproject.Activitys.ProductPageActivity;
import com.example.cmd.testproject.JavaObjects.Product;
import com.example.cmd.testproject.R;

import java.util.List;



public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.ProductListHolder> {
    private static final String TAG = "ListFragmentAdapter";
    private List<Product> mProducts;
    private Context ctx;

    public ListFragmentAdapter(List<Product> mProducts) {
        this.mProducts = mProducts;


    }

    @Override
    public ProductListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ProductListHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(ProductListHolder holder, int position) {
        Product pr = mProducts.get(position);
        holder.bind(pr);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ProductListHolder extends RecyclerView.ViewHolder implements
    View.OnClickListener {
        private TextView mTitle,mDesc,mPrice;
        private ImageView imageView;

        private Product mProduct;

        public void bind(Product product) {
            this.mProduct = product;
            mTitle.setText(product.getTitle());
            mDesc.setText(product.getDesc());
            mPrice.setText(product.getPrice());
            //TODO: Set Image
        }

        public ProductListHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item,parent,false));
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDesc = (TextView) itemView.findViewById(R.id.description);
            mPrice = (TextView) itemView.findViewById(R.id.price);
            itemView.setOnClickListener(this);
//            imageView = (ImageView) itemView.findViewById(R.id.imgUrl);

        }

        @Override
        public void onClick(View v) {
            Intent i = ProductPageActivity.newIntent(v.getContext(),mProduct.getId());
            v.getContext().startActivity(i);
        }
    }

}
