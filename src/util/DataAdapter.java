package util;

import java.util.ArrayList;

import com.tanyoo.teroka.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataAdapter extends ArrayAdapter<DataList> {
	private ArrayList<DataList> alData;

	public DataAdapter(Context con, int textViewResourceId,
			ArrayList<DataList> alData) {
		super(con, textViewResourceId, alData);
		this.alData = alData;
	}
	
	
	public View getView(int pos, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater li = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.row, null);// gunakan layout row
		}
		DataList dl = alData.get(pos);
		if (dl != null) {
			TextView tvNama = (TextView) v.findViewById(R.id.tvJudul);
			TextView tvAtt = (TextView) v.findViewById(R.id.tvKeterangan);
			
			tvNama.setText(dl.getNama());
			tvAtt.setText(dl.getHarga());

		}
		return v;
	}

}
