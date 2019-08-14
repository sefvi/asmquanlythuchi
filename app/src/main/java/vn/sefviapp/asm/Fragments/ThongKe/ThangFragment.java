package vn.sefviapp.asm.Fragments.ThongKe;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

import vn.sefviapp.asm.Adapter.spAdapter;
import vn.sefviapp.asm.Database.Database;
import vn.sefviapp.asm.Modle.ThangNam;
import vn.sefviapp.asm.R;

public class ThangFragment extends Fragment {
    PieChart pieChart;
    TextView txtTongThu, txtTongChi;
    Spinner spThang;
    int tongThu = 0;
    int tongChi = 0;
    Database database;
    Calendar calendar = Calendar.getInstance();
    String thangHienTai;
    ArrayList<ThangNam> thangNamArrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thang, container, false);
        initControls(v);
        initEvents();
        return v;
    }
    private void initEvents() {
       spThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               ThangNam thangNam = thangNamArrayList.get(i);
               if (thangNam.getId() == 0){
                   ngayHienTai();
                   getChi();
                   getThu();
                   pieChart();
                   txtTongChi.setText("Tổng Chi: " + FormatCost(tongChi) + " VND");
                   txtTongThu.setText("Tổng Thu: " + FormatCost(tongThu) + " VND");
               }else {
                   thangHienTai = String.valueOf(thangNam.getId()) + "/" + calendar.get(Calendar.YEAR);
                   getChi();
                   getThu();
                   pieChart();
                   txtTongChi.setText("Tổng Chi: " + FormatCost(tongChi) + " VND");
                   txtTongThu.setText("Tổng Thu: " + FormatCost(tongThu) + " VND");
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
    }
    private void initControls(View v) {
        pieChart = v.findViewById(R.id.piechartThang);
        txtTongChi = v.findViewById(R.id.txtTongchiThang);
        txtTongThu = v.findViewById(R.id.txtTongthuThang);
        spThang = v.findViewById(R.id.spChonThang);
        database = new Database(getContext());
        thangNamArrayList = new ArrayList<>();

        thangNamArrayList.add(new ThangNam(0,"Chọn tháng"));
        for (int i = 1; i < 13; i++){
            thangNamArrayList.add(new ThangNam(i,"Tháng " + i));
        }
        spAdapter adapter = new spAdapter(getContext(), thangNamArrayList);
        spThang.setAdapter(adapter);
    }
    public String FormatCost(long cost){
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            return decimalFormat.format(Integer.parseInt(cost+""));
        }catch (Exception e) {
            return cost + "";
        }
    }
    public void getChi(){
        Cursor cursor = database.GetDate("SELECT * FROM chi WHERE deleteFlag = '0'");
        int usd = 0;
        int toVnd = 23255;
        int vnd = 0;
        int vietNamDong = 0;
        while (cursor.moveToNext()) {
            int dinhMucChi = cursor.getInt(2);
            String donViChi = cursor.getString(3);
            String ngayThang = cursor.getString(4);
            if (ngayThang.contains(thangHienTai)){
                if (donViChi.equalsIgnoreCase("USD")){
                    usd = usd + dinhMucChi;
                    vnd = (usd * toVnd);
                }
                if (donViChi.equalsIgnoreCase("VND")){
                    vietNamDong = vietNamDong + dinhMucChi;
                }
            }
        }
        tongChi = vnd + vietNamDong;
    }
    public void getThu(){
        Cursor cursor = database.GetDate("SELECT * FROM thu WHERE deleteFlag = '0'");
        int usd = 0;
        int toVnd = 23255;
        int vnd = 0;
        int vietNamDong = 0;
        while (cursor.moveToNext()) {
            int dinhMucThu = cursor.getInt(2);
            String donViChi = cursor.getString(3);
            String ngayThang = cursor.getString(4);
            if (ngayThang.contains(thangHienTai)) {
                if (donViChi.equalsIgnoreCase("USD")) {
                    usd = usd + dinhMucThu;
                    vnd = (usd * toVnd);
                }
                if (donViChi.equalsIgnoreCase("VND")) {
                    vietNamDong = vietNamDong + dinhMucThu;
                }
            }
        }
        tongThu = vnd + vietNamDong;
    }
    public void pieChart(){
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,5,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yvalue = new ArrayList<>();
        yvalue.clear();
        yvalue.add(new PieEntry(tongChi,"Tổng chi"));
        yvalue.add(new PieEntry(tongThu,"Tổng thu"));

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yvalue," ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLUE);

        pieChart.setData(data);
    }
    public void ngayHienTai(){
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        thangHienTai = (mMonth+1) + "/" + mYear;
    }

}
