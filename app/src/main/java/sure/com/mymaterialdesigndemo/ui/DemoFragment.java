package sure.com.mymaterialdesigndemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sure.com.mymaterialdesigndemo.R;

/**
 * 描述：
 * 创建人：ShuoLi
 * 创建时间：2016/10/27 15:57
 */
public class DemoFragment extends Fragment {

    private RecyclerView recyclerView;
    List<String> list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("知识点1：给TabLayout加图标");
            list.add("知识点2：使用v7包的Palette获取图片的推荐颜色");
            list.add("知识点3：使用收缩布局");
            list.add("知识点4：沉浸式状态栏并兼容4.4系统手机");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyler,null);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new RecyclerView.Adapter() {

            @Override
            public Myholer onCreateViewHolder(ViewGroup parent, int viewType) {
                return new Myholer(LayoutInflater.from(getActivity()).inflate(R.layout.item,parent,false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((Myholer)holder).tv.setText(list.get(position));
            }



            @Override
            public int getItemCount() {
                return list.size();
            }

            class Myholer extends RecyclerView.ViewHolder{
                TextView tv;
                public Myholer(View itemView) {
                    super(itemView);
                    tv = (TextView) itemView.findViewById(R.id.tv);
                }
            }
        });
        return view;
    }
}
