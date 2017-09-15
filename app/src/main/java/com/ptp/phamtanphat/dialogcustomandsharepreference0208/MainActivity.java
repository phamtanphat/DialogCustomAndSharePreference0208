package com.ptp.phamtanphat.dialogcustomandsharepreference0208;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btndangnhap;
    static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndangnhap = (Button) findViewById(R.id.buttondangnhap);

        sharedPreferences = getSharedPreferences("datalogin",MODE_PRIVATE);


        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                //Xoa line va title cho thiet bi tu 5.0 tro xuong
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog);
                dialog.setTitle("Dang nhap he thong");
                // Khong the click ra ngoai hop thoai(Co the tat bang nut back)
                dialog.setCanceledOnTouchOutside(false);
                //Khong the click ra ngoai hop thoai(Khong the tat bang nut back)
//                dialog.setCancelable(false);

                final EditText edtuser = dialog.findViewById(R.id.edittextuser);
                final EditText edtpassword = dialog.findViewById(R.id.edittextpassword);
                Button btnhuy = dialog.findViewById(R.id.buttonhuy);
                Button btnxacnhan = dialog.findViewById(R.id.buttonxacnhan);
                final CheckBox checkBox = dialog.findViewById(R.id.checkboxsave);

                edtuser.setText(sharedPreferences.getString("ten",""));
                edtpassword.setText(sharedPreferences.getString("matkhau",""));
                checkBox.setChecked(sharedPreferences.getBoolean("checked",false));

                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                btnxacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ten = edtuser.getText().toString().trim();
                        String matkhau = edtpassword.getText().toString().trim();

                        if (ten.equals("phat") &&  matkhau.equals("123")){
                            Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                            if (checkBox.isChecked()){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("ten",ten);
                                editor.putString("matkhau",matkhau);
                                editor.putBoolean("checked",true);
                                editor.commit();
                            }else {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("ten");
                                editor.remove("matkhau");
                                editor.remove("checked");
                                editor.commit();
                            }


                            dialog.cancel();
                        }else {
                            Toast.makeText(MainActivity.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                dialog.show();
            }
        });
    }
}
