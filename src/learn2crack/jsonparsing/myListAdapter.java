package learn2crack.jsonparsing;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myListAdapter extends ArrayAdapter {
	private Context context;
	private LayoutInflater inflater;
	private List<Item> elementos;

	public myListAdapter(Context context, List<Item> elementos) {
		super(context, R.layout.row, elementos);
		this.context = context;
		this.elementos = elementos;
	}

	@Override
	public int getCount() {
		return elementos.size();
	}

	@Override
	public Object getItem(int location) {
		return elementos.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		inflater = LayoutInflater.from(context);
		View coso = inflater.inflate(R.layout.row, null);
		
		ImageView imagen = (ImageView) coso.findViewById(R.id.image);
		imagen.setImageResource(elementos.get(position).getFoto());
		
		TextView title = (TextView) convertView.findViewById(R.id.Title);
		TextView points = (TextView) convertView.findViewById(R.id.Points);



		Item m = coso.get(position);

		// thumbnail image
		imagen.setImageUrl(m.getImage(), imageLoader);
		
		// title
		title.setText(m.getTitle());
		
		// points
		points.setText(String.valueOf(m.getPoints()));
		


		return convertView;
	}

}