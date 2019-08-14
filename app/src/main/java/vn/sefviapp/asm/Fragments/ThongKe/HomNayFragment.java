package vn.sefviapp.asm.Fragments.ThongKe;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.sefviapp.asm.Adapter.spAdapter;
import vn.sefviapp.asm.Database.Database;
import vn.sefviapp.asm.R;


public class HomNayFragment extends Fragment {

    PieChart pieChart;
    TextView txtTongThu, txtTongChi;

    int tongThuNgay = 0;
    int tongChiNgay = 0;
    Database database;
    Calendar calendar = Calendar.getInstance();
    String ngayHienTai;
    SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hom_nay, container, false);
        initControls(v);
        initEvents();
        return v;
    }
    private void initEvents() {
        ngayHienTai();
        getChi();
        getThu();
        pieChart();
        txtTongChi.setText("Tổng Chi: " + FormatCost(tongChiNgay) + " VND");
        txtTongThu.setText("Tổng Thu: " + FormatCost(tongThuNgay) + " VND");
    }
    private void initControls(View v) {
        pieChart = v.findViewById(R.id.piechartNgay);
        txtTongChi = v.findViewById(R.id.txtTongchiNgay);
        txtTongThu = v.findViewById(R.id.txtTongthuNgay);
        database = new Database(getContext());
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
            try {
                Date date = simpleDate.parse(ngayThang);
                if (simpleDate.format(date).contains(ngayHienTai)){
                    if (donViChi.equalsIgnoreCase("USD")){
                        usd = usd + dinhMucChi;
                        vnd = (usd * toVnd);
                    }
                    if (donViChi.equalsIgnoreCase("VND")){
                        vietNamDong = vietNamDong + dinhMucChi;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        tongChiNgay = vnd + vietNamDong;
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
            try {
                Date date = simpleDate.parse(ngayThang);
                if (simpleDate.format(date).contains(ngayHienTai)) {
                    if (donViChi.equalsIgnoreCase("USD")) {
                        usd = usd + dinhMucThu;
                        vnd = (usd * toVnd);
                    }
                    if (donViChi.equalsIgnoreCase("VND")) {
                        vietNamDong = vietNamDong + dinhMucThu;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        tongThuNgay = vnd + vietNamDong;

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
        yvalue.add(new PieEntry(tongChiNgay,"Tổng chi"));
        yvalue.add(new PieEntry(tongThuNgay,"Tổng thu"));

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
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        ngayHienTai = mDay + "/" + (mMonth+1) + "/" + mYear;
        Date date = null;
        try {
            date = simpleDate.parse(ngayHienTai);
            ngayHienTai = simpleDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
