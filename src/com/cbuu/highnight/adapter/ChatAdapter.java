package com.cbuu.highnight.adapter;

import java.util.List;

import com.cbuu.highnight.R;
import com.cbuu.highnight.common.IMessage;
import com.cbuu.highnight.utils.Logger;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;

public class ChatAdapter extends BaseAdapter{

	private Context context = null;
	private LayoutInflater inflater = null;
	private List<IMessage> messages = null;
	

	public ChatAdapter(Context context_, List<IMessage> msg) {
		context = context_;
		inflater = LayoutInflater.from(context);
		messages = msg;
	}

	@Override
	public int getCount() {
		return messages.size();
	}

	@Override
	public Object getItem(int arg0) {
		return messages.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}


	class Holder {
		public ImageButton usrHead;
		public Button usrMessage;
		int type;
	}

	@Override
	public int getItemViewType(int position) {
		IMessage msg = messages.get(position);
		int type = msg.getType();
		return type;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {

		int type = getItemViewType(position);
		String m = messages.get(position).getContent();
		Holder holder = null;

		if (view==null) {
			
			holder = new Holder();
			if (type == IMessage.CHAT_LEFT) {
				view = inflater.inflate(R.layout.item_left, null);
			}else {
				view = inflater.inflate(R.layout.item_right, null);
			}
			holder.usrHead = (ImageButton) view.findViewById(R.id.chat_image);
			holder.usrMessage = (Button) view.findViewById(R.id.message);
			holder.type = type;
			view.setTag(holder);
		} else {
			holder = (Holder)view.getTag();
			if (type != holder.type) {
				Logger.log("aaa");
				holder = new Holder();
				if (type == IMessage.CHAT_LEFT) {
					view = inflater.inflate(R.layout.item_left, null);
				}else {
					view = inflater.inflate(R.layout.item_right, null);
				}
				holder.usrHead = (ImageButton) view.findViewById(R.id.chat_image);
				holder.usrMessage = (Button) view.findViewById(R.id.message);
				holder.type = type;
				view.setTag(holder);
			}
		}
		holder.usrMessage.setText(m);

		return view;
	}

}
