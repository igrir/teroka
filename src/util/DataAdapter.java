package util;

import java.util.ArrayList;

import com.tanyoo.teroka.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataAdapter extends ArrayAdapter<DataListSenjata> {
	private ArrayList<DataListSenjata> alData;

	private int nowArmor;
	
	public DataAdapter(Context con, int textViewResourceId,
			ArrayList<DataListSenjata> alData, int nowArmor) {
		super(con, textViewResourceId, alData);
		this.alData = alData;
		this.nowArmor = nowArmor;
	}
	
	public void setNowArmor(int nowArmor){
		this.nowArmor = nowArmor;
	}
	
	public View getView(int pos, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater li = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.row, null);// gunakan layout row
		}
		DataListSenjata dl = alData.get(pos);
		if (dl != null) {
			TextView tvNama = (TextView) v.findViewById(R.id.tvJudul);
			TextView tvAtt = (TextView) v.findViewById(R.id.tvKeterangan);
			TextView tvStatusBeli = (TextView) v.findViewById(R.id.tvStatusBeli);
			TextView tvMaxStep    = (TextView) v.findViewById(R.id.tvInfoMaxText);
			
			tvNama.setText(dl.getNama());
			tvAtt.setText(dl.getHarga());
			tvMaxStep.setText(dl.getSyaratStep());
			
			if (String.valueOf(nowArmor).equalsIgnoreCase(dl.getId())) {
				tvStatusBeli.setText("Dipakai");
			}else{
				if (dl.getStatus().equalsIgnoreCase("0")) {
					tvStatusBeli.setText("Belum dibeli");
				}else{
					tvStatusBeli.setText("Sudah dibeli");
				}
			}
			
			
			

		}
		return v;
	}

}
